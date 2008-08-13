<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- sequenceExportOptions.jsp -->
<html:xhtml/>

Sequence to export:

<c:forEach items="${exportClassPaths}" var="entry">
  <c:set var="path" value="${entry.key}"/>
  <c:choose>
    <c:when test="${fn:length(exportClassPaths) == 1}">
      ${entry.value}
    </c:when>
    <c:otherwise>
      <html:radio property="sequencePath" value="${path}"/>${entry.value}<br/>
    </c:otherwise>
  </c:choose>
</c:forEach>

<!-- /sequenceExportOptions.jsp -->
