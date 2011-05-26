package org.intermine.web.logic.results;

/*
 * Copyright (C) 2002-2011 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.intermine.api.InterMineAPI;
import org.intermine.model.InterMineObject;
import org.intermine.util.CacheMap;
import org.intermine.web.logic.config.WebConfig;
import org.intermine.web.logic.session.SessionMethods;

/**
 * A factory for ReportObjects.  If get() is called and the is no existing ReportObject for the
 * argument InterMineObject, one is created, saved and returned.
 *
 * @author Radek Stepan (Kim Rutherford more like)
 */

public class ReportObjectFactory extends CacheMap<InterMineObject, ReportObject>
{
    private HttpSession session = null;

    /**
     * Create a new ReportObjectCache for the given session.
     * @param session the HTTP session
     */
    public ReportObjectFactory(HttpSession session) {
        this.session = session;
    }

    /**
     * Always returns true because get always returns an Object.
     * {@inheritDoc}
     */
    public boolean containsKey(Object key) {
        return true;
    }

    /**
     * Get a ReportObject for the given InterMineObject.  If there is no existing ReportObject for
     * the argument InterMineObject, one is created, saved and returned.
     * {@inheritDoc}
     * @param object an InterMineObject to make a ReportObject for
     * @return a ReportObject
     */
    @Override public synchronized ReportObject get(Object object) {
        InterMineObject imObj = (InterMineObject) object;
        ReportObject reportObject = super.get(imObj);

        if (reportObject == null) {
            try {
                final InterMineAPI im = SessionMethods.getInterMineAPI(session);
                ServletContext servletContext = session.getServletContext();
                WebConfig webConfig = SessionMethods.getWebConfig(servletContext);
                Properties webProperties = SessionMethods.getWebProperties(servletContext);
                reportObject = new ReportObject(imObj, webConfig, im, webProperties);
            } catch (Exception e) {
                throw new RuntimeException("Failed to make a reportObject", e);
            }

            super.put(imObj, reportObject);
        }
        return reportObject;
    }

    /**
     * Disable this method.
     *
     * @param key Do not use
     * @param value Do not use
     * @return never
     */
    public ReportObject put(InterMineObject key, ReportObject value) {
        throw new UnsupportedOperationException("Put called on ReportObjectFactory");
    }
}
