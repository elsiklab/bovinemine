package org.intermine.api.query.codegen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.intermine.objectstore.query.ConstraintOp;
import org.intermine.pathquery.OrderElement;
import org.intermine.pathquery.OuterJoinStatus;
import org.intermine.pathquery.PathConstraint;
import org.intermine.pathquery.PathConstraintAttribute;
import org.intermine.pathquery.PathConstraintBag;
import org.intermine.pathquery.PathConstraintIds;
import org.intermine.pathquery.PathConstraintLookup;
import org.intermine.pathquery.PathConstraintLoop;
import org.intermine.pathquery.PathConstraintMultiValue;
import org.intermine.pathquery.PathConstraintNull;
import org.intermine.pathquery.PathConstraintSubclass;
import org.intermine.pathquery.PathQuery;
import org.intermine.template.TemplateQuery;
import org.intermine.util.TypeUtil;

import static java.lang.String.format;

/*
 * Copyright (C) 2002-2013 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */


/**
 * This Class generates Java source code of web service client for path query and template query.
 *
 * @author Fengyuan Hu
 * @author Alex Kalderimis
 *
 */
public class WebserviceJavaCodeGenerator implements WebserviceCodeGenerator
{
    private static final String LOOKUP_FMT = "Constraints.lookup(%s, %s, %s)";
    protected static final String TEST_STRING = "This is a Java test string...";
    protected static final String INVALID_QUERY =
            "/**\n * Invalid query.\n * =============\n * "
             + "The java code for this query could not be generated for the following reasons:\n";
    protected static final String NULL_QUERY = "The query is null.";

    protected static final String INDENT = "    ";
    protected static final String INDENT2 = INDENT + INDENT;
    protected static final String INDENT3 = INDENT + INDENT + INDENT;
    protected static final String SPACE = " ";
    protected static final String ENDL = System.getProperty("line.separator");

    private static final String ROOT_IDENTIFIER = "private static final String ROOT = ";
    private static final String TOKEN_INIT = "private static final String TOKEN = null;";
    private static final String INTERNAL_FEATURE_MSG
        = "This query makes use of a feature that is only for internal use";

    private static final String OUTER_JOIN_TITLE = "Outer Joins";
    private static final String OUTER_JOIN_EXPL
        = "Show all information about these relationships if they exist, but do not require that they exist.";

    private static final String TEMPLATE_PARAMS_INIT
        = "List<TemplateParameter> parameters = new ArrayList<TemplateParameter>();";
    private static final String TEMPLATE_PARAMS_EXPL
        = "Edit the template parameter values to get different results";

    private static final String MULTI_VALUE_FMT = "Constraints.%s(\"%s\", %s);";

    private static final String STR_FMT = "s";
    private static final String GET_ITERATOR = "Iterator<List<Object>> rows = service.getRowListIterator(";
    private static final String INIT_OUT = "PrintStream out = System.out;";

    private static class MethodNameMap extends HashMap<ConstraintOp, String> {

        public String get(ConstraintOp op) throws UnhandledFeatureException {
            String ret = super.get(op);
            if (ret == null) {
                throw new UnhandledFeatureException("Unknown constraint operator: " + op);
            }
            return ret;
        }

        private static final long serialVersionUID = -2113407677691787960L;
    };

    private static final MethodNameMap multiMethodNames = new MethodNameMap() {
        private static final long serialVersionUID = -2113407677691787960L;
        {
            put(ConstraintOp.ONE_OF, "oneOfValues");
            put(ConstraintOp.NONE_OF, "noneOfValues");
        }
    };

    private static final MethodNameMap loopMethodNames = new MethodNameMap() {
        private static final long serialVersionUID = -2113407677691787960L;
        {
            put(ConstraintOp.EQUALS, "equalToLoop");
            put(ConstraintOp.NOT_EQUALS, "notEqualToLoop");
        }
    };

    private static final MethodNameMap nullMethodNames = new MethodNameMap() {
        private static final long serialVersionUID = -2113407677691787960L;
        {
            put(ConstraintOp.IS_NULL, "isNull");
            put(ConstraintOp.IS_NOT_NULL, "isNotNull");
        }
    };

