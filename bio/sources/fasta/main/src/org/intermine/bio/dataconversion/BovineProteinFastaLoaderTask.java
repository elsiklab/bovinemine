package org.intermine.bio.dataconversion;

/*
 * Copyright (C) 2002-2013 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */
 import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.biojava.bio.Annotation;
import org.biojava.bio.seq.Sequence;
import org.intermine.metadata.Model;
import org.intermine.model.FastPathObject;
import org.intermine.model.InterMineObject;
import org.intermine.model.bio.BioEntity;
import org.intermine.model.bio.DataSet;
import org.intermine.model.bio.Location;
import org.intermine.model.bio.Organism;
import org.intermine.model.bio.SequenceFeature;
import org.intermine.objectstore.ObjectStore;
import org.intermine.objectstore.ObjectStoreException;
import org.intermine.util.DynamicUtil;

/**
 * A fasta loader that understand the headers of CDS fasta files and can make the
 * appropriate extra objects and references.
 * @author Kim Rutherford
 * This script has been adapted with the help of AIPCDSFastaLoaderTask.java and FlyBaseCDSFastaLoaderTask.java
 */
public class BovineProteinFastaLoaderTask extends BovineFeatureFastaLoaderTask
{

    private Map<String, InterMineObject> geneIdO = new HashMap<String, InterMineObject>();
   /**
     * {@inheritDoc}
     */
    @Override
    protected void extraProcessing(Sequence bioJavaSequence,
            org.intermine.model.bio.Sequence flymineSequence,
            BioEntity bioEntity, Organism organism, DataSet dataSet)
        throws ObjectStoreException {
        Annotation annotation = bioJavaSequence.getAnnotation();
        String mrnaIdentifier = bioJavaSequence.getName();
        String header = (String) annotation.getProperty("description");
 //       String regexp = "^.+\\s+(\\S+):([0-9]+-[0-9]+)\\s+(\\S+)\\s+(\\S+)\\s+hasEarlyStopCodon=(.+)$";
 //       Pattern p = Pattern.compile(regexp);
 //       Matcher m = p.matcher(header);
 //       String hasESC = "";
 //       if (m.matches()) {
 //           hasESC = m.group(5);
 //       }
//        if (hasESC != "") {
//            bioEntity.setFieldValue("hasEarlyStopCodon", hasESC.toLowerCase());
//        }
        ObjectStore os = getIntegrationWriter().getObjectStore();
        Model model = os.getModel();
        if (model.hasClassDescriptor(model.getPackageName() + ".Protein")) {
            Class<? extends FastPathObject> cdsCls =
                model.getClassDescriptorByName("Protein").getType();
            if (!DynamicUtil.isInstance(bioEntity, cdsCls)) {
                throw new RuntimeException("the InterMineObject passed to "
                        + "BovineProteinFastaLoaderTask.extraProcessing() is not a "
                        + "Protein: " + bioEntity);
            }
     //      InterMineObject mrna = getMRNA(mrnaIdentifier, organism, model);
    //       if (mrna != null) {
    //             Set<? extends InterMineObject> mrnas = new HashSet(Collections.singleton(mrna));  
    //          bioEntity.setFieldValue("transcript", mrna);
    //       }
      
    //     String geneIdentifier = mrnaIdentifier.substring(mrnaIdentifier.lastIndexOf(' ') + 4);
         String geneIdent = header.substring(header.lastIndexOf(' ') );
              String geneIdentifier=geneIdent.replaceAll("^\\s+", "");
              
                if (!geneIdO.containsKey(geneIdentifier)) {
                InterMineObject gene = getGene(geneIdentifier, organism, model);
                if (gene != null) {
                    Set<? extends InterMineObject> genes = new HashSet(Collections.singleton(gene));
                    bioEntity.setFieldValue("genes", genes);
                }
                geneIdO.put(geneIdentifier, gene);
            } else {
                HashSet geneColl;
                try {
                    geneColl = (HashSet) bioEntity.getFieldValue("genes");
                    geneColl.add(geneIdO.get(geneIdentifier));
                    bioEntity.setFieldValue("genes", geneColl);
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }      
              


//            Location loc = getLocationFromHeader(header, (SequenceFeature) bioEntity,
//                    organism);
 //           getDirectDataLoader().store(loc);
        } else {
            throw new RuntimeException("Trying to load CDS sequence but CDS does not exist in the"
                    + " data model");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getIdentifier(Sequence bioJavaSequence) {
        Annotation annotation = bioJavaSequence.getAnnotation();
        String mrnaIdentifier = bioJavaSequence.getName();
        String header = (String) annotation.getProperty("description");
       // String last = header.substring(header.lastIndexOf(' ') + 1);
      //   String MRNAIdentifier = header.substring(header.lastIndexOf(' ') + 2)
        String mrnaIdent= mrnaIdentifier.substring( mrnaIdentifier.indexOf(" ")+ 1);
        String MRNAI=mrnaIdent.trim();
        // it doesn't matter too much what the CDS identifier is
        return MRNAI;

    }
}
