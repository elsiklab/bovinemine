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
 * A converter/retriever for the EnsemblGff dataset via GFF files.
 */

public class EnsemblGffGFF3RecordHandler extends GFF3RecordHandler
{
    /**
     * Create a new EnsemblGffGFF3RecordHandler for the given data model.
     * @param model the model for which items will be created
     */

    Map<String,String> aliasToRefId = new HashMap<String,String>();
    Map<String,String> geneToRefId = new HashMap<String,String>();
    Map<String,String> xRefToRefId = new HashMap<String,String>();

    public EnsemblGffGFF3RecordHandler (Model model) {
        super(model);
        refsAndCollections.put("Transcript", "gene");
        refsAndCollections.put("MRNA", "gene");
        refsAndCollections.put("TRNA", "gene");
        refsAndCollections.put("MiRNA", "gene");
        refsAndCollections.put("RRNA", "gene");
        refsAndCollections.put("SnRNA", "gene");
        refsAndCollections.put("SnoRNA", "gene");
        refsAndCollections.put("Exon", "transcripts");
        refsAndCollections.put("CDS", "transcript");
        refsAndCollections.put("UTR", "transcripts");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(GFF3Record record) {

        Item feature = getFeature();
        String clsName = feature.getClassName();
        feature.removeAttribute("secondaryIdentifier");
        feature.removeAttribute("symbol");
        feature.setAttribute("source", record.getSource());

        if( clsName.equals("Gene") ) {
            if(record.getAttributes().get("ID") != null){
                String id = record.getAttributes().get("ID").iterator().next();
                feature.setAttribute("primaryIdentifier", id);
            }
            if(record.getAttributes().get("symbol_ensembl") != null){
                String symbol = record.getAttributes().get("symbol_ensembl").iterator().next();
                feature.setAttribute("symbol", symbol);
            }

            if (record.getAliases() != null) {
                List<String> aliases = record.getAliases();
                Iterator<String> aliasesIterator = aliases.iterator();
                while (aliasesIterator.hasNext()) {
                    setAliasName(aliasesIterator.next());
                }
            }

            if (record.getAttributes().get("xRef") != null) {
                List<String> xRefList = record.getAttributes().get("xRef");
                Iterator<String> xRefIterator = xRefList.iterator();
                while (xRefIterator.hasNext()) {
                    setCrossReference(xRefIterator.next());
                }
            }
        }
        else if( clsName.equals("MRNA") || clsName.equals("Transcript") || clsName.equals("TRNA") || clsName.equals("MiRNA") || clsName.equals("RRNA") || clsName.equals("SnRNA") || clsName.equals("SnoRNA")) {
            if(record.getAttributes().get("ID") != null){
                String id = record.getAttributes().get("ID").iterator().next();
                feature.setAttribute("primaryIdentifier", id);
            }
            if(record.getAttributes().get("symbol_ensembl") != null){
                String symbol = record.getAttributes().get("symbol_ensembl").iterator().next();
                feature.setAttribute("symbol", symbol);
            }
            if(record.getAttributes().get("protein_id") != null){
                String prot = record.getAttributes().get("protein_id").iterator().next();
                feature.setAttribute("proteinIdentifier", prot);
            }
        }
        else if( clsName.equals("Exon") || clsName.equals("CDS") || clsName.equals("UTR") ) {
            // Do nothing
        }
        else {
            System.out.println("Unaccounted class type encountered: " + clsName);
            System.exit(1);
        }
    }

    /**
     * Method parses the alias string, creates an AliasName item and sets the necessary references and collections
     * @param alias
     */
    public void setAliasName(String alias) {
        Item feature = getFeature();
        List<String> splitVal = new ArrayList<String>(Arrays.asList(StringUtil.split(alias, ":")));
        if (splitVal.size() != 2) {
            System.out.println("Ambiguous aliasName: " + splitVal);
            System.out.println("Expected aliasName format is '<ALIAS_ID> <ALIAS_SOURCE>'");
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
            System.out.println("Expected xRef format is '<XREF_ID> <XREF_SOURCE>'");
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
