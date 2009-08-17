<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im"%>
<%@ taglib uri="http://flymine.org/imutil" prefix="imutil"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/string-1.1"
  prefix="str"%>

<tiles:importAttribute />

<html:xhtml />

<style type="text/css">
div#submissionLabName h3, div#submissionLabName div#submissionProject {
  color: black;
  margin-bottom: 10px;
}
div#submissionOrganism {
  color: black;
  margin-bottom: 20px;
}
div#submissionResults {
  color: black;
  margin-bottom: 20px;
  border: 1px;
  border-style: solid;
  border-color: green;
  background-color: #DFA;
  padding: 5px;
  width: 500px;
}

div#submissionResults h2 {
  font-size: 1.2em;
  color: black;
  font-style: bold;
}

div#submissionResults h3 {
  font-size: 1.1em;
  color: black;
}

div#submissionDescription {
  color: black;
  margin-bottom: 20px;
  border: 1px;
  border-style: solid;
  border-color: green;
  font-size: 1em;
  background-color: white;
  padding: 5px;
  width: 500px;
}

</style>

<div class="body">
  <div id="submissionLabName">
    <h3>
      <b>Lab:</b> <html:link href="/${WEB_PROPERTIES['webapp.path']}/objectDetails.do?id=${object.lab.id}">${object.lab.name}</html:link>
      - ${object.lab.affiliation}
    </h3>
  </div>
  <div id="submissionProject">
      <b>Project:</b> <html:link href="/${WEB_PROPERTIES['webapp.path']}/objectDetails.do?id=${object.lab.project.id}">${object.lab.project.name}</html:link> - ${object.lab.project.surnamePI}
  </div>
  <div id="submissionOrganism">
      <b>Organism:</b> <html:link href="/${WEB_PROPERTIES['webapp.path']}/objectDetails.do?id=${object.organism.id}">${object.organism.shortName}</html:link>
  </div>
  
  <div id="submissionDescription">
    <p><b>Submission description</b></p>
    <br/>
    <p>
  <html href="/${WEB_PROPERTIES['webapp.path']}/objectDetails.do?id=${object.id}">${object.description}</html>
  </div>

  <div id="submissionResults">

