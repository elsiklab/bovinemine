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
        // This method is called for every line of GFF3 file(s) being read.  Features and their
        // locations are already created but not stored so you can make changes here.  Attributes
        // are from the last column of the file are available in a map with the attribute name as
        // the key.

        Item feature = getFeature();
        String clsName = feature.getClassName();

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
                feature.setAttribute("source", source); // setting 'status' attribute of class 'Gene'
            }
            if( record.getAttributes().get("duplicate_entity") != null ) {
                String duplicates = record.getAttributes().get("duplicate_entity").iterator().next();
                feature.setAttribute("duplicate_entity", duplicates.replace("|", "\n"));

                // ----- Experimental ------//
                // List<String> entities = new ArrayList<String>( Arrays.asList(StringUtil.split(duplicates, "|")) );
                // System.out.println(entities.toString());
                
                // for( String eachDuplicate in entities) {
                //     List<String> subEntities = new ArrayList<String>( Arrays.asList(StringUtil.split(eachDuplicate, ",")) )
                //     List<String> locationInformation = new ArrayList<String>( Arrays.asList(StringUtil.split(subEntities[0], ":")) )
                //     String geneIdentifier = subEntities[1]
                    // List<String> product = new ArrayList<String>( Arrays.asList(StringUtil.split(subEntities[2], ",")) )
                    // subEntities[0] => Chr:Start..End:Strand
                    // subEntities[1] => NCBI_Gene
                    // subEntities[2] => gene_product
                    // subEntities[n-1] => NCBI_Gene
                    // subEntities[n] => gene_product
                // ----- Experimental ------//

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
                        if( ref.startsWith("BGD:") || ref.startsWith("BEEBASE:") || ref.startsWith("NASONIABASE:") ) {
                            String[] splitVal = ref.split(":");
			    String secondaryId = splitVal[1];  
			    feature.setAttribute("secondaryIdentifier", secondaryId); // setting 'secondaryIdentifier' attribute of class 'Gene'	    
                        }
                    }
                }
            }
          
            List<String> aliases = record.getAliases();  // getAliases() is a method predefined in Intermine
            System.out.println("ALIASES: " + aliases);
            if (aliases != null) {
                Iterator<String> aliasesIterator = aliases.iterator();
                while(aliasesIterator.hasNext()) {
                    // iterating through the list of aliases
                    String ref = aliasesIterator.next();
                      String[] splitVal = ref.split(" ");
                    String ssource = splitVal[1];
                    String aliasPrimaryIdentifier = splitVal[0];
                     if(aliasToRefId.containsKey(aliasPrimaryIdentifier)){
                        feature.addToCollection("alias", aliasToRefId.get(aliasPrimaryIdentifier));
                        } else {
                        Item aliasItem = converter.createItem("AliasName");  // creating an AliasName object
                        aliasItem.setAttribute("source", splitVal[1]);
                       aliasItem.setAttribute("primaryIdentifier", splitVal[0]);  // setting primaryIdentifier of AliasName object
                       String aliasRefId = aliasItem.getIdentifier();  // getting the reference ID of the AliasName object (needed for linking AliasName object to Gene object)
                       feature.addToCollection("alias", aliasRefId);  // addToCollection creates the link between feature (Gene) and the AliasName object
                       aliasItem.addToCollection("gene", feature.getIdentifier());  // and vice-versa
                       aliasToRefId.put(aliasPrimaryIdentifier, aliasRefId);
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
                feature.setAttribute("source", source); // setting 'status' attribute of class 'Gene'
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
                       
                        if( ref.startsWith("BGD:") || ref.startsWith("BEEBASE:") || ref.startsWith("NASONIABASE:") ) {
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



        //     Item feature = getFeature();
        //     String symbol = record.getAttributes().get("symbol");
        //     feature.setAttribute("symbol", symbol);
        //
        // Any new Items created can be stored by calling addItem().  For example:
        // 
        //     String geneIdentifier = record.getAttributes().get("gene");
        //     gene = createItem("Gene");
        //     gene.setAttribute("primaryIdentifier", geneIdentifier);
        //     addItem(gene);
        //
        // You should make sure that new Items you create are unique, i.e. by storing in a map by
        // some identifier. 

    }

}
