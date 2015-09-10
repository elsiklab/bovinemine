<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im"%>


<!-- dataCategories -->
<c:set var="note1" value="Only genes that have been mapped to the genome have been loaded"/>
<c:set var="note2" value="Also orthologues from these organisms to <i>C. familiaris</i>, <i>D. discoideum</i>, <i>D. rerio</i>, <i>G. gallus</i>, <i>H. sapiens</i>, <i>M. musculus</i>, <i>P. troglodytes</i>, <i>R. norvegicus</i>, <i>S. pombe</i>." />
<c:set var="note3" value="These data have been re-mapped to genome sequence release 5.0 as of FlyMine release 7.0."/>

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
  <tr><td rowspan="3" class="leftcol">
    <h2><p>Genes</p></h2></td>
    <td> <i>Bos taurus</i> </td>
    <td>NCBI Annotations</td>
    <td>NCBI Annotation Release 104</td>
    <td><a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=24259432>24259432</a> <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25355515>24259432</a></td>
    <td> </td>
  </tr>
  <tr>
    <td> <i>Bos taurus</i> </td>
    <td> Official Gene Set v2.0  </td>
    <td> Bovine Genome Database</td>
    <td><a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=19390049>19390049</a> <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=21092105>21092105</a></td>
    <td></td>
  </tr>
  <tr>
    <td> <i> Bos taurus</i></td>
    <td> Ensembl annotations </td>
    <td>Ensembl Release 79</td>
    <td><a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25352552>25352552</a></td>
    <td></td>
  </tr>



  <tr>
    <td rowspan="4"  class="leftcol"><p><h2>Homology</h2></p></td>
    <td>
       <p><i>B. taurus</i></p>
       <p><i>C. canis familiaris</i></p>
       <p><i>E. caballus</i></p>
       <p><i>H. sapien</i></p>
       <p><i>M. musculus</i></p>
       <p><i>S. scrofa</i></p>
    </td>
     <td>Orthologue and paralogue relationships</td>
    <td>OrthoDB - Version 7</td>
    <td><a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25428351>25428351</a></td>
    <td></td>
  </tr>

  <tr>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>C. canis familiaris</i></p>
      <p><i>E. caballus</i></p>
      <p><i>H. sapien</i></p>
      <p><i>M. musculus</i></p>
      <p><i>S. scrofa</i></p>
    </td>
    <td>Orthologue and paralogue relationships</td>
    <td>Treefam - release 8.0</td>
    <td><a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=24194607>24194607</a></td>
    <td> &nbsp; </td>
  </tr>
  <tr>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>C. canis familiaris</i></p>
      <p><i>E. caballus</i></p>
      <p><i>H. sapien</i></p>
      <p><i>M. musculus</i></p>
      <p><i>S. scrofa</i></p>
    </td>
    <td>Orthologue and paralogue relationships</td>
    <td>Ensembl Compara</td>
    <td> <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=19029536>19029536</a> </td>
    <td> &nbsp; </td>
  </tr>
  <tr>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>C. canis familiaris</i></p>
      <p><i>H. sapien</i></p>
      <p><i>M. musculus</i></p>
      <p><i>S. scrofa</i></p>
    </td>
    <td>Orthologue and paralogue relationships</td>
    <td>HomoloGene build 68</td>
    <td><a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25398906>25398906</a></td>
    <td> &nbsp; </td>
  </tr>

  <tr>
    <td rowspan="2"  class="leftcol"><p><h2>Proteins</h2></p></td>
    <td>
        <p><i>H. sapien</i></p>
        <p><i>M. musculus</i></p>
        <p><i>B. taurus</i></p>
    </td>
    <td> Protein annotation</td>
    <td> UniProt - Release 2015_08</td>
    <td> <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25348405>25348405</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td>
        <p><i>B. taurus</i></p>
    </td>
    <td> Protein family and domain assignments to proteins</td>
    <td> InterProVersion 53.0  </td>
    <td> <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25428371>25428371</a></td>
    <td> &nbsp;</td>
  </tr>
  <tr>
    <td class="leftcol"><p><h2>Interactions</h2></p></td>
    <td>
        <p><i>B. taurus</i></p>
        <p><i>H. sapien</i></p>
        <p><i>M. musculus</i></p>
    </td>
    <td> Interactions from BioGRID </td>
    <td> <a href="http://www.thebiogrid.org" target="_new" class="extlink">BioGRID</a> - Version 3.4.128 </td>
    <td> <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25428363>25428363</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td class="leftcol"><p> <h2>Gene Ontology</h2></p></td>
    <td> <i>B. taurus</i> </td>
    <td> GO annotations </td>
    <td> <a href="http://www.geneontology.org" target="_new" class="extlink">Gene Ontology Site</a> - Sep 2014</td>
    <td> <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25378336>25378336</a> <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25428369>25428369</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td class="leftcol"><p><h2>Pathways</h2></p></td>
    <td>
      <p><i>H. sapien</i></p>
      <p><i>M. musculus</i></p>
      <p><i>B. taurus</i></p>
    </td>
    <td> Pathway information and the genes involved in them, inferred through orthologues from curated human pathways</td>
    <td> Reactome - version 50, Oct 2014</td>
    <td> <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=24243840>24243840</a></td>
    <td> &nbsp;</td>
  </tr>
  <tr>
    <td class="leftcol"><p><h2>Publications</h2></p></td>
    <td> <i>B. taurus</i> </td>
    <td> A mapping from genes to publications using NCBI resources</td>
    <td> NCBI PubMed</td>
    <td><a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25398906>25398906</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td class="leftcol" rowspan="2"><p><h2>Variation</h2></p></td>
    <td>
      <i>B. taurus</i>
    </td>
    <td>QTL data for Bos taurus</td>
    <td> AnimalQTLdb release 25 (Feb 2015)</td>
    <td> <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=17135205>17135205</a></td>
    <td></td>
  </tr>
  <tr>
    <td><i>B. taurus</i></td>
    <td>dbSNP data for Bos taurus</td>
    <td> dbSNP v143</td>
    <td> <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=11125122>11125122</a></td>
    <td></td>
  </tr>

  <tr>
    <td class="leftcol"><p><h2>Gene expression</h2></p></td>
    <td><p>B. taurus</p></td>
    <td>RNA-seq gene expression data from 92 tissues</td>
    <td> NCBI SRA  <a href=http://www.ncbi.nlm.nih.gov/sra?term=SRP049415>link</a></td>
    <td> <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=22009675>22009675</a></td>
    <td> </td>
  </tr>

</table>

<div class="body">
</div>

</div>
<!-- /dataCategories -->