<c:choose> 
<c:when test="${fn:length(featureCounts) ge 1}">
    <h2>Features generated by this submission:</h2>
    <br/>
    <p>
        <table cellpadding="0" cellspacing="0" border="0" class="results">
      <tr>
        <th>Feature type</th>
        <th>Count</th>
        <th>View data</th>
        <th colspan="3">Export</th>
      </tr>
      <c:forEach items="${featureCounts}" var="fc" varStatus="status">
        <c:if test='${fc.key != "Chromosome"}'>
          <tr>
            <td>${fc.key}</td>
            <td>${fc.value}</td>                     
            <td align="right">
              <c:choose>
                <c:when test='${fc.key eq "EST" || fc.key eq "MNRA"}'>
                  <im:querylink text="RESULTS TABLE" skipBuilder="true">
                    <query name="" model="genomic"
                           view="${fc.key}.primaryIdentifier ${fc.key}.secondaryIdentifier ${fc.key}.length
                                 ${fc.key}:chromosomeLocation.object.primaryIdentifier ${fc.key}:chromosomeLocation.start ${fc.key}:chromosomeLocation.end ${fc.key}.dataSets.title"
                           sortOrder="${fc.key}.primaryIdentifier asc">
                      <node path="${fc.key}" type="${fc.key}">
                      </node>
                      <node path="${fc.key}.dataSets" type="DataSet">
                      </node>
                      <node path="${fc.key}.dataSets.title" type="String">
                        <constraint op="=" value="${object.title}" description=""
                                    identifier="" code="A">
                        </constraint>
                      </node>
                    </query>
                  </im:querylink>
                </c:when>

                <c:otherwise>
                  <im:querylink text="RESULTS TABLE" skipBuilder="true">
                    <query name="" model="genomic"
                           view="${fc.key}.secondaryIdentifier ${fc.key}.length
                                 ${fc.key}:chromosomeLocation.object.primaryIdentifier ${fc.key}:chromosomeLocation.start ${fc.key}:chromosomeLocation.end ${fc.key}.dataSets.title"
                           sortOrder="${fc.key}.secondaryIdentifier asc">
                      <node path="${fc.key}" type="${fc.key}">
                      </node>
                      <node path="${fc.key}.dataSets" type="DataSet">
                      </node>
                      <node path="${fc.key}.dataSets.title" type="String">
                        <constraint op="=" value="${object.title}" description=""
                                    identifier="" code="A">
                        </constraint>
                      </node>
                    </query>
                  </im:querylink>
                </c:otherwise>
              </c:choose>
            </td>
            <td align="right">
              <c:choose>
                <c:when test='${fc.key eq "EST" || fc.key eq "MNRA"}'>
                  <im:querylink text="TAB DELIMITED" skipBuilder="true" exportFormat="tab">
                    <query name="" model="genomic"
                           view="${fc.key}.primaryIdentifier ${fc.key}.secondaryIdentifier ${fc.key}.length
                                 ${fc.key}:chromosomeLocation.object.primaryIdentifier ${fc.key}:chromosomeLocation.start ${fc.key}:chromosomeLocation.end ${fc.key}.dataSets.title"
                           sortOrder="${fc.key}.primaryIdentifier asc">
                      <node path="${fc.key}" type="${fc.key}">
                      </node>
                      <node path="${fc.key}.dataSets" type="DataSet">
                      </node>
                      <node path="${fc.key}.dataSets.title" type="String">
                        <constraint op="=" value="${object.title}" description=""
                                    identifier="" code="A">
                        </constraint>
                      </node>
                    </query>
                  </im:querylink>
                </c:when>

                <c:otherwise>
                  <im:querylink text="TAB DELIMITED" skipBuilder="true" exportFormat="tab">
                    <query name="" model="genomic"
                           view="${fc.key}.secondaryIdentifier ${fc.key}.length
                                 ${fc.key}:chromosomeLocation.object.primaryIdentifier ${fc.key}:chromosomeLocation.start ${fc.key}:chromosomeLocation.end ${fc.key}.dataSets.title"
                           sortOrder="${fc.key}.secondaryIdentifier asc">
                      <node path="${fc.key}" type="${fc.key}">
                      </node>
                      <node path="${fc.key}.dataSets" type="DataSet">
                      </node>
                      <node path="${fc.key}.dataSets.title" type="String">
                        <constraint op="=" value="${object.title}" description=""
                                    identifier="" code="A">
                        </constraint>
                      </node>
                    </query>
                  </im:querylink>
                </c:otherwise>
              </c:choose>
            </td>
            <td align="right">
              <c:choose>
                <c:when test='${fc.key eq "EST" || fc.key eq "MNRA"}'>
                  <im:querylink text="GFF3" skipBuilder="true" exportFormat="gff3">
                    <query name="" model="genomic"
                           view="${fc.key}.primaryIdentifier ${fc.key}.secondaryIdentifier ${fc.key}.length
                                 ${fc.key}:chromosomeLocation.object.primaryIdentifier ${fc.key}:chromosomeLocation.start ${fc.key}:chromosomeLocation.end ${fc.key}.dataSets.title"
                           sortOrder="${fc.key}.primaryIdentifier asc">
                      <node path="${fc.key}" type="${fc.key}">
                      </node>
                      <node path="${fc.key}.dataSets" type="DataSet">
                      </node>
                      <node path="${fc.key}.dataSets.title" type="String">
                        <constraint op="=" value="${object.title}" description=""
                                    identifier="" code="A">
                        </constraint>
                      </node>
                    </query>
                  </im:querylink>
                </c:when>

                <c:otherwise>
                  <im:querylink text="GFF3" skipBuilder="true" exportFormat="gff3">
                    <query name="" model="genomic"
                           view="${fc.key}.secondaryIdentifier ${fc.key}.length
                                 ${fc.key}.dataSets.title"
                           sortOrder="${fc.key}.secondaryIdentifier asc">
                      <node path="${fc.key}" type="${fc.key}">
                      </node>
                      <node path="${fc.key}.dataSets" type="DataSet">
                      </node>
                      <node path="${fc.key}.dataSets.title" type="String">
                        <constraint op="=" value="${object.title}" description=""
                                    identifier="" code="A">
                        </constraint>
                      </node>
                    </query>
                  </im:querylink>
                </c:otherwise>
              </c:choose>
            </td>
            <td align="right">
              <c:choose>
                <c:when test='${fc.key eq "EST" || fc.key eq "MNRA"}'>
                  <im:querylink text="SEQUENCE" skipBuilder="true" exportFormat="sequence">
                    <query name="" model="genomic"
                           view="${fc.key}.primaryIdentifier ${fc.key}.secondaryIdentifier ${fc.key}.length
                                 ${fc.key}:chromosomeLocation.object.primaryIdentifier ${fc.key}:chromosomeLocation.start ${fc.key}:chromosomeLocation.end ${fc.key}.dataSets.title"
                           sortOrder="${fc.key}.primaryIdentifier asc">
                      <node path="${fc.key}" type="${fc.key}">
                      </node>
                      <node path="${fc.key}.dataSets" type="DataSet">
                      </node>
                      <node path="${fc.key}.dataSets.title" type="String">
                        <constraint op="=" value="${object.title}" description=""
                                    identifier="" code="A">
                        </constraint>
                      </node>
                    </query>
                  </im:querylink>
                </c:when>

                <c:otherwise>
                  <im:querylink text="SEQUENCE" skipBuilder="true" exportFormat="sequence">
                    <query name="" model="genomic"
                           view="${fc.key}.secondaryIdentifier ${fc.key}.length
                                 ${fc.key}:chromosomeLocation.object.primaryIdentifier ${fc.key}:chromosomeLocation.start ${fc.key}:chromosomeLocation.end ${fc.key}.dataSets.title"
                           sortOrder="${fc.key}.secondaryIdentifier asc">
                      <node path="${fc.key}" type="${fc.key}">
                      </node>
                      <node path="${fc.key}.dataSets" type="DataSet">
                      </node>
                      <node path="${fc.key}.dataSets.title" type="String">
                        <constraint op="=" value="${object.title}" description=""
                                    identifier="" code="A">
                        </constraint>
                      </node>
                    </query>
                  </im:querylink>
                </c:otherwise>
              </c:choose>
            </td>
          </tr>
        </c:if>
      </c:forEach>
      <!-- end submission loop -->
    </table>
    <br/>

     <html:form action="/submissionOverlapsAction" method="post" enctype="multipart/form-data" >

       <html:hidden property="submissionTitle" value="${object.title}" />
       <html:hidden property="submissionId" value="${object.id}" />

     <h3>Find overlapping features:</h3>

