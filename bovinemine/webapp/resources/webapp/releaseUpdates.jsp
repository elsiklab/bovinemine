<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im"%>
<%@ taglib uri="/WEB-INF/functions.tld" prefix="imf" %>

<!DOCTYPE html>

<html>
	<head>
	    <title>Release Updates</title>
	    <style>
	    </style>
	</head>
	<body>
	    <div id="content-wrap">
            <div id="release-updates">
		<div class="note_header">
		    <h3>BovineMine v1.6 Release</h3>
		</div>
		<br/>
		<div class="note_desc">
			<h3>Updates</h3>
	           	 <ul>
		                <li>dbSNP from Ensembl for bovine ARS-UCD1.2 has been added.</li>
                		<li>Variant consequences are now from Ensembl, and do not include consequences to RefSeq genes.</li>
		                <li>dbSNP and variant consequences for UMD3.1.1 have been removed.</li>
		                <li>dbVar data has been removed.</li>
                		<li>Gene expression for UMD3.1.1 has been removed.</li>
            		</ul>	
		</div>
		<br/>
		<div class="note_header">
                    <h3>BovineMine v1.5 Release</h3>
                </div>
		<br/>
		<div class="note_desc">
			<h4>Features</h4>
			<ul>
			    <li>The bovine genome assembly ARS-UCD1.2 has been added, but UMD3.1.1 is still available.</li>
			    <li>The new sheep genome Oar_v4.0 has been added, but Oar_v3.1 is still available.</li>
			    <li>The regions search allows you to select a genome assembly. </li>
			    <li>Expression levels are provided for genes instead of transcripts.</li>
			    <li>Expression values have been changed to FPKM and TPM, instead of FPKM and normalized counts, and were computed with StringTie instead of Cufflinks.</li>
			</ul>

		</div>
		<br/>
                <div class="note_header">
                    <h3>BovineMine v1.4 Release</h3>
                </div>
                <br>
                <div class="note_desc">
                    <h4>Features</h4>
                    <ul>
                        <li>Genomic Region Search Tool now support input regions with GK000001.2, Chr1 and AC_000158.1 as chromosome names</li>
                        <li>GO Annotations now display ECO terms as evidence codes</li>
                        <li>QTLs are now linked with their relevant ontologies, as provided from AnimalQTLdb</li>
                        <li>QTLs are now linked to SNPs that represent flanking markers</li>
                    </ul>
                    <br>
                    <h4>Datasets</h4>
                    <ul>
                        <li>Ensembl annotations updated to Release 89</li>
                        <li>EnsemblCompara updated to Release 89</li>
                        <li>QTLs from AnimalQTLdb updated to release 33</li>
                        <li>NCBI dbVar updated to revision May 2017</li>
                        <li>OrthoDB updated to v9.1</li>
                        <li>TreeFam updated to release 9.0</li>
                        <li>UniProt updated to 08/2017</li>
                        <li>BioGRID updated to version 3.4.151</li>
                        <li>IntAct updated to 2017-06-03 release</li>
                        <li>InterPro updated to version 64</li>
                        <li>Reactome updated to version 60</li>
                        <li>Gene Ontology Annotation updated</li>
                        <li>Uber Anatomy Ontology (UBERON) added</li>
                        <li>Mouse Adult Gross Anatomy Ontology added</li>
                        <li>Clinical Measurement Ontology added</li>
                        <li>Livestock Breed Ontology added</li>
                        <li>Livestock Product Trait Ontology added</li>
                        <li>Vertebrate Trait Ontology added</li>
                    </ul>
                </div>
                <br>
                <br>
                <div class="note_header">
                    <h3>BovineMine v1.3 Release</h3>
                </div>
                <br>
                <div class="note_desc">
                    <h4>Datasets</h4>
                    <ul>
                        <li>Ensembl annotations updated to Release 84</li>
                        <li>EnsemblCompara updated to Release 84</li>
                        <li>QTLs from AnimalQTLdb release 30</li>
                        <li>NCBI dbVar updated to revision January 2016</li>
                        <li>UniProt updated to 12/2016</li>
                        <li>Gene Ontology Annotation updated</li>
                        <li>OrthoDB updated to v9.0</li>
                        <li>InterPro updated to version 60</li>
                        <li>BioGRID updated to version 3.4.144</li>
                        <li>Reactome updated to version 59</li>
                    </ul>
                </div>
                <br>
                <br>
                <div class="note_header">
                    <h3>BovineMine v1.2 Release</h3>
                </div>
                <br>
                <div class="note_desc">
                    <h4>Features</h4>
                    <ul>
                        <li>Added support for genome, gene annotations and protein annotations for Goat (<i>Capra hircus</i>)</li>
                        <li>Added support for genome, gene annotations and protein annotations for Sheep (<i>Ovis aries</i>)</li>
                    </ul>
                    <br>
                    <h4>Datasets</h4>
                    <ul>
                        <li>NCBI annotations for <i>Bos taurus</i> updated to Release 105</li>
                        <li>Ensembl annotations updated to Release 84</li>
                        <li>EnsemblCompara updated to Release 84</li>
                        <li>QTLs from AnimalQTLdb release 28</li>
                        <li>NCBI dbSNP updated to v146</li>
                        <li>NCBI dbVar updated to revision January 2016</li>
                        <li>UniProt updated to 04/2016</li>
                        <li>Gene Ontology Annotation updated</li>
                        <li>InterPro updated to version 56.0</li>
                        <li>BioGRID updated to version 3.4.135</li>
                        <li>Reactome updated to version 55</li>
                    </ul>
                </div>
            </div>
        </div>
	</body>
</html>
