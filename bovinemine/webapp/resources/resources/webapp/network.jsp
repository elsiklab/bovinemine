<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im"%>

<!DOCTYPE html>
<html>
<head>
<style>
ul {
    list-style-type: none;
    float: left;
    margin: 0;
    padding: 0;
}
li {
    display: inline;
    border: 4px white;
    padding: 2px 0px;
    cursor: pointer;
    noresize="noresize"
}
li:hover {
    background-color: #524745;
}
</style>
</head>
<body>
<ul>
  <a>Click here for more information on identifiers:</a>
  <li><a href="model/images/IdentifierRelationshipTableBovinemine.png" target="_blank">Identifier Relationship Table</a></li>
</ul>
     <object id="model" data="model/images/bovinemine_map.png" style="width:120%"></object>
</body>
</html>