Find    <html:select styleId="typeSelector" property="overlapFindType">
          <html:option value="Gene">Genes</html:option>
          <html:option value="Exon">Exons</html:option>
          <html:option value="Intron">Introns</html:option>
          <html:option value="IntergenicRegion">IntergenicRegions</html:option>
        </html:select>
which overlap the
       <html:select styleId="typeSelector" property="overlapFeatureType">
         <c:forEach items="${featureCounts}" var="fc" varStatus="status">
           <c:if test='${fc.key != "Chromosome"}'>
             <html:option value="${fc.key}">${fc.key}</html:option>
           </c:if>  
         </c:forEach>
       </html:select>
features generated by this submission.


    <html:submit styleId="submitBag" property="overlaps">Results</html:submit>

       <br />

    <h3>Find nearby genes:</h3>    
    
Find Genes that have
       <html:select styleId="typeSelector" property="flankingFeatureType">
         <c:forEach items="${featureCounts}" var="fc" varStatus="status">
           <c:if test='${fc.key != "Chromosome"}'>
             <html:option value="${fc.key}">${fc.key}</html:option>
           </c:if>  
         </c:forEach>
       </html:select>
features generated by this submission located within         
       <html:select styleId="typeSelector" property="distance">
          <html:option value="0.5kb">.5kb</html:option>
          <html:option value="1.0kb">1kb</html:option>
          <html:option value="2.0kb">2kb</html:option>
          <html:option value="5.0kb">5kb</html:option>
          <html:option value="10.0kb">10kb</html:option>
       </html:select>

        <html:select styleId="typeSelector" property="direction">
          <html:option value="upstream">upstream</html:option>
          <html:option value="downstream">downstream</html:option>
        </html:select>
        
    <html:submit styleId="submitBag" property="flanking">Results</html:submit>

     </html:form>
    
  </div>
  
  </c:when> 
  <c:otherwise>
     <h2>This submission has no generated features.</h2>
    <br/>
  </c:otherwise>
  </c:choose>
</div>
