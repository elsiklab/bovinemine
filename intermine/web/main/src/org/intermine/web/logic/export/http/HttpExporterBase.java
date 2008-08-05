package org.intermine.web.logic.export.http;

/*
 * Copyright (C) 2002-2008 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.util.ArrayList;
import java.util.List;

import org.intermine.path.Path;
import org.intermine.web.logic.RequestUtil;
import org.intermine.web.logic.export.ExportException;
import org.intermine.web.logic.export.ExportHelper;
import org.intermine.web.logic.export.Exporter;
import org.intermine.web.logic.results.PagedTable;
import org.intermine.web.struts.TableExportForm;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract class that implements basic functionality common for exporters
 * exporting table with results in simple format like comma separated format.
 * The business logic of export is performed with exporter obtained via
 * getExport() method and so each subclass can redefine it overwriting this method.
 * @author Jakub Kulaviak
 **/
public abstract class HttpExporterBase implements TableHttpExporter
{

    /**
     * Constructor.
     */
    public HttpExporterBase() { }

    /**
     * @param pt PagedTable
     * @return true if given PagedTable can be exported with this exporter
     */
    public boolean canExport(PagedTable pt) {
        return true;
    }

    /**
     * Perform export.
     * @param pt exported PagedTable
     * @param request request
     * @param response response
     */
    public void export(PagedTable pt, HttpServletRequest request,
                       HttpServletResponse response, TableExportForm form) {

        OutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            throw new ExportException("Export failed.", e);
        }
        setResponseHeader(response);
        String separator;
        if (RequestUtil.isWindowsClient(request)) {
            separator = Exporter.WINDOWS_SEPARATOR;
        } else {
            separator = Exporter.UNIX_SEPARATOR;
        }
        Exporter exporter = getExporter(out, separator);
        exporter.export(pt.getResultElementRows());
        if (exporter.getWrittenResultsCount() == 0) {
            throw new ExportException("Nothing was found for export.");
        }
    }

    /**
     * For TableHttpExporter we always return an empty list because all columns and classes are
     * equal for this exporter.
     * {@inheritDoc}
     */
    public List<Path> getExportClassPaths(@SuppressWarnings("unused") PagedTable pt) {
        return new ArrayList<Path>();
    }

    /**
     * The intial export path list is just the paths from the columns of the PagedTable.
     * {@inheritDoc}
     */
    public List<Path> getInitialExportPaths(PagedTable pt) {
        return ExportHelper.getColumnPaths(pt);
    }

    /**
     * @param out output stream
     * @param separator line separator
     * @return exporter that will perform the business logic of export.
     */
    protected abstract Exporter getExporter(OutputStream out, String separator);

    /**
     * Sets header and content type of result in response.
     * @param response response
     */
    protected abstract void setResponseHeader(HttpServletResponse response);

}
