package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.asiainfo.baas.common.ProductSpecificationStatus;
import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.ProductSpecification;
import com.asiainfo.baas.marathon.specification.ProductSpecificationRelationship;

public class ProductSpecificationRelationshipTest {

    private Logger logger = Logger.getLogger(ProductSpecificationRelationshipTest.class);

    private ProductSpecification srcProdSpec = null;

    @Before
    public void initSrcProdSpec() {
        srcProdSpec = new AtomicProductSpecification("S001", "iPhone6", "Apple iPhone",
                ProductSpecificationStatus.PLANNED.getValue());
    }

    @Test
    public void testAddRelatedProdSpec() {

        logger.info("-------------testAddRelatedProdSpec start---------------");

        logger.info("***********testAddRelatedProdSpec Case1��������֧�� start**************");
        ProductSpecification targetProdSpec = new AtomicProductSpecification("T001", "AppleCare For iPhone",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        String type = RelationshipType.DEPENDENCY.getValue();
        TimePeriod validFor = new TimePeriod();

        List<ProductSpecificationRelationship> expectedRelatedSpecList = new ArrayList<ProductSpecificationRelationship>();
        ProductSpecificationRelationship expectedRelatedSpec = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec, type, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec);

        this.srcProdSpec.addRelatedProdSpec(targetProdSpec, type, validFor);
        assertEquals(1, this.srcProdSpec.getProdSpecRelationship().size());
        for (int i = 0; i < expectedRelatedSpecList.size(); i++) {
            assertEquals(expectedRelatedSpecList.get(i).getTargetProdSpec().getName(), this.srcProdSpec
                    .getProdSpecRelationship().get(i).getTargetProdSpec().getName());
        }
        assertEquals(expectedRelatedSpecList.toArray(new ProductSpecificationRelationship[0]), srcProdSpec
                .getProdSpecRelationship().toArray(new ProductSpecificationRelationship[0]));

        logger.info("��Ӻ�src�ڵ�relationship��");
        for (int i = 0; i < this.srcProdSpec.getProdSpecRelationship().size(); i++) {
            logger.info(this.srcProdSpec.getProdSpecRelationship().get(i).toString());
        }
        logger.info("***********testAddRelatedProdSpec Case1 end**************");

        logger.info("\n");
        logger.info("***********testAddRelatedProdSpec Case2�����ͬ�����ݣ�ͬһ�������ͣ� start**************");
        // �ٴ����һ��ͬ������
        ProductSpecification targetProdSpec2 = new AtomicProductSpecification("T001", "AppleCare For iPhone2",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec2, type, validFor);
        assertEquals(1, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals(expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());
        logger.info("��Ӻ�src�ڵ�relationship��");
        for (int i = 0; i < this.srcProdSpec.getProdSpecRelationship().size(); i++) {
            logger.info(this.srcProdSpec.getProdSpecRelationship().get(i).toString());
        }
        logger.info("***********testAddRelatedProdSpec Case2 end**************");

        logger.info("\n");
        logger.info("***********testAddRelatedProdSpec Case3����Ӳ�ͬ���ݣ�ͬһ�������ͣ� start**************");
        // �ٴ����һ����ͬ����,��ͬ����
        ProductSpecification targetProdSpec3 = new AtomicProductSpecification("T002", "AppleCare For iPhone2",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec3, type, validFor);
        assertEquals(2, this.srcProdSpec.getProdSpecRelationship().size());
        // �����ڴ�����
        ProductSpecificationRelationship expectedRelatedSpec3 = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec3, type, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec3);
        assertEquals(expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());
        logger.info("��Ӻ�src�ڵ�relationship��");
        for (int i = 0; i < this.srcProdSpec.getProdSpecRelationship().size(); i++) {
            logger.info(this.srcProdSpec.getProdSpecRelationship().get(i).toString());
        }
        logger.info("***********testAddRelatedProdSpec Case3 end**************");

        logger.info("\n");
        logger.info("***********testAddRelatedProdSpec Case4�����ͬ�����ݣ���ͬ�������ͣ� start**************");
        // �ٴ����һ����ͬ����,��ͬ����
        String type4 = RelationshipType.AGGREGATION.getValue();
        ProductSpecification targetProdSpec4 = new AtomicProductSpecification("T001", "AppleCare For iPhone2",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec4, type4, validFor);
        assertEquals(3, this.srcProdSpec.getProdSpecRelationship().size());
        // �����ڴ�����
        ProductSpecificationRelationship expectedRelatedSpec4 = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec4, type4, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec4);
        assertEquals(expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());
        logger.info("��Ӻ�src�ڵ�relationship��");
        for (int i = 0; i < this.srcProdSpec.getProdSpecRelationship().size(); i++) {
            logger.info(this.srcProdSpec.getProdSpecRelationship().get(i).toString());
        }
        logger.info("***********testAddRelatedProdSpec Case4 end**************");
        
        logger.info("\n");
        logger.info("***********testAddRelatedProdSpec Case4�����ͬ�����ݣ���ͬ�������ͣ� start**************");
        // �ٴ����һ����ͬ����,��ͬ����
        this.srcProdSpec.addRelatedProdSpec(this.srcProdSpec, type4, validFor);
        assertEquals(3, this.srcProdSpec.getProdSpecRelationship().size());
        // �����ڴ�����
        assertEquals(expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());
        logger.info("��Ӻ�src�ڵ�relationship��");
        for (int i = 0; i < this.srcProdSpec.getProdSpecRelationship().size(); i++) {
            logger.info(this.srcProdSpec.getProdSpecRelationship().get(i).toString());
        }
        logger.info("***********testAddRelatedProdSpec Case4 end**************");
        logger.info("-------------testAddRelatedProdSpec end---------------");
    }

}
