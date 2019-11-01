#!/usr/bin/bash

ant -Dsource=uberon -v
ant -Dsource=mouse-anatomy-ontology -v
ant -Dsource=brenda-tissue-ontology -v
ant -Dsource=clinical-measurement-ontology -v
ant -Dsource=livestock-breed-ontology -v
ant -Dsource=livestock-product-trait-ontology -v
ant -Dsource=vertebrate-trait-ontology -v
ant -Dsource=evidence-ontology -v
ant -Dsource=psi-mi-ontology -v
ant -Dsource=sequence-ontology -v
sudo systemctl restart postgresql
sleep 3m
ant -Dsource=gene-ontology -v
sudo systemctl restart postgresql
sleep 3m
ant -Dsource=human-gene-info-refseq -v
ant -Dsource=mouse-gene-info-refseq -v
ant -Dsource=rat-gene-info-refseq -v
ant -Dsource=pig-gene-info-refseq -v
ant -Dsource=human-gene-info-ensembl -v
ant -Dsource=mouse-gene-info-ensembl -v
ant -Dsource=rat-gene-info-ensembl -v
ant -Dsource=pig-gene-info-ensembl -v
sudo systemctl restart postgresql
sleep 1m


#ant -Dsource=bovine-dbsnp-variation-I -v
#sudo systemctl restart postgresql
#sleep 3m

# ant -Dsource=bovine-dbsnp-variation-II -v
# sudo systemctl restart postgresql
#sleep 4m

# ant -Dsource=bovine-dbsnp-variation-III -v
# sudo systemctl restart postgresql
#sleep 4m

#ant -Dsource=bovine-dbsnp-variation-IV -v
# sudo systemctl restart postgresql
#sleep 4m

# ant -Dsource=bovine-dbsnp-variation-V -v
# sudo systemctl restart postgresql
#sleep 4m

#ant -Dsource=bovine-dbsnp-variation-VI -v
#sudo systemctl restart postgresql
#sleep 4m

#ant -Dsource=bovine-dbsnp-variation-VII -v
# sudo systemctl restart postgresql






#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-ARS-UCD1.2-chromosome-fasta -v
#sudo systemctl restart postgresql
#sleep 3m
#ant -Dsource=bovine-UMD3.1.1-chromosome-fasta -v
#sudo systemctl restart postgresql
#sleep 3m
#ant -Dsource=chir-chromosome-fasta -v
#ant -Dsource=oari-v3.1-chromosome-fasta -v
#ant -Dsource=oari-v4.0-chromosome-fasta -v
#ant -Dsource=bovine-refseq-UMD3.1.1-coding-gff -v
#ant -Dsource=bovine-refseq-UMD3.1.1-noncoding-gff -v
#ant -Dsource=bovine-refseq-ARS-UCD1.2-coding-gff -v
#ant -Dsource=bovine-refseq-ARS-UCD1.2-noncoding-gff -v

#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=chir-refseq-coding-gff -v
#ant -Dsource=chir-refseq-noncoding-gff -v
#ant -Dsource=oari-refseq-Oar-v3.1-coding-gff -v
#ant -Dsource=oari-refseq-Oar-v4.0-coding-gff -v
#ant -Dsource=oari-refseq-Oar-v3.1-noncoding-gff -v
#ant -Dsource=oari-refseq-Oar-v4.0-noncoding-gff -v
#sudo systemctl restart postgresql
#sleep 3m
#ant -Dsource=bovine-ensembl-UMD3.1.1-gff -v
#sudo systemctl restart postgresql
#sleep 3m
#ant -Dsource=bovine-ensembl-ARS-UCD1.2-gff -v
#sudo systemctl restart postgresql
#sleep 3m
#ant -Dsource=chir-ensembl-gff -v
#ant -Dsource=oar-ensembl-gff -v





#CHECK COUNTS





#ant -Dsource=bovine-ars-ucd1.2-qtl-gff -v
#ant -Dsource=bovine-umd3.1.1-refseq-cds-fasta -v
#ant -Dsource=bovine-ars-ucd1.2-refseq-cds-fasta -v
#ant -Dsource=bovine-umd3.1.1-ensembl-cds-fasta -v
# --- load
#ant -Dsource=bovine-ars-ucd1.2-ensembl-cds-fasta -v
# -- load
#sudo systemctl restart postgresql
#sleep 3m
#ant -Dsource=oari-Oar-v3.1-refseq-cds-fasta -v
#ant -Dsource=oari-Oar-v4.0-refseq-cds-fasta -v
#ant -Dsource=oari-Oar-v3.1-ensembl-cds-fasta -v
#ant -Dsource=chir-ars1-refseq-cds-fasta -v
#ant -Dsource=chir-ars1-ensembl-cds-fasta -v
#sudo systemctl restart postgresql
#sleep 3m






