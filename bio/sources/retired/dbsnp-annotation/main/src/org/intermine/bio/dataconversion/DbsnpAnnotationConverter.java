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
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Array;
import java.rmi.UnexpectedException;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.apache.commons.lang.StringUtils;
import org.intermine.dataconversion.ItemWriter;
import org.intermine.metadata.Model;
import org.intermine.xml.full.Item;
import org.apache.log4j.Logger;
import org.intermine.util.FormattedTextParser;

import javax.net.ssl.SSLContext;


/**
 * 
 * @author
 */
public class DbsnpAnnotationConverter extends BioFileConverter
{
    //
    private static final String DATASET_TITLE = "NCBI DBSNP variation annotation";
    private static final String DATA_SOURCE_NAME = "NCBI DBSNP";
    private static final String TAXON_ID = "9913";
    private String orgRefId;
    private boolean startOfBlock = false;
    private boolean currentAssembly = false;
    private boolean isPair = false;
    private boolean ignoreEntry = false;
    private HashMap<String,Item> snpItemMap = new HashMap<String,Item>();
    private HashMap<String,Item> transcriptItemMap = new HashMap<String,Item>();
    /**
     * Constructor
     * @param writer the ItemWriter used to handle the resultant items
     * @param model the Model
     */
    public DbsnpAnnotationConverter(ItemWriter writer, Model model) {
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
            if ("rsId".equals(line[0])) {
                // ignoring headers
                continue;
            }

            String rsId = line[0].trim();
            String type = line[1].trim();
            if (! "snp".equals(type) && ! "in-del".equals(type)) {
                System.out.println("skipping type: " + type + " as it is not supported");
                continue;
            }
            String transcriptId = line[4].trim();

            Item snpItem = getSnpItem(rsId, type);
            Item transcriptItem = getTranscriptItem(transcriptId);
            Item annotationItem = createItem("VariantAnnotation");
            annotationItem.setAttribute("rsId", rsId);
            annotationItem.setAttribute("transcriptIdentifier", transcriptId);
            annotationItem.setAttribute("functionClass", line[6].trim());

            if (!".".equals(line[5].trim())) {
                annotationItem.setAttribute("proteinIdentifier", line[5].trim());
            }
            if (!".".equals(line[7].trim())) {
                annotationItem.setAttribute("referenceAlleleInContext", line[7].trim());
            }
            if (!".".equals(line[8].trim())) {
                annotationItem.setAttribute("alternateAlleleInContext", line[8].trim());
            }
            if (!".".equals(line[9].trim())) {
                annotationItem.setAttribute("referenceResidue", line[9].trim());
            }
            if (!".".equals(line[10].trim())) {
                annotationItem.setAttribute("alternateResidue", line[10].trim());
            }
            if (!".".equals(line[11].trim())) {
                annotationItem.setAttribute("aminoAcidPosition", line[11].trim());
            }

            annotationItem.setReference("transcript", transcriptItem.getIdentifier());
            transcriptItem.addToCollection("variations", snpItem.getIdentifier());

            annotationItem.setReference("variant", snpItem.getIdentifier());
            snpItem.addToCollection("variantAnnotations", annotationItem.getIdentifier());

            annotationItem.setReference("organism", orgRefId);

            snpItemMap.put(rsId, snpItem);
            transcriptItemMap.put(transcriptId, transcriptItem);
            storeItem(annotationItem);
        }
    }

    private Item getSnpItem(String rsId, String type) {
        Item snpItem = null;
        if (snpItemMap.containsKey(rsId)) {
            snpItem = snpItemMap.get(rsId);
        }
        else {
            if ("snp".equals(type.toLowerCase())) {
                snpItem = createItem("SNP");
                snpItem.setAttribute("primaryIdentifier", rsId);
                snpItem.setReference("organism", orgRefId);
                snpItemMap.put(rsId, snpItem);
            }
            else if ("in-del".equals(type.toLowerCase())) {
                snpItem = createItem("Indel");
                snpItem.setAttribute("primaryIdentifier", rsId);
                snpItem.setReference("organism", orgRefId);
                snpItemMap.put(rsId, snpItem);
            }
        }
        return snpItem;
    }

    private Item getTranscriptItem(String transcriptId) {
        Item transcriptItem = null;
        if (transcriptItemMap.containsKey(transcriptId)) {
            transcriptItem = transcriptItemMap.get(transcriptId);
        }
        else {
            transcriptItem = createItem("Transcript");
            transcriptItem.setAttribute("primaryIdentifier", transcriptId);
            transcriptItem.setReference("organism", orgRefId);
            transcriptItemMap.put(transcriptId, transcriptItem);
        }
        return transcriptItem;
    }

    private void storeItem(Item item) {
        try {
            store(item);
        } catch (Exception e) {
            System.out.println("Error while storing item:\n" + item + "\nStack trace:\n" + e);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        for (String transcript : transcriptItemMap.keySet()) {
            storeItem(transcriptItemMap.get(transcript));
        }

        for (String rsId : snpItemMap.keySet()) {
            storeItem(snpItemMap.get(rsId));
        }
    }
}
