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
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.intermine.dataconversion.ItemWriter;
import org.intermine.metadata.Model;
import org.intermine.xml.full.Item;
import org.apache.log4j.Logger;
import org.intermine.util.FormattedTextParser;


/**
 * 
 * @author
 */
public class BovineExpressionConverter extends BioFileConverter
{
    //
    protected static final Logger LOG = Logger.getLogger(BovineExpressionConverter.class);
    private String orgRefId;
    private static final String DATASET_TITLE = "Bovine RNASeq Expression data";
    private static final String DATA_SOURCE_NAME = "Bovine RNASeq Expression data from USDA";
    private static final String TAXON_ID = "9913";
    private ArrayList<String> sampleNames = new ArrayList<String>();
    private HashMap<String,Item> items = new HashMap<String, Item>();
    private HashMap<String,Item> transcriptItems = new HashMap<String, Item>();
    private HashMap<String, Item> sampleEntityMap = new HashMap<String, Item>();
    private String valueType = "";

    /**
     * Constructor
     * @param writer the ItemWriter used to handle the resultant items
     * @param model the Model
     */
    public BovineExpressionConverter(ItemWriter writer, Model model) {
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
            if (Pattern.matches("Transcript", line[0])) {
                // parsing header
                for (int i = 1; i < line.length; i++) {
                    sampleNames.add(line[i]);
                }
                continue;
            }

            File currentFile = getCurrentFile();
            String currentFileName = currentFile.getName().toUpperCase();
            if (currentFileName.contains("FPKM")) {
                valueType = "FPKM";
            }
            else if (currentFileName.contains("RPKM")) {
                valueType = "RPKM";
            }
            else if (currentFileName.contains("RAW_COUNTS") || currentFileName.contains("RAWCOUNTS")) {
                valueType = "RAWCOUNTS";
            }
            else if (currentFileName.contains("NORMALIZED_COUNTS") || currentFileName.contains("NORMALIZEDCOUNTS")) {
                valueType = "NORMALIZEDCOUNTS";
            }
            else {
                System.out.println("Error: valueType never determined\nCheck file naming for " + currentFileName);
                System.exit(1);
            }

            String transcriptId = line[0];
            String transcriptType = line[1];
            String classType = getClassForTranscriptType(transcriptType);

            for (int i = 2; i < line.length; i++) {
                String value = line[i];
                String sampleName = sampleNames.get(i - 1);
                sampleName = sampleName.replaceAll("\\s", "");
                String key = transcriptId + "_" + sampleName;
                if (items.containsKey(key)) {
                    Item item = items.get(key);
                    if (valueType.equals("FPKM")) {
                        item.setAttribute("FPKM", value);
                    }
                    else if (valueType.equals("RPKM")) {
                        item.setAttribute("rpkm", value);
                    } else if (valueType.equals("RAWCOUNTS")) {
                        item.setAttribute("rawCounts", value);
                    }
                    else if (valueType.equals("NORMALIZEDCOUNTS")) {
                        item.setAttribute("normalizedCounts", value);
                    }
                    items.put(key, item);
                }
                else {
                    Item item = createItem("Expression");
                    item.setAttribute("sampleName", sampleName);
                    item.setReference("organism", orgRefId);
                    if (valueType.equals("FPKM")) {
                        item.setAttribute("FPKM", value);
                    }
                    else if (valueType.equals("RPKM")) {
                        item.setAttribute("rpkm", value);
                    } else if (valueType.equals("RAWCOUNTS")) {
                        item.setAttribute("rawCounts", value);
                    }
                    else if (valueType.equals("NORMALIZEDCOUNTS")) {
                        item.setAttribute("normalizedCounts", value);
                    }
                    items.put(key, item);
                }

                if (transcriptItems.containsKey(transcriptId)) {
                    Item item = items.get(key);
                    Item tmpTranscriptItem = transcriptItems.get(transcriptId);
                    item.setReference("isoform", tmpTranscriptItem.getIdentifier());
                    tmpTranscriptItem.addToCollection("expressionValues", item.getIdentifier());
                    items.put(key, item);
                    transcriptItems.put(transcriptId, tmpTranscriptItem);
                }
                else {
                    Item transcriptItem = createItem(classType);
                    Item item = items.get(key);
                    System.out.println("Processing expression data for isoform: " + transcriptId);
                    transcriptItem.setAttribute("primaryIdentifier", transcriptId);
                    transcriptItem.setReference("organism", orgRefId);
                    transcriptItem.addToCollection("expressionValues", item.getIdentifier());
                    item.setReference("isoform", transcriptItem.getIdentifier());
                    items.put(key, item);
                    transcriptItems.put(transcriptId, transcriptItem);
                }

                if (sampleEntityMap.containsKey(sampleName)) {
                    Item tmpItem  = items.get(key);
                    Item tmpSampleItem = sampleEntityMap.get(sampleName);
                    tmpSampleItem.addToCollection("isoforms", tmpItem.getIdentifier());
                    tmpItem.setReference("sampleMetadata", tmpSampleItem.getIdentifier());
                    items.put(key, tmpItem);
                    sampleEntityMap.put(sampleName, tmpSampleItem);
                }
                else {
                    Item sampleItem = createItem("ExpressionMetadata");
                    Item tmpItem = items.get(key);
                    sampleItem.setAttribute("label", sampleName);
                    sampleItem.setReference("organism", getOrganism(TAXON_ID));
                    sampleItem.addToCollection("isoforms", tmpItem.getIdentifier());
                    sampleEntityMap.put(sampleName, sampleItem);
                    tmpItem.setReference("sampleMetadata", sampleItem.getIdentifier());
                    items.put(key, tmpItem);
                }
            }
        }
    }

    /**
     * Get the class name (MRNA) for a given transcript type (mRNA)
     * @param transcriptType
     * @return className
     */
    private String getClassForTranscriptType(String transcriptType) {
        String className = "";
        if (transcriptType.equals("mRNA")) {
            className = "MRNA";
        }
        else if (transcriptType.equals("transcript")) {
            className = "Transcript";
        }
        else if (transcriptType.equals("primary_transcript")) {
            className = "PrimaryTranscript";
        }
        else if (transcriptType.equals("tRNA")) {
            className = "TRNA";
        }
        else if (transcriptType.equals("rRNA")) {
            className = "RRNA";
        }
        else if (transcriptType.equals("miRNA")) {
            className = "MiRNA";
        }
        else if (transcriptType.equals("snRNA")) {
            className = "SnRNA";
        }
        else if (transcriptType.equals("snoRNA")) {
            className = "SnoRNA";
        }
        else {
            System.out.println("Unexpected transcript type: " + transcriptType);
            System.exit(1);
        }
        return className;
    }


    /**
     * Storing all Expression Items
     */
    public void storeAllItems() {
        for (String key : items.keySet()) {
            try {
                store(items.get(key));
            } catch (Exception e) {
                System.out.println("Error while storing item:\n" + items.get(key) + "\nStackTrace:\n" + e);
            }
        }
    }

    /**
     * Storing all Transcript Items
     */
    public void storeAllTranscriptItems() {
        for (String key : transcriptItems.keySet()) {
            try {
                store(transcriptItems.get(key));
            } catch (Exception e) {
                System.out.println("Error while storing Transcript item:\n" + transcriptItems.get(key) + "\nStackTrace:\n" + e);
            }
        }
    }

    /**
     * Storing all Sample Items
     */
    public void storeAllSampleItems() {
        for (String key : sampleEntityMap.keySet()) {
            try {
                store(sampleEntityMap.get(key));
            } catch (Exception e) {
                System.out.println("Error while storing Sample item:\n" + sampleEntityMap.get(key) + "\nStackTrace:\n" + e);
            }
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        storeAllItems();
        storeAllTranscriptItems();
        storeAllSampleItems();
    }
}
