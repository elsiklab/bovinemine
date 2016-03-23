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


/**
 * A converter/retriever for the QtlGff dataset via GFF files.
 */

public class QtlGffGFF3RecordHandler extends GFF3RecordHandler
{

    /**
     * Create a new QtlGffGFF3RecordHandler for the given data model.
     * @param model the model for which items will be created
     */
    public QtlGffGFF3RecordHandler (Model model) {
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
            if( record.getAttributes().get("QTL_ID") != null ) {
                String qtlId = record.getAttributes().get("QTL_ID").iterator().next();
                feature.setAttribute("qtlId", qtlId);
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
                        if( record.getAttributes().get("gene_ID") != null ) {
                String gene_identifier = record.getAttributes().get("gene_ID").iterator().next();
                feature.setAttribute("geneIdentifier", gene_identifier);
                Item gene = converter.createItem("Gene");
                gene.setAttribute("primaryIdentifier", gene_identifier);
                feature.setReference("gene", gene.getIdentifier());

                try {
                    converter.store(gene);
                } catch (ObjectStoreException e) {
                    System.out.println(e.toString());
                }

                // Item gene = converter.createItem("Gene");
                // gene.setReference("primaryIdentifier", gene_identifier);
                // addItem(gene); 
                // String geneRefId = gene.getIdentifier();
                // System.out.println("geneRefId: " + geneRefId);
                //feature.setReference("gene", gene_identifier); // adding a reference to table 'gene'
            }
        }

        // This method is called for every line of GFF3 file(s) being read.  Features and their
        // locations are already created but not stored so you can make changes here.  Attributes
        // are from the last column of the file are available in a map with the attribute name as
        // the key.   For example:
        //
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
