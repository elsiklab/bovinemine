package org.intermine.bio.dataconversion;

/*
 * Copyright (C) 2002-2011 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.intermine.bio.io.gff3.GFF3Record;
import org.intermine.metadata.Model;
import org.intermine.metadata.StringUtil;
import org.intermine.xml.full.Item;
/**
 * A converter/retriever for the EnsemblGff dataset via GFF files.
 */

public class EnsemblGffGFF3RecordHandler extends GFF3RecordHandler
{

    /**
     * Create a new EnsemblGffGFF3RecordHandler for the given data model.
     * @param model the model for which items will be created
     */
    public EnsemblGffGFF3RecordHandler (Model model) {
        super(model);
        refsAndCollections.put("Transcript", "gene");
        refsAndCollections.put("MRNA", "gene");
    	refsAndCollections.put("TRNA", "gene");
        refsAndCollections.put("MiRNA", "gene");
        refsAndCollections.put("RRNA", "gene");
        refsAndCollections.put("SnRNA", "gene");
        refsAndCollections.put("SnoRNA", "gene");
        refsAndCollections.put("Exon", "transcripts");
        refsAndCollections.put("CDS", "transcript");
        refsAndCollections.put("UTR", "transcripts");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(GFF3Record record) {
        // This method is called for every line of GFF3 file(s) being read.  Features and their
        // locations are already created but not stored so you can make changes here.  Attributes
        // are from the last column of the file are available in a map with the attribute name as
        // the key
        Item feature = getFeature();
        String clsName = feature.getClassName();

        if( clsName.equals("Gene") ) {
            if(record.getAttributes().get("ID") != null){
                String id = record.getAttributes().get("ID").iterator().next();
                feature.setAttribute("primaryIdentifier", id);
            }
              if(record.getAttributes().get("source") != null){
                String source = record.getAttributes().get("source").iterator().next();
                feature.setAttribute("source", source);
            }


            if(record.getAttributes().get("symbol_ensembl") != null){
                String symbol = record.getAttributes().get("symbol_ensembl").iterator().next();
                feature.setAttribute("symbol", symbol);
            }
        }
        else if( clsName.equals("MRNA") || clsName.equals("Transcript") || clsName.equals("TRNA") || clsName.equals("MiRNA") || clsName.equals("RRNA") || clsName.equals("SnRNA") || clsName.equals("SnoRNA")) {
            if(record.getAttributes().get("ID") != null){
                String id = record.getAttributes().get("ID").iterator().next();
                feature.setAttribute("primaryIdentifier", id);
            }
            if(record.getAttributes().get("source") != null){
                String source = record.getAttributes().get("source").iterator().next();
                feature.setAttribute("source", source);
            }
            if(record.getAttributes().get("symbol_ensembl") != null){
                String symbol = record.getAttributes().get("symbol_ensembl").iterator().next();
                feature.setAttribute("symbol", symbol);
            }
            if(record.getAttributes().get("protein_id") != null){
                String prot = record.getAttributes().get("protein_id").iterator().next();
                feature.setAttribute("proteinIdentifier", prot);
            }
        }
        else if( clsName.equals("Exon") || clsName.equals("CDS") || clsName.equals("UTR") ) {
            // Do nothing
        }
        else {
            System.out.println(clsName);
            System.exit(0);
        }

    }
}
