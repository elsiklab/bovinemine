package org.intermine.bio.web.model;

/*
 * Copyright (C) 2002-2017 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

/**
 * This Java bean stores the information for span validation use.
 *
 * @author Fengyuan Hu
 *
 */
public class ChromosomeInfo
{
    private String orgName;
    private String chrPID;
    private String chrPIDLowerCase;
    private String chrSecondaryIdentifier;
    private String chrTertiaryIdentifier;
    private String chrName;
    private String chrAssembly;
    private Integer chrLength = null;

    /**
     * @return the orgName
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName the orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @return the chrPID
     */
    public String getChrPID() {
        return chrPID;
    }

    /**
     * @param chrPID the chrPID to set
     */
    public void setChrPID(String chrPID) {
        this.chrPID = chrPID;
        this.chrPIDLowerCase = chrPID.toLowerCase();
    }

    /**
     *
     * @return
     */
    public String getChrSecondaryIdentifier() {
        return chrSecondaryIdentifier;
    }
    public String getChrTertiaryIdentifier() {
        return chrTertiaryIdentifier;
    }

    /**
     *
     * @param chrSecondaryIdentifier
     */
    public void setChrSecondaryIdentifier(String chrSecondaryIdentifier) {
        if(chrSecondaryIdentifier != null) {
            this.chrSecondaryIdentifier = chrSecondaryIdentifier;
        }
        else {
            this.chrSecondaryIdentifier = "";
        }
    }

    public void setChrTertiaryIdentifier(String chrTertiaryIdentifier) {
        if(chrTertiaryIdentifier != null) {
            this.chrTertiaryIdentifier = chrTertiaryIdentifier;
        }
        else {
            this.chrTertiaryIdentifier = "";
        }
    }


    /**
     *
     * @return
     */
    public String getChrName() {
        return chrName;
    }

    /**
     *
     * @param chrName
     */
    public void setChrName(String chrName) {
        if(chrName != null) {
            this.chrName = chrName;
        }
        else {
            this.chrName = "";
        }
    }
    public String getChrAssembly() {
        return chrAssembly;
    }
     public void setChrAssembly(String chrAssembly) {
        this.chrAssembly = chrAssembly;
    }


    /**
     * @return the chrLength
     */
    public Integer getChrLength() {
        return chrLength;
    }

    /**
     * @param chrLength the chrLength to set
     */
    public void setChrLength(Integer chrLength) {
        this.chrLength = chrLength;
    }

    /**
     * @return the chrPIDLowerCase
     */
    public String getChrPIDLowerCase() {
        return chrPIDLowerCase;
    }
}