    private static final MethodNameMap methodNames = new MethodNameMap() {
        private static final long serialVersionUID = -2113407677691787960L;
        {
            put(ConstraintOp.EQUALS, "eq");
            put(ConstraintOp.NOT_EQUALS, "neq");
            put(ConstraintOp.MATCHES, "like");
            put(ConstraintOp.DOES_NOT_MATCH, "notLike");
            put(ConstraintOp.LESS_THAN, "lessThan");
            put(ConstraintOp.LESS_THAN_EQUALS, "lessThanEqualTo");
            put(ConstraintOp.GREATER_THAN, "greaterThan");
            put(ConstraintOp.GREATER_THAN_EQUALS, "greaterThanEqualTo");
            put(ConstraintOp.IN, "in");
            put(ConstraintOp.NOT_IN, "notIn");
        }
    };

    /**
     * This method will generate web service source code in Java from a path query
     * or template query.
     *
     * @param wsCodeGenInfo a WebserviceCodeGenInfo object
     * @return web service source code in a string
     */
    @Override
    public String generate(WebserviceCodeGenInfo wsCodeGenInfo) {

        PathQuery query = wsCodeGenInfo.getQuery();

        // query is null
        if (query == null) {
            return INVALID_QUERY + formatProblems(Arrays.asList(NULL_QUERY));
        }

        StringBuffer packageName = new StringBuffer();
        Set<String> javaImports = new TreeSet<String>();
        Set<String> intermineImports = new TreeSet<String>();
        StringBuffer codeBody = new StringBuffer();

        try {
            if (query instanceof TemplateQuery) {
                generateTemplateQueryCode(wsCodeGenInfo, packageName, javaImports, intermineImports, codeBody);
            } else {
                generatePathQueryCode(wsCodeGenInfo, packageName, javaImports, intermineImports, codeBody);
            }
        } catch (InvalidQueryException e) {
            return INVALID_QUERY + formatProblems(e.getProblems());
        }

        return "package " + packageName.toString() + ";" + ENDL
                + ENDL
                + importsToString(javaImports)
                + ENDL
                + importsToString(intermineImports)
                + ENDL
                + codeBody.toString();
    }

    private static String importsToString(Collection<? extends String> imports) {
        StringBuffer sb = new StringBuffer();
        for (String s : imports) {
            sb.append("import " + s + ";" + ENDL);
        }
        return sb.toString();
    }

    private static String formatProblems(Collection<? extends String> problems) {
        StringBuffer sb = new StringBuffer();
        int c = 1;
        for (String s : problems) {
            sb.append(" * " + c + ". " + s + ENDL);
            c++;
        }
        return sb.toString() + " **/";
    }

    private String getIntro(WebserviceCodeGenInfo info) {
        final String src = info.getProjectTitle();
        final String author = (StringUtils.isBlank(info.getUserName())) ? src : info.getUserName();
        StringBuffer sb = new StringBuffer();
        sb.append("/**" + ENDL);
        sb.append(" * This is a Java program to run a query from " + src + "." + ENDL);
        sb.append(" * It was automatically generated at " + new Date().toString()  + ENDL);
        sb.append(" *" + ENDL);
        if (StringUtils.isNotBlank(info.getQuery().getDescription())) {
            sb.append(" * " + info.getQuery().getDescription() + ENDL + ENDL);
        }
        sb.append(" * " + "@author " + author + ENDL);
        sb.append(" *" + ENDL);
        sb.append(" */" + ENDL);
        return sb.toString();
    }

