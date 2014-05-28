package org.intermine.web.filters;

/*
 * Copyright (C) 2002-2014 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.log4j.Logger;
import org.intermine.web.context.InterMineContext;
import org.intermine.web.logic.Constants;

/**
 * Return responses tagged with the release version.
 *
 * This class is designed to aid caching of resources that do not change between releases (specifically
 * model based ones).
 *
 * @author Alex Kalderimis
 *
 */
public class ReleaseEtagFilter implements Filter {

	private final static Logger LOG = Logger.getLogger(ReleaseEtagFilter.class);
	private static String RELEASE = null;
	private final static long START_UP = System.currentTimeMillis();

	@Override
	public void doFilter(
			ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String etag = getRelease();
        String zipEtag = etag + "-gzip";

		HttpServletResponse inner = (HttpServletResponse) response;
        HttpServletRequest req = ((HttpServletRequest) request);

        String ifNoneMatch = req.getHeader("If-None-Match"); 
        long ifModSince = req.getDateHeader("If-Modified-Since");
        LOG.info("etag = " + etag + ", START_UP = " + START_UP + " , ifNoneMatch = " + ifNoneMatch + ", ifModSince = " + ifModSince);

        if (etag.equals(ifNoneMatch) || zipEtag.equals(ifNoneMatch) || (ifModSince == START_UP)) {
            inner.setStatus(304);
        } else {
            inner.setHeader("ETag", etag);
            inner.setHeader("Cache-Control", "public,max-age=600");
            inner.setDateHeader("Last-Modified", START_UP);
            chain.doFilter(request, new EtagIgnorer(inner));
        }
	}
	
	public static String getRelease() {
		if (RELEASE == null) {
			RELEASE = String.format("%s-%s",
                    InterMineContext.getWebProperties().getProperty("project.releaseVersion"),
                    Constants.WEB_SERVICE_VERSION);
		}
		return RELEASE;
	}
	
	@Override
	public void destroy() {
		// Nothing to do
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// Nothing to do.
		
	}
	
	private class EtagIgnorer extends HttpServletResponseWrapper
	{

		public EtagIgnorer(HttpServletResponse response) {
			super(response);
		}
		
		@Override
		public void setHeader(String name, String value) {
            if ("etag".equalsIgnoreCase(name)
            	|| "cache-control".equalsIgnoreCase(name)
                || ("pragma".equalsIgnoreCase(name) && "no-cache".equals(value))
            	|| "Last-Modified".equalsIgnoreCase(name)) {
                LOG.debug("Ignoring cache header: " + name + " " + value);
            } else {
                super.setHeader(name, value);
            }
        }
		
	}

}
