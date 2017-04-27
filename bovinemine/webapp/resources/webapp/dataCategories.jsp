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
    <th>Link</th>
  </tr>
  <tr><td rowspan="3" class="leftcol">
    <h2><p>Genes</p></h2></td>
    <td>
        <p><i>B. taurus</i></p>
        <p><i>C. hircus</i></p>
        <p><i>O. aries</i></p>
    </td>
    <td>NCBI Genes (RefSeq)</td>
    <td>NCBI Annotation Release 105</td>
    <td><p>Pruitt et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=24259432">24259432</a></p></td>
    <td> <a href="ftp://ftp.ncbi.nlm.nih.gov/genomes/">NCBI FTP</a></td>
  </tr>
  <tr>
    <td> <i>B. taurus</i> </td>
    <td> Official Gene Set v2.0  </td>
    <td> Bovine Genome Database</td>
    <td><p>Bovine Genome Sequencing and Analysis Consortium - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=19390049">19390049</a></p><p>Reese et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=21092105">21092105</a></p></td>
    <td><a href="http://bovinegenome.org/?q=node/61">BGD Download</a></td>
  </tr>
  <tr>
    <td>
        <p><i>B. taurus</i></p>
        <p><i>O. aries</i></p>
    </td>
    <td> Ensembl Genes </td>
    <td>Ensembl Release 84</td>
    <td>Cunningham et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=25352552">25352552</a></td>
    <td><a href="ftp://ftp.ensembl.org/pub/release-84/gtf/bos_taurus/">Ensembl Download</a></td>
  </tr>



  <tr>
    <td rowspan="4"  class="leftcol"><p><h2>Homology</h2></p></td>
    <td>
       <p><i>B. taurus</i></p>
       <p><i>C. canis familiaris</i></p>
       <p><i>E. caballus</i></p>
       <p><i>H. sapiens</i></p>
       <p><i>M. musculus</i></p>
       <p><i>R. norvegicus</i></p>
       <p><i>S. scrofa</i></p>
       <p><i>C. hircus</i></p>
       <p><i>O. aries</i></p>
    </td>
     <td>Homologous relationships</td>
    <td>OrthoDB - Version 9</td>
    <td>Kriventseva et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=25428351">25428351</a></td>
    <td><a href="ftp://cegg.unige.ch/OrthoDB8/">OrthoDB Download</a></td>
  </tr>

  <tr>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>C. canis familiaris</i></p>
      <p><i>E. caballus</i></p>
      <p><i>H. sapiens</i></p>
      <p><i>M. musculus</i></p>
      <p><i>R. norvegicus</i></p>
      <p><i>S. scrofa</i></p>
      <p><i>O. aries</i></p>
    </td>
    <td>Homologous relationships</td>
    <td>Treefam - release 8.0</td>
    <td>Schreiber et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=24194607">24194607</a></td>
    <td><a href="ftp://ftp.ebi.ac.uk/pub/databases/ensembl/muffato/treefam/release-8.0/MySQL/">EBI FTP</a></td>
  </tr>
  <tr>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>C. canis familiaris</i></p>
      <p><i>E. caballus</i></p>
      <p><i>H. sapiens</i></p>
      <p><i>M. musculus</i></p>
      <p><i>R. norvegicus</i></p>
      <p><i>S. scrofa</i></p>
      <p><i>O. aries</i></p>
    </td>
    <td>Homologous relationships</td>
    <td>Ensembl Compara</td>
    <td>Vilella et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=19029536">19029536</a> </td>
    <td><a href="http://useast.ensembl.org/biomart/martview/">Ensembl BioMart<a/></td>
  </tr>
  <tr>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>C. canis familiaris</i></p>
      <p><i>H. sapiens</i></p>
      <p><i>M. musculus</i></p>
      <p><i>R. norvegicus</i></p>
      <p><i>S. scrofa</i></p>
      <p><i>O. aries</i></p>
    </td>
    <td>Homologous relationships</td>
    <td>HomoloGene build 68</td>
    <td>NCBI Resource Coordinators - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=25398906">25398906</a></td>
    <td><a href="ftp://ftp.ncbi.nih.gov/pub/HomoloGene/build68/">NCBI FTP</a></td>
  </tr>

  <tr>
    <td rowspan="2"  class="leftcol"><p><h2>Proteins</h2></p></td>
    <td>
        <p><i>H. sapiens</i></p>
        <p><i>M. musculus</i></p>
        <p><i>R. norvegicus</i></p>
        <p><i>B. taurus</i></p>
        <p><i>C. hircus</i></p>
        <p><i>O. aries</i></p>
    </td>
    <td> Protein annotation</td>
    <td> UniProt - Release 2016_12</td>
    <td> UniProt Consortium - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=25348405">25348405</a></td>
    <td> <a href="ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/">UniProt FTP</a></td>
  </tr>

  <tr>
    <td>
        <p><i>H. sapiens</i></p>
        <p><i>M. musculus</i></p>
        <p><i>R. norvegicus</i></p>
        <p><i>B. taurus</i></p>
        <p><i>C. hircus</i></p>
        <p><i>O. aries</i></p>
    </td>
    <td> Protein domains</td>
    <td> InterPro Version 60  </td>
    <td> Mitchell et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=25428371">25428371</a></td>
    <td> <a href="ftp://ftp.ebi.ac.uk/pub/databases/interpro/55.0/">InterPro FTP</a></td>
  </tr>
  <tr>
     <td rowspan="2"  class="leftcol"><p><h2>Interactions</h2></p></td>
    <td>
        <p><i>B. taurus</i></p>
        <p><i>H. sapiens</i></p>
        <p><i>M. musculus</i></p>
        <p><i>R. norvegicus</i></p>
        <p><i>C. hircus</i></p>
    </td>
    <td> Interactions</td>
    <td> BioGRID - Version 3.4.144 </td>
    <td> Chatr-Aryamontri et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=25428363">25428363</a></td>
    <td> <a href="http://thebiogrid.org/download.php">BioGRID Download</a></td>
  </tr>

     <tr>
    <td>
        <p><i>H. sapiens</i></p>
        <p><i>M. musculus</i></p>
        <p><i>R. norvegicus</i></p>
        <p><i>B. taurus</i></p>
        <p><i>O. aries</i></p>
    </td>
    <td> Interactions</td>
    <td> IntAct Release 203 </td>
    <td> Hermjakob H et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/14681455">14681455</a></td>
    <td> <a href="ftp://ftp.ebi.ac.uk/pub/databases/IntAct/current/">IntAct FTP</a></td>
  </tr> 
   
  <tr>
    <td class="leftcol"><p> <h2>Gene Ontology</h2></p></td>
    <td>
        <p><i>B. taurus</i></p>
        <p><i>H. sapiens</i></p>
        <p><i>M. musculus</i></p>
        <p><i>R. norvegicus</i></p>
        <p><i>C. hircus</i></p>
        <p><i>O. aries</i></p>
    </td>
    <td> GO annotations </td>
    <td> GOA at UniProt (GOC Validation Date: 12/16/2016)</td>
    <td> <p>Huntley et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=25378336">25378336</a></p><p>Gene Ontology Consortium - PubMed <a href=http://www.ncbi.nlm.nih.gov/pubmed/?term=25428369>25428369</a></p></td>
    <td> <a href="http://geneontology.org/page/download-annotation">GO Consortium Annotation Download</a></td>
  </tr>

  <tr>
    <td class="leftcol" rowspan="3"><p><h2>Pathways</h2></p></td>
    <td>
      <p><i>H. sapiens</i></p>
      <p><i>M. musculus</i></p>
      <p><i>B. taurus</i></p>
      <p><i>R. norvegicus</i></p>
    </td>
    <td> Pathway information inferred through orthologues from curated human pathways</td>
    <td> Reactome - version 59, Dec 2016</td>
    <td> Croft et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=24243840">24243840</a></td>
    <td> <a href="http://www.reactome.org/download/current/">Reactome Download</a></td>
  </tr>
  <tr>
    <td>
    <p><i>B. taurus</i></p>
      <p><i>H. sapiens</i></p>
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
      <p><i>H. sapiens</i></p>
      <p><i>M. musculus</i></p>
      <p><i>B. taurus</i></p>
      <p><i>R. norvegicus</i></p>
      <p><i>C. canis familiaris</i></p>
      <p><i>E. caballus</i></p>
    </td>
    <td>Pathway information</td>
    <td>KEGG</td>
    <td>Kanehisa M et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/10592173">10592173</a></td>
    <td><a href="http://www.genome.jp/kegg/">KEGG</a>
  </tr>
  <tr>
    <td class="leftcol"><p><h2>Publications</h2></p></td>
    <td> <i>B. taurus</i> </td>
    <td> A mapping from genes to publications</td>
    <td> NCBI PubMed</td>
    <td>NCBI Resource Coordinators - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=25398906">25398906</a></td>
    <td> <a href="ftp://ftp.ncbi.nlm.nih.gov/gene/DATA/">NCBI FTP</a></td>
  </tr>

  <tr>
    <td class="leftcol" rowspan="5"><p><h2>Variation</h2></p></td>
    <td>
      <i>B. taurus</i>
    </td>
    <td>QTL data for Bos taurus</td>
    <td> AnimalQTLdb release 30 (Dec 2016)</td>
    <td>Hu et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=17135205">17135205</a></td>
    <td><a href="http://www.animalgenome.org/cgi-bin/QTLdb/BT/index">CattleQTLdb Download</a></td>
  </tr>
  <tr>
    <td><i>B. taurus</i></td>
    <td>dbSNP data for Bos taurus</td>
    <td> dbSNP v146</td>
    <td> Sherry et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=11125122">11125122</a></td>
    <td><p><a href="ftp://ftp.ncbi.nih.gov/snp/organisms/cow_9913/VCF/">NCBI FTP</a></p><p><i> *SNP arrays and SNP NCBI IDs*</i></p></td>
  </tr>
  <tr>
    <td><i>B. taurus</i></td>
    <td>SNP Alias Identifiers</td>
    <td>SNPchiMp v.3</td>
    <td>Nicolazzi et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=25881165">25881165</a></td>
    <td><p><a href="http://bioinformatics.tecnoparco.org/SNPchimp/">SNPchiMp</a></p></td>
  </tr>
  <tr>
      <td><i>B. taurus</i></td>
      <td>dbVar data for Bos taurus</td>
      <td> dbVar (Revision January, 2016)</td>
      <td> Church et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3531204/">23193291</a></td>
      <td><a href="ftp://ftp.ncbi.nlm.nih.gov/pub/dbVar/data/Bos_taurus/">NCBI FTP</a></td>
  </tr>
    <tr>
      <td><i>B. taurus</i></td>
      <td>HapMap data for Bos taurus</td>
      <td>Bovine HapMap</td>
      <td> Bovine HapMap Consortium - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=19390050">19390050</a></td>
      <td></td>
    </tr>
  <tr>
    <td class="leftcol" rowspan="1"><p><h2>Variant Annotations</h2></p></td>
    <td><i>B. taurus</i></td>
    <td>Variant effects predicted on RefSeq and Ensembl using Ensembl Variant Effect Predictor</td>
    <td>Bovine Genome Database</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td class="leftcol"><p><h2>Gene expression</h2></p></td>
    <td><p>B. taurus</p></td>
    <td>RNA-seq gene expression data from L1 Dominette 01449 and her relatives</td>
    <td> NCBI SRA </td>
    <td> Kodama et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=22009675">22009675</a></td>
    <td> <a href="http://www.ncbi.nlm.nih.gov/sra?term=SRP049415">NCBI SRA Project Page</a></td>
  </tr>
    <tr>
      <td class="leftcol" rowspan="4"><p><h2>Assembly</h2></p></td>
      <td><i>B. taurus</i></td>
      <td>Chromosome Assembly</td>
      <td>UMD3.1.1</td>
      <td>Zimin et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=19393038">19393038</a></td>
      <td><a href="ftp://ftp.ncbi.nlm.nih.gov/genomes/Bos_taurus/">NCBI FTP</a></td>
    </tr>
    <tr>
      <td><i>C. hircus</i></td>
      <td>Chromosome Assembly</td>
      <td>ARS1</td>
      <td>Bickhart et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=28263316">28263316</a></td>
      <td><a href="ftp://ftp.ncbi.nlm.nih.gov/genomes/Capra_hircus/">NCBI FTP</a></td>
    </tr>
    <tr>
      <td><i>O. aries</i></td>
      <td>Chromosome Assembly</td>
      <td>Oar_v3.1</td>
      <td>Jiang et al - PubMed <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=24904168">24904168</a></td>
      <td><a href="ftp://ftp.ncbi.nlm.nih.gov/genomes/Ovis_aries/">NCBI FTP</a></td>
    </tr>
    <tr>
</table>

<div class="body">
</div>

</div>
<!-- /dataCategories -->