    private String generateStartOfClass(WebserviceCodeGenInfo info, String className) {
        StringBuffer sb = new StringBuffer();
        String base = info.getServiceBaseURL() + "/service";

        // Add class code
        sb.append("public class " + className + ENDL)
            .append("{" + ENDL)
            .append(INDENT + ROOT_IDENTIFIER + qq(base) + ";" + ENDL)
            .append(ENDL);
        if (!info.isPublic()) {
            sb.append(
                INDENT + "//Authenticate your request by providing an API access token." + ENDL
              + INDENT + TOKEN_INIT + ENDL
              + ENDL);
        }
        // Add methods code
        // Add Main method
        sb.append(
              INDENT +         "/**" + ENDL
            + INDENT + SPACE + "*" + SPACE + "Perform the query and print the rows of results." + ENDL
            + INDENT + SPACE + "*" + SPACE + "@param args command line arguments" + ENDL
            + INDENT + SPACE + "*" + SPACE + "@throws IOException" + ENDL
            + INDENT + SPACE + "*/" + ENDL);
        sb.append(INDENT + "public static void main(String[] args) throws IOException {" + ENDL);
        if (info.isPublic()) {
            sb.append(INDENT2 + "ServiceFactory factory = new ServiceFactory(ROOT);" + ENDL);
        } else {
            sb.append(INDENT2 + "ServiceFactory factory = new ServiceFactory(ROOT, TOKEN);" + ENDL);
        }

        return sb.toString();
    }

    /**
     * This method will generate Java source code for PathQuery.
     * @param info The information object containing the parameters needed to generate code.
     * @param packageName the package string
     * @param javaImports the import strings from standard Java classes
     * @param intermineImports the import strings from InterMine classes
     * @param codeBody the body of the generated class
     */
    private void generatePathQueryCode(WebserviceCodeGenInfo info,
            StringBuffer packageName, Set<String> javaImports, Set<String> intermineImports,
            StringBuffer codeBody)
        throws InvalidQueryException {

        PathQuery query = info.getQuery();

        // Check the query first.
        if (!query.isValid()) {
            throw new InvalidQueryException(query.verifyQuery());
        }

        // Add package and import
        packageName.append(TypeUtil.javaisePackageName(info.getProjectTitle()));

        // Add class comments
        codeBody.append(getIntro(info));
        codeBody.append(generateStartOfClass(info, "QueryClient"));

        javaImports.add("java.io.IOException");

        intermineImports.addAll(Arrays.asList(
                "org.intermine.metadata.Model",
                "org.intermine.webservice.client.core.ServiceFactory",
                "org.intermine.pathquery.PathQuery"));


        codeBody.append(INDENT2 + "Model model = factory.getModel();" + ENDL)
                .append(INDENT2 + "PathQuery query = new PathQuery(model);" + ENDL)
                .append(ENDL);


        // Add views
        codeBody.append(INDENT2 + "// Select the output columns:" + ENDL);
        if (query.getView().size() > 1) {
            int idx = 1;
            for (String pathString : query.getView()) {
                if (idx == 1) {
                    codeBody.append(INDENT2 + "query.addViews(\"" + pathString + "\"," + ENDL);
                    idx++;
                    continue;
                }
                if (idx == query.getView().size()) {
                    codeBody.append(INDENT2 + INDENT2 + "\"" + pathString + "\");" + ENDL);
                    break;
                }
                codeBody.append(INDENT2 + INDENT2 + "\"" + pathString + "\"," + ENDL);
                idx++;
            }
        } else {
            codeBody.append(INDENT + INDENT + "query.addView(\""
                    + query.getView().iterator().next() + "\");" + ENDL);
        }

        codeBody.append(ENDL);

        // Add orderby
        if (query.getOrderBy() != null && !query.getOrderBy().isEmpty()) {
            intermineImports.add("org.intermine.pathquery.OrderDirection");
            codeBody.append(INDENT + INDENT + "// Add orderby" + ENDL);
            for (OrderElement oe : query.getOrderBy()) {
                codeBody.append(INDENT + INDENT + "query.addOrderBy(\""
                        + oe.getOrderPath() + "\", OrderDirection." + oe.getDirection() + ");"
                        + ENDL);
            }
            codeBody.append(ENDL);
        }

        // Add constraints
        List<String> constraintProblems = new ArrayList<String>();
        if (query.getConstraints() != null && !query.getConstraints().isEmpty()) {
            intermineImports.add("org.intermine.pathquery.Constraints");
            codeBody.append(INDENT + INDENT
                    + "// Filter the results with the following constraints:" + ENDL);
            if (query.getConstraints().size() == 1) {
                PathConstraint pc = query.getConstraints().entrySet()
                        .iterator().next().getKey();
                try {
                    codeBody.append(INDENT + INDENT + "query.addConstraint("
                            + pathContraintUtil(pc, javaImports) + ");"
                            + ENDL);
                } catch (UnhandledFeatureException e) {
                    constraintProblems.add(e.getMessage());
                }
            } else {
                int constraintsWithCodes = 0;
                for (Entry<PathConstraint, String> entry : query.getConstraints().entrySet()) {
                    PathConstraint pc = entry.getKey();
                    String code = entry.getValue();
                    codeBody.append(INDENT2 + "query.addConstraint(");
                    String conArg = null;
                    try {
                        conArg = pathContraintUtil(pc, javaImports);
                    } catch (UnhandledFeatureException e) {
                        constraintProblems.add(e.getMessage());
                    }
                    if (code == null) {
                        codeBody.append(conArg + ");");
                    } else {
                        constraintsWithCodes++;
                        codeBody.append(conArg + ", " + qq(code) + ");");
                    }
                    codeBody.append(ENDL);
                }

                // Add constraintLogic
                if (constraintsWithCodes > 1 && StringUtils.isNotBlank(query.getConstraintLogic())) {
                    codeBody.append(INDENT2 + "// Specify how these constraints should be combined." + ENDL);
                    codeBody.append(INDENT2 + "query.setConstraintLogic(")
                            .append("\"" + query.getConstraintLogic() + "\"")
                            .append(");" + ENDL);
                }
            }
            codeBody.append(ENDL);
        }
        if (!constraintProblems.isEmpty()) {
            throw new InvalidQueryException(constraintProblems);
        }

        // Add join status
        if (query.getOuterJoinStatus() != null && !query.getOuterJoinStatus().isEmpty()) {
            intermineImports.add("org.intermine.pathquery.OuterJoinStatus");
            codeBody.append(INDENT2 + "// " + OUTER_JOIN_TITLE + ENDL);
            codeBody.append(INDENT2 + "// " + OUTER_JOIN_EXPL + ENDL);
            for (Entry<String, OuterJoinStatus> entry : query.getOuterJoinStatus().entrySet()) {
                // There is no need to declare INNER joins
                if (entry.getValue() == OuterJoinStatus.OUTER) {
                    codeBody.append(INDENT2 + "query.setOuterJoinStatus(\""
                            + entry.getKey() + "\", OuterJoinStatus." + entry.getValue() + ");"
                            + ENDL);
                }
            }

            codeBody.append(ENDL);
        }

        // Add display results code
        handleResults(javaImports, intermineImports, codeBody, query);

        codeBody.append(INDENT + "}" + ENDL + ENDL); // END METHOD
        codeBody.append("}" + ENDL); // END CLASS
    }

