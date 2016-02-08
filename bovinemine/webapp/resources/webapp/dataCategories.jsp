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
    <td>
        <p><i>B. taurus</i></p>
        <p><i>C. hircus</i></p>
        <p><i>O. aries</i></p>
    </td>
    <td>NCBI Genes (RefSeq)</td>
    <td>NCBI Annotation Release 104</td>
    <td><p>Pruitt et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=24259432>24259432</a></p></td>
    <td> <a href="ftp://ftp.ncbi.nlm.nih.gov/genomes/">NCBI FTP</a></td>
  </tr>
  <tr>
    <td> <i>B. taurus</i> </td>
    <td> Official Gene Set v2.0  </td>
    <td> Bovine Genome Database</td>
    <td><p>Bovine Genome Sequencing and Analysis Consortium - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=19390049>19390049</a></p><p>Reese et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=21092105>21092105</a></p></td>
    <td><a href="http://bovinegenome.org/?q=node/61">BGD Download</a></td>
  </tr>
  <tr>
    <td>
        <p><i>B. taurus</i></p>
        <p><i>O. aries</i></p>
    </td>
    <td> Ensembl Genes </td>
    <td>Ensembl Release 82</td>
    <td>Cunningham et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25352552>25352552</a></td>
    <td><a href="ftp://ftp.ensembl.org/pub/release-82/gtf/bos_taurus/">Ensembl Download</a></td>
  </tr>



  <tr>
    <td rowspan="4"  class="leftcol"><p><h2>Homology</h2></p></td>
    <td>
       <p><i>B. taurus</i></p>
       <p><i>C. canis familiaris</i></p>
       <p><i>E. caballus</i></p>
       <p><i>H. sapien</i></p>
       <p><i>M. musculus</i></p>
       <p><i>R. norvegicus</i></p>
       <p><i>S. scrofa</i></p>
       <p><i>C. hircus</i></p>
       <p><i>O. aries</i></p>
    </td>
     <td>Orthologue and paralogue relationships</td>
    <td>OrthoDB - Version 8</td>
    <td>Kriventseva et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25428351>25428351</a></td>
    <td><a href="ftp://cegg.unige.ch/OrthoDB8/">OrthoDB Download</a></td>
  </tr>

  <tr>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>C. canis familiaris</i></p>
      <p><i>E. caballus</i></p>
      <p><i>H. sapien</i></p>
      <p><i>M. musculus</i></p>
      <p><i>R. norvegicus</i></p>
      <p><i>S. scrofa</i></p>
      <p><i>C. hircus</i></p>
      <p><i>O. aries</i></p>
    </td>
    <td>Orthologue and paralogue relationships</td>
    <td>Treefam - release 8.0</td>
    <td>Schreiber et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=24194607>24194607</a></td>
    <td><a href="ftp://ftp.ebi.ac.uk/pub/databases/ensembl/muffato/treefam/release-8.0/MySQL/">EBI FTP</a></td>
  </tr>
  <tr>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>C. canis familiaris</i></p>
      <p><i>E. caballus</i></p>
      <p><i>H. sapien</i></p>
      <p><i>M. musculus</i></p>
      <p><i>R. norvegicus</i></p>
      <p><i>S. scrofa</i></p>
      <p><i>C. hircus</i></p>
      <p><i>O. aries</i></p>
    </td>
    <td>Orthologue and paralogue relationships</td>
    <td>Ensembl Compara</td>
    <td>Vilella et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=19029536>19029536</a> </td>
    <td><a href="http://useast.ensembl.org/biomart/martview/">Ensembl BioMart<a/></td>
  </tr>
  <tr>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>C. canis familiaris</i></p>
      <p><i>H. sapien</i></p>
      <p><i>M. musculus</i></p>
      <p><i>R. norvegicus</i></p>
      <p><i>S. scrofa</i></p>
      <p><i>C. hircus</i></p>
      <p><i>O. aries</i></p>
    </td>
    <td>Orthologue and paralogue relationships</td>
    <td>HomoloGene build 68</td>
    <td>NCBI Resource Coordinators - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25398906>25398906</a></td>
    <td><a href="ftp://ftp.ncbi.nih.gov/pub/HomoloGene/build68/">NCBI FTP</a></td>
  </tr>

  <tr>
    <td rowspan="2"  class="leftcol"><p><h2>Proteins</h2></p></td>
    <td>
        <p><i>H. sapien</i></p>
        <p><i>M. musculus</i></p>
        <p><i>R. norvegicus</i></p>
        <p><i>B. taurus</i></p>
        <p><i>C. hircus</i></p>
        <p><i>O. aries</i></p>
    </td>
    <td> Protein annotation</td>
    <td> UniProt - Release 2015_12</td>
    <td> UniProt Consortium - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25348405>25348405</a></td>
    <td> <a href=ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/">UniProt FTP</a></td>
  </tr>

  <tr>
    <td>
        <p><i>H. sapien</i></p>
        <p><i>M. musculus</i></p>
        <p><i>R. norvegicus</i></p>
        <p><i>B. taurus</i></p>
        <p><i>C. hircus</i></p>
        <p><i>O. aries</i></p>
    </td>
    <td> Protein domains</td>
    <td> InterPro Version 55.0  </td>
    <td> Mitchell et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25428371>25428371</a></td>
    <td> <a href="ftp://ftp.ebi.ac.uk/pub/databases/interpro/55.0/">InterPro FTP</a></td>
  </tr>
  <tr>
     <td rowspan="2"  class="leftcol"><p><h2>Interactions</h2></p></td>
    <td>
        <p><i>B. taurus</i></p>
        <p><i>H. sapien</i></p>
        <p><i>M. musculus</i></p>
        <p><i>R. norvegicus</i></p>
        <p><i>C. hircus</i></p>
        <p><i>O. aries</i></p>
    </td>
    <td> Interactions</td>
    <td> BioGRID - Version 3.4.132 </td>
    <td> Chatr-Aryamontri et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25428363>25428363</a></td>
    <td> <a href="http://thebiogrid.org/download.php">BioGRID Download</a></td>
  </tr>

     <tr>
    <td>
        <p><i>H. sapien</i></p>
        <p><i>M. musculus</i></p>
        <p><i>R. norvegicus</i></p>
        <p><i>B. taurus</i></p>
        <p><i>C. hircus</i></p>
        <p><i>O. aries</i></p>
    </td>
    <td> Interactions</td>
    <td> IntAct Version 4.2.3.2 </td>
    <td> Hermjakob H et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/14681455>14681455</a></td>
    <td> <a href="ftp://ftp.ebi.ac.uk/pub/databases/IntAct/current/">IntAct FTP</a></td>
  </tr> 
   
  <tr>
    <td class="leftcol"><p> <h2>Gene Ontology</h2></p></td>
    <td>
        <p><i>B. taurus</i></p>
        <p><i>H. sapien</i></p>
        <p><i>M. musculus</i></p>
        <p><i>R. norvegicus</i></p>
        <p><i>C. hircus</i></p>
        <p><i>O. aries</i></p>
    </td>
    <td> GO annotations </td>
    <td> GOA at UniProt (GOC Validation Date: 11/11/2015)</td>
    <td> <p>Huntley et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25378336>25378336</a></p><p>Gene Ontology Consortium - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25428369>25428369</a></p></td>
    <td> <a href="http://geneontology.org/page/download-annotation">GO Consortium Annotation Download</a></td>
  </tr>

  <tr>
    <td class="leftcol" rowspan="3"><p><h2>Pathways</h2></p></td>
    <td>
      <p><i>H. sapien</i></p>
      <p><i>M. musculus</i></p>
      <p><i>B. taurus</i></p>
      <p><i>R. norvegicus</i></p>
      <p><i>C. hircus</i></p>
      <p><i>O. aries</i></p>
    </td>
    <td> Pathway information inferred through orthologues from curated human pathways</td>
    <td> Reactome - version 53, July 2015</td>
    <td> Croft et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=24243840>24243840</a></td>
    <td> <a href="http://www.reactome.org/download/current/">Reactome Download</a></td>
  </tr>
  <tr>
    <td>
      <p><i>H. sapien</i></p>
      <p><i>M. musculus</i></p>
    </td>
    <td>Pathway information</td>
    <td>BioCyc - Version 19.5</td>
    <td>Caspi et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/26527732">26527732</a></td>
    <td><a href="http://biocyc.org/">BioCyc</a>
  </tr>
    <tr>
    <td>
      <p><i>S. scrofa</i></p>
      <p><i>H. sapien</i></p>
      <p><i>M. musculus</i></p>
      <p><i>B. taurus</i></p>
      <p><i>R. norvegicus</i></p>
      <p><i>C. canis familiaris</i></p>
      <p><i>E. caballus</i></p>
    </td>
    <td>Pathway information</td>
    <td>Kegg</td>
    <td>Kanehisa M et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/10592173">10592173</a></td>
    <td><a href="http://www.genome.jp/kegg/">Kegg</a>
  </tr>




  <tr>
    <td class="leftcol"><p><h2>Publications</h2></p></td>
    <td> <i>B. taurus</i> </td>
    <td> A mapping from genes to publications</td>
    <td> NCBI PubMed</td>
    <td>NCBI Resource Coordinators - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25398906>25398906</a></td>
    <td> <a href="ftp://ftp.ncbi.nlm.nih.gov/gene/DATA/">NCBI FTP</a></td>
  </tr>

  <tr>
    <td class="leftcol" rowspan="3"><p><h2>Variation</h2></p></td>
    <td>
      <i>B. taurus</i>
    </td>
    <td>QTL data for Bos taurus</td>
    <td> AnimalQTLdb release 25 (Feb 2015)</td>
    <td>Hu et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=17135205>17135205</a></td>
    <td><a href=http://www.animalgenome.org/cgi-bin/QTLdb/BT/index>CattleQTLdb Download</a></td>
  </tr>
  <tr>
    <td><i>B. taurus</i></td>
    <td>dbSNP data for Bos taurus</td>
    <td> dbSNP v143</td>
    <td> Sherry et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=11125122>11125122</a></td>
    <td><a href="ftp://ftp.ncbi.nih.gov/snp/organisms/cow_9913/VCF/">NCBI FTP</a></td>
  </tr>
  <tr>
      <td><i>B. taurus</i></td>
      <td>dbVar data for Bos taurus</td>
      <td> dbVar (Revision January 30, 2015)</td>
      <td> Church et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3531204/>23193291</a></td>
      <td><a href="ftp://ftp.ncbi.nlm.nih.gov/pub/dbVar/data/Bos_taurus/">NCBI FTP</a></td>
  </tr>
  <tr>
    <td class="leftcol" rowspan="1"><p><h2>Variant Annotations</h2></p></td>
    <td><i>B. taurus</i></td>
    <td>Variant effects predicted on RefSeq and Ensembl using Ensembl Variant Effect Predictor</td>
    <td>Ensembl VEP (Release 82)</td>
    <td>McLaren et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=20562413">20562413</a></td>
    <td><a href="http://useast.ensembl.org/info/docs/tools/vep/index.html">Ensembl VEP</a></td>
  </tr>
  <tr>
    <td class="leftcol"><p><h2>Gene expression</h2></p></td>
    <td><p>B. taurus</p></td>
    <td>RNA-seq gene expression data from L1 Dominette 01449 and her relatives</td>
    <td> NCBI SRA </td>
    <td> Kodama et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=22009675>22009675</a></td>
    <td> <a href=http://www.ncbi.nlm.nih.gov/sra?term=SRP049415>NCBI SRA Project Page</a></td>
  </tr>

  <tr>
    <td class="leftcol"><p><h2>Assembly</h2></p></td>
    <td><p>B. taurus</p></td>
    <td>Chromosome Assembly</td>
    <td>UMD3.1.1</td>
    <td>Zimin et al - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=19393038>19393038</a></td>
    <td><a href="ftp://ftp.ncbi.nlm.nih.gov/genomes/Bos_taurus/">NCBI FTP</a></td>
  </tr>



</table>

<div class="body">
</div>

</div>
<!-- /dataCategories -->

