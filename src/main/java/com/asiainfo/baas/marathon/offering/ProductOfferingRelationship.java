package com.asiainfo.baas.marathon.offering;

import com.asiainfo.baas.marathon.baseType.*;

/**
 * A significant connection or similarity between two or more ProductOfferings. For example, the relationship between a provider's ProductOffering and a supplier/partner's ProductOffering used to fulfill the provider's ProductOffering; a service provider offers various photos for download and printing...a print shop prints them for the provider and considers one photo (ProductOffering) the same as any other from a pricing perspective...one partners' photo offering is related to many of the provider's photos.
 */
public class ProductOfferingRelationship {

    private ProductOffering targetOffering;
    private ProductOffering srcOffering;
    /**
     * A categorization of the relationship, such as supplier/partner equivalent, alternate, and so forth.
     */
    private String typeRelationship;
    /**
     * The period during which the relationship is applicable.
     */
    private TimePeriod validFor;

    public ProductOffering getTargetOffering() {
        return this.targetOffering;
    }

    public void setTargetOffering(ProductOffering targetOffering) {
        this.targetOffering = targetOffering;
    }

    public ProductOffering getSrcOffering() {
        return this.srcOffering;
    }

    public void setSrcOffering(ProductOffering sourceOffering) {
        this.srcOffering = sourceOffering;
    }

    public String getTypeRelationship() {
        return this.typeRelationship;
    }

    public void setTypeRelationship(String typeRelationship) {
        this.typeRelationship = typeRelationship;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param srcProdOffering
     * @param targetProdOffering
     * @param type
     * @param validFor
     */
    public ProductOfferingRelationship(ProductOffering srcProdOffering, ProductOffering targetProdOffering, String type, TimePeriod validFor) {
    	this.srcOffering = srcProdOffering;
    	this.targetOffering = targetProdOffering;
    	this.typeRelationship = type;
    	this.validFor = validFor;
    	
    }

}