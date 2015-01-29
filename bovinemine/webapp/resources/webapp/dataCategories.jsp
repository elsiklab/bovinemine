<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im"%>


<!-- dataCategories -->

<c:set var="note1" value="Annotations from OGS v2.0 (predicted on Btau_4.0 assembly) has been mapped to UMD3.1 chromosome assembly using UCSC liftOver."/>
<!-- <c:set var="note2" value="Also orthologues from these organisms to <i>C. familiaris</i>, <i>D. discoideum</i>, <i>D. rerio</i>, <i>G. gallus</i>, <i>H. sapiens</i>, <i>M. musculus</i>, <i>P. troglodytes</i>, <i>R. norvegicus</i>, <i>S. pombe</i>." />
<c:set var="note3" value="These data have been re-mapped to genome sequence release 5.0 as of FlyMine release 7.0."/> -->


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
    <td><i>B. taurus</i></td>
    <td>RefSeq Genome Annotation for <i>Bos taurus</i> UMD3.1 chromosome assembly</td>
    <td><a href="http://www.ncbi.nlm.nih.gov/assembly/GCF_000003055.4" target="_new" class="extlink">NCBI</a></td>
    <td>Salzberg et al - <a href="http://genomebiology.com/content/10/4/R42 " target="_new" class="extlink">PubMed: 19393038</a></td>
    <td> &nbsp; </td>
  </tr>

  <tr>
    <td>
       <p><i>B. taurus</i></p>
    </td>
     <td>Official Gene Set v2.0 annotation for Bos taurus UMD3.1 chromosome assembly</td>
    <td><a href="http://bovinegenome.org/?q=node/49" target="_new" class="extlink">Bovine Genome Database</a></td>
    <td></td>
    <!-- <td>Ruan et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/18056084 " target="_new" class="extlink">PubMed: 18056084</a></td> -->
    <td> <html:link action="/dataCategories" anchor="note1" title="${note1}">#1</html:link></td>
  </tr>
  
  <tr>
    <td>
       <p><i>B. taurus</i></p>
    </td>
     <td>Ensembl Annotation (rev. 77) for Bos taurus UMD3.1 chromosome assembly</td>
    <td><a href="http://useast.ensembl.org/Bos_taurus/Info/Index" target="_new" class="extlink">Ensembl</a> release 77</td>
    <!-- <td>Mi et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/23193289 " target="_new" class="extlink">PubMed: 23193289</a></td> -->
    <td> &nbsp; </td>
    <td> &nbsp; </td>
  </tr>
  
  </tr>
  
  <tr>
    <td rowspan="4"  class="leftcol"><p><h2>Homology</h2></p></td>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>H. sapiens</i></p>
      <p><i>S. scrofa</i></p>
    </td>
    <td>Orthologues and paralogues between <i>Bos taurus</i>, <i>Homo sapiens</i>, <i>Sus scrofa</i>, ... </td>
    <td><a href="http://orthodb.org/orthodb7" target="_new" class="extlink">OrthoDB</a> - Version 7</td>
    <td>Kriventseva EV et al. - <a href="http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3531149/ " target="_new" class="extlink">PubMed: 23180791</a></td>
    <td> &nbsp; </td>
  </tr>

  <tr>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>H. sapiens</i></p>
      <p><i>S. scrofa</i></p>
    </td>
     <td>Orthologue and paralogue relationships between <i>Bos taurus</i>, <i>Homo sapiens</i>, <i>Sus scrofa</i>, ... </td>
    <td><a href="http://www.treefam.org/" target="_new" class="extlink">Treefam</a> - release 8.0</td>
    <td>Ruan et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/18056084 " target="_new" class="extlink">PubMed: 18056084</a></td>
    <td> &nbsp; </td>
  </tr>
  
  <tr>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>H. sapiens</i></p>
    </td>
     <td>Orthologue relationships between <i>Bos taurus</i> and <i>Homo sapiens</i></td>
    <td><a href="http://useast.ensembl.org/biomart/martview/" target="_new" class="extlink">Ensembl BioMart</a></td>
    <!-- <td>Mi et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/23193289 " target="_new" class="extlink">PubMed: 23193289</a></td> -->
    <td> &nbsp; </td>
    <td> &nbsp; </td>
  </tr>

  <tr>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>H. sapiens</i></p>
    </td>
     <td>Homologs between <i>Bos taurus</i> and <i>Homo sapiens</i></td>
    <td><a href="http://www.ncbi.nlm.nih.gov/homologene" target="_new" class="extlink">HomoloGene</a></td>
    <!-- <td>Mi et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/23193289 " target="_new" class="extlink">PubMed: 23193289</a></td> -->
    <td> &nbsp; </td>
    <td> &nbsp; </td>
  </tr>

  <tr>
    <td rowspan="3"  class="leftcol"><p><h2>Proteins</h2></p></td>
    <td>
        <p><i>B. taurus</i></p>
    </td>
    <td> Protein annotation</td>
    <td> <a href="http://www.ebi.uniprot.org/index.shtml" target="_new" class="extlink">UniProt</a> - Release 2014_10</td>
    <td> UniProt Consortium - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17142230" target="_new" class="extlink">PubMed: 17142230</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td>
        <p><i>B. taurus</i></p>
    </td>
    <td> Protein family and domain assignments to proteins</td>
    <td> <a href="http://www.ebi.ac.uk/interpro" target="_new" class="extlink">InterPro</a>Version 48.0 - July 2014 </td>
    <td> Mulder et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17202162" target="_new" class="extlink">PubMed: 17202162</a></td>
    <td> &nbsp;</td>
  </tr>
  <tr>
    <td> <i>B. taurus</i> </td>
    <td> Experimentally determined 3-D structures</td>
    <td> <a href="http://www.rcsb.org/pdb/home/home.do" target="_new" class="extlink">PDB [Protein Data Bank]</a> - Nov 2014</td>
    <td> &nbsp;</td>
    <td> &nbsp;</td>
  </tr>
<!--   <tr>
    <td rowspan="3"  class="leftcol"><p><h2>Interactions</h2></p></td>
    <td>
        <p><i>D. melanogaster</i></p>
        <p><i>C. elegans</i></p>
        <p><i>S. cerevisiae</i></p>
    </td>
    <td> High-throughput yeast 2-hybrid protein interaction datasets </td>
    <td> <a href="http://www.ebi.ac.uk/intact" target="_new" class="extlink">IntAct</a>Release 181 - June 2014</td>
    <td> Kerrien et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17145710" target="_new" class="extlink">PubMed:17145710</a></td>
    <td> &nbsp;</td>
  </tr> -->

 <tr>
    <td rowspan="1"  class="leftcol"><p><h2>Interactions</h2></p></td>
    <td>
       <p><i>B. taurus</i></p>
    </td>
    <td> Interactions from the BioGRID</td>
    <td> <a href="http://www.thebiogrid.org" target="_new" class="extlink">BioGRID</a> - Version 3.2.119 </td>
    <td> Stark et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/16381927" target="_new" class="extlink">PubMed:16381927</a></td>
    <td> &nbsp;</td>
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
    <td> <i>B. taurus</i> </td>
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
    <td rowspan="1" class="leftcol"><p> <h2>Pathways</h2></p></td>
    <td> <p><i>B. taurus</i></p></td>
    <td> Pathway information and the genes involved in them, inferred through orthologues from Human curated pathways</td>
    <td> <a href="http://www.reactome.org/" target="_new" class="extlink">Reactome</a> - version 50, Oct 2014</td>
    <td> &nbsp;</td>
    <td>&nbsp;</td>
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
    <td> <i>B. taurus</i> </td>
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
