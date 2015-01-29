<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- footer.jsp -->
<br/>
<br/>
<br/>

<div class="body" align="center" style="clear:both">
    <!-- contact -->
    <c:if test="${pageName != 'contact'}">
        <div id="contactFormDivButton">
            <im:vspacer height="11" />
            <div class="contactButton">
                <a href="#" onclick="showContactForm();return false">
                    <b><fmt:message key="feedback.title"/></b>
                </a>
            </div>
        </div>
        <div id="contactFormDiv" style="display:none;">
            <im:vspacer height="11" />
            <tiles:get name="contactForm" />
        </div>
    </c:if>
    <br/>

    <!-- funding -->
    <div id="funding-footer">
        <fmt:message key="funding" />
        <br/>
        <br/>

        <!-- powered -->
        <p>Powered by</p>
        <a target="new" href="http://intermine.org" title="InterMine">
            <img src="images/icons/intermine-footer-logo.png" alt="InterMine logo" />
        </a>
    </div>
</div>


<!-- cam logo and links -->
<!--
<div class="body bottom-footer">

    <ul class="footer-links">
        <li><a href="http://www.intermine.org" target="_blank">InterMine</a></li>
        <li><a href="http://www.flymine.org" target="_blank">FlyMine</a></li>
        <li><a href="http://www.modmine.org" target="_blank">modMine</a></li>
        <li><a href="http://www.mousemine.org" target="_blank">MouseMine</a></li>
        <li><a href="http://ratmine.mcw.edu/ratmine" target="_blank">RatMine</a></li>
        <li><a href="http://www.wormbase.org/tools/wormmine" target="_blank">WormMine</a></li>
        <li><a href="http://yeastmine.yeastgenome.org" target="_blank">YeastMine</a></li>
        <li><a href="http://www.zebrafishmine.org" target="_blank">ZebrafishMine</a></li>
	<li><a href="http://apps.araport.org/thalemine" target="_blank">ThaleMine</a></li>
    </ul>

    <div style="clear:both"></div>
</div>
-->

<!-- /footer.jsp -->