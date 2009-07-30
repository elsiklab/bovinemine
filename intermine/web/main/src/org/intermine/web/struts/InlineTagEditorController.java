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

import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.actions.TilesAction;
import org.intermine.metadata.ClassDescriptor;
import org.intermine.metadata.CollectionDescriptor;
import org.intermine.metadata.FieldDescriptor;
import org.intermine.metadata.ReferenceDescriptor;
import org.intermine.web.logic.Constants;
import org.intermine.web.logic.bag.InterMineBag;
import org.intermine.web.logic.profile.Profile;
import org.intermine.web.logic.profile.ProfileManager;
import org.intermine.web.logic.profile.TagManager;
import org.intermine.web.logic.session.SessionMethods;
import org.intermine.web.logic.tagging.TagTypes;
import org.intermine.web.logic.template.TemplateQuery;

/**
 * Controller for the inline tag editing tile
 * @author Thomas Riley
 */
public class InlineTagEditorController extends TilesAction
{

    private static int counter = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionForward execute(ComponentContext context,
                                 @SuppressWarnings("unused") ActionMapping mapping,
                                 @SuppressWarnings("unused") ActionForm form,
                                 HttpServletRequest request,
                                 @SuppressWarnings("unused") HttpServletResponse response)
        throws Exception {
        // Retrieve the taggable thing from the context
        Object taggable = context.getAttribute("taggable");
        Profile profile = (Profile) request.getSession().getAttribute(Constants.PROFILE);

        String tagged = null;
        String type = null;

        if (taggable instanceof FieldDescriptor) {
            FieldDescriptor fd = (FieldDescriptor) taggable;
            tagged = fd.getClassDescriptor().getUnqualifiedName() + "." + fd.getName();
            if (taggable instanceof CollectionDescriptor) {
                type = "collection";
            } else if (taggable instanceof ReferenceDescriptor) {
                type = "reference";
            } else {
                type = "attribute";
            }
        } else if (taggable instanceof TemplateQuery) {
            type = TagTypes.TEMPLATE;
            tagged = ((TemplateQuery) taggable).getName();
        } else if (taggable instanceof ClassDescriptor) {
            type = TagTypes.CLASS;
            tagged = ((ClassDescriptor) taggable).getName();
        } else if (taggable instanceof InterMineBag) {
            type = TagTypes.BAG;
            tagged = ((InterMineBag) taggable).getName();
        }

        request.setAttribute("editorId", createUniqueEditorId(tagged));
        request.setAttribute("type", type);

        Set<String> currentTags;
        Set<String> availableTags;
        TagManager tagManager = SessionMethods.getTagManager(request.getSession());
        if (profile.isLoggedIn()) {
            currentTags = tagManager.getObjectTagNames(tagged, type, profile.getUsername());
            availableTags = tagManager.getUserTagNames(type, profile.getUsername());
        } else {
            currentTags = new TreeSet<String>();
            availableTags = new TreeSet<String>();
        }
        request.setAttribute("currentTags", currentTags);
        request.setAttribute("availableTags", availableTags);
        return null;
    }

    private String createUniqueEditorId(String tagged) {
        return tagged + "@" + counter++;
    }
}


