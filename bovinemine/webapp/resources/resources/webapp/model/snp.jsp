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
    <td rowspan="3"  class="leftcol"><p><h2>SNPs/INDELs</h2></p></td>
    <td width="19%">
         <p><i>B. taurus</i></p>
    </td>
     <td>SNPs and INDELs <i>Bos taurus</i> UMD3.1 chromosome assembly</td>
    <td><a href="http://www.ncbi.nlm.nih.gov/SNP/" target="_new" class="extlink">dbSNP</a></td>
    <td>Smigielski EM et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/10592272" target="_new" class="extlink">PubMed: 10592272</a></td>
    <td> &nbsp; </td>
     </tr>

  <tr>
    <td>
      <p><i>B. taurus</i></p>
    </td>
           <td>HapMap</td>
    <td><a href="hapmap.ncbi.nlm.nih.gov/" target="_new" class="extlink">HapMap</a></td>
    <td></td>
    <!-- <td>Buchanan CC et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/22319179" target="_new" class="extlink">PubMed: 22319179</a></td> -->
    <td> <html:link action="/dataCategories" anchor="note1" title="${note1}">#1</html:link></td>
  </tr>

  <tr>
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
