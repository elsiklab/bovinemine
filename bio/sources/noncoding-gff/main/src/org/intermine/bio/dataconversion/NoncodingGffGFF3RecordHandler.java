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
 * A converter/retriever for the NoncodingGff dataset via GFF files.
 */

public class NoncodingGffGFF3RecordHandler extends GFF3RecordHandler
{

    /**
     * Create a new NoncodingGffGFF3RecordHandler for the given data model.
     * @param model the model for which items will be created
     */
    public NoncodingGffGFF3RecordHandler (Model model) {
        super(model);
        refsAndCollections.put("Exon", "transcripts");
        refsAndCollections.put("Transcript", "gene");
        refsAndCollections.put("PrimaryTranscript", "gene");
        refsAndCollections.put("NcRNA", "gene");
        refsAndCollections.put("MiRNA", "gene");
        refsAndCollections.put("TRNA", "gene");
        refsAndCollections.put("RRNA", "gene");
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

        Item feature = getFeature();
        String clsName = feature.getClassName();

        if( clsName.equals("Gene") ) {

            if( record.getAttributes().get("symbol_ncbi") != null ) {
                String symbol = record.getAttributes().get("symbol_ncbi").iterator().next();
                feature.setAttribute("symbol", symbol); // setting 'symbol' attribute of class 'Gene'
            }
            else {
                feature.removeAttribute("symbol"); // removing the 'symbol' set by intermine by default                                                                            
            }
            if( record.getAttributes().get("feature_type") != null ) {
                String ft = record.getAttributes().get("feature_type").iterator().next();
                feature.setAttribute("status", ft); // setting 'status' attribute of class 'Gene'
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
                            feature.setAttribute( "primaryIdentifier", ref.replace("NCBI_Gene:", "") ); // setting 'primaryIdentifier' attribute of class 'Gene'
                        }                        
                        
                        if( ref.startsWith("BGD:") || ref.startsWith("BEEBASE:") || ref.startsWith("NASONIABASE:") ) {
			    String[] splitVal = ref.split(":");
			    String secondaryId = splitVal[1];
                            feature.setAttribute("secondaryIdentifier", secondaryId); // setting 'secondaryIdentifier' attribute of class 'Gene'
                        }
                   



                    }
                }
            }
        }            
        else if( clsName.equals("Transcript") ) {
            
            if( record.getAttributes().get("symbol_ncbi") != null) {
                String symbol = record.getAttributes().get("symbol_ncbi").iterator().next();
                feature.setAttribute("symbol", symbol); // setting 'symbol' attribute of class 'Transcript'               
            }
            else {
                feature.removeAttribute("symbol"); // removing the 'symbol' set by intermine by default                                                                            
            }
            if( record.getAttributes().get("ncbi_desc") != null) {
                String description = record.getAttributes().get("ncbi_desc").iterator().next();
                feature.setAttribute("description", description); // setting 'description' attribute of class 'Transcript'               
            }
            if( record.getAttributes().get("feature_type") != null ) {
                String ft = record.getAttributes().get("feature_type").iterator().next();
                feature.setAttribute("status", ft); // setting 'status' attribute of class 'Gene'
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
                        // if( ref.startsWith("NCBI_Gene:") ) {
                        //     feature.setAttribute( "primaryIdentifier", ref.replace("NCBI_Gene:", "") ); // setting 'primaryIdentifier' attribute of class 'Transcript'
                        // }    


                    
                        if( ref.startsWith("BGD:") || ref.startsWith("BEEBASE:") || ref.startsWith("NASONIABASE:")) {
			    String[] splitVal = ref.split(":");
			    String secondaryId = splitVal[1];
                            feature.setAttribute("secondaryIdentifier", secondaryId); // setting 'secondaryIdentifier' attribute of class 'Transcript'
                        } 

                    
                        if( ref.startsWith("RefSeq_NA:") ) {
                            feature.setAttribute("primaryIdentifier", ref.replace("RefSeq_NA:", "")); // setting 'primaryIdentifier' attribute of class 'Transcript'
                        }
                    }
                }
            }           
        }
        else if( clsName.equals("PrimaryTranscript") ) {

            if( record.getAttributes().get("symbol_ncbi") != null) {
                String symbol = record.getAttributes().get("symbol_ncbi").iterator().next();
                feature.setAttribute("symbol", symbol); // setting 'symbol' attribute of class 'PrimaryTranscript'               
            }
            else {
                feature.removeAttribute("symbol"); // removing the 'symbol' set by intermine by default                                                                            
            }
            if( record.getAttributes().get("ncbi_desc") != null) {
                String description = record.getAttributes().get("ncbi_desc").iterator().next();
                feature.setAttribute("description", description); // setting 'description' attribute of class 'PrimaryTranscript'               
            }
            if( record.getAttributes().get("feature_type") != null ) {
                String ft = record.getAttributes().get("feature_type").iterator().next();
                feature.setAttribute("status", ft); // setting 'status' attribute of class 'Gene'
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
                        // if( ref.startsWith("NCBI_Gene:") ) {
                        //     feature.setAttribute( "primaryIdentifier", ref.replace("NCBI_Gene:", "") ); // setting 'primaryIdentifier' attribute of class 'PrimaryTranscript'
                        // }                        
                        if( ref.startsWith("BGD:") || ref.startsWith("BEEBASE:") || ref.startsWith("NASONIABASE:") ) {
                            String[] splitVal = ref.split(":");
			    String secondaryId = splitVal[1];
			    feature.setAttribute("secondaryIdentifier", secondaryId); // setting 'secondaryIdentifier' attribute of class 'PrimaryTranscript'
                        }

                        if( ref.startsWith("miRBase:") ) {
                            feature.setAttribute("secondaryIdentifier", ref.replace("miRBase:", "")); // setting 'secondaryIdentifier' attribute of class 'PrimaryTranscript'; replacing BGD with miRBase ID for miRNAs
                        }
                        if( ref.startsWith("RefSeq_NA:") ) {
                            feature.setAttribute("primaryIdentifier", ref.replace("RefSeq_NA:", "")); // setting 'primaryIdentifier' attribute of class 'PrimaryTranscript'
                        } // if transcript ID is not present then the feature defaults to 'ID' as primaryIdentifier
                    }
                }
            }           
        }
        else if( clsName.equals("MiRNA") ) {

            if( record.getAttributes().get("symbol_ncbi") != null) {
                String symbol = record.getAttributes().get("symbol_ncbi").iterator().next();
                feature.setAttribute("symbol", symbol); // setting 'symbol' attribute of class 'MiRNA'               
            }
            else {
                feature.removeAttribute("symbol"); // removing the 'symbol' set by intermine by default                                                                            
            }
            if( record.getAttributes().get("ncbi_desc") != null) {
                String description = record.getAttributes().get("ncbi_desc").iterator().next();
                feature.setAttribute("description", description); // setting 'description' attribute of class 'MiRNA'               
            }
            if( record.getAttributes().get("feature_type") != null ) {
                String ft = record.getAttributes().get("feature_type").iterator().next();
                feature.setAttribute("status", ft); // setting 'status' attribute of class 'Gene'
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
                        // if( ref.startsWith("NCBI_Gene:") ) {
                        //     feature.setAttribute( "primaryIdentifier", ref.replace("NCBI_Gene:", "") ); // setting 'primaryIdentifier' attribute of class 'MiRNA'
                        // }                        
                        if( ref.startsWith("BGD:") || ref.startsWith("BEEBASE:") || ref.startsWith("NASONIABASE:") ) {
                            String[] splitVal = ref.split(":");
			    String secondaryId = splitVal[1];
			    feature.setAttribute("secondaryIdentifier", secondaryId); // setting 'secondaryIdentifier' attribute of class 'MiRNA'
                        }
                        if( ref.startsWith("miRBase") ) {
                            feature.setAttribute("primaryIdentifier", ref.replace("miRBase:", "")); // setting 'secondaryIdentifier' attribute of class 'MiRNA'
                        }

                    }
                }
            } 
        }
        else if(clsName.equals("TRNA")) {

            if( record.getAttributes().get("symbol_ncbi") != null) {
                String symbol = record.getAttributes().get("symbol_ncbi").iterator().next();
                feature.setAttribute("symbol", symbol); // setting 'symbol' attribute of class 'TRNA'               
            }
            else {
                feature.removeAttribute("symbol"); // removing the 'symbol' set by intermine by default                                                                            
            }
            if( record.getAttributes().get("ncbi_desc") != null) {
                String description = record.getAttributes().get("ncbi_desc").iterator().next();
                feature.setAttribute("description", description); // setting 'description' attribute of class 'TRNA'               
            }
            if( record.getAttributes().get("feature_type") != null ) {
                String ft = record.getAttributes().get("feature_type").iterator().next();
                feature.setAttribute("status", ft); // setting 'status' attribute of class 'Gene'
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
                        // if( ref.startsWith("NCBI_Gene:") ) {
                        //     feature.setAttribute( "primaryIdentifier", ref.replace("NCBI_Gene:", "") ); // setting 'primaryIdentifier' attribute of class 'TRNA'
                        // }                        
                        if( ref.startsWith("BGD:") || ref.startsWith("BEEBASE:") || ref.startsWith("NASONIABASE:") ) {
			    String[] splitVal = ref.split(":");
			    String secondaryId = splitVal[1];
                            feature.setAttribute("secondaryIdentifier", secondaryId); // setting 'secondaryIdentifier' attribute of class 'TRNA'
                        }                        



                        if( ref.startsWith("RefSeq_NA") ) {
                            feature.setAttribute("primaryIdentifier", ref.replace("RefSeq_NA:", "")); // setting 'primaryIdentifier' attribute of class 'TRNA'
                        } // if no Transcript ID then the primaryIdentifier defaults to 'ID' attribute
                    }
                }
            }   
        }
        else if( clsName.equals("RRNA") ) {

            if( record.getAttributes().get("symbol_ncbi") != null) {
                String symbol = record.getAttributes().get("symbol_ncbi").iterator().next();
                feature.setAttribute("symbol", symbol); // setting 'symbol' attribute of class 'RRNA'               
            }
            else {
                feature.removeAttribute("symbol"); // removing the 'symbol' set by intermine by default                                                                            
            }
            if( record.getAttributes().get("ncbi_desc") != null) {
                String description = record.getAttributes().get("ncbi_desc").iterator().next();
                feature.setAttribute("description", description); // setting 'description' attribute of class 'RRNA'               
            }
            if( record.getAttributes().get("feature_type") != null ) {
                String ft = record.getAttributes().get("feature_type").iterator().next();
                feature.setAttribute("status", ft); // setting 'status' attribute of class 'Gene'
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
                        // if( ref.startsWith("NCBI_Gene:") ) {
                        //     feature.setAttribute( "primaryIdentifier", ref.replace("NCBI_Gene:", "") ); // setting 'primaryIdentifier' attribute of class 'RRNA'                        // }                        
                        if( ref.startsWith("BGD:") || ref.startsWith("BEEBASE:") || ref.startsWith("NASONIABASE:") ) {
                            String[] splitVal = ref.split(":");
			    String secondaryId = splitVal[1];
			    feature.setAttribute("secondaryIdentifier", secondaryId); // setting 'secondaryIdentifier' attribute of class 'RRNA'
                        }


                        if( ref.startsWith("RefSeq_NA") ) {
                            feature.setAttribute("primaryIdentifier", ref.replace("RefSeq_NA:", "")); // setting 'primaryIdentifier' attribute of class 'RRNA'
                        } // if no Transcript ID then the primaryIdentifier defaults to 'ID' attribute              
                    }
                }
            } 
        }
        else if( clsName.equals("Exon") ) {
            // Do nothing
        }
        else {
            System.out.println("Unexpected feature type encountered: " + clsName);
            System.exit(0);
        }

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
