package org.intermine.web.struts;

/*
 * Copyright (C) 2002-2009 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.actions.TilesAction;
import org.intermine.api.profile.Profile;
import org.intermine.api.profile.TagManager;
import org.intermine.api.tag.TagTypes;
import org.intermine.api.template.TemplatePrecomputeHelper;
import org.intermine.api.template.TemplateQuery;
import org.intermine.api.template.TemplateSummariser;
import org.intermine.objectstore.ObjectStoreException;
import org.intermine.objectstore.intermine.ObjectStoreInterMineImpl;
import org.intermine.objectstore.query.Query;
import org.intermine.web.logic.Constants;
import org.intermine.web.logic.results.WebState;
import org.intermine.web.logic.session.SessionMethods;

/**
 * Tiles controller for history tile (page).
 *
 * @author Thomas Riley
 */
public class MyMineController extends TilesAction
{
    /**
     * Set up attributes for the myMine page.
     * {@inheritDoc}
     */
    @Override
    public ActionForward execute(@SuppressWarnings("unused") ComponentContext context,
                                 @SuppressWarnings("unused") ActionMapping mapping,
                                 @SuppressWarnings("unused") ActionForm form,
                                 HttpServletRequest request,
                                 @SuppressWarnings("unused") HttpServletResponse response)
        throws Exception {
        HttpSession session = request.getSession();
        TagManager tagManager = SessionMethods.getTagManager(session);
        String page = request.getParameter("page");

        Profile profile = (Profile) session.getAttribute(Constants.PROFILE);
                
        /* if the user is on a restricted page and they are not logged in, send them to the bags
         * page.  query history is not a restricted page.
         */
        if (page != null && !page.equals("history") && !profile.isLoggedIn()) {
            page = "lists";
        }

        if (!StringUtils.isEmpty(page)) {
            session.setAttribute(Constants.MYMINE_PAGE, page);
        }

        if (page != null) {
            if (page.equals("templates")) {
                // prime the tags cache so that the templates tags will be quick to access
                String userName = ((Profile) session.getAttribute(Constants.PROFILE)).getUsername();
                if (userName != null) {
                    // discard result
                    tagManager.getTags(null, null, TagTypes.TEMPLATE, userName);
                }
            }
        }
        
        WebState webState = SessionMethods.getWebState(request.getSession());        
        // get the precomputed and summarised info
        if ((request.getParameter("subtab") != null && request.getParameter("subtab").equals(
                        "templates"))
            || (webState.getSubtab("subtabmymine") != null 
                    && webState.getSubtab("subtabmymine").equals("templates"))) {
            getPrecomputedSummarisedInfo(profile, session, request);
        }
        return null;
    }

    /**
     * Retrieve the information about precomputed and summarised templates for the
     * given profile, and store it into the request
     *
     * @param profile the user Profile
     * @param session the HttpSession
     * @param request the Servlet Request
     * @throws ObjectStoreException when something goes wrong...
     */
    public static void getPrecomputedSummarisedInfo(Profile profile, HttpSession session,
            HttpServletRequest request) throws ObjectStoreException {
        Map<String, TemplateQuery> templates = profile.getSavedTemplates();
        ObjectStoreInterMineImpl os = (ObjectStoreInterMineImpl) session.getServletContext()
            .getAttribute(Constants.OBJECTSTORE);

        Map<String, String> precomputedTemplateMap = new HashMap<String, String>();
        Map<String, String> summarisedTemplateMap = new HashMap<String, String>();

        TemplateSummariser summariser = (TemplateSummariser) session.getServletContext()
            .getAttribute(Constants.TEMPLATE_SUMMARISER);
        for (TemplateQuery template : templates.values()) {
            if (template.isValid()) {
                if (session.getAttribute("precomputing_" + template.getName()) != null
                        && session.getAttribute("precomputing_" + template.getName())
                        .equals("true")) {
                    precomputedTemplateMap.put(template.getName(), "precomputing");
                } else {
                    Query query = TemplatePrecomputeHelper
                        .getPrecomputeQuery(template, new ArrayList(), null);
                    precomputedTemplateMap.put(template.getName(), Boolean.toString(os
                                .isPrecomputed(query, "template")));
                }
                if ((session.getAttribute("summarising_" + template.getName()) != null)
                        && session.getAttribute("summarising_" + template.getName())
                        .equals("true")) {
                    summarisedTemplateMap.put(template.getName(), "summarising");
                } else {
                    summarisedTemplateMap.put(template.getName(), Boolean.toString(summariser
                                .isSummarised(template)));
                }
            }
        }

        request.setAttribute("precomputedTemplateMap", precomputedTemplateMap);
        request.setAttribute("summarisedTemplateMap", summarisedTemplateMap);
    }
}
