package org.intermine.bio.dataconversion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.intermine.model.InterMineObject;
import org.intermine.model.bio.BioEntity;
import org.intermine.model.bio.Chromosome;
import org.intermine.model.bio.Consequence;
import org.intermine.model.bio.ConsequenceType;
import org.intermine.model.bio.DataSet;
import org.intermine.model.bio.DataSource;
import org.intermine.model.bio.DiversitySample;
import org.intermine.model.bio.Gene;
import org.intermine.model.bio.Location;
import org.intermine.model.bio.MRNA;
import org.intermine.model.bio.Ontology;
import org.intermine.model.bio.Organism;
import org.intermine.model.bio.SNP;
import org.intermine.model.bio.SNPSampleGroup;
import org.intermine.model.bio.Genotype;
import org.intermine.model.bio.SOTerm;
import org.intermine.objectstore.ObjectStoreException;
import org.intermine.objectstore.proxy.ProxyReference;
import org.intermine.objectstore.query.ConstraintSet;
import org.intermine.metadata.ConstraintOp;
import org.intermine.objectstore.query.ContainsConstraint;
import org.intermine.objectstore.query.QueryClass;
import org.intermine.objectstore.query.QueryField;
import org.intermine.objectstore.query.QueryObjectReference;
import org.intermine.objectstore.query.Query;
import org.intermine.objectstore.query.QueryValue;
import org.intermine.objectstore.query.Results;
import org.intermine.objectstore.query.ResultsRow;
import org.intermine.objectstore.query.SimpleConstraint;
import org.intermine.task.FileDirectDataLoaderTask;
import org.intermine.util.FormattedTextParser;

public class TestDirectDataLoaderTask extends FileDirectDataLoaderTask {
    private static final String DATASET_TITLE = "dbSNP variation and variant annotations for Bos taurus";
    private static final String DATA_SOURCE_NAME = "NCBI dbSNP";
    private static final Logger LOG = Logger.getLogger(TestConverter.class);

    // this loader is exclusively for Bos taurus
    private Integer taxonId = 9913;

    private ProxyReference orgRef = null;
    private Organism organism = null;
    private ProxyReference groupRef = null;
    private DataSet dataSet = null;
    private DataSource dataSource = null;
    private ProxyReference ontologyRef;
    private Map<String, ProxyReference> consequenceTypes = new HashMap<String, ProxyReference>();
    private Map<String, ProxyReference> genes = new HashMap<String, ProxyReference>();
    private Map<String, ProxyReference> transcripts = new HashMap<String, ProxyReference>();
    private Map<String, ProxyReference> chromosomes = new HashMap<String, ProxyReference>();
    private Map<String, ProxyReference> soTerms = new HashMap<String, ProxyReference>();

    // collections need to be added as the object itself
    private Map<String, Consequence> consequences = new HashMap<String, Consequence>();


    public void processFile(File file) {
        System.out.println("Processing file " + file.getName());
        System.exit(1);
        //LOG.info()

        if (!file.getName().endsWith(".vcf")) {
            LOG.info("Ignoring file " + file.getName());
        } else {
            preFill(genes, Gene.class);
            preFill(mrnas, MRNA.class);
            prefill(chromosomes, Chromosome.class);
        }
    }

    private void preFill(Map<String, ProxyReference> map, Class<? extends InterMineObject> objectClass) {
        Query q = new Query();
        QueryClass qC = new QueryClass(objectClass);
        q.addFrom(qC);
        QueryField qFName = new QueryField(qC,"primaryIdentifier");
        QueryField qFId = new QueryField(qC,"id");
        q.addToSelect(qFName);
        q.addToSelect(qFId);
        QueryClass qcOrg = new QueryClass(Organism.class);
        q.addFrom(qcOrg);
        QueryObjectReference orgRef = new QueryObjectReference(qC,"organism");
        QueryField qFProtId = new QueryField(qcOrg,"taxonId");

        ConstraintSet cs = new ConstraintSet(ConstraintOp.AND);
        cs.addConstraint(new ContainsConstraint(orgRef,ConstraintOp.CONTAINS,qcOrg));
        cs.addConstraint(new SimpleConstraint(qFProtId,ConstraintOp.EQUALS,new QueryValue(proteomeId)));

        q.setConstraint(cs);
        System.out.println(q);
        System.exit(1);
        LOG.info("Prefilling ProxyReferences. Query is "+q);
        try {
            Results res = getIntegrationWriter().getObjectStore().execute(q,5000,false,false,false);
            Iterator<Object> resIter = res.iterator();
            System.out.println("Iterating...");
            while (resIter.hasNext()) {
                @SuppressWarnings("unchecked")
                ResultsRow<Object> rr = (ResultsRow<Object>) resIter.next();
                String name = (String)rr.get(0);
                Integer id = (Integer)rr.get(1);
                map.put(name,new ProxyReference(getIntegrationWriter().getObjectStore(),id,objectClass));
            }
        } catch (Exception e) {
            throw new BuildException("Problem in prefilling ProxyReferences: " + e.getMessage());
        }
        LOG.info("Retieved "+map.size()+" ProxyReferences.");
    }
}