package org.intermine.bio.dataconversion;

/*
 * Copyright (C) 2002-2015 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.lang.System;
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
 * A converter/retriever for the RefseqProteincodingGff dataset via GFF files.
 */

public class RefseqProteincodingGffGFF3RecordHandler extends GFF3RecordHandler
{

    /**
     * Create a new RefseqProteincodingGffGFF3RecordHandler for the given data model.
     * @param model the model for which items will be created
     */
    Map<String,String> aliasToRefId = new HashMap<String,String>();
    Map<String,String> geneToRefId = new HashMap<String,String>();
    Map<String,String> xRefToRefId = new HashMap<String,String>();

    public RefseqProteincodingGffGFF3RecordHandler (Model model) {
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

        if (clsName.equals("Gene"))  {
            if (record.getAttributes().get("symbol_ncbi") != null) {
                String symbol = record.getAttributes().get("symbol_ncbi").iterator().next();
                feature.setAttribute("symbol", symbol); // setting 'symbol' attribute of class 'Gene'
            }
            else {
                feature.removeAttribute("symbol"); // removing the 'symbol' set by intermine by default
            }

            if (record.getAttributes().get("feature_type") != null) {
                String ft = record.getAttributes().get("feature_type").iterator().next();
                feature.setAttribute("status", ft); // setting 'status' attribute of class 'Gene'
            }

            if (record.getAttributes().get("duplicate_entity") != null) {
                String duplicates = record.getAttributes().get("duplicate_entity").iterator().next();
                List<String> entities = new ArrayList<String>( Arrays.asList(StringUtil.split(duplicates, "|")));
                for (String entity : entities) {
                    Item duplicateEntityItem = converter.createItem("DuplicateEntity");
                    String duplicateEntityItemRefId = duplicateEntityItem.getIdentifier();
                    List<String> entityAttributes = new ArrayList<String>( Arrays.asList(StringUtil.split(entity, ",")));
                    List<String> locationInformation = new ArrayList<String>( Arrays.asList(StringUtil.split(entityAttributes.get(0), ":")));
                    String chromosome = locationInformation.get(0);
                    List<String> positionInfo = new ArrayList<String>( Arrays.asList(StringUtil.split(locationInformation.get(1), "..")));
                    String start = positionInfo.get(0);
                    String end = positionInfo.get(1);
                    int strand = locationInformation.get(2).equals("+") ? 1 : -1;
                    duplicateEntityItem.setAttribute("chromosome", chromosome);
                    duplicateEntityItem.setAttribute("start", start);
                    duplicateEntityItem.setAttribute("end", end);
                    duplicateEntityItem.setAttribute("strand", Integer.toString(strand));

                    if (entityAttributes.size() > 1) {
                        String geneIdentifier = entityAttributes.get(1).replace("NCBI_Gene:", "");
                        duplicateEntityItem.setAttribute("geneIdentifier", geneIdentifier);
                    }
                    if (entityAttributes.size() > 2) {
                        ArrayList<String> product = new ArrayList<String>(Arrays.asList(StringUtil.split(entityAttributes.get(2), ":")));

                        if (product.size() > 1) {
                            String transcriptId = product.get(1);
                            duplicateEntityItem.setAttribute("transcriptIdentifier", transcriptId);
                        }
                        if (product.size() > 2) {
                            String proteinIdentifier = product.get(2);
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
            if (dbxrefs != null) {
                Iterator<String> dbxrefsIter = dbxrefs.iterator();
                while (dbxrefsIter.hasNext()) {
                    String dbxref = dbxrefsIter.next();
                    List<String> refList = new ArrayList<String>( Arrays.asList(StringUtil.split(dbxref, ",")));
                    for (String ref : refList) {
                        ref = ref.trim();
                        int colonIndex = ref.indexOf(":");
                        if (colonIndex == -1) {
                            throw new RuntimeException("Error in Dbxref attribute " + ref );
                        }
                        if (ref.startsWith("NCBI_Gene")) {
                            feature.setAttribute( "primaryIdentifier", ref.replace("NCBI_Gene:", "") ); // setting 'primaryIdentifier' attribute of class 'Gene'
                        }
                        if (ref.startsWith("BGD:")) {
                            // treating as xRef
                            // TODO: Verify this assumption
                            String xRefValue = ref.replace("BGD:", "") +  ":btau_OGSv2";
                            setCrossReference(xRefValue);
                        }
                    }
                }
            }

//            if (record.getAliases() != null) {
//                List<String> aliases = record.getAliases();
//                Iterator<String> aliasesIterator = aliases.iterator();
//                while (aliasesIterator.hasNext()) {
//                    setAliasName(aliasesIterator.next());
//                }
//            }
//
//            if (record.getAttributes().get("xRef") != null) {
//                List<String> xRefList = record.getAttributes().get("xRef");
//                Iterator<String> xRefIterator = xRefList.iterator();
//                while (xRefIterator.hasNext()) {
//                    setCrossReference(xRefIterator.next());
//                }
//            }
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
                        if( ref.startsWith("RefSeq_Prot") ) {
                            feature.setAttribute("proteinIdentifier", ref.replace("RefSeq_Prot:", "")); // setting 'proteinIdentifier' attribute of class 'MRNA'
                        }
                    }
                }
            }
        }
    }

    /**
     * Method parses the alias string, creates an AliasName item and sets the necessary references and collections
     * @param alias
     */
    public void setAliasName(String alias) {
        // TODO: Should the relationship between Gene and AliasName be a Collection or a Reference?
        Item feature = getFeature();
        List<String> splitVal = new ArrayList<String>(Arrays.asList(StringUtil.split(alias, ":")));
        if (splitVal.size() != 2) {
            System.out.println("size: " + splitVal.size());
            System.out.println("Ambiguous aliasName: " + splitVal);
            System.out.println("Expected aliasName format is '<ALIAS_ID>:<ALIAS_SOURCE>'");
            System.out.println("Note: ALIAS_ID must be associated with its source");
            System.exit(1);
        }
        String aliasPrimaryIdentifier = splitVal.get(0);
        String aliasSource = splitVal.get(1);
        if (aliasToRefId.containsKey(aliasPrimaryIdentifier)) {
            feature.addToCollection("alias", aliasToRefId.get(aliasPrimaryIdentifier));
        } else {
            Item aliasItem = converter.createItem("AliasName");  // create an AliasName object
            aliasItem.setAttribute("identifier", aliasPrimaryIdentifier);  // set identifier of AliasName object
            aliasItem.setAttribute("source", aliasSource);
            aliasItem.setReference("organism", getOrganism());
            String aliasRefId = aliasItem.getIdentifier();  // get the reference ID of the AliasName object (needed for linking AliasName object to Gene object)
            feature.addToCollection("alias", aliasRefId);  // addToCollection creates the link between feature (Gene) and the AliasName object
            aliasItem.addToCollection("gene", feature.getIdentifier());  // and vice-versa
            aliasToRefId.put(aliasPrimaryIdentifier, aliasRefId);
            addItem(aliasItem);  // add AliasName object to be loaded into the database
        }
    }

    /**
     * Method parses the xRef string, creates a xRef item, creates a Gene item and sets the necessary references and collections
     * @param xRef
     */
    public void setCrossReference(String xRef) {
        Item feature = getFeature();
        List<String> xRefPair = new ArrayList<String>(Arrays.asList(StringUtil.split(xRef, ":")));
        if (xRefPair.size() == 0) { return; }
        if (xRefPair.size() != 2) {
            System.out.println("Ambiguous xRef: " + xRefPair);
            System.out.println("Expected xRef format is '<XREF_ID>:<XREF_SOURCE>'");
            System.out.println("Note: XREF_SOURCE should match column 2 of the alternate GFF3 (if any)");
            System.exit(1);
        }

        String identifier = xRefPair.get(0);
        String xRefSource = xRefPair.get(1);
        if (xRefToRefId.containsKey(identifier)) {
            feature.setReference("crossReference", xRefToRefId.get(identifier));
            if (! geneToRefId.containsKey(identifier)) {
                System.out.println("xRef exists but its corresponding gene instance does not exist");
                System.exit(1);
            }
        } else {
            Item xRefItem = converter.createItem("xRef");  // creating an xRef object
            xRefItem.setAttribute("identifier", identifier);  // setting primaryIdentifier of xRef object
            xRefItem.setAttribute("source", xRefSource);
            xRefItem.setReference("organism", getOrganism());
            String xRefRefId = xRefItem.getIdentifier();  // getting the reference ID of the xRef object (needed for linking AliasName object to Gene object)
            feature.setReference("crossReference", xRefRefId);  // setReference creates the link between feature (Gene) and the AliasName object
            // xRefItem.addToCollection("geneCrossReference", feature.getIdentifier());  // and vice-versa
            xRefToRefId.put(identifier, xRefRefId);
            if (!geneToRefId.containsKey(identifier)) {
                // storing the Gene instance of xRef
                Item geneItem = converter.createItem("Gene");
                geneItem.setAttribute("primaryIdentifier", identifier);
                geneItem.setAttribute("source", xRefSource);
                geneItem.setReference("organism", getOrganism());
                geneToRefId.put(identifier, geneItem.getIdentifier());
                xRefItem.setReference("gene", geneItem.getIdentifier());
                addItem(geneItem);
            }
            addItem(xRefItem);
        }
    }
}
