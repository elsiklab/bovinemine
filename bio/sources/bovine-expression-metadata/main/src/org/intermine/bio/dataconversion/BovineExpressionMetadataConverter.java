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
public class BovineExpressionMetadataConverter extends BioFileConverter
{
    //
    protected static final Logger LOG = Logger.getLogger(BovineExpressionMetadataConverter.class);
    private String orgRefId;
    private HashMap<String,Item> btoItems = new HashMap<String, Item>();
    private static final String DATASET_TITLE = "Metadata for Bovine RNASeq";
    private static final String DATA_SOURCE_NAME = "Metadata for Bovine RNASeq expression from USDA";
    private static final String TAXON_ID = "9913";

    /**
     * Constructor
     * @param writer the ItemWriter used to handle the resultant items
     * @param model the Model
     */
    public BovineExpressionMetadataConverter(ItemWriter writer, Model model) {
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
            if (line[0].equals("Sample name")) {
                // skipping header
                continue;
            }

            /*
            assign variables
             */
            String label = line[1].replaceAll("\\s","");
            String sampleType = line[2];
            String libraryName = line[3];
            String brendaTissueOntologyId = line[4];
            String btoName = line[5];
            String sraExperimentAccession = line[6];
            String experimentName = line[7];
            String submittedBy = line[8];
            String bioProjectId = line[9];
            String sraStudyAccession = line[10];
            String sraSubmissionAccession = line[11];
            String studyType = line[12];
            String studyAbstract = line[13];
            String bioSampleId = line[14];
            String sampleName = line[15].replaceAll("\\s","");
            String sraSampleAccession = line[16];
            String sampleDescription = line[17];
            String organismName = line[18];
            String breed = line[19];
            String sex = line[20];
            String age = line[21];
            String tissue = line[22];
            String biomaterialProvider = line[23];
            String sraRunAccession = line[24];
            String spots = line[25];
            String bases = line[26];
            String averageReadLength = line[27];
            String gcContent = line[28];
            String published = line[29];
            String accessType = line[30];
            String platform = line[31];
            String strategy = line[32];
            String source = line[33];
            String selection = line[34];
            String layout = line[35];

            Item item = createItem("ExpressionMetadata");
            if (!label.isEmpty()) {
                item.setAttribute("label", label);
            }
            else {
                System.out.println("label cannot be empty as it serves as a primaryIdentifier");
                System.exit(1);
            }

            /*
            set attributes
             */
            item.setAttribute("sampleType", sampleType);
            item.setAttribute("libraryName", libraryName);
            item.setAttribute("btoName", btoName);
            item.setAttribute("sraExperimentAccession", sraExperimentAccession);
            item.setAttribute("experimentName", experimentName);
            item.setAttribute("submittedBy", submittedBy);
            item.setAttribute("bioProjectId", bioProjectId);
            item.setAttribute("sraStudyAccession", sraStudyAccession);
            item.setAttribute("sraSubmissionAccession", sraSubmissionAccession);
            item.setAttribute("studyType", studyType);
            item.setAttribute("studyAbstract", studyAbstract);
            item.setAttribute("bioSampleId", bioSampleId);
            item.setAttribute("sraSampleAccession", sraSampleAccession);
            item.setAttribute("sampleDescription", sampleDescription);
            item.setAttribute("organismName", organismName);
            item.setAttribute("breed", breed);
            item.setAttribute("sex", sex);
            item.setAttribute("age", age);
            item.setAttribute("sampleName", sampleName);
            item.setAttribute("tissue", tissue);
            item.setAttribute("bioMaterialProvider", biomaterialProvider);
            item.setAttribute("sraRunAccession", sraRunAccession);
            item.setAttribute("spots", spots);
            item.setAttribute("bases", bases);
            item.setAttribute("averageReadLength", averageReadLength);
            item.setAttribute("gcContent", gcContent);
            item.setAttribute("publishedDate", published);
            item.setAttribute("accessType", accessType);
            item.setAttribute("platform", platform);
            item.setAttribute("libraryStrategy", strategy);
            item.setAttribute("librarySource", source);
            item.setAttribute("librarySelection", selection);
            item.setAttribute("libraryLayout", layout);
            item.setReference("organism", getOrganism(TAXON_ID));

            if (!brendaTissueOntologyId.isEmpty()) {
                if (btoItems.containsKey(brendaTissueOntologyId)) {
                    item.addToCollection("brendaTissueOntology", btoItems.get(brendaTissueOntologyId).getIdentifier());
                }
                else {
                    Item btoItem = createItem("BRENDATerm");
                    btoItem.setAttribute("identifier", brendaTissueOntologyId);
                    btoItem.setAttribute("name", btoName);
                    item.addToCollection("brendaTissueOntology", btoItem.getIdentifier());
                    btoItems.put(brendaTissueOntologyId, btoItem);
                }
            }

            try {
                store(item);
            } catch(Exception e) {
                System.out.println("Error while storing ExpressionMetadata item: " + item + "\nStacktrace: " + e);
            }
        }

    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        for (String key : btoItems.keySet()) {
            store(btoItems.get(key));
        }
    }
}
