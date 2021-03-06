package com.asiainfo.baas.marathon.specification;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.asiainfo.baas.marathon.baseType.*;
import com.asiainfo.baas.marathon5.common.CommonUtils;

/**
 * A migration, substitution, dependency, or exclusivity relationship
 * between/among ProductSpecifications.
 */
public class ProductSpecificationRelationship {

    private ProductSpecification targetProdSpec;
    private ProductSpecification srcProdSpec;
    /**
     * A categorization of the relationship, such as migration, substitution,
     * dependency, exclusivity.
     */
    private String type;
    /**
     * The period for which the relationship is applicable.
     */
    private TimePeriod validFor;

    public ProductSpecification getTargetProdSpec() {
        return this.targetProdSpec;
    }

    public void setTargetProdSpec(ProductSpecification targetProdSpec) {
        this.targetProdSpec = targetProdSpec;
    }

    public ProductSpecification getSrcProdSpec() {
        return this.srcProdSpec;
    }

    public void setSrcProdSpec(ProductSpecification srcSpec) {
        this.srcProdSpec = srcSpec;
    }

    /**
     * 
     * @param srcSpec
     * @param targetSpec
     * @param type
     * @param validFor
     */
    public ProductSpecificationRelationship(ProductSpecification srcSpec, ProductSpecification targetSpec, String type,
            TimePeriod validFor) {
        this.srcProdSpec = srcSpec;
        this.targetProdSpec = targetSpec;
        this.type = type;
        this.validFor = validFor;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((targetProdSpec == null) ? 0 : targetProdSpec.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductSpecificationRelationship other = (ProductSpecificationRelationship) obj;
        if (targetProdSpec == null) {
            if (other.targetProdSpec != null)
                return false;
        } else if (!targetProdSpec.equals(other.targetProdSpec))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return CommonUtils.getPropertyToJson(null, null, this);
    }

}