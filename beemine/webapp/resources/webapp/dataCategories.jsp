 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im"%>

<!-- dataCategories -->


<html:xhtml/>

<div class="body">
<im:boxarea title="Data" stylename="plainbox"><p><fmt:message key="dataCategories.intro"/></p></im:boxarea>


<table cellpadding="0" cellspacing="0" border="0" class="dbsources">
  <tr>
    <th>Data Category</th>
    <th>Organism</th>
    <th>Data</th>
    <th>Source</th>
    <th>PubMed</th>
    <th>Note</th>
  </tr>
  <tr>
    <td rowspan="3"  class="leftcol"><p><h2>Genes</h2></p></td>
    <td width="12%">
      <p><i>A. mellifera</i></p>
      <p><i>A. dorsata</i></p>
      <p><i>A. florea</i></p>
      <p><i>B. impatiens</i></p>
      <p><i>B. terrestris</i></p>
      <p><i>N. vitripennis</i></p>
    </td>
    <td>RefSeq Genome Annotation for Apis mellifera Genome 4.5 scaffold assembly, Apis dorsata Genome 1.3 scaffold assembly, Apis florea Genome 1.0 scaffold assembly, Bombus impatiens Genome 2.0 scaffold assembly, Bombus terrestris Genome 1.0 scaffold assembly</td>
    <td><a href="http://www.ncbi.nlm.nih.gov/assembly/?term=hymenoptera" target="_new" class="extlink">NCBI</a></td>
    <td> &nbsp;</td>
    <td> &nbsp; </td>
  </tr>

  <tr>
    <td>
      <p><i>A.mellifera</i></p>
      <p><i>H.saltator</i></p>
      <p><i>C.obscurior</i></p>
      <p><i>L.albipes</i></p>
      <p><i>L.humile</i></p>
      <p><i>P.barbatus</i></p>
      <p><i>A.cephalotes</i></p>
      <p><i>A.echinatior</i></p>
      <p><i>S.invicta</i></p>
      <p><i>C.floridanus</i></p>
      <p><i>B.impatiens</i></p>
    </td>
     <td>Official Gene Set v3.2 for Apis mellifera Genome, Official Gene Set v3.3 for Harpegnathos saltator Genome, Official Gene Set 1.4  for Cardiocondyla obscurior Genome,Official Gene Set v5.42 for Lasioglossum albipes Genome, Official Gene Set v1.2 for Linepithema humile Genome, Official Gene Set v1.2 for Pogonomyrmex barbatus Genome, Official Gene Set v1.2 for Atta cephalotes Genome, Official Gene Set v3.8 for Acromyrmex echinatior Genome, Official Gene Set v2.2.3 for Solenopsis invicta Genome, Official Gene Set v3.3 for Camponotus floridanus Genome, Official Gene Set v1.0 for Bombus impatiens Genome</td>
    <td><a href="http://hymenopteragenome.org/?q=home" target="_new" class="extlink">Hymenoptera Genome Database</a></td>
    <td> Munoz-Torres MC et al. -  <a href="http://www.ncbi.nlm.nih.gov/pubmed/21071397" target="_new" class="extlink">PubMed: 21071397</a></td>
   <td> &nbsp; </td>
  </tr>
    
  <tr>
    <!--   <p><i></i></p> -->
    <!-- <td>Mi et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/23193289 " target="_new" class="extlink">PubMed: 23193289</a></td> -->
    <!-- <td> &nbsp; </td>
    <td> &nbsp; </td> -->
  </tr>
         
  </tr>
  
  <tr>
    <td rowspan="4"  class="leftcol"><p><h2>Homology</h2></p></td>
    <td>
      <p><i>A. mellifera</i></p>
      <p><i>A. florea</i></p>
      <p><i>H. saltator</i></p>
      <p><i>N. vitripennis</i></p>
      <p><i>L. albipes</i></p>
      <p><i>L. humile</i></p>
      <p><i>P. barbatus</i></p>
      <p><i>A. cephalotes</i></p>
      <p><i>A. echinatior</i></p>
      <p><i>S. invicta</i></p>
      <p><i>C. floridanus</i></p>
      <p><i>B. impatiens</i></p>
      <p><i>B. terrestris</i></p>      
    </td>
    <td>Orthologues and paralogues  across <i>Hymenoptera</i> bees, Ants <i>Nasonia vitripennis </i>, and <i>Drosophila melanogaster </i></td>
    <td><a href="http://orthodb.org/orthodb7" target="_new" class="extlink">OrthoDB</a> - Version 7</td>
    <td>Kriventseva EV et al. - <a href="http://www.ncbi.nlm.nih.gov/pubmed/20972218 " target="_new" class="extlink">PubMed: 20972218</a></td>
    <td> &nbsp; </td>
  </tr>

  <tr>
    
  </tr>
  
  <tr>
    <td>
      <p><i>A. mellifera</i></p>
      <p><i>A. cephalotes</i></p>
      <p><i>S. invicta</i></p>
      <p><i>D. melanogaster</i></p>
      <p><i>N. vitripennis</i></p>
    </td>
     <td>Orthologues and paralogues  across <i>Hymenoptera</i> bees, Ants <i>Nasonia vitripennis </i>, and <i>Drosophila melanogaster </i></td>
    <td><a href="http://useast.ensembl.org/biomart/martview/" target="_new" class="extlink">Ensembl BioMart</a></td>
    <!-- <td>Mi et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/23193289 " target="_new" class="extlink">PubMed: 23193289</a></td> -->
    <td> &nbsp; </td>
    <td> &nbsp; </td>
  </tr>

  <tr>
 
  </tr>

  <tr>
    <td rowspan="3"  class="leftcol"><p><h2>Proteins</h2></p></td>

    <td>
      <p><i>A. mellifera</i></p>
      <p><i>A. florea</i></p>
      <p><i>A. dorsata</i></p>
      <p><i>H. saltator</i></p>
      <p><i>N. vitripennis</i></p>
      <p><i>L. humile</i></p>
      <p><i>P. barbatus</i></p>
      <p><i>A. cephalotes</i></p>
      <p><i>S. invicta</i></p>
      <p><i>C. floridanus</i></p>
      <p><i>B. impatiens</i></p>
      <p><i>B. terrestris</i></p>

   </td>
    <td> Protein annotation</td>
    <td> <a href="http://www.ebi.uniprot.org/index.shtml" target="_new" class="extlink">UniProt</a> - Release 2014_10</td>
    <td> UniProt Consortium - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17142230" target="_new" class="extlink">PubMed: 17142230</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td>

      <p><i>A. mellifera</i></p>
      <p><i>A. florea</i></p>
      <p><i>A. dorsata</i></p>
      <p><i>H. saltator</i></p>
      <p><i>N. vitripennis</i></p>
      <p><i>L. humile</i></p>
      <p><i>P. barbatus</i></p>
      <p><i>A. cephalotes</i></p>
      <p><i>S. invicta</i></p>
      <p><i>C. floridanus</i></p>
      <p><i>B. impatiens</i></p>
      <p><i>B. terrestris</i></p>
