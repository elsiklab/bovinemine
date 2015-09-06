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
    <td> UMD3.1 RefSeq Genome Annotation and reference sequence</td>
    <td>NCBI</td>
    <td> Salzberg et al - PubMed: 19393038</td>
    <td> </td>
  </tr>
  <tr>
    <td> <i>Bos taurus</i> </td>
    <td> Official Gene Set v2.0  </td>
    <td> Bovine Genome Database</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td> <i> Bos taurus</i></td>
    <td> Ensembl Annotations for UMD3.1 </td>
    <td>Ensembl Release 77</td>
    <td></td>
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
    <td>Kriventseva EV et al. - PubMed: 23180791</td>
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

    <td>Ruan et al - PubMed: 18056084</td>
    <td> &nbsp; </td>
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

    <td></td>
    <td> &nbsp; </td>
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
    <td>HomoloGene</td>

    <td></td>
    <td> &nbsp; </td>
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
    <td> UniProt - Release 2014_10</td>
    <td> UniProt Consortium - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17142230" target="_new" class="extlink">PubMed: 17142230</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td>
        <p><i>B. taurus</i></p>
    </td>
    <td> Protein family and domain assignments to proteins</td>
    <td> InterProVersion 48.0 - July 2014  </td>
    <td> Mulder et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/17202162" target="_new" class="extlink">PubMed: 17202162</a></td>
    <td> &nbsp;</td>
  </tr>
  <tr>
    <td class="leftcol"><p><h2>Interactions</h2></p></td>
    <td>
        <p><i>B. taurus</i></p>
        <p><i>H. sapien</i></p>
        <p><i>M. musculus</i></p>
    </td>
    <td> Interactions from the BioGRID </td>
    <td> <a href="http://www.thebiogrid.org" target="_new" class="extlink">BioGRID</a> - Version 3.2.113 </td>
    <td> Stark et al - <a href="http://www.ncbi.nlm.nih.gov/pubmed/16381927" target="_new" class="extlink">PubMed:16381927</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td rowspan="2" class="leftcol"><p> <h2>Gene Ontology</h2></p></td>
    <td> <i>B. taurus</i> </td>
    <td> GO annotations </td>
    <td> <a href="http://www.geneontology.org" target="_new" class="extlink">Gene Ontology Site</a> - Sep 2014</td>
    <td> Gene Ontology Consortium - <a href="http://www.ncbi.nlm.nih.gov/pubmed/10802651" target="_new" class="extlink">PubMed:10802651</a></td>
    <td> &nbsp;</td>
  </tr>

  <tr>
    <td>
      <p><i>B. taurus</i></p>
      <p><i>M. musculus</i></p>
      <p><i>H. sapien</i></p>
    </td>

    <td> InterPro domains to GO annotations </td>
    <td></td>
    <td></td>
    <td> &nbsp;</td>
  </tr>
  <tr>
    <td class="leftcol"><p><h2>Pathways</h2></p></td>
    <td>
      <p><i>H. sapien</i></p>
      <p><i>M. musculus</i></p>
      <p><i>B. taurus</i></p>
    </td>
    <td> Pathway information and the genes involved in them, inferred through orthologues from Human curated pathways</td>
    <td> <a href="http://www.reactome.org/" target="_new" class="extlink">Reactome</a> - version 50, Oct 2014</td>
    <td> &nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td class="leftcol"><p><h2>Publications</h2></p></td>
    <td> <i>B. taurus</i> </td>
    <td> Gene versus publications</td>
    <td>NCBI PubMed - June 2014</td>
    <td></td>
    <td> &nbsp;</td>
  </tr>
</table>

<div class="body">
</div>

</div>
<!-- /dataCategories -->
=======
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im" %>

<div class="body">
  <div id="leftCol">
    <div id="pageDesc" class="pageDesc">
      <p><fmt:message key="dataCategories.intro"/></p>
    </div>

    <im:boxarea title="Actions" stylename="plainbox" >
         <html:link action="/templates">
           <fmt:message key="dataCategories.viewTemplates"/>
           <img border="0" class="arrow" src="images/right-arrow.gif" alt="Go"/>
         </html:link>
    </im:boxarea>
  </div>
</div>

<div id="rightCol">
  <im:boxarea titleKey="dataCategories.title" stylename="gradientbox">
    <c:choose>
      <c:when test="${!empty ASPECTS}">
        <tiles:insert name="aspects.tile"/>
      </c:when>
      <c:otherwise>
        <c:forEach items="${CATEGORIES}" var="category">
          <c:if test="${!empty CATEGORY_CLASSES[category]}">
            <div class="heading">
              <c:out value="${category}"/>
            </div>

            <div class="body">
              <c:set var="classes" value="${CATEGORY_CLASSES[category]}"/>
              <c:forEach items="${classes}" var="classname" varStatus="status">
                <a href="<html:rewrite page="/queryClassSelect.do"/>?action=<fmt:message key="button.selectClass"/>&amp;className=${classname}" title="<c:out value="${classDescriptions[classname]}"/>">${classname}</a><c:if test="${!status.last}">,</c:if>
              </c:forEach>

              <c:if test="${!empty CATEGORY_TEMPLATES[category]}">
                <br/>
                <span class="smallnote"><fmt:message key="begin.or"/> <html:link action="/templates" paramId="category" paramName="category"><fmt:message key="begin.related.templates"/></html:link></span>
              </c:if>
            </div>
            <im:vspacer height="5"/>
          </c:if>
        </c:forEach>
      </c:otherwise>
    </c:choose>
  </im:boxarea>
</div>
