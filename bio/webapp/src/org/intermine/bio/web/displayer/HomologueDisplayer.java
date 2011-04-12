package org.intermine.bio.web.displayer;

/*
 * Copyright (C) 2002-2011 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.Logger;
import org.intermine.api.InterMineAPI;
import org.intermine.api.results.ResultElement;
import org.intermine.api.util.PathUtil;
import org.intermine.model.bio.DataSet;
import org.intermine.model.bio.Gene;
import org.intermine.model.bio.Homologue;
import org.intermine.model.bio.Organism;
import org.intermine.pathquery.Path;
import org.intermine.pathquery.PathException;
import org.intermine.web.displayer.CustomDisplayer;
import org.intermine.web.logic.config.ReportDisplayerConfig;
import org.intermine.web.logic.results.ReportObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomologueDisplayer extends CustomDisplayer {

    private static final List<String> SPECIES = Arrays.asList(new String[] {"grimshawi", "virilis",
            "mojavensis", "willistoni", "persimilis", "pseudoobscura", "ananassae", "erecta",
            "yakuba", "melanogaster", "sechellia", "simulans"});
    protected static final Logger LOG = Logger.getLogger(OverlappingFeaturesDisplayer.class);


    public HomologueDisplayer(ReportDisplayerConfig config, InterMineAPI im) {
        super(config, im);
    }

    @Override
    public void display(HttpServletRequest request, ReportObject reportObject) {
        request.setAttribute("parameters", config.getParameterString());
        Map<String, Set<ResultElement>> homologues =
            new TreeMap<String, Set<ResultElement>>();
        Map<String, String> organismIds = new HashMap<String, String>();

        Path symbolPath = null;
        Path primaryIdentifierPath = null;
        try {
            symbolPath = new Path(im.getModel(), "Gene.symbol");
            primaryIdentifierPath = new Path(im.getModel(), "Gene.primaryIdentifier");
        } catch (PathException e) {
            return;
        }

        Gene gene = (Gene) reportObject.getObject();
        Set<String> dataSets = new HashSet<String>();
        JSONObject params = config.getParameterJson();
        try {
			JSONArray dataSetsArray = params.getJSONArray("dataSets");
			for (int i = 0; i < dataSetsArray.length(); i++) {
				dataSets.add(dataSetsArray.getString(i));
			}
        } catch (JSONException e) {
        	throw new RuntimeException("Error parsing configuration value 'dataSets'", e);
		}

        for (Homologue homologue : gene.getHomologues()) {
            for (DataSet dataSet : homologue.getDataSets()) {
                if (dataSets.contains(dataSet.getName())) {
                    Organism org = homologue.getHomologue().getOrganism();
                    organismIds.put(org.getSpecies(), org.getId().toString());
                    try {
                        if (PathUtil.resolvePath(symbolPath, homologue.getHomologue()) != null) {
                            ResultElement re = new ResultElement(homologue.getHomologue(),
                                    symbolPath, true);
                            addToMap(homologues, org.getShortName(), re);
                        } else {
                            ResultElement re = new ResultElement(homologue.getHomologue(),
                                    primaryIdentifierPath, true);
                            addToMap(homologues, org.getShortName(), re);
                        }
                    } catch (PathException e) {
                        LOG.error("Failed to resolve path: " + symbolPath + " for gene: " + gene);
                    }
                }
            }
        }

        request.setAttribute("organismIds", organismIds);
        request.setAttribute("homologues", homologues);
    }

    private Map<String, Set<ResultElement>> initMap() {
        Map homologues = new ListOrderedMap();
        for (String species : SPECIES) {
            addToMap(homologues, species, null);
        }
        return homologues;
    }

    private void addToMap(Map<String, Set<ResultElement>> homologues, String species,
            ResultElement re) {
        Set<ResultElement> speciesHomologues = homologues.get(species);
        if (speciesHomologues == null) {
            speciesHomologues = new HashSet<ResultElement>();
            homologues.put(species, speciesHomologues);
        }
        if (re != null) {
            speciesHomologues.add(re);
        }
    }
}