</td>
    <td> Protein family and domain assignments to proteins</td>
    <td> <a href="http://www.ebi.ac.uk/interpro" target="_new" class="extlink">InterPro</a>Version 48.0 - July 2014 </td>
    <td> Mulder et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17202162" target="_new" class="extlink">PubMed: 17202162</a></td>
    <td> &nbsp;</td>
  </tr>
  <tr>
    <td> <i>A. mellifera</i> </td>
    <td> Experimentally determined 3-D structures</td>
    <td> <a href="http://www.rcsb.org/pdb/home/home.do" target="_new" class="extlink">PDB [Protein Data Bank]</a> - Nov 2014</td>
    <td> &nbsp;</td>
    <td> &nbsp;</td>
  </tr>

 <tr>
  </tr>
<!--  <tr>
    <td> <i>D. melanogaster</i></td>
    <td> miRNA target predictions from miRBase</td>
    <td> <a href="http://microrna.sanger.ac.uk/targets/v5" target="_new" class="extlink">miRBase</a> - Version 5 </td>
    <td> Enright et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/14709173" target="_new" class="extlink">PubMed:14709173</a></td>
    <td> &nbsp;</td>
  </tr> -->
  <tr>
    <td rowspan="1" class="leftcol"><p> <h2>Gene Ontology</h2></p></td>
    <td> 
      <p><i>A. mellifera</i></p>
      <p><i>A. florea</i></p>
      <p><i>A. dorsata</i></p>
      <p><i>N. vitripennis</i></p>
      <p><i>A. cephalotes</i></p>
      <p><i>S. invicta</i></p>
      <p><i>B. terrestris</i></p>
   </td>
    <td> GO annotations </td>
    <td> <a href="http://www.geneontology.org" target="_new" class="extlink">Gene Ontology Site</a> - Sep 2014</td>
    <td> Gene Ontology Consortium - <a href="http://www.ncbi.nlm.nih.gov/pubmed/10802651" target="_new" class="extlink">PubMed:10802651</a></td>
    <td> &nbsp;</td>
  </tr>
