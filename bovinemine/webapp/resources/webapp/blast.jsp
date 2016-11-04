<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im"%>
<%@ taglib uri="/WEB-INF/functions.tld" prefix="imf" %>

<html:xhtml/>
	<div align="center">
		<a href="http://bovinegenome.org/bgd_blast_release_1.2/" target="_blank">Fullscreen mode</a>
	</div>
	<div style="overflow:scroll">
		<object id="bgd_sequenceserver" data="http://bovinegenome.org/bgd_blast_release_1.2/" style="width:100%;height:1700px"></object>
	</div>
