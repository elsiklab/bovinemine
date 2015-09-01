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
import org.intermine.metadata.StringUtil;
import org.intermine.bio.io.gff3.GFF3Record;
import org.intermine.metadata.Model;
import org.intermine.xml.full.Item;

/**
 * A converter/retriever for the AmelProteincoding dataset via GFF files.
 */

public class AmelProteincodingGFF3RecordHandler extends GFF3RecordHandler
{

    /**
     * Create a new AmelProteincodingGFF3RecordHandler for the given data model.
     * @param model the model for which items will be created
     */
    public AmelProteincodingGFF3RecordHandler (Model model) {
        super(model);

	refsAndCollections.put("CDS", "transcript");
        refsAndCollections.put("Exon", "transcripts");
        refsAndCollections.put("MRNA", "gene");
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
  // This method is called for every line of GFF3 file(s) being read.  Features and their
        // locations are already created but not stored so you can make changes here.  Attributes
        // are from the last column of the file are available in a map with the attribute name as
        // the key.

        Item feature = getFeature();
        String clsName = feature.getClassName();

        if( clsName.equals("Gene") ) {

            if( record.getAttributes().get("symbol_ncbi") != null ) {
                String symbol = record.getAttributes().get("symbol_ncbi").iterator().next();
                feature.setAttribute("symbol", symbol); // setting 'symbol' attribute of class 'Gene'
            }
            // Accessing Dbxrefs
            List<String> dbxrefs = record.getDbxrefs();
            if( dbxrefs != null) {
                Iterator<String> dbxrefsIter = dbxrefs.iterator();

                while( dbxrefsIter.hasNext() ) {
                    String dbxref = dbxrefsIter.next();
                    List<String> refList = new ArrayList<String>( Arrays.asList(StringUtil.split(dbxref, ",")));
                    for( String ref : refList) {
                        ref = ref.trim();
                        int colonIndex = ref.indexOf(":");
                        if( colonIndex == -1 ) {
                            throw new RuntimeException("Error in Dbxref attribute " + ref );
                        }
                        if( ref.startsWith("NCBI_Gene") ) {
                            feature.setAttribute( "secondaryIdentifier", ref.replace("NCBI_Gene:", "") ); // setting 'primaryIdentifier' attribute of class 'Gene'
                        }
                        if( ref.startsWith("BGD:") || ref.startsWith("BEEBASE:") || ref.startsWith("NASONIABASE:") ) {
                            String[] splitVal = ref.split(":");
                            String secondaryId = splitVal[1];
                            System.out.println(secondaryId);
			    feature.setAttribute("primaryIdentifier", secondaryId); // setting 'secondaryIdentifier' attribute of class 'Gene'            
                            if( ref.startsWith("NCBI_Gene") ) {
                            feature.setAttribute( "secondaryIdentifier", ref.replace("NCBI_Gene:", "") ); // setting 'primaryIdentifier' attribute of class 'Gene'
		        	} 
                       }
			else{ 
   			   if( ref.startsWith("NCBI_Gene") ) {
                            feature.setAttribute( "primaryIdentifier", ref.replace("NCBI_Gene:", "") ); // setting 'primaryIdentifier' attribute of class 'Gene'	
			 }
                    }
                 }
                }
            }
        }
    else if( clsName.equals("MRNA") ) {

            if( record.getAttributes().get("symbol_ncbi") != null) {
                String symbol = record.getAttributes().get("symbol_ncbi").iterator().next();
                feature.setAttribute("symbol", symbol); // setting 'symbol' attribute of class 'MRNA'               
            }
            if( record.getAttributes().get("Note") != null) {
                String description = record.getAttributes().get("Note").iterator().next();
                feature.setAttribute("description", description); // setting 'description' attribute of class 'MRNA'               
            }

            // Accessing Dbxrefs
            List<String> dbxrefs = record.getDbxrefs();
            if( dbxrefs != null) {
                Iterator<String> dbxrefsIter = dbxrefs.iterator();

                while( dbxrefsIter.hasNext() ) {
                    String dbxref = dbxrefsIter.next();
                    List<String> refList = new ArrayList<String>( Arrays.asList(StringUtil.split(dbxref, ",")));
                    for( String ref : refList) {
                        ref = ref.trim();
                        int colonIndex = ref.indexOf(":");
                        if( colonIndex == -1 ) {
                            throw new RuntimeException("Error in Dbxref attribute " + ref );
                        }
                        if( ref.startsWith("RefSeq_NA") ) {
                            feature.setAttribute( "primaryIdentifier", ref.replace("RefSeq_NA:", "") ); // setting 'primaryIdentifier' attribute of class 'MRNA'
                        }
                        if( ref.startsWith("BGD:") || ref.startsWith("BEEBASE:") || ref.startsWith("NASONIABASE:") ) {
                            String[] splitVal = ref.split(":");
                            String secondaryId = splitVal[1];
                            feature.setAttribute("secondaryIdentifier", secondaryId); // setting 'secondaryIdentifier' attribute of class 'MRNA'
                        }
                        if( ref.startsWith("RefSeq_Prot") ) {
                            feature.setAttribute("proteinIdentifier", ref.replace("RefSeq_Prot:", "")); // setting 'proteinIdentifier' attribute of class 'MRNA'
                        }
                    }
                }
            }
        }
else if( clsName.equals("Transcript") ) {

            if( record.getAttributes().get("symbol_ncbi") != null) {
                String symbol = record.getAttributes().get("symbol_ncbi").iterator().next();
                feature.setAttribute("symbol", symbol); // setting 'symbol' attribute of class 'MRNA'               
            }
            if( record.getAttributes().get("Note") != null) {
                String description = record.getAttributes().get("Note").iterator().next();
                feature.setAttribute("description", description); // setting 'description' attribute of class 'MRNA'               
            }

            // Accessing Dbxrefs
            List<String> dbxrefs = record.getDbxrefs();
            if( dbxrefs != null) {
                Iterator<String> dbxrefsIter = dbxrefs.iterator();

                while( dbxrefsIter.hasNext() ) {
                    String dbxref = dbxrefsIter.next();
                    List<String> refList = new ArrayList<String>( Arrays.asList(StringUtil.split(dbxref, ",")));
                    for( String ref : refList) {
                        ref = ref.trim();
                        int colonIndex = ref.indexOf(":");
                        if( colonIndex == -1 ) {
                            throw new RuntimeException("Error in Dbxref attribute " + ref );
                        }
                        if( ref.startsWith("RefSeq_NA") ) {
                            feature.setAttribute( "primaryIdentifier", ref.replace("RefSeq_NA:", "") ); // setting 'primaryIdentifier' attribute of class 'MRNA'
                        }
	                if( ref.startsWith("BGD:") || ref.startsWith("BEEBASE:") || ref.startsWith("NASONIABASE:") ) {
                            String[] splitVal = ref.split(":");
                            String secondaryId = splitVal[1];
                            feature.setAttribute("secondaryIdentifier", secondaryId); // setting 'secondaryIdentifier' attribute of class 'MRNA'
                        }

                        if( ref.startsWith("RefSeq_Prot") ) {
                            feature.setAttribute("proteinIdentifier", ref.replace("RefSeq_Prot:", "")); // setting 'proteinIdentifier' attribute of class 'MRNA'
                        }
                    }
                }
            }
        }
        




	













//     Item feature = getFeature();
        //     String symbol = record.getAttributes().get("symbol");
        //     feature.setAttribute("symbol", symbol);
        //
        // Any new Items created can be stored by calling addItem().  For example:
        // 
        //     String geneIdentifier = record.getAttributes().get("gene");
        //     gene = createItem("Gene");
        //     gene.setAttribute("primaryIdentifier", geneIdentifier);
        //     addItem(gene);
        //
        // You should make sure that new Items you create are unique, i.e. by storing in a map by
        // some identifier. 

    }

}