<!--
  <tr>
    <td> <i>C. elegans</i></td>
    <td> GO annotations </td>
    <td> <a href="http://www.geneontology.org" target="_new" class="extlink">Gene Ontology Site</a> - 05th June 2014</td>
    <td> Gene Ontology Consortium - <a href="http://www.ncbi.nlm.nih.gov/pubmed/10802651" target="_new" class="extlink">PubMed:10802651</a></td>
    <td> &nbsp;</td>
  </tr>
  -->
<!--   <tr>
    <td> <i>A. gambiae</i> </td>
    <td> GO annotations </td>
    <td> <a href="http://www.ebi.ac.uk/GOA/uniprot_release.html" target="_new" class="extlink">UniProt GOA</a> - 9th June 2014</td>
    <td> Camon et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/14681408" target="_new" class="extlink">PubMed: 14681408</a></td>
    <td> &nbsp;</td>
  </tr>

    <tr>
    <td> <i>D. melanogaster, A. gambiae, C. elegans</i></td>
    <td> InterPro domains to GO annotations </td>
    <td> <a href="http://www.ebi.ac.uk/interpro" target="_new" class="extlink">InterPro</a> (from <a href="http://www.geneontology.org" target="_new" class="extlink">Gene Ontology Site</a>) - June 2014</td>
    <td> Gene Ontology Consortium - <a href="http://www.ncbi.nlm.nih.gov/pubmed/10802651" target="_new" class="extlink">PubMed:10802651</a>, Mulder et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17202162" target="_new" class="extlink">PubMed: 17202162</a></td>
    <td> &nbsp;</td>
  </tr> -->

  <!-- <tr>
    <td rowspan="6" class="leftcol"><p><h2>Expression</h2></p></td>
    <td> <i>D. melanogaster</i> </td>
    <td> Microarray-based gene expression data for the life cycle of D. melanogaster</td>
    <td> <a href="http://www.ebi.ac.uk/arrayexpress" target="_new" class="extlink"> ArrayExpress </a> - Experiment E-FLYC-6</td>
    <td> Arbeitman et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/ 12351791" target="_new" class="extlink">PubMed: 12351791</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td> <i>D. melanogaster</i> </td>
    <td> Affymetrix microarray-based atlas of gene expression in larval and adult tissues</td>
    <td> <a href="http://www.flyatlas.org" target="_new" class="extlink">FlyAtlas</a> - 13th June 2011</td>
    <td> Chintapalli et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17534367" target="_new" class="extlink">PubMed: 17534367</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td> <i>D. melanogaster</i> </td>
    <td> Expression patterns of mRNAs at the subcellular level during early embryogenesis</td>
    <td> <a href="http://fly-fish.ccbr.utoronto.ca" target="_new" class="extlink">Fly-FISH</a> - 16th October 2007 </td>
    <td> Lecuyer et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17923096" target="_new" class="extlink">PubMed: 17923096</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td> <i>D. melanogaster</i> </td>
    <td> Expression patterns of mRNAs during embryogenesis</td>
    <td> <a href="http://www.fruitfly.org/cgi-bin/ex/insitu.pl" target="_new" class="extlink">BDGP</a> - Release 3.0 June 2014</td>
    <td> Tomancak et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17645804" target="_new" class="extlink">PubMed:17645804</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td> <i>D. melanogaster</i> </td>
    <td> Genome-wide RNA_seq Expression Data: raw data produced by modENCODE analysed by FlyBase</td>
    <td> <a href="http://www.flybase.org" target="_new" class="extlink">Flybase</a> - Version FB2014_03</td>
    <td> Gelbart and Emmert - 2010<a href="http://flybase.org/reports/FBrf0212041.html" target="_new" class="extlink">FlyBase Report</a></td>
    <td> &nbsp;</td>
  </tr>


  <tr>
    <td> <i>A. gambiae</i> </td>
    <td> Microarray-based gene expression data for the life cycle of A. gambiae</td>
    <td> <a href="http://www.ebi.ac.uk/arrayexpress" target="_new" class="extlink"> ArrayExpress </a> - Experiment E-TABM-186</td>
    <td> Koutsos et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/ 17563388" target="_new" class="extlink">PubMed: 17563388</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td rowspan="3" class="leftcol"><p><h2>Regulation</h2></p></td>
    <td> <i>D. melanogaster</i> </td>
    <td> Transcriptional cis-regulatory modules (CRMs)</td>
    <td> <a href="http://redfly.ccr.buffalo.edu" target="_new" class="extlink">REDfly</a> - 11th Jul 2013</td>
    <td> Gallo et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/16303794" target="_new" class="extlink">PubMed: 16303794</a></td>
    <td>  &nbsp;</td>
  </tr>

  <tr>
    <td> <i>D. melanogaster</i> </td>
    <td> Transcription factor binding sites</td>
    <td> <a href="http://www.flyreg.org" target="_new" class="extlink">REDfly</a> - 11th Jul 2013</td>
    <td> Bergman et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/15572468" target="_new" class="extlink">PubMed: 15572468</a></td>
    <td>  &nbsp; </td>
  </tr>

  <tr>
    <td> <i>D. melanogaster</i> </td>
    <td> Enhancers</td>
    <td> <a href="http://www.flybase.org" target="_new" class="extlink">FlyBase</a> - Version FB2014_03</td>
    <td> Crosby et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17099233" target="_new" class="extlink">PubMed: 17099233</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td rowspan="2" class="leftcol"><p> <h2>Phenotypes</h2></p></td>
    <td> <i>D. melanogaster</i> </td>
    <td> Alleles and phenotypes</td>
    <td> <a href="http://www.flybase.org" target="_new" class="extlink">FlyBase</a> - Version FB2014_03</td>
    <td> Crosby et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17099233" target="_new" class="extlink">PubMed: 17099233</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td> <i>D. melanogaster</i> </td>
    <td> High-throughput cell-based RNAi screens</td>
    <td> <a href="http://genomernai.org" target="_new" class="extlink">GenomeRNAi</a> - Version 13.0</td>
    <td> Schmidt et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/23193271" target="_new" class="extlink">PubMed: 23193271