#ant -Dsource=bovine-umd3.1.1-protein-ncbi -v
#ant -Dsource=bovine-ars-ucd1.2-protein-ncbi -v
#ant -Dsource=bovine-umd3.1.1-protein-ensembl -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-ars-ucd1.2-protein-ensembl -v
#ant -Dsource=chir-ars1-protein-ncbi -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=chir-ars1-protein-ensembl -v
#ant -Dsource=oari-Oar-v3.1-protein-ncbi -v
#ant -Dsource=oari-Oar-v4.0-protein-ncbi -v
#ant -Dsource=oari-Oar-v3.1-protein-ensembl -v
#ant -Dsource=bovine-xref -v
#ant -Dsource=chir-xref -v
#ant -Dsource=oari-xref -v
#ant -Dsource=oari-xref -v

#ant -Dsource=bovine-expression-metadata -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-ars-ucd1.2-rnaseq-expression-for-refseq -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-ars-ucd1.2-rnaseq-expression-for-ensembl -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-umd3.1.1-affy-probe -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-ars-ucd1.2-affy-probe -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-umd3.1.1-oligo-microarray-probe -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-ars-ucd1.2-oligo-microarray-probe -v
#sudo systemctl restart postgresql
#sleep 4m

#ant -Dsource=bovine-umd3.1.1-repeat-region -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-ars-ucd1.2-repeat-region -v
#sudo systemctl restart postgresql
#sleep 4m
 
# # STOOOOOOOP
# #change files
#ant -Dsource=bovine-uniprot -v
#sudo systemctl restart postgresql
#sleep 3m
# 


# COUNT UNIPROT




# STOOOOOOOOP
# #change files
# ant -Dsource=bovine-uniprot-sec -v
# sudo systemctl restart postgresql
#sleep 4m

 
#ant -Dsource=uniprot-keywords -v
#ant -Dsource=bovine-uniprot-fasta -v
#ant -Dsource=interpro -v
#ant -Dsource=protein2ipr -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=go-annotation -v
#ant -Dsource=reactome -v
#sudo systemctl restart postgresql
#sleep 4m


#ant -Dsource=ncbi-pubmed-gene-umd3.1.1 -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=ncbi-pubmed-gene-ars-ucd1.2 -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=ncbi-pubmed-gene-oarv3 -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=ncbi-pubmed-gene-oarv4 -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=ncbi-pubmed-gene-ars1 -v
#sudo systemctl restart postgresql
#sleep 4m
##
#ant -Dsource=ensembl-pubmed-gene -v
#sudo systemctl restart postgresql
#sleep 4m

#ant -Dsource=ensembl-compara -v
#sudo systemctl restart postgresql
#sleep 4m

#ant -Dsource=treefam-extended -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-orthodb -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=gpluse-orthologs -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-biogrid -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=psi-intact -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=kegg -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=gpluse -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-refseq-umd3.1.1-gene-alias -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-refseq-umd3.1.1-transcript-alias -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-refseq-umd3.1.1-protein-alias -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-ensembl-umd3.1.1-gene-alias -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-ensembl-umd3.1.1-transcript-alias -v
#sudo systemctl restart postgresql
#sleep 4m
#ant -Dsource=bovine-ensembl-umd3.1.1-protein-alias -v


#sudo systemctl restart postgresql
#sleep 5m
#ant -Dsource=bovine-refseq-ars-ucd1.2-to-ogs-gene-alias -v
#sudo systemctl restart postgresql
#sleep 5m
#ant -Dsource=bovine-ensembl-ars-ucd1.2-to-ogs-gene-alias -v
#sudo systemctl restart postgresql
#sleep 5m
#ant -Dsource=sheep-oar_v3.1-gene-alias -v
#sudo systemctl restart postgresql
#sleep 5m
#ant -Dsource=sheep-oar_v3.1-transcript-alias -v
#ant -Dsource=sheep-oar_v3.1-protein-alias -v

# STOOOOOOP
# Run SQL Statement
#ant -Dsource=entrez-organism -v
#ant -Dsource=update-publications -v
##
