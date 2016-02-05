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

/**
 * A converter/retriever for the DbsnpGff dataset via GFF files.
 */

public class DbsnpGffGFF3RecordHandler extends GFF3RecordHandler
{

    /**
     * Create a new DbsnpGffGFF3RecordHandler for the given data model.
     * @param model the model for which items will be created
     */
    public DbsnpGffGFF3RecordHandler (Model model) {
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
            if (record.getAttributes().get("Note") != null) {
                String note = record.getAttributes().get("Note").iterator().next();
                String refAllele = note.split(">")[0];
                String altAllele = note.split(">")[1];
                feature.setAttribute("referenceAllele", refAllele);
                feature.setAttribute("alternateAllele", altAllele);
            }
            if (record.getAttributes().get("rs_id") != null) {
                String primaryIdentifier = record.getAttributes().get("rs_id").iterator().next();
                feature.setAttribute("primaryIdentifier", primaryIdentifier);
            }
            if (record.getAttributes().get("ID") != null) {
                String secondaryIdentifier = record.getAttributes().get("ID").iterator().next();
                feature.setAttribute("secondaryIdentifier", secondaryIdentifier);
            }
            if (record.getAttributes().get("rspos") != null) {
                String ref = record.getAttributes().get("rspos").iterator().next();
                feature.setAttribute("rsPosition", ref);
            }
            if (record.getAttributes().get("sao") != null) {
                String sao = record.getAttributes().get("sao").iterator().next();
                feature.setAttribute("snpAlleleOrigin", sao);
            }
            if (record.getAttributes().get("dbsnpbuildid") != null) {
                String dbBuild = record.getAttributes().get("dbsnpbuildid").iterator().next();
                feature.setAttribute("dbsnpBuild", dbBuild);
            }
        }

        else if (clsName.equals("Indel")) {
            if (record.getAttributes().get("Note") != null) {
                String note = record.getAttributes().get("Note").iterator().next();
                String refAllele = note.split(">")[0];
                String altAllele = note.split(">")[1];
                feature.setAttribute("referenceAllele", refAllele);
                feature.setAttribute("alternateAllele", altAllele);
            }
            if (record.getAttributes().get("rs_id") != null) {
                String primaryIdentifier = record.getAttributes().get("rs_id").iterator().next();
                feature.setAttribute("primaryIdentifier", primaryIdentifier);
            }
            if (record.getAttributes().get("ID") != null) {
                String secondaryIdentifier = record.getAttributes().get("ID").iterator().next();
                feature.setAttribute("secondaryIdentifier", secondaryIdentifier);
            }
            if (record.getAttributes().get("rspos") != null) {
                String ref = record.getAttributes().get("rspos").iterator().next();
                feature.setAttribute("rsPosition", ref);
            }
            if (record.getAttributes().get("sao") != null) {
                String sao = record.getAttributes().get("sao").iterator().next();
                feature.setAttribute("snpAlleleOrigin", sao);
            }
            if (record.getAttributes().get("dbsnpbuildid") != null) {
                String dbBuild = record.getAttributes().get("dbsnpbuildid").iterator().next();
                feature.setAttribute("dbsnpBuild", dbBuild);
            }
        }
    }

   





}