    private void handleResults(Set<? super String> javaImports, Set<? super String> intermineImports,
            StringBuffer codeBody, PathQuery query) throws InvalidQueryException {

        javaImports.add("java.util.List");
        javaImports.add("java.util.Iterator");
        javaImports.add("java.io.PrintStream");

        intermineImports.add("org.intermine.webservice.client.services.QueryService");

        codeBody.append(INDENT2 + "QueryService service = factory.getQueryService();" + ENDL);
        codeBody.append(INDENT2 + INIT_OUT + ENDL);

        if (query.getView().size() == 1) {
            codeBody.append(format(INDENT2 + "out.println(%s);" + ENDL, qq(query.getView().get(0))));
        } else {
            codeBody.append(format(INDENT2 + "String format = %s;" + ENDL, qq(getFormat(query) + "\\n")));
            codeBody.append(INDENT2 + "out.printf(format, query.getView().toArray());" + ENDL);
        }

        codeBody.append(INDENT2 + GET_ITERATOR + "query);" + ENDL);
        codeBody.append(INDENT2 + "while (rows.hasNext()) {" + ENDL);
        if (query.getView().size() == 1) {
            codeBody.append(INDENT3 + "out.println(rows.next().get(0));" + ENDL);
        } else {
            codeBody.append(INDENT3 + "out.printf(format, rows.next().toArray());" + ENDL);
        }
        codeBody.append(INDENT2 + "}" + ENDL);

        codeBody.append(INDENT2 + "out.printf(\"%d rows\\n\", service.getCount(query));" + ENDL);
    }

