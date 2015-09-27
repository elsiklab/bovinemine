********
Tutorial
********

BovineMine is powered by InterMine and provides a user-friendly way to access genomic, proteomic, interaction and literature data. This tutorial is aimed at giving users an introduction to different features of BovineMine.

Overview
^^^^^^^^

This section provides a brief overview of the layout for BovineMine.

 .. image:: images/bovinemine-banner.png
    :width: 800px
    :align: center
    :height: 90px
    :alt: BovineMine header

The navigation panel highlights different functionalities of BovineMine based on what the user is searching for.

#. **Home** - The homepage for BovineMine
#. **Templates** - Shows the list of templates that users can use based on the nature of their query. Each template is associated with a description which describes what input is expected from the user and what will be the output.
#. **Lists** - The list page offers users a form which they can use to submit or upload a list of genes. Once the list is saved, users can perform enrichment analyses of these genes and export the results.
#. **QueryBuilder** - The QueryBuilder is an interface that provides the user to directly interact with the BovineMine data model. Some users might find the QueryBuilder difficult at first, but this tutorial is aimed at familiarizing users on the functionality of QueryBuilder, how to build a custom template and save their own templates.
#. **BLAST** - The BLAST page is where users can search their input sequence(s) against the several databases provided.
#. **Regions** - The Genomic Region Search tool provides a form where users can enter a series of genomic coordinates, specify flanking regions (if any) and fetch all features that fall within the given interval. The result can be exported, saved as a list for further analyses.
#. **Data Sources** - The Data Sources page provides a summary of all the data that is loaded into BovineMine. It lists a combination of datasets from other databases as well as in-house prepared datasets.
#. **API** - The API provides users an option for programmatically accessing BovineMine by using the InterMine API.
#. **MyMine** - The MyMine serves as a portal for users (those who are logged in) for account management. Users can access their saved templates, most recent queries and saved lists.


Navigation and Searching in BovineMine
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

There are three ways for users to query BovineMine.

a. 'Search' Box
---------------

The Search box is located on the BovineMine home page.


 .. image:: images/search-box.png
   :width: 800px
   :height: 350px
   :alt: Search Box on BovineMine home page
   :align: center


There are two locations (marked) where users can input the name, Gene ID, or keywords related to their gene of interest. The search box is not just limited to Gene but a variety of features such as Transcript, Exons, Interaction, Pathways, GO Annotations and more. Users can make use of wildcard character '*' to get all results that match a particular prefix.


Lets consider gene **rpl24** as an example. Enter rpl24 in the search box and click ``Search``. The results page is tabulated and shows a summary about your query, as shown below.


 .. image:: images/rpl24-search-result.png
   :width: 800px
   :height: 376px
   :alt: rpl24 search result
   :align: center


Users can filter the results based on ``Category`` and ``Organism``. The score column in the result table indicates the similarity of your query to each of the result fetched by BovineMine.

The results page can also be converted to a list. To enable this feature click on ``Gene`` in ``Hits by Category``.


 .. image:: images/rpl24-search-result-filtered.png
   :width: 800px
   :height: 320px
   :alt: filtered rpl24 search result
   :align: center


This will filter the results for the feature type ``Gene`` and checkboxes will be available for users to select genes they would like to add to their list. Once the genes are selected, click on ``CREATE LIST``.

 
b. Templates
------------


Apart from the search box, users can make use of predefined templates by clicking on the ``Templates`` tab. 

Users can choose from a list of templates based on the nature of their query.


 .. image:: images/template.png
   :width: 800px
   :height: 440px
   :alt: Templates page
   :align: center


As an example, lets click on ``GO terms -> Genes`` template. The goal of this template is to query BovineMine to find all genes having a given GO term.


 .. image:: images/go-gene-template.png
   :width: 800px
   :height: 442px
   :alt: GO -> Gene Template
   :align: center


Users can also specify a filter for GO evidence code. Lets search for the GO term ``DNA binding`` with GO evidence code ``IEA``.


 .. image:: images/go-gene-template-result.png
   :width: 800px
   :height: 378px
   :alt: GO -> Gene Template results
   :align: center


The result page shows all the genes that have been annotated with the Gene Ontology term ``DNA binding``. 

Users can,

1. create or add these genes to a list, by clicking on ``Create/Add to List`` (Box 1) to perform further analyses.
2. get the code for the query in Perl, Python, Java, Ruby, JavaScript or XML by clicking on ``Get Code`` (Box 2).
3. download the search results, by clicking on ``Download`` (Box 3), as tab-delimited, comma-separated values, XML or JSON. If the results are genomic features, which is true for the current example, then users can download the results in GFF3 and BED format.
4. customize the layout of the result page by clicking on ``Manage Columns`` (Box 4).


c. QueryBuilder
---------------


We have provided templates suitable for several common types of queries but if users need more fidelity in their search they can make use of the QueryBuilder. The possibilities of queries using the QueryBuilder are endless. You can format the output the way you want and constrain your queries to perform complex search operations.



 .. image:: images/querybuilder.png
   :width: 800px
   :height: 384px
   :alt: Query Builder
   :align: center


