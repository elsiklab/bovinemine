<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/string-1.1" prefix="str" %>


<!-- minePathwaysDisplayer.jsp -->
<script type="text/javascript" charset="utf-8">
function getFriendlyMinePathways(mine, orthologues) {

    AjaxServices.getFriendlyMinePathways(mine, orthologues, function(pathways) {
        im.log(pathways);
        var jSONObject = jQuery.parseJSON(pathways);
        // switch off loading img
        jQuery('#intermine_pathways_' + mine).toggleClass('loading');
        if (jSONObject['results'].length > 0) {
            generate(jSONObject, "#intermine_pathways_" + mine);
        } else {
            jQuery("#intermine_pathways_" + mine).html("No pathways found.");
        }
    });
}

function generate(jSONObject, target) {
          var url = '';
          im.log(jSONObject);
          if (jSONObject['mineURL'] != undefined) {
            url = jSONObject['mineURL'];
          }

          if (jSONObject['results'] != undefined) {
             jQuery.each(jSONObject['results'], function(index, pathway) {
                  jQuery('<li/>', {
                    'html': jQuery('<a/>', {
                    'href': url + "/report.do?id=" + pathway['id'],
                    'text': pathway['name'],
                    'target': '_blank'
                })
                }).appendTo(target);
             });
          }
}

</script>

<div id="mine-pathway-displayer" class="collection-table">
<h3>Pathways</h3>


    <!-- one column for each mine -->
    <table>
      <tbody>
      <tr>
            <!-- this mine -->
            <th><c:out value="${WEB_PROPERTIES['project.title']}" escapeXml="false"/></th>

            <!-- other mines -->
            <c:forEach items="${mines}" var="entry">
                 <c:set var="mine" value="${entry.key}" />
                 <th>${mine.name}</th>
            </c:forEach>
      </tr>

      <tr>
          <!-- this mine -->
        <td>

            <c:choose>
              <c:when test="${empty gene.pathways}">
                No pathways found
              </c:when>
              <c:otherwise>
            <c:forEach items="${gene.pathways}" var="pathway">
                <html:link href="/${WEB_PROPERTIES['webapp.path']}/report.do?id=${pathway.id}"><c:out value="${pathway.name}"/></html:link><br/>
            </c:forEach>
            </c:otherwise>
            </c:choose>
        </td>

        <!-- other mines -->
        <c:forEach items="${mines}" var="entry">
            <td>
            <c:set var="mine" value="${entry.key}" />
            <div id="intermine_pathways_${mine.name}" class="loading"></div>
            <script type="text/javascript" charset="utf-8">
                getFriendlyMinePathways('${mine.name}', '${entry.value}');
            </script>
            </td>
        </c:forEach>
      </tr>
      </tbody>
    </table>

</div>
<!-- /publicationCountsDisplayer.jsp -->