    private String getFormat(PathQuery query) throws InvalidQueryException {
        List<String> parts = new ArrayList<String>();
        int width = 0;
        int span = 100;
        int cellCount = query.getView().size();
        // Cells smaller than 10 characters aren't worth it.
        while (width < 10) {
            width = (span - cellCount * 3) / cellCount;
            span += 20;
        }
        String prefix = "%-" + width;
        for (int i = 0; i < query.getView().size(); i++) {
            parts.add(prefix + "." + width + STR_FMT );
        }
        return StringUtils.join(parts, " | ");
    }

    /**
     * This method will generate Java source code for TemplateQuery.
     * @param info The bundle of information needed to construct the generated code.
     * @param packageName The name of the package should be inserted here.
     * @param javaImports the import strings from standard Java classes.
     * @param intermineImports the import strings from InterMine classes.
     * @param codeBody The body of the class definition.
     * @throws InvalidQueryException If the query is invalid.
    */
    private void generateTemplateQueryCode(WebserviceCodeGenInfo info,
            StringBuffer packageName,
            Set<String> javaImports, Set<String> intermineImports,
            StringBuffer codeBody)
        throws InvalidQueryException {

        TemplateQuery template = (TemplateQuery) info.getQuery();

        // Check the query first.
        if (!template.isValid()) {
            throw new InvalidQueryException(template.verifyQuery());
        }

        String srcClassName = TypeUtil.javaiseClassName(template.getName());

        // Add package and import
        packageName.append(TypeUtil.javaisePackageName(info.getProjectTitle()));

        codeBody.append(getIntro(info));
        codeBody.append(generateStartOfClass(info, "TemplateQuery" + srcClassName));

        intermineImports.add("org.intermine.webservice.client.core.ServiceFactory");
        intermineImports.add("org.intermine.webservice.client.template.TemplateParameter");
        javaImports.addAll(Arrays.asList("java.util.List", "java.util.ArrayList", "java.io.IOException"));

        codeBody.append(INDENT2 + "// " + TEMPLATE_PARAMS_EXPL + ENDL);
        codeBody.append(INDENT2 + TEMPLATE_PARAMS_INIT + ENDL);

        // Only editable constraints will be generated
        List<PathConstraint> editableConstraints = template.getEditableConstraints();
        if (editableConstraints == null || editableConstraints.isEmpty()) {
            throw new InvalidQueryException("This template has no editable constraints");
        }
        List<String> constraintProblems = new ArrayList<String>();
        for (PathConstraint pc : editableConstraints) {
            String constraintDes = template.getConstraintDescription(pc);
            if (StringUtils.isNotBlank(constraintDes)) {
                codeBody.append(INDENT2 + "// " + constraintDes + ENDL);
            }
            String code = template.getConstraints().get(pc);
            try {
                codeBody.append(INDENT2 + templateConstraintUtil(pc, code, javaImports) + ENDL);
            } catch (UnhandledFeatureException e) {
                constraintProblems.add(e.getMessage());
            }
        }
        if (!constraintProblems.isEmpty()) {
            throw new InvalidQueryException(constraintProblems);
        }

        codeBody.append(ENDL);
        intermineImports.add("org.intermine.webservice.client.services.TemplateService");
        javaImports.add("java.util.Iterator");

        // Add display results code
        String[] lines = new String[] {
                "// Name of template",
                format("String name = %s;", qq(template.getName())),
                "// Template Service - use this object to fetch results.",
                "TemplateService service = factory.getTemplateService();",
                "// Format to present data in fixed width columns",
                format("String format = %s;", qq(getFormat(template)))
        };
        codeBody.append(INDENT2).append(StringUtils.join(lines, ENDL + INDENT2)).append(ENDL + ENDL);

        StringBuffer currentLine = new StringBuffer(INDENT2 + "System.out.printf(format,");
        Iterator<String> viewIt = template.getView().iterator();
        while (viewIt.hasNext()) {
            String view = qq(viewIt.next());
            if (viewIt.hasNext()) {
                view += ",";
            }
            if (currentLine.length() + view.length() > 100) {
                codeBody.append(currentLine.toString() + ENDL);
                currentLine = new StringBuffer(INDENT2 + INDENT);
            }
            if (StringUtils.isNotBlank(currentLine.toString())) {
                currentLine.append(" ");
            }
            currentLine.append(view);
        }
        codeBody.append(currentLine.toString() + ");" + ENDL);
        codeBody.append(INDENT2).append(StringUtils.join(PROCESS_TEMPLATE_RES_LINES, ENDL + INDENT2)).append(ENDL);
        
        codeBody.append(INDENT2).append("System.out.printf(\"%d rows\\n\", service.getCount(name, parameters));" + ENDL);

        codeBody.append(INDENT + "}" + ENDL + ENDL); // END METHOD
        codeBody.append("}" + ENDL); // END CLASS

    }