First lets select ``Gene`` as a Data Type in the QueryBuilder. Then click on ``Select``. This will take you to a Model browser where you can select the attributes, for the feature class ‘Gene’, which you would want to be shown in your results.


 .. image:: images/querybuilder-gene.png
   :width: 800px
   :height: 498px
   :alt: Query Builder with selected type Gene
   :align: center


Lets consider three scenarios for using the QueryBuilder,


**i. Querying for Protein Coding genes**

Click on ``Show`` tab for the attributes ``Gene Identifier``, ``BGD Identifier``, ``Symbol`` and ``Status``. This tells the QueryBuilder to show the Gene ID, Bovine Genome Database (BGD) ID and the Symbol for ``Gene``.

Then click on ``Constrain`` tab for the attribute ``Status``.


 .. image:: images/querybuilder-constrain-status.png
   :width: 800px
   :height: 500px
   :alt: Query Builder for Gene showing attributes and constrained by status 'Protein Coding'
   :align: center


Click on the drop down list for ``Status`` and select ``Protein Coding``. Then click on ``Add to Query``.
 
The Query Overview should resemble the image below,


 .. image:: images/querybuilder-constrain-status-overview.png
   :width: 800px
   :height: 504px
   :alt: Overview of Query Builder for Gene showing attributes and constrained by status 'Protein Coding'
   :align: center

Now, click on ``Show results`` to see all the genes that have the status ``Protein Coding``.


 .. image:: images/querybuilder-constrain-status-result.png
   :width: 800px
   :height: 350px
   :alt: Results for Query for Gene showing attributes and constrained by status 'Protein Coding'
   :align: center


**ii. Querying for Protein Coding genes on a particular chromosome**


Users can customize the previously run query by adding another constraint for ``Chromosome``.


 .. image:: images/querybuilder-additional-constrain-by-chromosome.png
   :width: 800px
   :height: 503px
   :alt: Adding an additional constraint for Chromosome name
   :align: center


While building the query, click on the ``Constrain`` tab for the ``Chromosome`` feature class, enter ``Chr1`` in the text box of the pop-up window and click on ``Add to Query``. Then click on ``Show`` for ``Chromosome Identifier`` and ``Chromosome Name``.
 
Now, click on ``Show results`` and the query will result in all the genes that are of type ``Protein Coding`` on Chromosome with ID ``Chr1``. 

Note: Since here we are specifying a constrain at a class level, instead of at an individual attribute level, the QueryBuilder will look up the input ID ``chr1`` in all attributes.


 .. image:: images/querybuilder-additional-constrain-by-chromosome-result.png
   :width: 800px
   :height: 350px
   :alt: Results after adding an additional constraint for Chromosome name
   :align: center


**iii. Querying for Protein Coding genes on a particular chromosome and theirs exons**


Users can customize this query further by configuring the query to show all the exons for each Gene. 


 .. image:: images/querybuilder-getting-exons-overview.png
   :width: 800px
   :height: 504px
   :alt: Query Builder overview for getting exons
   :align: center


1. Expand the Transcript subclass for Gene
2. Expand the Exon subclass for Transcript
3. Select ``Exon Identifier`` and ``Length``.
4. To make the query more relaxed, click on the blue square icon (labelled with No. 4) near the Exon collection in the Query Overview. You will see a pop as shown below,

 .. image:: images/exon-collection-join-popup.png
   :width: 800px
   :height: 425px
   :alt: Join Style for the current query at Exon level
   :align: center


Select ``Show all Genes and show Exons if they are present`` and click on ``Add to query``.
 
Now run the query and you should see the following results page,


 .. image:: images/querybuilder-getting-exons-result.png
   :width: 800px
   :height: 443px
   :alt: Results after modifying query for showing all exons for all transcripts of current gene
   :align: center


There are 1923 genes on Chromosome 1 and the gene with NCBI Gene ID 100125416 has two transcripts, each with 14 exons. Users can click on the ``14 exons`` to expand the table with additional rows describing the Exon ID and Length for each exon.



Report Page
^^^^^^^^^^^

Every query result has a report page and the layout of the report page depends on the data available for a given query. Continuing with the example of **rpl24**, the report page for this gene is shown below.

 .. image:: images/report-page-overview.png
   :width: 800px
   :height: 566px
   :alt: Report Page for RPL24
   :align: center

The report page provides a complete description for gene rpl24. The header of the report page shows the ``Gene Identifier`` (NCBI Gene ID) and ``status`` indicating the type of gene, in this case a protein coding gene.

On the right hand side of the report page there are external links that links out to other Mines and databases.

The contents of the report page is divided into categories based on the type of information provided,

**Summary**

Provides a summary about a gene such as length, chromosome location and strand information. Users can also get the complete FASTA sequence of the gene by clicking on the FASTA tab.

**Genes**

This section provides information about the gene model. It displays all the transcripts and exons for a gene. The FASTA sequence of each transcript or exon can be downloaded by clicking on the FASTA tab. JBrowse is used to visualize the gene model.

**Proteins**

This section provides information about the protein product of gene rpl24. The comments section gives a brief description about the protein along with the UniProt accession.

