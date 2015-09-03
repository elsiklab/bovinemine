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

import org.intermine.bio.io.gff3.GFF3Record;
import org.intermine.metadata.Model;
import org.intermine.metadata.StringUtil;
import org.intermine.xml.full.Item;


import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.intermine.bio.util.OrganismRepository;
import org.intermine.dataconversion.ItemWriter;
import org.intermine.objectstore.ObjectStoreException;
import org.intermine.util.SAXParser;
import org.intermine.xml.full.ReferenceList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.intermine.bio.dataconversion.GFF3Converter;
/**
 * A converter/retriever for the Qtlparser dataset via GFF files.
 */

public class QtlparserGFF3RecordHandler extends GFF3RecordHandler
{

    /**
     * Create a new QtlparserGFF3RecordHandler for the given data model.
     * @param model the model for which items will be created
     */
    private HashMap<String,Item> geneItems = new HashMap<String, Item>();

    public QtlparserGFF3RecordHandler (Model model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(GFF3Record record) {
        Item feature = getFeature();
        String clsName = feature.getClassName();

        if( clsName.equals("QTL") ) {
            if( record.getAttributes().get("ID") != null ) {
                String primaryIdentifier = record.getAttributes().get("ID").iterator().next();
                feature.setAttribute("primaryIdentifier", primaryIdentifier);
            }
            if( record.getAttributes().get("qtl_type") != null ) {
                String qtl_type = record.getAttributes().get("qtl_type").iterator().next().replace("_"," ");
                feature.setAttribute("type", qtl_type);
            }
            if( record.getAttributes().get("Abbrev") != null ) {
                String abbrevName = record.getAttributes().get("Abbrev").iterator().next();
                feature.setAttribute("abbreviation", abbrevName);
            }
            if( record.getAttributes().get("trait") != null ) {
                String traitName = record.getAttributes().get("trait").iterator().next();
                feature.setAttribute("trait", traitName);
            }
            if( record.getAttributes().get("Additive_Effect") != null ) {
                String addEffect = record.getAttributes().get("Additive_Effect").iterator().next();
                feature.setAttribute("additiveEffect", addEffect);
            }
            if( record.getAttributes().get("Bayes-value") != null ) {
                String bayesValue = record.getAttributes().get("Bayes-value").iterator().next();
                feature.setAttribute("bayesValue", bayesValue);
            }
            if( record.getAttributes().get("CMO_name") != null ) {
                String cmo_name = record.getAttributes().get("CMO_name").iterator().next();
                feature.setAttribute("cmoName", cmo_name);
            }
            if( record.getAttributes().get("Dominance_Effect") != null ) {
                String dominance_effect = record.getAttributes().get("Dominance_Effect").iterator().next();
                feature.setAttribute("dominanceEffect", dominance_effect);
            }
            if( record.getAttributes().get("F-Stat") != null ) {
                String fStatistics = record.getAttributes().get("F-Stat").iterator().next();
                feature.setAttribute("fStat", fStatistics);
            }
            if( record.getAttributes().get("FlankMarkers") != null ) {
                String flankMarkers = StringUtil.join(record.getAttributes().get("FlankMarkers"), ", ");
                feature.setAttribute("flankMarkers", flankMarkers);
            }
            if( record.getAttributes().get("LOD-score") != null ) {
                String lod_score = record.getAttributes().get("LOD-score").iterator().next();
                feature.setAttribute("lodScore", lod_score);
            }
            if( record.getAttributes().get("LS-means") != null ) {
                String ls_Means = record.getAttributes().get("LS-means").iterator().next();
                feature.setAttribute("lsMeans", ls_Means);
            }
            if( record.getAttributes().get("Likelihood_Ratio") != null ) {
                String likelihood = record.getAttributes().get("Likelihood_Ratio").iterator().next();
                feature.setAttribute("likelihoodRatio", likelihood);
            }
            if( record.getAttributes().get("Map_Type") != null ) {
                String map_type = record.getAttributes().get("Map_Type").iterator().next();
                feature.setAttribute("mapType", map_type);
            }
            if( record.getAttributes().get("Model") != null ) {
                String model_name = record.getAttributes().get("Model").iterator().next();
                feature.setAttribute("model", model_name);
            }
            if( record.getAttributes().get("Name") != null ) {
                String name = record.getAttributes().get("Name").iterator().next();
                feature.setAttribute("name", name);
            }
            if( record.getAttributes().get("P-value") != null ) {
                String p_val = record.getAttributes().get("P-value").iterator().next();
                feature.setAttribute("pValue", p_val);
            }
            if( record.getAttributes().get("PTO_name") != null ) {
                String pto_name = record.getAttributes().get("PTO_name").iterator().next();
                feature.setAttribute("ptoName", pto_name);
            }
            if( record.getAttributes().get("PUBMED_ID") != null ) {
                String pmid = record.getAttributes().get("PUBMED_ID").iterator().next();
                feature.setAttribute("pubmed", pmid);
            }
            if( record.getAttributes().get("Significance") != null ) {
                String significance = record.getAttributes().get("Significance").iterator().next();
                feature.setAttribute("significance", significance);
            }
            if( record.getAttributes().get("Test_Base") != null ) {
                String test_base = record.getAttributes().get("Test_Base").iterator().next();
                feature.setAttribute("testBase", test_base);
            }
            if( record.getAttributes().get("VTO_name") != null ) {
                String vto_name = record.getAttributes().get("VTO_name").iterator().next();
                feature.setAttribute("vtoName", vto_name);
            }   
            if( record.getAttributes().get("Variance") != null ) {
                String variance = record.getAttributes().get("Variance").iterator().next();
                feature.setAttribute("variance", variance);
            }
            if( record.getAttributes().get("breed") != null ) {
                String breed = record.getAttributes().get("breed").iterator().next();
                feature.setAttribute("breed", breed);
            }
            if( record.getAttributes().get("gene_ID") != null ) {
                String gene_identifier = record.getAttributes().get("gene_ID").iterator().next();
                feature.setAttribute("geneIdentifier", gene_identifier);
                if (items.containsKey(gene_identifier)) {
                    Item item = items.get(gene_identifier);
                    item.addToCollection("Qtls", feature.getIdentifier());
                    feature.addToCollection("gene", item.getIdentifier());
                    items.put(gene_identifier, item);
                }
                else {
                    Item geneItem = converter.createItem("Gene");
                    geneItem.setAttribute("primaryIdentifier", gene_identifier);
                    geneItem.addToCollection("Qtls", feature.getIdentifier());
                    geneItem.setReference("organism", getOrganism());
                    feature.addToCollection("gene", geneItem.getIdentifier());
                    items.put(gene_identifier, geneItem);
                }
            }
            if( record.getAttributes().get("gene_IDsrc") != null ) {
                String gene_identifier_src = record.getAttributes().get("gene_IDsrc").iterator().next();
                feature.setAttribute("geneIdentifierSource", gene_identifier_src);
            }
            if( record.getAttributes().get("peak_cM") != null ) {
                String peak_cM = record.getAttributes().get("peak_cM").iterator().next();
                feature.setAttribute("peakCentimorgan", peak_cM);
            }
            if( record.getAttributes().get("trait_ID") != null ) {
                String trait_id = record.getAttributes().get("trait_ID").iterator().next();
                feature.setAttribute("traitIdentifier", trait_id);
            }
        }
    }
}
