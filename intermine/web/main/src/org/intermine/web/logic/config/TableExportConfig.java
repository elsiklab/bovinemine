package org.intermine.web.logic.config;

/*
 * Copyright (C) 2002-2008 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

/**
 * Configuration information for exporting.
 *
 * @author Kim Rutherford
 */

public class TableExportConfig
{
    String id, className;

    /**
     * Return the id of this TableExportConfig.
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id of this TableExportConfig
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the class name of the exporter eg. org.intermine.bio.web.export.SequenceHttpExporter
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * Set the className of this TableExportConfig
     * @param className the new className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * {@inheritDoc}
     *
     * @param obj the Object to compare with
     * @return true if this is equal to obj
     */
    public boolean equals (Object obj) {
        if (obj instanceof TableExportConfig) {
            TableExportConfig exporterObj = (TableExportConfig) obj;
            return exporterObj.id.equals(id)
                && exporterObj.className.equals(className);
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return the hashCode for this TableExportConfig object
     */
    public int hashCode() {
        return id.hashCode() * 5 + 3 * className.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "<tableExportConfig id=\"" + id
            + "\" className=\"" + className + "\"/>";
    }
}
