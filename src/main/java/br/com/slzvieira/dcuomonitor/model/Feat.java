/*
 * @(#)Feat.java 1.00 22/10/2014 Copyright 2014 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.model;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 22/10/2014 - sandro.vieira - Implementacao.
 */
public class Feat implements Comparable<Feat> {

    private String parentCategory;
    private String category;
    private String name;
    private String description;
    private String imagePath;
    private String predicate;

    /**
     * TODO DOCUMENT ME!
     * @return the parentCategory
     */
    public String getParentCategory() {
        return parentCategory == null ? category : parentCategory;
    }

    /**
     * TODO DOCUMENT ME!
     * @param parentCategory the parentCategory to set
     */
    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the category
     */
    public String getCategory() {
        return parentCategory == null ? "General" : category;
    }

    /**
     * TODO DOCUMENT ME!
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     */
    public String getFullCategoryName() {
        return getParentCategory() + "/" + getCategory();
    }

    /**
     * TODO DOCUMENT ME!
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * TODO DOCUMENT ME!
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * TODO DOCUMENT ME!
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * TODO DOCUMENT ME!
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the predicate
     */
    public String getPredicate() {
        return predicate;
    }

    /**
     * TODO DOCUMENT ME!
     * @param predicate the predicate to set
     */
    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
//        return String.format("%s\t%s\t%s\t%s\t%s", getParentCategory(), getCategory(), getName(), getDescription(), getPredicate());
        return String.format("%-20s %-25s %-60s %s", getParentCategory(), getCategory(), getName(), getDescription());
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Feat another) {
        int compare = this.getFullCategoryName().compareTo(another.getFullCategoryName());
        if (compare != 0) return compare;
        return this.getName().compareTo(another.getName());
    }
}
