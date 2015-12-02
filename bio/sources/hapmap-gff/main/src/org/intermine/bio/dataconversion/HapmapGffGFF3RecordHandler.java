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
import org.intermine.xml.full.Item;
import org.intermine.metadata.StringUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * A converter/retriever for the HapmapGff dataset via GFF files.
 */

public class HapmapGffGFF3RecordHandler extends GFF3RecordHandler
{

    /**
     * Create a new HapmapGffGFF3RecordHandler for the given data model.
     * @param model the model for which items will be created
     */
    public HapmapGffGFF3RecordHandler (Model model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(GFF3Record record) {
        Item feature = getFeature();
        String clsName = feature.getClassName();
        feature.removeAttribute("symbol");

        if (clsName.equals("SNP")) {
            if (record.getAttributes().get("ID") != null) {
                String secondaryIdentifier = record.getAttributes().get("ID").iterator().next();
                feature.setAttribute("primaryIdentifier", secondaryIdentifier);
            }
            if (record.getAttributes().get("Reference_seq") != null) {
                String refAllele = record.getAttributes().get("Reference_seq").iterator().next();
                feature.setAttribute("referenceAllele", refAllele);
            }
            if (record.getAttributes().get("Variant_seq") != null) {
                String altAllele = record.getAttributes().get("Variant_seq").iterator().next();
                feature.setAttribute("alternateAllele", altAllele);
            }

            List<String> dbxrefs = record.getDbxrefs();
            if (dbxrefs != null) {
                Iterator<String> dbxrefsIter = dbxrefs.iterator();
                while (dbxrefsIter.hasNext()) {
                    String dbxref = dbxrefsIter.next();
                    List<String> refList = new ArrayList<String>( Arrays.asList(StringUtil.split(dbxref, ",")));
                    for (String ref : refList) {
                        ref = ref.trim();
                        int colonIndex = ref.indexOf(":");
                        if (colonIndex == -1) {
                            throw new RuntimeException("Error in Dbxref attribute " + ref);
                        }
                        if (ref.startsWith("dbSNP")) {
                            feature.setAttribute("secondaryIdentifier", ref.replace("dbSNP:", ""));
                        }
                    }
                }
            }
        }
    }
}
