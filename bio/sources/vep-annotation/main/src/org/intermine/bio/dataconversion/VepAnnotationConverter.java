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


import java.io.File;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.*;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.System;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.intermine.dataconversion.ItemWriter;
import org.intermine.metadata.Model;
import org.intermine.xml.full.Item;
import org.apache.log4j.Logger;
import org.intermine.util.FormattedTextParser;


/**
 * Converter loads the variant annotations from Ensembl Variant Effect Predictor script
 *
 * @author Deepak Unni
 */
public class VepAnnotationConverter extends BioFileConverter
{
    private static final String DATASET_TITLE = "VEP Annotations";
    private static final String DATA_SOURCE_NAME = "Variant annotations from Variant Effect Predictor";
    private String TAXON_ID = "9913";
    private String orgRefId;
    private HashMap<String, Item> sequenceAlterationItemMap = new HashMap<String, Item>();
    private HashMap<String, Item> transcriptItemMap = new HashMap<String, Item>();

    /**
     * Constructor
     * @param writer the ItemWriter used to handle the resultant items
     * @param model the Model
     */
    public VepAnnotationConverter(ItemWriter writer, Model model) {
        super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
        orgRefId = getOrganism(TAXON_ID);
    }

    /**
     * 
     *
     * {@inheritDoc}
     */
    public void process(Reader reader) throws Exception {
        Iterator<String[]> lineIter = FormattedTextParser.parseTabDelimitedReader(reader);
        while (lineIter.hasNext()) {
            String[] line = lineIter.next();
            if (line.length != 14) {continue;}

            String rsId = line[0];
            String position = line[1].split(":")[1];
            String variantAllele = line[2];
            String geneId = line[3];
            String transcriptId = line[4];
            String annotationType = line[6];
            String cdnaPosition = line[7];
            String cdsPosition = line[8];
            String aminoAcidPosition = line[9];
            String aminoAcidChange = line[10];
            String codonChange = line[11];

            String[] extraInformation = line[13].split(";");
            String impact;
            String strand;
            String distanceFromClosestTranscript;

            for (String info : extraInformation) {
                if (info.contains("IMPACT")) { impact = getValue(info); }
                else if (info.contains("STRAND")) { strand = getValue(info); }
                else if (info.contains("DISTANCE")) { distanceFromClosestTranscript = getValue(info); }
                else { System.out.println("Unhandled information type: " + info); }
            }

            Item variantAnnotationItem = createItem("VariantAnnotation");
            variantAnnotationItem.setAttribute("rsId", rsId);
            variantAnnotationItem.setAttribute("variantAllele", variantAllele);
            variantAnnotationItem.setAttribute("geneIdentifier", geneId);
            variantAnnotationItem.setAttribute("transcriptIdentifier", transcriptId);
            variantAnnotationItem.setAttribute("type", annotationType.replace("/", " | "));

            if (! "-".equals(cdnaPosition)) { variantAnnotationItem.setAttribute("cdnaPosition", cdnaPosition); }
            if (! "-".equals(cdsPosition)) { variantAnnotationItem.setAttribute("cdsPosition", cdsPosition); }
            if (! "-".equals(aminoAcidPosition)) { variantAnnotationItem.setAttribute("aminoAcidPosition", aminoAcidPosition); }
            if (! "-".equals(aminoAcidChange)) {
                String[] aminoAcidSplit = aminoAcidChange.split("/");
                if (aminoAcidSplit.length == 1) {
                    if (annotationType.contains("synonymous_variant") || annotationType.contains("stop_retained_variant")) {
                        variantAnnotationItem.setAttribute("referenceAminoAcid", aminoAcidSplit[0]);
                        variantAnnotationItem.setAttribute("variantAminoAcid", aminoAcidSplit[0]);
                    }
                    else {
                        System.out.println("Warning: AminoAcid Split has only 1 residue even though type is not 'synonymous_variant'");
                        printLineEntry(line);
                        //System.exit(1);
                        variantAnnotationItem.setAttribute("referenceAminoAcid", aminoAcidSplit[0]);
                    }
                }
                else {
                    variantAnnotationItem.setAttribute("referenceAminoAcid", aminoAcidSplit[0]);
                    variantAnnotationItem.setAttribute("variantAminoAcid", aminoAcidSplit[1]);
                }
            }
            if (! "-".equals(codonChange)) {
                variantAnnotationItem.setAttribute("referenceCodon", codonChange.split("/")[0]);
                variantAnnotationItem.setAttribute("variantCodon", codonChange.split("/")[1]);
            }

            Item sequenceAlterationItem = getSequenceAlteration(rsId);
            Item transcriptItem = getTranscript(transcriptId);

            sequenceAlterationItem.addToCollection("variantAnnotations", variantAnnotationItem.getIdentifier());
            variantAnnotationItem.setReference("variant", sequenceAlterationItem.getIdentifier());

            variantAnnotationItem.setReference("transcript", transcriptItem.getIdentifier());
            transcriptItem.addToCollection("variantAnnotations", variantAnnotationItem.getIdentifier());

            transcriptItemMap.put(transcriptId, transcriptItem);
            sequenceAlterationItemMap.put(rsId, sequenceAlterationItem);
            storeItem(variantAnnotationItem);
        }
    }

    private void printLineEntry(String[] entry) {
        for (String eachEntry : entry) {
            System.out.print(eachEntry + "\t");
        }
        System.out.println("\n");
    }

    /**
     * returns the value of a key-value string
     * @param pair  a string of the format 'key=value'
     * @return      the value
     */
    private String getValue(String pair) {
        return pair.split("=")[1];
    }

    /**
     * stores the item into object store
     * @param item  the item that needs to be stored
     */
    private void storeItem(Item item) {
        try {
            store(item);
        } catch(Exception e) {
            System.out.println("Error while storing item:\n" + item);
            System.out.println(e);
        }
    }

    /**
     * returns a 'SequenceAlteration' Item.
     * If the Item does not exist then a new Item is created.
     * @param rsId  a string that serves as the primaryIdentifier for the Item
     * @return      the Item
     *
     */
    private Item getSequenceAlteration(String rsId) {
        Item item;
        if (sequenceAlterationItemMap.containsKey(rsId)) {
            item = sequenceAlterationItemMap.get(rsId);
        }
        else {
            item = createItem("SequenceAlteration");
            item.setAttribute("primaryIdentifier", rsId);
            item.setReference("organism", orgRefId);
            sequenceAlterationItemMap.put(rsId, item);
        }
        return item;
    }

    /**
     * returns a 'Transcript' Item.
     * If the Item does not exist then a new Item is created.
     * @param identifier    a string that serves as the primaryIdentifier for the Item
     * @return              the Item
     */
    private Item getTranscript(String identifier) {
        Item item;
        if (transcriptItemMap.containsKey(identifier)) {
            item = transcriptItemMap.get(identifier);
        }
        else {
            item = createItem("Transcript");
            item.setAttribute("primaryIdentifier", identifier);
            item.setReference("organism", orgRefId);
            transcriptItemMap.put(identifier, item);
        }
        return item;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        for (String rsId : sequenceAlterationItemMap.keySet()) {
             storeItem(sequenceAlterationItemMap.get(rsId));
        }
        for (String transcriptId : transcriptItemMap.keySet()) {
            storeItem(transcriptItemMap.get(transcriptId));
        }
    }
}