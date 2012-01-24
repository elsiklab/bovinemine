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

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.intermine.api.InterMineAPI;
import org.intermine.api.query.PathQueryExecutor;
import org.intermine.api.results.ExportResultsIterator;
import org.intermine.api.results.ResultElement;
import org.intermine.metadata.Model;
import org.intermine.model.InterMineObject;
import org.intermine.pathquery.Constraints;
import org.intermine.pathquery.OrderDirection;
import org.intermine.pathquery.PathQuery;
import org.intermine.web.displayer.ReportDisplayer;
import org.intermine.web.logic.config.ReportDisplayerConfig;
import org.intermine.web.logic.results.ReportObject;
import org.intermine.web.logic.session.SessionMethods;

/**
 *
 * If we are mouse, fetch results straight off of us, otherwise create a PathQuery from other
 *  organisms.
 * @author radek
 *
 */
public class MouseAllelesDisplayer extends ReportDisplayer
{

    protected static final Logger LOG = Logger.getLogger(MouseAllelesDisplayer.class);

/**
 * Construct with config and the InterMineAPI.
 * @param config to describe the report displayer
 * @param im the InterMine API
 */
    public MouseAllelesDisplayer(ReportDisplayerConfig config, InterMineAPI im) {
        super(config, im);
    }

    @SuppressWarnings({ "unchecked", "unused" })
    @Override
    public void display(HttpServletRequest request, ReportObject reportObject) {
        HttpSession session = request.getSession();
        final InterMineAPI im = SessionMethods.getInterMineAPI(session);
        Model model = im.getModel();
        PathQueryExecutor executor = im.getPathQueryExecutor(SessionMethods.getProfile(session));

        // Counts of HLPT Names
        PathQuery q = new PathQuery(model);

        Boolean mouser = false;
        if (!this.isThisAMouser(reportObject)) {
            // to give us some homologue identifier and the actual terms to tag-cloudize
            q.addViews(
                    "Gene.symbol",
                    "Gene.primaryIdentifier",
                    "Gene.id",
                    "Gene.homologues.homologue.alleles.genotypes.phenotypeTerms.name");
            // add this rubbish so we do not filter out the same terms
            q.addViews(
                    "Gene.homologues.homologue.id",
                    "Gene.homologues.homologue.alleles.id",
                    "Gene.homologues.homologue.alleles.genotypes.id");

            // mouse homologues only
            q.addConstraint(Constraints.eq("Gene.homologues.homologue.organism.shortName",
                    "M. musculus"), "A");
            // for our gene object
            q.addConstraint(Constraints.eq("Gene.id", reportObject.getObject().getId().toString()),
                    "B");
            // we want only those homologues that have a non-empty alleles collection
            q.addConstraint(Constraints.isNotNull("Gene.homologues.homologue.alleles.id"));
            q.setConstraintLogic("A and B");
            // order by the homologue db id, just to keep the alleles in a reasonable order
            q.addOrderBy("Gene.homologues.homologue.id", OrderDirection.ASC);
        } else {
            mouser = true;

            // to give us some homologue identifier and the actual terms to tag-cloudize
            q.addViews(
                    "Gene.symbol",
                    "Gene.primaryIdentifier",
                    "Gene.id",
                    "Gene.alleles.genotypes.phenotypeTerms.name");
            // add this rubbish so we do not filter out the same terms
            q.addViews(
                    "Gene.alleles.id",
                    "Gene.alleles.genotypes.id");

            // for our gene object
            q.addConstraint(Constraints.eq("Gene.id", reportObject.getObject().getId().toString()),
                    "A");
            // we want only those homologues that have a non-empty alleles collection
            q.addConstraint(Constraints.isNotNull("Gene.alleles.id"));
        }

        ExportResultsIterator qResults = executor.execute((PathQuery) q);
        // traverse so we get a nice map from homologue symbol to a map of allele term names (and
        //  some extras)
        HashMap<String, HashMap<String, Object>> counts = new HashMap<String, HashMap<String,
                Object>>();
        while (qResults.hasNext()) {
            List<ResultElement> row = qResults.next();
            String sourceGeneSymbol = getIdentifier(row);
            // a per source gene map
            HashMap<String, Integer> terms;
            if (!counts.containsKey(sourceGeneSymbol)) {
                HashMap<String, Object> wrapper = new HashMap<String, Object>();
                wrapper.put("terms", terms = new LinkedHashMap<String, Integer>());
                wrapper.put("homologueId", row.get(2).getField().toString());
                wrapper.put("isMouser", mouser);
                counts.put(sourceGeneSymbol, wrapper);
            } else {
                terms = (HashMap<String, Integer>) counts.get(sourceGeneSymbol).get("terms");
            }
            // populate the allele term with count
            String alleleTerm = row.get(3).getField().toString();
            if (!alleleTerm.isEmpty()) {
                Object k = (!terms.containsKey(alleleTerm)) ? terms.put(alleleTerm, 1)
                        : terms.put(alleleTerm, terms.get(alleleTerm) + 1);
            }
        }

        // Now give us a map of top 20 per homologue
        HashMap<String, HashMap<String, Object>> top = new HashMap<String, HashMap<String,
                Object>>();
        for (String symbol : counts.keySet()) {
            HashMap<String, Object> gene = counts.get(symbol);
            LinkedHashMap<String, Integer> terms =
                    (LinkedHashMap<String, Integer>) gene.get("terms");
            if (terms != null) {
                // sorted by value
                TreeMap<String, Integer> sorted = new TreeMap<String, Integer>(
                        new IntegerValueComparator(terms));
                // deep copy
                for (String term : terms.keySet()) {
                    sorted.put(term, (Integer) terms.get(term));
                }
                // "mark" top 20 and order by natural order - the keys
                HashMap<String, Map<String, Object>> marked =
                        new HashMap<String, Map<String, Object>>();
                Integer i = 0;
                for (String term : sorted.keySet()) {
                    // wrapper map
                    HashMap<String, Object> m = new HashMap<String, Object>();
                    // am I top dog?
                    Boolean topTerm = false;
                    if (i < 20) {
                        topTerm = true;
                    }
                    m.put("top", topTerm);
                    m.put("count", (Integer) sorted.get(term));

                    // save it
                    marked.put(term, m);
                    i++;
                }

                HashMap<String, Object> wrapper = new HashMap<String, Object>();
                wrapper.put("terms", marked);
                wrapper.put("homologueId", gene.get("homologueId"));
                wrapper.put("isMouser", gene.get("isMouser"));
                top.put(symbol, wrapper);
            }
        }

        request.setAttribute("counts", top);
    }

/**
 * Given columns: [symbol, primaryId, id] in a List<ResultElement> row, give us a nice
 *  identifier back
 * @param row
 * @return
 */
    private String getIdentifier(List<ResultElement> row) {
        String id = null;
        return (!(id = row.get(0).getField().toString()).isEmpty()) ? id : ((id = row.get(1).getField().toString()).isEmpty()) ? id : row.get(2).getField().toString();
    }

/**
 *
 * @return true if we are on a mouseified gene
 */
    private Boolean isThisAMouser(ReportObject reportObject) {
        try {
            return "Mus".equals(((InterMineObject) reportObject.getObject()
                        .getFieldValue("organism"))
                .getFieldValue("genus"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Compare Maps by their integer values
     * @author radek
     *
     */
    class IntegerValueComparator implements Comparator
    {

        Map base;
        /**
         * A constructor
         * @param base parameter
         */
        public IntegerValueComparator(Map base) {
            this.base = base;
        }

        /**
         * A function
         * @param a parameter
         * @param b parameter
         * @return Integer
         */
        public int compare(Object a, Object b) {
            Integer aV = (Integer) base.get(a);
            Integer bV = (Integer) base.get(b);

            if (aV < bV) {
                return 1;
            } else {
                if (aV == bV) {
                    // Same size, need to sort by name
                    return a.toString().compareTo(b.toString());
                }
            }
            return -1;
        }
    }

}
