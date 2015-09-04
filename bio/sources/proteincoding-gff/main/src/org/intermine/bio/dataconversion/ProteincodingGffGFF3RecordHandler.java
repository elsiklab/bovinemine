package org.intermine.bio.dataconversion;

/*
 * Copyright (C) 2002-2011 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URLDecoder;

import java.util.HashMap;
import java.util.HashSet;
import org.intermine.bio.io.gff3.GFF3Record;
import org.intermine.metadata.Model;
import org.intermine.metadata.StringUtil;
import org.intermine.xml.full.Item;
import java.util.Map;
import java.util.Map.Entry;


/**
 * A converter/retriever for the ProteincodingGff dataset via GFF files.
 */

public class ProteincodingGffGFF3RecordHandler extends GFF3RecordHandler
{

   Map<String,String> aliasToRefId = new HashMap<String,String>();
    Map<String,String> geneToRefId = new HashMap<String,String>();
    /**
     * Create a new ProteincodingGffGFF3RecordHandler for the given data model.
     * @param model the model for which items will be created
     */
    public ProteincodingGffGFF3RecordHandler (Model model) {
        super(model);
        refsAndCollections.put("CDS", "transcript");
        refsAndCollections.put("Exon", "transcripts");
        refsAndCollections.put("MRNA", "gene");
        refsAndCollections.put("Transcript", "gene");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(GFF3Record record) {

        Item feature = getFeature();
        String clsName = feature.getClassName();
        feature.setAttribute("source", record.getSource());

        if( clsName.equals("Gene") ) {

            if( record.getAttributes().get("symbol_ncbi") != null ) {
                String symbol = record.getAttributes().get("symbol_ncbi").iterator().next();
                feature.setAttribute("symbol", symbol); // setting 'symbol' attribute of class 'Gene'
            }
	        else {
                feature.removeAttribute("symbol"); // removing the 'symbol' set by intermine by default
	        }
            if( record.getAttributes().get("feature_type") != null ) {
                String ft = record.getAttributes().get("feature_type").iterator().next();
                feature.setAttribute("status", ft); // setting 'status' attribute of class 'Gene'
            }
            if( record.getAttributes().get("source") != null ) {
                String source = record.getAttributes().get("source").iterator().next();
                //feature.setAttribute("source", source); // setting 'status' attribute of class 'Gene'
            }

            if( record.getAttributes().get("duplicate_entity") != null ) {
                String duplicates = record.getAttributes().get("duplicate_entity").iterator().next();
                List<String> entities = new ArrayList<String>( Arrays.asList(StringUtil.split(duplicates, "|")));
                for (String entity : entities) {
                    Item duplicateEntityItem = converter.createItem("DuplicateEntity");
                    String duplicateEntityItemRefId = duplicateEntityItem.getIdentifier();
                    List<String> entityAttributes = new ArrayList<String>( Arrays.asList(StringUtil.split(entity, ",")));
                    List<String> locationInformation = new ArrayList<String>( Arrays.asList(StringUtil.split(entityAttributes.get(0), ":")));
                    System.out.println("locInfo: " + locationInformation.toString());
                    String chromosome = locationInformation.get(0);
                    System.out.println("CHR: " + chromosome);
                    List<String> positionInfo = new ArrayList<String>( Arrays.asList(StringUtil.split(locationInformation.get(1), "..")));
                    System.out.println ("POSINFO: " + positionInfo.toString());
                    String start = positionInfo.get(0);
                    String end = positionInfo.get(1);
                    int strand = locationInformation.get(2).equals("+") ? 1 : -1;
                    duplicateEntityItem.setAttribute("chromosome", chromosome);
                    duplicateEntityItem.setAttribute("start", start);
                    duplicateEntityItem.setAttribute("end", end);
                    duplicateEntityItem.setAttribute("strand", Integer.toString(strand));

                    if (entityAttributes.size() > 1) {
                        String geneIdentifier = entityAttributes.get(1).replace("NCBI_Gene:", "");
                        System.out.println("geneIdentifier: " + geneIdentifier);
                        duplicateEntityItem.setAttribute("geneIdentifier", geneIdentifier);
                    }
                    if (entityAttributes.size() > 2) {
                        ArrayList<String> product = new ArrayList<String>(Arrays.asList(StringUtil.split(entityAttributes.get(2), ":")));

                        if (product.size() > 1) {
                            String transcriptId = product.get(1);
                            System.out.println("transcriptId: " + transcriptId);
                            duplicateEntityItem.setAttribute("transcriptIdentifier", transcriptId);
                        }
                        if (product.size() > 2) {
                            String proteinIdentifier = product.get(2);
                            System.out.println("protId: " + proteinIdentifier);
                            duplicateEntityItem.setAttribute("proteinIdentifier", proteinIdentifier);
                        }
                    }
                    try {
                        converter.store(duplicateEntityItem);
                    }
                    catch (Exception e) {
                        System.out.println("Exception while storing duplicateEntityItem:" + duplicateEntityItem + "\n" + e);
                    }
                    feature.addToCollection("duplicateEntities", duplicateEntityItemRefId);
                }
            }
            // Accessing Dbxrefs
            List<String> dbxrefs = record.getDbxrefs();
            if( dbxrefs != null) {
                Iterator<String> dbxrefsIter = dbxrefs.iterator();

                while( dbxrefsIter.hasNext() ) {
                    String dbxref = dbxrefsIter.next();
                    List<String> refList = new ArrayList<String>( Arrays.asList(StringUtil.split(dbxref, ",")));
                    for( String ref : refList) {
                        ref = ref.trim();
                        int colonIndex = ref.indexOf(":");
                        if( colonIndex == -1 ) {
                            throw new RuntimeException("Error in Dbxref attribute " + ref ); 
                        }
                        if( ref.startsWith("NCBI_Gene") ) {
                            feature.setAttribute( "primaryIdentifier", ref.replace("NCBI_Gene:", "") ); // setting 'primaryIdentifier' attribute of class 'Gene'
                        }                        
                        if( ref.startsWith("BGD:") ) {
                            String[] splitVal = ref.split(":");
			                String secondaryId = splitVal[1];
			                feature.setAttribute("secondaryIdentifier", secondaryId); // setting 'secondaryIdentifier' attribute of class 'Gene'
                        }
                    }
                }
            }
          
            List<String> aliases = record.getAliases();  // getAliases() is a method predefined in Intermine
            if (aliases != null) {
                Iterator<String> aliasesIterator = aliases.iterator();
                while(aliasesIterator.hasNext()) {
                    // iterating through the list of aliases
                    String ref = aliasesIterator.next();
                    String[] splitVal = ref.split(" ");
                    String ssource = splitVal[1];
                    String aliasPrimaryIdentifier = splitVal[0];
                    if (aliasToRefId.containsKey(aliasPrimaryIdentifier)) {
                        feature.addToCollection("alias", aliasToRefId.get(aliasPrimaryIdentifier));
                        if (! geneToRefId.containsKey(aliasPrimaryIdentifier)) {
                            Item geneItem = converter.createItem("Gene");
                            geneItem.setAttribute("primaryIdentifier", aliasPrimaryIdentifier);
                            geneToRefId.put(aliasPrimaryIdentifier, geneItem.getIdentifier());
                            addItem(geneItem);
                        }
                    } else {
                        Item aliasItem = converter.createItem("AliasName");  // creating an AliasName object
                        aliasItem.setAttribute("source", splitVal[1]);
                        aliasItem.setAttribute("primaryIdentifier", splitVal[0]);  // setting primaryIdentifier of AliasName object
                        String aliasRefId = aliasItem.getIdentifier();  // getting the reference ID of the AliasName object (needed for linking AliasName object to Gene object)
                        feature.addToCollection("alias", aliasRefId);  // addToCollection creates the link between feature (Gene) and the AliasName object
                        aliasItem.addToCollection("gene", feature.getIdentifier());  // and vice-versa
                        aliasToRefId.put(aliasPrimaryIdentifier, aliasRefId);
                        if (! geneToRefId.containsKey(aliasPrimaryIdentifier)) {
                            Item geneItem = converter.createItem("Gene");
                            geneItem.setAttribute("primaryIdentifier", aliasPrimaryIdentifier);
                            geneToRefId.put(aliasPrimaryIdentifier, geneItem.getIdentifier());
                            aliasItem.addToCollection("gene", geneItem.getIdentifier());
                            addItem(geneItem);
                        }
                        addItem(aliasItem);  // adding AliasName object to be loaded into the database
                    }
                }
            }

        }
        else if( clsName.equals("MRNA") || clsName.equals("Transcript") ) {

            if( record.getAttributes().get("symbol_ncbi") != null) {
                String symbol = record.getAttributes().get("symbol_ncbi").iterator().next();
                feature.setAttribute("symbol", symbol); // setting 'symbol' attribute of class 'MRNA'               
            }
            else {
                feature.removeAttribute("symbol"); // removing the 'symbol' set by intermine by default                                                                            
            }
            if( record.getAttributes().get("ncbi_desc") != null) {
                String description = record.getAttributes().get("ncbi_desc").iterator().next();
                feature.setAttribute("description", description); // setting 'description' attribute of class 'MRNA'               
            }
            if( record.getAttributes().get("source") != null ) {
                String source = record.getAttributes().get("source").iterator().next();
                //feature.setAttribute("source", source); // setting 'status' attribute of class 'Gene'
            }
            if( record.getAttributes().get("feature_type") != null ) {
                String ft = record.getAttributes().get("feature_type").iterator().next();
                feature.setAttribute("status", ft); // setting 'status' attribute of class 'MRNA'
            }

            // Accessing Dbxrefs
            List<String> dbxrefs = record.getDbxrefs();
            if( dbxrefs != null) {
                Iterator<String> dbxrefsIter = dbxrefs.iterator();

                while( dbxrefsIter.hasNext() ) {
                    String dbxref = dbxrefsIter.next();
                    List<String> refList = new ArrayList<String>( Arrays.asList(StringUtil.split(dbxref, ",")));
                    for( String ref : refList) {
                        ref = ref.trim();
                        int colonIndex = ref.indexOf(":");
                        if( colonIndex == -1 ) {
                            throw new RuntimeException("Error in Dbxref attribute " + ref ); 
                        }
                        if( ref.startsWith("RefSeq_NA") ) {
                            feature.setAttribute( "primaryIdentifier", ref.replace("RefSeq_NA:", "") ); // setting 'primaryIdentifier' attribute of class 'MRNA'
                        }                        
                       
                        if( ref.startsWith("BGD:") ) {
                            String[] splitVal = ref.split(":");
			    String secondaryId = splitVal[1];
			    feature.setAttribute("secondaryIdentifier", secondaryId); // setting 'secondaryIdentifier' attribute of class 'MRNA'
                        }

                        if( ref.startsWith("RefSeq_Prot") ) {
                            feature.setAttribute("proteinIdentifier", ref.replace("RefSeq_Prot:", "")); // setting 'proteinIdentifier' attribute of class 'MRNA'
                        }                        
                    }
                }
            }
        }
    }
}