    private static final String[] PROCESS_TEMPLATE_RES_LINES = new String[] {
        "Iterator<List<Object>> rows = service.getRowListIterator(name, parameters);",
        "while (rows.hasNext()) {",
        INDENT + "System.out.printf(format, rows.next().toArray());",
        "}"
    };

    /**
     * Format a list at a certain indentation, so
     * <pre>
     *  ["a string", "another string", "yet another string"]
     * </pre>
     * Becomes:
     * <pre>
     *  "\"a string\", "\"another string\",\n    \"yet another string\""
     * </pre>
     */
    private String formatList(Collection<String> items, int indentation) {
        String indent = "";
        for (int i = 0; i < indentation; i++) {
            indent += INDENT;
        }
        StringBuffer sb = new StringBuffer();
        StringBuffer currentLine = new StringBuffer();
        Iterator<String> it = items.iterator();
        int limit = 25;
        while (currentLine.length() < limit && it.hasNext()) {
            String next = "\"" + it.next() + "\"";
            if (it.hasNext()) {
                next += ", ";
            }
            if (next.length() >= limit) {
                sb.append(currentLine.toString().trim());
                currentLine = new StringBuffer(indent);
                limit = 100;
            }
            currentLine.append(next);
            if (currentLine.length() >= limit) {
                sb.append(currentLine.toString().trim());
                currentLine = new StringBuffer(indent);
                limit = 100;
            }
        }
        if (currentLine.length() > 0 && !indent.equals(currentLine.toString())) {
            sb.append(currentLine.toString().trim());
        }
        return sb.toString();
    }

    private String produceCallToConstraints(String method, String path, String value) {
        return format("Constraints.%s(%s, %s)", method, qq(path), qq(value));
    }

    private String produceCallToConstraints(String method, String path) {
        return format("Constraints.%s(%s)", method, qq(path));
    }

    /**
     * This method helps to generate constraint source code for PathQuery
     * @param pc PathConstraint object
     * @return a string like "Constraints.lessThan(\"Gene.length\", \"1000\")"
     * @throws UnhandledFeatureException
     */
    private String pathContraintUtil(PathConstraint pc, Set<String> javaImports) throws UnhandledFeatureException {
        // Generate a string like "Constraints.lessThan(\"Gene.length\", \"1000\")"
        // Ref to Constraints

        if (pc instanceof PathConstraintAttribute || pc instanceof PathConstraintBag) {
            return handleConstraintWithValue(pc);
        } else if (pc instanceof PathConstraintLookup) {
            return handleConstraint((PathConstraintLookup) pc);
        } else if (pc instanceof PathConstraintIds) {
            throw new UnhandledFeatureException(INTERNAL_FEATURE_MSG
                + " (" + TypeUtil.unqualifiedName(pc.getClass().getName()) + ")");
        } else if (pc instanceof PathConstraintMultiValue) {
            return handleConstraint((PathConstraintMultiValue) pc, javaImports);
        } else if (pc instanceof PathConstraintNull) {
            return handleConstraint((PathConstraintNull) pc);
        } else if (pc instanceof PathConstraintLoop) {
            return handleConstraint((PathConstraintLoop) pc);
        } else if (pc instanceof PathConstraintSubclass) {
            return handleConstraint((PathConstraintSubclass) pc);
        }

        throw new UnhandledFeatureException("Unknown constraint type " + pc.getClass().getName());
    }

