package com.asiainfo.baas.marathon5.apple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import org.junit.Test;

import com.asiainfo.baas.common.ProductConst;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.offering.BundledProductOffering;
import com.asiainfo.baas.marathon.offering.ProductOffering;
import com.asiainfo.baas.marathon.offering.SimpleProductOffering;
import com.asiainfo.baas.marathon.offering.catalog.ProductCatalog;
import com.asiainfo.baas.marathon5.common.CommonUtils;
import com.asiainfo.baas.marathon5.common.JsonDateValueProcessor;
import com.asiainfo.baas.marathon5.common.JsonPropertyFilter;

public class TestAppleStoreOffering {

    /**
     * ��������offering��һ������offering������ԭ��offering
     */
    @Test
    public void createOffering() {

        List<ProductOffering> offering = this.createProductOffering();
        CommonUtils.printPropertyToJson(null, offering, null);

    }

    @Test
    public void createCatalog() {

        ProductCatalog macBookProproductCatalog = this.createProductCatalog();
        CommonUtils.printPropertyToJson(null, null, macBookProproductCatalog);

    }

    @Test
    public void publishOffering() {

        ProductCatalog macBookProproductCatalog = this.createProductCatalog();
        List<ProductOffering> offerings = this.createProductOffering();
        if (offerings != null) {
            for (ProductOffering productOffering : offerings) {
                TimePeriod validFor = new TimePeriod("2015-01-01 00:00:00", "2015-07-01 00:00:00");
                macBookProproductCatalog.publishOffering(productOffering, validFor);
            }
        }
        CommonUtils.printPropertyToJson(null, null, macBookProproductCatalog);
    }

    public ProductCatalog createProductCatalog() {
        StringBuilder description1 = new StringBuilder();
        description1.append("2.7GHz ˫�� Intel Core i5 ������");
        description1.append("\n");
        description1.append("Turbo Boost �ߴ� 3.1GHz");
        description1.append("\n");
        description1.append("8GB 1866MHz LPDDR3 �ڴ�");
        description1.append("\n");
        description1.append("���� PCIe �� 128GB ����1");
        description1.append("\n");
        description1.append("Intel Iris Graphics 6100");
        description1.append("\n");
        description1.append("���õ�� (10 Сʱ)2");
        description1.append("\n");
        description1.append("Force Touch ���ذ�");
        description1.append("\n");

        TimePeriod validFor1 = new TimePeriod("2013-01-01 00:00:00", null);
        ProductCatalog macBookProproductCatalog = new ProductCatalog("1", "MacBook Pro", "MacBook", validFor1);
        return macBookProproductCatalog;

    }

    public List<ProductOffering> createProductOffering() {
        List<ProductOffering> productOfferings = new ArrayList<ProductOffering>();
        StringBuilder description1 = new StringBuilder();
        description1.append("2.7GHz ˫�� Intel Core i5 ������");
        description1.append("\n");
        description1.append("Turbo Boost �ߴ� 3.1GHz");
        description1.append("\n");
        description1.append("8GB 1866MHz LPDDR3 �ڴ�");
        description1.append("\n");
        description1.append("���� PCIe �� 128GB ����1");
        description1.append("\n");
        description1.append("Intel Iris Graphics 6100");
        description1.append("\n");
        description1.append("���õ�� (10 Сʱ)2");
        description1.append("\n");
        description1.append("Force Touch ���ذ�");
        description1.append("\n");

        TimePeriod validFor1 = new TimePeriod("2013-01-01 00:00:00", null);
        SimpleProductOffering simpleProductOffering1 = new SimpleProductOffering("11", "2.7GHz ������\n128 GB �洢����",
                validFor1, ProductConst.OFFERING_STATUS_ACTIVE, null, description1.toString());

        StringBuilder description2 = new StringBuilder();
        description2.append("2.7GHz ˫�� Intel Core i5 ������");
        description2.append("\n");
        description2.append("Turbo Boost �ߴ� 3.1GHz");
        description2.append("\n");
        description2.append("8GB 1866MHz LPDDR3 �ڴ�");
        description2.append("\n");
        description2.append("���� PCIe �� 128GB ����1");
        description2.append("\n");
        description2.append("Intel Iris Graphics 6100");
        description2.append("\n");
        description2.append("���õ�� (10 Сʱ)2");
        description2.append("\n");
        description2.append("Force Touch ���ذ�");
        description2.append("\n");

        TimePeriod validFor2 = new TimePeriod("2013-01-01 00:00:00", null);
        SimpleProductOffering simpleProductOffering2 = new SimpleProductOffering("12", "2.7GHz ������\n256 GB �洢����",
                validFor2, ProductConst.OFFERING_STATUS_ACTIVE, null, description2.toString());

        TimePeriod validFor4 = new TimePeriod("2013-01-01 00:00:00", null);
        BundledProductOffering bundledProductOffering = new BundledProductOffering("1",
                "13 Ӣ���䱸 Retina ��ʾ���� MacBook Pro", validFor4, ProductConst.OFFERING_STATUS_ACTIVE, "");
        bundledProductOffering.addSubOffering(simpleProductOffering1);
        bundledProductOffering.addSubOffering(simpleProductOffering2);

        // ����ԭ��Offering�ǻ����ϵ
        simpleProductOffering1.addRelatedOffering(simpleProductOffering2, ProductConst.RELATIONSHIP_TYPE_EXCLUSIVITY,
                validFor1);
        simpleProductOffering2.addRelatedOffering(simpleProductOffering1, ProductConst.RELATIONSHIP_TYPE_EXCLUSIVITY,
                validFor2);

        productOfferings.add(simpleProductOffering1);
        productOfferings.add(simpleProductOffering2);
        productOfferings.add(bundledProductOffering);

        return productOfferings;
    }

}
