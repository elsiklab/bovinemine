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
        <tr><td rowspan="17" class="leftcol">
            <h2><p>Genes</p></h2></td>
            <td><i>B. taurus</i></td>
            <td>NCBI Genes (RefSeq)</td>
            <td>NCBI Bos taurus Annotation Release 106 (ARS-UCD1.2)</td>
            <td><p>O'Leary et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=26553804">26553804</a></p></td>
            <td> <a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Bos_taurus/latest_assembly_versions/GCF_002263795.1_ARS-UCD1.2">NCBI FTP</a></td>
        </tr>
            <td><i>B. taurus</i></td>
            <td>NCBI Genes (RefSeq)</td>
            <td>NCBI Bos taurus Annotation Release 105 (UMD3.1.1)</td>
            <td><p>O'Leary et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=26553804">26553804</a></p></td>
            <td> <a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Bos_taurus/all_assembly_versions/suppressed/GCF_000003055.6_Bos_taurus_UMD_3.1.1">NCBI FTP</a></td>
        </tr>
            <td><i>C. hircus</i></td>
            <td>NCBI Genes (RefSeq)</td>
            <td>NCBI Capra hircus Annotation Release 102 (ARS1)</td>
            <td><p>O'Leary et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=26553804">26553804</a></p></td>
            <td> <a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Capra_hircus/latest_assembly_versions/GCF_001704415.1_ARS1/">NCBI FTP</a></td>
        </tr>
            <td><i>O. aries</i></td>
            <td>NCBI Genes (RefSeq)</td>
            <td>NCBI Ovis aries Annotation Release 102 (Oar_v4.0)</td>
            <td><p>O'Leary et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=26553804">26553804</a></p></td>
            <td> <a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Ovis_aries/all_assembly_versions/suppressed/GCF_000298735.2_Oar_v4.0">NCBI FTP</a></td>
        </tr>
            <td><i>O. aries</i></td>
            <td>NCBI Genes (RefSeq)</td>
            <td>NCBI Ovis aries Annotation Release 101 (Oar_v3.1)</td>
            <td><p>O'Leary et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=26553804">26553804</a></p></td>
            <td> <a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Ovis_aries/all_assembly_versions/suppressed/GCF_000298735.1_Oar_v3.1">NCBI FTP</a></td>
        </tr>

	<tr>
	    <td><i>Homo sapiens</i></td>
            <td>NCBI Genes (RefSeq)</td>
            <td>NCBI Homo sapiens Annotation Release 109 (GRCh38.p12)</td>
            <td><p>O'Leary et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=26553804">26553804</a></p></td>
            <td> <a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Homo_sapiens/latest_assembly_versions/GCF_000001405.38_GRCh38.p12">NCBI FTP</a></td>
        </tr>
        <tr>
            <td><i>Mus musculus</i></td>
            <td>NCBI Genes (RefSeq)</td>
            <td>NCBI Mus musculus Annotation Release 106 (GRCm38.p6)</td>
            <td><p>O'Leary et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=26553804">26553804</a></p></td>
	   <td> <a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Mus_musculus/latest_assembly_versions/GCF_000001635.26_GRCm38.p6">NCBI FTP</a></td>
        </tr>
        <tr>
            <td><i>Rattus norvegicus</i></td>
            <td>NCBI Genes (RefSeq)</td>
            <td>NCBI Rattus norvegicus Annotation Release 106 (Rnor_6.0)</td>
            <td><p>O'Leary et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=26553804">26553804</a></p></td>
	    <td> <a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Rattus_norvegicus/latest_assembly_versions/GCF_000001895.5_Rnor_6.0">NCBI FTP</a></td>
        </tr>
        <tr>
            <td><i>Sus scrofa</i></td>
            <td>NCBI Genes (RefSeq)</td>
            <td>NCBI Sus scrofa Annotation Release 106 (Sscrofa11.1)</td>
            <td><p>O'Leary et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=26553804">26553804</a></p></td>
	    <td> <a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Sus_scrofa/latest_assembly_versions/GCF_000003025.6_Sscrofa11.1">NCBI FTP</a></td>
        </tr>
        <tr>
            <td> <i>B. taurus</i> </td>
            <td> Official Gene Set v2.0  (UMD3.1.1)</td>
            <td> Bovine Genome Database</td>
            <td><p>Bovine Genome Sequencing and Analysis Consortium - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=19390049">19390049</a></p><p>Reese et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=21092105">21092105</a></p></td>
            <td><a target="_blank" href="http://bovinegenome.org/?q=node/61">BGD Download</a></td>
        </tr>


	<tr>
            <td>
                <p><i>B. taurus</i></p>
            </td>
            <td> Ensembl Genes </td>
            <td><p>Ensembl Release 95</p><p>Ensembl Release 93</p></td>
            <td>Cunningham et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=30407521">30407521</a></td>
            <td><p><a target="_blank" href="ftp://ftp.ensembl.org/pub/release-95/gff3/bos_taurus/">Ensembl 95 Download</p><p><a target="_blank" href="ftp://ftp.ensembl.org/pub/release-93/gff3/bos_taurus/">Ensembl 93 Download</a></p></td>
        </tr>
        <tr>
            <td>
                <p><i>C. hircus</i></p>
            </td>
            <td> Ensembl Genes </td>
            <td>Ensembl Release 95</td>
            <td>Cunningham et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=30407521">30407521</a></td>
            <td><a target="_blank" href="ftp://ftp.ensembl.org/pub/release-95/gff3/capra_hircus/">Ensembl Download</a></td>
        </tr>
        <tr>
            <td>
                <p><i>O. aries</i></p>
            </td>
            <td> Ensembl Genes </td>
            <td>Ensembl Release 95</td>
            <td>Cunningham et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=30407521">30407521</a></td>
            <td><a target="_blank" href="ftp://ftp.ensembl.org/pub/release-95/gff3/ovis_aries/">Ensembl Download</a></td>
        </tr>

        <tr>
            <td>
                <p><i>Homo sapiens</i></p>
            </td>
            <td> Ensembl Genes </td>
            <td>Ensembl Release 95</td>
            <td>Cunningham et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=30407521">30407521</a></td>
            <td><a target="_blank" href="ftp://ftp.ensembl.org/pub/release-95/gff3/homo_sapiens/">Ensembl Download</a></td>
        </tr>
        <tr>
            <td>
                <p><i>Mus musculus</i></p>
            </td>
            <td> Ensembl Genes </td>
            <td>Ensembl Release 95</td>
            <td>Cunningham et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=30407521">30407521</a></td>
            <td><a target="_blank" href="ftp://ftp.ensembl.org/pub/release-95/gff3/mus_musculus/">Ensembl Download</a></td>
        </tr>
        <tr>
            <td>
                <p><i>Rattus norvegicus</i></p>
            </td>
            <td> Ensembl Genes </td>
            <td>Ensembl Release 95</td>
            <td>Cunningham et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=30407521">30407521</a></td>
            <td><a target="_blank" href="ftp://ftp.ensembl.org/pub/release-95/gff3/rattus_norvegicus/">Ensembl Download</a></td>
        </tr>
        <tr>
            <td>
                <p><i>Sus scrofa</i></p>
            </td>
            <td> Ensembl Genes </td>
            <td>Ensembl Release 95</td>
            <td>Cunningham et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=30407521">30407521</a></td>
            <td><a target="_blank" href="ftp://ftp.ensembl.org/pub/release-95/sus_scrofa/">Ensembl Download</a></td>
        </tr>



        <tr>
            <td rowspan="3" class="leftcol"><p><h2>Homology</h2></p></td>
            <td>
                <p><i>B. taurus</i></p>
                <p><i>C. familiaris</i></p>
                <p><i>E. caballus</i></p>
                <p><i>G. gallus</i></p>
        <p><i>H. sapiens</i></p>
                <p><i>M. musculus</i></p>
                <p><i>R. norvegicus</i></p>
                <p><i>S. scrofa</i></p>
            </td>
            <td>Orthologue and paralogue relationships</td>
            <td>Treefam - release 9.0</td>
            <td>Schreiber et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=24194607">24194607</a></td>
            <td><a target="_blank" href="http://www.treefam.org/download">TreeFam Download</a></td>
        </tr>
        <tr>
            <td>
                <p><i>B. taurus</i></p>
                <p><i>C. familiaris</i></p>
                <p><i>E. caballus</i></p>
                <p><i>H. sapiens</i></p>
                <p><i>M. musculus</i></p>
                <p><i>O. aries</i></p>
                <p><i>R. norvegicus</i></p>
                <p><i>S. scrofa</i></p>
            </td>
            <td>Orthologue and paralogue relationships</td>
            <td>Ensembl Compara</td>
            <td>Vilella et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=19029536">19029536</a> </td>
            <td><a target="_blank" href="http://useast.ensembl.org/biomart/martview/">Ensembl BioMart<a/></td>
        </tr>
        <tr>
            <td>
                <p><i>B. taurus</i></p>
                <p><i>H. sapiens</i></p>
                <p><i>M. musculus</i></p>
                <p><i>O. aries</i></p>
                <p><i>S. scrofa</i></p>
            </td>
            <td>Orthologue and paralogue relationships</td>
            <td>ORIS (Orthology Inference Using Synteny)</td>
            <td>Conant et al</td>
            <td></td>
        </tr>
        <tr>
            <td rowspan="2"  class="leftcol"><p><h2>Proteins</h2></p></td>
            <td>
                <p><i>B. taurus</i></p>
                <p><i>C. hircus</i></p>
                <p><i>H. sapiens</i></p>
                <p><i>M. musculus</i></p>
                <p><i>O. aries</i></p>
                <p><i>R. norvegicus</i></p>
            </td>
            <td> Protein annotation</td>
            <td> UniProt - Release 2018_09</td>
            <td> UniProt Consortium - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=30395287">30395287</a></td>
            <td> <a target="_blank" href="ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/">UniProt FTP</a></td>
        </tr>
        <tr>
            <td>
                <p><i>B. taurus</i></p>
                <p><i>C. hircus</i></p>
                <p><i>H. sapiens</i></p>
                <p><i>M. musculus</i></p>
                <p><i>O. aries</i></p>
                <p><i>R. norvegicus</i></p>
            </td>
            <td> Protein domains</td>
            <td> InterPro Version 69.0  </td>
            <td> Mitchell et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=30398656">30398656</a></td>
            <td> <a target="_blank" href="ftp://ftp.ebi.ac.uk/pub/databases/interpro/63.0/">InterPro FTP</a></td>
        </tr>
        <tr>
            <td rowspan="2"  class="leftcol"><p><h2>Interactions</h2></p></td>
            <td>
                <p><i>B. taurus</i></p>
                <p><i>H. sapiens</i></p>
                <p><i>M. musculus</i></p>
                <p><i>O. aries</i></p>
                <p><i>R. norvegicus</i></p>
            </td>
            <td> Interactions</td>
            <td> BioGRID - Version 3.4.164</td>
            <td> Oughtred et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=30476227">30476227</a></td>
            <td> <a target="_blank" href="http://thebiogrid.org/download.php">BioGRID Download</a></td>
        </tr>
        <tr>
            <td>
                <p><i>B. taurus</i></p>
                <p><i>C. hircus</i></p>
                <p><i>H. sapiens</i></p>
                <p><i>M. musculus</i></p>
                <p><i>O. aries</i></p>
                <p><i>R. norvegicus</i></p>
            </td>
            <td> Interactions</td>
            <td> IntAct 2018-09-07 release</td>
            <td> Hermjakob H et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/14681455">14681455</a></td>
            <td> <a target="_blank" href="ftp://ftp.ebi.ac.uk/pub/databases/IntAct/current/">IntAct FTP</a></td>
        </tr>
        <tr>
            <td rowspan="11" class="leftcol"><p> <h2>Ontologies</h2></p></td>
            <td></td>
            <td>Uber Anatomy Ontology (UBERON)</td>
            <td>Release Date: 04/25/2017</td>
            <td><p>Mungall et al - PubMed <a target="_blank" href="https://www.ncbi.nlm.nih.gov/pubmed/?term=22293552">22293552</a></p></td>
            <td><a target="_blank" href="https://bioportal.bioontology.org/ontologies/UBERON">UBERON</a></td>
        </tr>
        <tr>
            <td></td>
            <td>Mouse Adult Gross Anatomy Ontology</td>
            <td>Release Date: 10/11/2018</td>
            <td></td>
            <td><a target="_blank" href="https://bioportal.bioontology.org/ontologies/MA">MA</a></td>
        </tr>
        <tr>
            <td></td>
            <td>BRENDA Tissue Ontology</td>
            <td>Release Date: 10/11/2018</td>
            <td><p>Gremse et al - PubMed <a target="_blank" href="https://www.ncbi.nlm.nih.gov/pubmed/?term=21030441">21030441</a></p></td>
            <td><a target="_blank" href="https://bioportal.bioontology.org/ontologies/BTO">BTO</a></td>
        </tr>
        <tr>
            <td></td>
            <td>Clinical Measurement Ontology</td>
            <td>Release Date: 10/11/2018</td>
            <td><p>Shimoyama et al - PubMed <a target="_blank" href="https://www.ncbi.nlm.nih.gov/pubmed/22654893">22654893</a></p></td>
            <td><a target="_blank" href="https://bioportal.bioontology.org/ontologies/CMO">CMO</a></td>
        </tr>
        <tr>
            <td></td>
            <td>Livestock Breed Ontology</td>
            <td>Release Date: 10/11/2018</td>
            <td></td>
            <td><a target="_blank" href="https://bioportal.bioontology.org/ontologies/LBO">LBO</a></td>
        </tr>
        <tr>
            <td></td>
            <td>Livestock Product Trait Ontology</td>
            <td>Release Date: 10/11/2018</td>
            <td></td>
            <td><a target="_blank" href="https://bioportal.bioontology.org/ontologies/PTO">PTO</a></td>
        </tr>
        <tr>
            <td></td>
            <td>Vertebrate Trait Ontology</td>
            <td>Release Date: 10/11/2018</td>
            <td></td>
            <td><a target="_blank" href="https://bioportal.bioontology.org/ontologies/VT">VT</a></td>
        </tr>
        <tr>
            <td></td>
            <td>Evidence Ontology</td>
            <td>Release Date: 10/11/2018</td>
            <td><p>Chibucos et al - PubMed <a target="_blank" href="https://www.ncbi.nlm.nih.gov/pubmed/?term=25052702">25052702</a></p></td>
            <td><a target="_blank" href="https://bioportal.bioontology.org/ontologies/ECO">ECO</a></td>
        </tr>
        <tr>
            <td></td>
            <td>PSI MI Ontology</td>
            <td>Release Date: 10/11/2018</td>
            <td></td>
            <td><a target="_blank" href="http://www.obofoundry.org/ontology/mi.html">MI</a></td>
        </tr>
        <tr>
            <td></td>
            <td>Gene Ontology</td>
            <td>Release Date: 10/11/2018</td>
            <td><p>Gene Ontology Consortium - PubMed <a target="_blank" href="https://www.ncbi.nlm.nih.gov/pubmed/27899567">27899567</a></p></td>
            <td><a target="_blank" href="https://bioportal.bioontology.org/ontologies/GO">GO</a></td>
        </tr>
        <tr>
            <td></td>
            <td>Sequence Ontology</td>
            <td>Release Date: 10/11/2018</td>
            <td><p>Eilbeck et al - PubMed <a target="_blank" href="https://www.ncbi.nlm.nih.gov/pubmed/15892872">15892872</a></p></td>
            <td><a target="_blank" href="https://bioportal.bioontology.org/ontologies/SO">SO</a></td>
        </tr>
            <td class="leftcol"><p> <h2>Gene Ontology Annotation</h2></p></td>
            <td>
                <p><i>B. taurus</i></p>
                <p><i>C. hircus</i></p>
                <p><i>H. sapiens</i></p>
                <p><i>M. musculus</i></p>
                <p><i>O. aries</i></p>
                <p><i>R. norvegicus</i></p>
            </td>
            <td> Gene Ontology Annotation </td>
            <td> GOA at UniProt (GOC Validation Date: 2018-07-24)</td>
            <td> <p>Huntley et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=25378336">25378336</a></p><p>Gene Ontology Consortium - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=25428369">25428369</a></p></td>
            <td> <a target="_blank" href="http://www.geneontology.org/page/download-annotations">GO Consortium Annotation Download</a></td>
        </tr>


        <tr>
            <td class="leftcol" rowspan="3"><p><h2>Pathways and Metabolic Reactions</h2></p></td>
            <td>
                <p><i>B. taurus</i></p>
                <p><i>C. familiaris</i></p>
                <p><i>E. caballus</i></p>
                <p><i>H. sapiens</i></p>
                <p><i>M. musculus</i></p>
                <p><i>R. norvegicus</i></p>
                <p><i>S. scrofa</i></p>
            </td>
            <td>Pathway information</td>
            <td>KEGG</td>
            <td>Kanehisa M et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/27899662">27899662</a></td>
            <td><a target="_blank" href="http://www.genome.jp/kegg/">KEGG</a>
        </tr>
    <tr>
            <td>
                <p><i>B. taurus</i></p>
                <p><i>C. hircus</i></p>
                <p><i>H. sapiens</i></p>
                <p><i>M. musculus</i></p>
                <p><i>O. aries</i></p>
                <p><i>R. norvegicus</i></p>
            </td>
            <td> Pathway information inferred through orthologues from curated human pathways</td>
            <td> Reactome - version 65</td>
            <td> Rigden et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=29145629">29145629</a></td>
            <td> <a target="_blank" href="http://www.reactome.org/download/current/">Reactome Download</a></td>
        </tr>
        <tr>
            <td>
                <p><i>H. sapiens</i></p>
                <p><i>M. musculus</i></p>
            </td>
            <td>Pathway information</td>
            <td>GplusE Metabolic Reactions</td>
            <td>Conant et al</td>
            <td></td>
        </tr>
        <tr>
            <td class="leftcol"><p><h2>Publications</h2></p></td>
            <td> <i>B. taurus</i> </td>
            <td> A mapping from genes to publications</td>
            <td> NCBI PubMed</td>
            <td>NCBI Resource Coordinators - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=25398906">25398906</a></td>
            <td> <a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/gene/DATA/">NCBI FTP</a></td>
        </tr>
        <tr>
            <td class="leftcol" rowspan="3"><p><h2>Variation</h2></p></td>
            <td>
            <i>B. taurus</i>
            </td>
            <td>QTL data for Bos taurus</td>
            <td> AnimalQTLdb release 36</td>
            <td>Hu et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=30407520">30407520</a></td>
            <td><a target="_blank" href="http://www.animalgenome.org/cgi-bin/QTLdb/BT/index">CattleQTLdb Download</a></td>
        </tr>
        <tr>
            <td><i>B. taurus</i></td>
            <td>dbSNP data for Bos taurus</td>
            <td> dbSNP v146</td>
            <td> Cunningham et al - PubMed <a target="_blank" href="https://www.ncbi.nlm.nih.gov/pubmed/?term=30407521">30407521</a></td>
            <td><p><a target="_blank" href="ftp://ftp.ensembl.org/pub/release-95/variation/vcf/bos_taurus/">Ensembl FTP</a></p><p><i> *SNP arrays and SNP IDs*</i></p></td>
        </tr>
        <tr>
            <td><i>B. taurus</i></td>
            <td>SNP Alias Identifiers</td>
            <td> SNPchiMp v.3</td>
            <td> Nicolazzi et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=25881165">25881165</a></td>
            <td><a target="_blank" href="http://bioinformatics.tecnoparco.org/SNPchimp/">SNPchiMp</a></td>
        </tr>
       <!-- <tr>
            <td><i>B. taurus</i></td>
            <td>dbVar data for Bos taurus</td>
            <td> dbVar (May, 2017)</td>
            <td> Lappalainen et al - PubMed <a target="_blank" href="https://www.ncbi.nlm.nih.gov/pubmed/?term=23193291">23193291</a></td>
            <td><a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/pub/dbVar/data/Bos_taurus/">NCBI FTP</a></td>
        </tr>-->
        <tr>
            <td class="leftcol" rowspan="1"><p><h2>Variant Annotations</h2></p></td>
            <td><i>B. taurus</i></td>
            <td>Variant effects predicted on RefSeq and Ensembl using Ensembl Variant Effect Predictor</td>
            <td>Ensembl VEP (Release 88)</td>
            <td>Cunningham et al - PubMed <a target="_blank" href="https://www.ncbi.nlm.nih.gov/pubmed/?term=30407521">30407521</a></td>
            <td><a target="_blank" href="ftp://ftp.ensembl.org/pub/release-95/variation/vcf/bos_taurus/">Ensembl VEP</a></td>
        </tr>
        <tr>
            <td class="leftcol"><p><h2>Gene expression</h2></p></td>
            <td><p>B. taurus</p></td>
            <td>RNA-seq gene expression data from L1 Dominette 01449 and her relatives</td>
            <td> NCBI SRA and BGD</td>
            <td> Kodama et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=22009675">22009675</a></td>
            <td> <a target="_blank" href="http://www.ncbi.nlm.nih.gov/sra?term=SRP049415">NCBI SRA Project Page</a></td>
        </tr>
        <tr>
            <td class="leftcol" rowspan="5"><p><h2>Assembly</h2></p></td>
            <td><i>B. taurus</i></td>
            <td>Chromosome Assembly</td>
            <td>ARS-UCD1.2</td>
            <td><a target="_blank" href="http://www.wcgalp.org/proceedings/2018/modernizing-bovine-reference-genome-assembly">Rosen et al</a></td>
            <td><a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Bos_taurus/latest_assembly_versions/GCF_002263795.1_ARS-UCD1.2/">NCBI FTP</a></td>
        </tr>
        <tr>
            <td><i>B. taurus</i></td>
            <td>Chromosome Assembly</td>
            <td>UMD3.1.1</td>
            <td>Zimin et al - PubMed <a target="_blank" href="http://www.ncbi.nlm.nih.gov/pubmed/?term=19393038">19393038</a></td>
            <td><a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Bos_taurus/all_assembly_versions/suppressed/GCF_000003055.6_Bos_taurus_UMD_3.1.1/">NCBI FTP</a></td>
        </tr>
        <tr>
            <td><i>C. hircus</i></td>
            <td>Chromosome Assembly</td>
            <td>ARS1</td>
            <td><p>Bickhart et al - PubMed <a target="_blank" href="https://www.ncbi.nlm.nih.gov/pubmed/28263316">28263316</a></p></td>
            <td><a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Capra_hircus/latest_assembly_versions/GCF_001704415.1_ARS1/">NCBI FTP</a></td>
        </tr>
        <tr>
            <td><i>O. aries</i></td>
            <td>Chromosome Assembly</td>
            <td>Oar_v4.0</td>
            <td><p>International Sheep Genomics Consortium - PubMed <a target="_blank" href="https://www.ncbi.nlm.nih.gov/pubmed/?term=20809919">24904168</a></p></td>
            <td><a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Ovis_aries/all_assembly_versions/suppressed/GCF_000298735.2_Oar_v4.0">NCBI FTP</a></td>
        </tr>
        <tr>
            <td><i>O. aries</i></td>
            <td>Chromosome Assembly</td>
            <td>Oar_v3.1</td>
            <td><p>Jiang et al - PubMed <a target="_blank" href="https://www.ncbi.nlm.nih.gov/pubmed/?term=24904168">24904168</a></p></td>
            <td><a target="_blank" href="ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/vertebrate_mammalian/Ovis_aries/all_assembly_versions/suppressed/GCF_000298735.1_Oar_v3.1">NCBI FTP</a></td>
        </tr>
    </table>
</div>
<!-- /dataCategories -->