</a></td>
    <td> &nbsp;</td>
  </tr> -->
<!--   <tr>
    <td rowspan="3" class="leftcol"><p> <h2>Pathways</h2></p></td>
    <td> <p><i>B. taurus</i></p></td>
    <td> Curated pathway information and the genes involved in them</td>
    <td> <a href="http://www.genome.jp/kegg/" target="_new" class="extlink">KEGG</a> - Release 58, 31 May 2011</td>
    <td> Kanehisa et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/16381885" target="_new" class="extlink">PubMed: 16381885</a></td>
    <td> &nbsp;</td>
  </tr> -->

  <tr>
   
  </tr>

<!-- <tr>
    <td> <i>B. taurus</i></td>
    <td> Curated pathway information and the genes involved in them</td>
    <td> <a href="http://fly.reactome.org/" target="_new" class="extlink">FlyReactome</a> - Version 3.0</td>
    <td> &nbsp;</td>
    <td>&nbsp;</td>
  </tr> -->

<!--   <tr>
    <td rowspan="1" class="leftcol"><p><h2>Diseases</h2></p></td>
    <td> <i>D. melanogaster</i> </td>
    <td> Human disease data set</td>
    <td> <a href="http://omim.org" target="_new" class="extlink">OMIM</a> - Version October 2013</td>
    <td> Amberger et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/18842627" target="_new" class="extlink">PubMed: 18842627</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td rowspan="5" class="leftcol"><p><h2>Resources</h2></p></td>
    <td> <i>D. melanogaster</i> </td>
    <td> <a href="http://www.drosdel.org.uk" target="_new" class="extlink">DrosDel</a> artificial deletions</td>
    <td> <a href="http://www.flybase.org" target="_new" class="extlink">FlyBase </a> - Version FB2014_03</td>
    <td> Ryder et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/15238529" target="_new" class="extlink">PubMed: 15238529</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td> <i>D. melanogaster</i> </td>
    <td> Insertions (including DrosDel and Exelixis) </td>
    <td> <a href="http://www.flybase.org" target="_new" class="extlink">Flybase</a> - Version FB2014_03</td>
    <td> Crosby et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17099233" target="_new" class="extlink">PubMed: 17099233</a></td>
    <td>&nbsp;</td>
  </tr>

  <tr>
    <td> <i>D. melanogaster</i> </td>
    <td> Probe sets from the Affymetrix GeneChip Drosophila Genome 1.0 Array</td>
    <td> <a href="http://www.ensembl.org" target="_new" class="extlink">Ensembl</a> - Release 50</td>
    <td> &nbsp;</td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td> <i>D. melanogaster</i> </td>
    <td> Probe sets from the Affymetrix GeneChip Drosophila Genome 2.0 Array</td>
    <td> <a href="http://www.ensembl.org" target="_new" class="extlink">Ensembl</a> - Release 50</td>
    <td> &nbsp;</td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td> <i>D. melanogaster</i> </td>
    <td> INDAC Microarray oligo set - Version 1.0</td>
    <td> <a href="http://www.indac.net" target="_new" class="extlink">International Drosophila Array Consortium</a></td>
    <td> &nbsp;</td>
    <td> &nbsp;</td>
  </tr> -->

  <tr>
    <td rowspan="2" class="leftcol"><p><h2>Publications</h2></p></td>
    <td> 
               
      <p><i>A. mellifera</i></p>
      <p><i>A. florea</i></p>
      <p><i>A. dorsata</i></p>
      <p><i>H. saltator</i></p>
      <p><i>N. vitripennis</i></p>
      <p><i>L. humile</i></p>
      <p><i>P. barbatus</i></p>
      <p><i>A. cephalotes</i></p>
      <p><i>A. echinatior</i></p>
      <p><i>S. invicta</i></p>
      <p><i>C. floridanus</i></p>
      <p><i>B. impatiens</i></p>
      <p><i>B. terrestris</i></p>


   </td>
    <td> Gene versus publications</td>
    <td> <a href="http://www.ncbi.nlm.nih.gov" target="_new" class="extlink">NCBI</a> - June 2014</td>
    <td> &nbsp;</td>
    <td> &nbsp;</td>
  </tr>
<!--   <tr>
    <td>
       <p><i>Drosophila</i></p>
       <p><i>C. elegans</i></p>
    </td>
    <td> Gene versus publications</td>
    <td> <a href="http://www.ncbi.nlm.nih.gov" target="_new" class="extlink">NCBI</a> - June 2014</td>
    <td> &nbsp;</td>
    <td> &nbsp;</td>
  </tr> -->

</table>

<div class="body">
<ol>
  <li><a name="note1">${note1}</a></li>
<!--   <li><a name="note2">${note2}</a></li>
  <li><a name="note3">${note3}</a></li> -->
</ol>
</div>

</div>
<!-- /dataCategories -->