    private String handleConstraint(PathConstraintSubclass pc) {
        return produceCallToConstraints("type", pc.getPath(), pc.getType());
    }

    private String handleConstraint(PathConstraintLoop pc) throws UnhandledFeatureException {
        final String method = loopMethodNames.get(pc.getOp());
        return produceCallToConstraints(method, pc.getPath(), pc.getLoopPath());
    }

    private String handleConstraint(PathConstraintNull pc) throws UnhandledFeatureException {
        return produceCallToConstraints(nullMethodNames.get(pc.getOp()), pc.getPath());
    }

    private String handleConstraint(PathConstraintMultiValue pc, Set<? super String> imports) throws UnhandledFeatureException {
        imports.add("java.util.Arrays");
        final String values = "Arrays.asList(" + formatList(pc.getValues(), 2) + ")";
        return format(MULTI_VALUE_FMT, multiMethodNames.get(pc.getOp()), pc.getPath(), values);
    }

    private String handleConstraint(PathConstraintLookup pc) throws UnhandledFeatureException {
        return format(LOOKUP_FMT, qq(pc.getPath()), qq(pc.getValue()), qq(pc.getExtraValue()));
    }

    private String handleConstraintWithValue(PathConstraint pc) throws UnhandledFeatureException {
        ConstraintOp op = pc.getOp();
        final String value = PathConstraint.getValue(pc);
        if (value == null) {
            throw new IllegalArgumentException("The constraint must have a value.");
        }
        return produceCallToConstraints(methodNames.get(op), pc.getPath(), value);
    }

    /**
     * Double-Quote a string, but return the string <code>null</code> rather
     * than <code>"null"</code> if the input is null.
     * @param input
     * @return A quoted string.
     */
    private static String qq(String input) {
        if (input == null) {
            return "null";
        } else {
            return "\"" + input + "\"";
        }
    }

    /**
     * This method helps to generate Template Parameters (predefined constraints) source code for
     * TemplateQuery
     * @param pc PathConstraint object
     * @return a line of source code
     */
    private String templateConstraintUtil(PathConstraint pc, String code, Set<String> javaImports) throws UnhandledFeatureException {
        String className = TypeUtil.unqualifiedName(pc.getClass().toString());

        if ("PathConstraintIds".equals(className)) {
            throw new UnhandledFeatureException(INTERNAL_FEATURE_MSG + "(" + className + ")");
        } else if ("PathConstraintSubclass".equals(className)) {
            throw new UnhandledFeatureException("Type constraints cannot be used with templates.");
        }

        String path = qq(pc.getPath());
        String op = qq(pc.getOp().toString());

        String prefix = "parameters.add(new TemplateParameter(" + path + ", " + op;
        code = qq(code);

        Collection<String> values = PathConstraint.getValues(pc);
        if (values == null) {
            String value = qq(PathConstraint.getValue(pc));
            String extraValue = qq(PathConstraint.getExtraValue(pc));
            return prefix + ", " + value + ", " + extraValue + ", " + code + "));";
        } else {
            javaImports.add("java.util.Arrays");
            String[] quoted = new String[values.size()];
            int i = 0;
            for (String s: values) {
                quoted[i] = qq(s);
                i++;
            }
            return prefix + ", Arrays.asList(" + StringUtils.join(quoted, ", ") + "), " + code + "));";
        }
    }
}