**Function**

This section provides Gene Ontology annotations for a gene and annotations are divided into three categories,

* Biological process
* Molecular function
* Cellular Component

The GO terms are displayed along with the evidence code indicating how the annotations were derived.

**Homology**

This section provides information on all the homologues for rpl24. The first part shows a summarized view of the homologues in different organisms. The table below gives a detailed information about the homologue, the type of homologue and from which dataset the information was obtained.

**Interaction**

This section provides information about interactions. For rpl24 there are no interaction information available but for genes that do have interaction information, a network is displayed showing all interactors for the current gene. The network displayer is a Cytoscape plugin.

**Others**

This section provides additional information such as,

* Child features – lists all the features that are sub features of the current gene
* Flanking regions – lists all the features flanking the current gene
* Overlapping features – lists all the features that overlap with current gene
* Pathways – lists all the pathways in which the current gene is a part of
* Publications – Publications related to the current gene
 

BLAST
^^^^^


Users can perform BLAST against the Bos taurus genomic, CDS or protein sequences using the BLAST page.
 

Genomic Region Search
^^^^^^^^^^^^^^^^^^^^^


The Genomic Region Search is a tool to fetch features that are within a given set of genomic coordinates or to fetch features that are within a given number of bases flanking a given set of genomic coordinates.

The coordinates have to be of the format,

``chromosome_name:start..end``

``chromosome_name:start-end``

``chromosome_name    start    end``


 .. image:: images/genomic-region-search.png
   :width: 800px
   :height: 583
   :alt: Genomic Region Search
   :align: center


Click on ``click to see an example`` for a representative set of genomic coordinates.
Users can extend the regions on either side of a given coordinate using the slider or using the textbox.

Users can also select the type of coordinate system they would like to use: **base coordinate** or **interbase coordinate**.

Lets try and example. Click on the ``click to see an example`` and extend the region search by 500 bp and click on ``Search``. The result page will give a list of features that are present in each of the genomic interval provided in the input.

 .. image:: images/genomic-region-search-result.png
   :width: 800px
   :height: 346px
   :alt: Result page for Genomic Region Search
   :align: center

The results can be exported as tab-delimited and comma-separated values. If the results have genomic features then they can be exported in GFF3 or BED format. Users can also export FASTA sequences of the features.
If users are interested in creating a list of particular features from the result page then they can filter based on feature type, shown in red box, and click on ‘Go’.


Lists
^^^^^

Users can create a list of features. The input can either be gene IDs, transcript IDs, gene symbols, etc.


 .. image:: images/list-page-overview.png
   :width: 800px
   :height: 590px
   :alt: List page overview
   :align: center


The list tool tries to lookup the query throughout the database and will attempt to convert the identifiers to the type selected in the list ‘Select Type’ option.
Lets try the examples provided. Click on ‘Click to see example’ link (highlighted in red box) and click on ‘Create List’.


 .. image:: images/list-page-selection.png
   :width: 800px
   :height: 443px
   :alt: Saving a List
   :align: center

The list tool does a lookup of the identifiers and shows you the results. If there are any duplicates, users can decide to add the relevant entries individually.

The summary section provides information regarding those identifiers that had a direct hit without any duplicates.

Click on 'Add all' and then click on ‘Save a list of 62 genes’.

This will take users to a List Analysis page,


 .. image:: images/list-page-analysis.png
   :width: 800px
   :height: 400px
   :alt: List Analysis page
   :align: center

This page provides users with widgets to perform analyses on gene lists that they have created.

 .. image:: images/list-analysis-widgets.png
   :width: 800px
   :height: 500px
   :alt: Widgets in List Analysis page
   :align: center



Currently available widgets are,

#. Chromosome Distribution

#. Gene Ontology Enrichment

#. Protein Domain Enrichment

#. Publication Enrichment

#. Pathway Enrichment

#. Orthologues

 
MyMine
^^^^^^

Users can create an account on BovineMine which enables them to store their lists, custom templates and keep track of their sessions.
To create a login, click on the 'Log in' on the top-right corner of any page on BovineMine.

New users can create a new account with the username being their Email ID.

BovineMine provides MyMine for users to manage their lists, queries, templates, and account details.

 .. image:: images/mymine.png
   :width: 800px
   :height: 127px
   :alt: MyMine
   :align: center


1. Lists – lists saved by the user.
2. History of queries by user – shows a list of most recent queries performed by the user.
3. Templates – Templates created by the user or existing templates that are marked as ‘favorite’ by the user.
4. Password change – change the password for the user’s account.
5. Account details – for updating user preferences.



API
^^^

For users who would like to programmatically access BovineMine they can make use of the InterMine API.

 .. image:: images/api.png
   :width: 800px
   :height: 538px
   :alt: API landing page
   :align: center


Perl, Python, Ruby and Java are the 4 languages in which the InterMine API is available.
For more information on the details on the API visit the InterMine Wiki.



Data Sources
^^^^^^^^^^^^

Provides a description of the datasets that are integrated into BovineMine along with the location from which these datasets were downloaded, their version or release, citations wherever applicable and any additional comments.







