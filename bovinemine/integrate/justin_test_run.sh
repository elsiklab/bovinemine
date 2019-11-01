#/bin/bash
ant -Dsource=bovine-ARS-UCD1.2-chromosome-fasta -v
sudo systemctl restart postgresql-9.5
sleep 5m
ant -Dsource=bovine-UMD3.1.1-chromosome-fasta -v
sudo systemctl restart postgresql-9.5
sleep 5m
ant -Dsource=chir-chromosome-fasta -v
sudo systemctl restart postgresql-9.5
sleep 5m
ant -Dsource=oari-v3.1-chromosome-fasta -v
ant -Dsource=oari-v4.0-chromosome-fasta -v
sudo systemctl restart postgresql-9.5
sleep 5m
ant -Dsource=bovine-refseq-UMD3.1.1-coding-gff -v
ant -Dsource=bovine-refseq-UMD3.1.1-noncoding-gff -v
sudo systemctl restart postgresql-9.5
sleep 5m
ant -Dsource=bovine-refseq-ARS-UCD1.2-coding-gff -v
ant -Dsource=bovine-refseq-ARS-UCD1.2-noncoding-gff -v
sudo systemctl restart postgresql-9.5
sleep 5m
ant -Dsource=chir-refseq-coding-gff -v
ant -Dsource=chir-refseq-noncoding-gff -v
sudo systemctl restart postgresql-9.5
sleep 5m
ant -Dsource=oari-refseq-Oar-v3.1-coding-gff -v
ant -Dsource=oari-refseq-Oar-v4.0-coding-gff -v
sudo systemctl restart postgresql-9.5
sleep 5m
ant -Dsource=oari-refseq-Oar-v3.1-noncoding-gff -v
ant -Dsource=oari-refseq-Oar-v4.0-noncoding-gff -v
sudo systemctl restart postgresql-9.5
sleep 5m
ant -Dsource=bovine-ensembl-UMD3.1.1-gff -v
ant -Dsource=bovine-ensembl-ARS-UCD1.2-gff -v
sudo systemctl restart postgresql-9.5
sleep 5m
ant -Dsource=chir-ensembl-gff -v
ant -Dsource=oar-ensembl-gff -v
sudo systemctl restart postgresql-9.5
sleep 5m
