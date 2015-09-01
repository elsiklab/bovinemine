<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im" %>

<table width="100%">
  <tr>
   
    
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
    <td width="19%">
         <p><i>B. taurus</i></p>
    </td>
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
   <td> &nbsp;</td>
   <td> &nbsp; </td>
  </tr>     



  <tr>
    <!--   <p><i></i></p> -->
    <!-- <td>Mi et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/23193289 " target="_new" class="extlink">PubMed: 23193289</a></td> -->
    <!-- <td> &nbsp; </td>
    <td> &nbsp; </td> -->
  </tr>
         
  </tr>

</table>





  
    </td>
  </tr>
</table>
