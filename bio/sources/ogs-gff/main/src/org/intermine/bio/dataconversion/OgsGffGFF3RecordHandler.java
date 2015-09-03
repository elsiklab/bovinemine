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

import org.intermine.bio.io.gff3.GFF3Record;
import org.intermine.metadata.Model;
import org.intermine.xml.full.Item;

/**
 * A converter/retriever for the OgsGff dataset via GFF files.
 */

public class OgsGffGFF3RecordHandler extends GFF3RecordHandler
{

    /**
     * Create a new OgsGffGFF3RecordHandler for the given data model.
     * @param model the model for which items will be created
     */
    public OgsGffGFF3RecordHandler (Model model) {
        super(model);
        refsAndCollections.put("MRNA", "gene");
        refsAndCollections.put("Exon", "transcripts");
        refsAndCollections.put("CDS", "transcript");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(GFF3Record record) {
        // This method is called for every line of GFF3 file(s) being read.  Features and their
        // locations are already created but not stored so you can make changes here.  Attributes
        // are from the last column of the file are available in a map with the attribute name as
        // the key.   For example:
        //
        Item feature = getFeature();
        String clsName = feature.getClassName();
        String assemblyName = record.getSource();
        feature.setAttribute("assemblyName", assemblyName);
        feature.setAttribute("source", record.getSource());

        if( clsName.equals("Gene") ) {
            if(record.getAttributes().get("ID") != null){
                String id = record.getAttributes().get("ID").iterator().next();
                feature.setAttribute("primaryIdentifier", id);
            }
            if(record.getAttributes().get("Name") != null){
                String name = record.getAttributes().get("Name").iterator().next();
                feature.setAttribute("name", name);
            }
            if(record.getAttributes().get("source") != null){
                String source = record.getAttributes().get("source").iterator().next();
                //feature.setAttribute("source", source);
            }
       //     if(record.getAttributes().get("isBroken") != null){
      //          String status = record.getAttributes().get("isBroken").iterator().next();
      //          feature.setAttribute("isBroken", status);
      //      }
            feature.removeAttribute("symbol");
        }
        else if( clsName.equals("MRNA") || clsName.equals("Polypeptide") ) {
            if(record.getAttributes().get("ID") != null){
                String id = record.getAttributes().get("ID").iterator().next();
                feature.setAttribute("primaryIdentifier", id);
            }
            if(record.getAttributes().get("Note") != null){
                String description = record.getAttributes().get("Note").iterator().next();
                feature.setAttribute("description", description);
            }
                if(record.getAttributes().get("source") != null){
                String source = record.getAttributes().get("source").iterator().next();
                //feature.setAttribute("source", source);
            }
            if(record.getAttributes().get("Name") != null){
                String name = record.getAttributes().get("Name").iterator().next();
                feature.setAttribute("name", name);
            }
            feature.removeAttribute("symbol");
        }
        else if( clsName.equals("StartCodon") || clsName.equals("StopCodon") ) {
            // Do nothing
        }
    }

}
