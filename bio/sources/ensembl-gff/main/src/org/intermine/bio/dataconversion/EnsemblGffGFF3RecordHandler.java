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

import org.intermine.bio.io.gff3.GFF3Record;
import org.intermine.metadata.Model;
import org.intermine.metadata.StringUtil;
import org.intermine.xml.full.Item;

import java.util.HashMap;
import java.util.HashSet;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Map.Entry;


/**
 * A converter/retriever for the EnsemblGff dataset via GFF files.
 */

public class EnsemblGffGFF3RecordHandler extends GFF3RecordHandler
{
     Map<String,String> aliasToRefId = new HashMap<String,String>();
    /**
     * Create a new EnsemblGffGFF3RecordHandler for the given data model.
     * @param model the model for which items will be created
     */
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
            if(record.getAttributes().get("source") != null){
                String source = record.getAttributes().get("source").iterator().next();
                //feature.setAttribute("source", source);
            }

            if(record.getAttributes().get("symbol_ensembl") != null){
                String symbol = record.getAttributes().get("symbol_ensembl").iterator().next();
                feature.setAttribute("symbol", symbol);
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
                    if(aliasToRefId.containsKey(aliasPrimaryIdentifier)){
                        feature.addToCollection("alias", aliasToRefId.get(aliasPrimaryIdentifier));
                    }
                    else {
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
        else if( clsName.equals("MRNA") || clsName.equals("Transcript") || clsName.equals("TRNA") || clsName.equals("MiRNA") || clsName.equals("RRNA") || clsName.equals("SnRNA") || clsName.equals("SnoRNA")) {
            if(record.getAttributes().get("ID") != null){
                String id = record.getAttributes().get("ID").iterator().next();
                feature.setAttribute("primaryIdentifier", id);
            }
            if(record.getAttributes().get("source") != null){
                String source = record.getAttributes().get("source").iterator().next();
                //feature.setAttribute("source", source);
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
            System.out.println(clsName);
            System.exit(0);
        }

    }
}
