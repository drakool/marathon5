package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.asiainfo.baas.common.ProductSpecificationStatus;
import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.ProdSpecCharValueUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;
import com.asiainfo.baas.marathon.specification.ProductSpecification;
import com.asiainfo.baas.marathon5.apple.TestProductSpecificationData;

public class ProductSpecificationTest {
	private Logger logger = Logger.getLogger(ProductSpecificationTest.class);
	private  ProductSpecification prodSpec = null;
	private  TimePeriod validFor = null;
	@Before
	public  void initProdSpec(){
		validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
		prodSpec = new AtomicProductSpecification("mac-13", "13-inch MacBook Pro", "apple",ProductSpecificationStatus.PLANNED.getValue());
	}
	@Test
    public void testAddCharacteristic() {
        ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        logger.info("���һ������");
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        assertEquals(1,prodSpec.getProdSpecCharUse().size());
        logger.info("��������ɹ���");
        
        logger.info("���һ���Ѵ��ڵ�����");
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        assertEquals(1,prodSpec.getProdSpecCharUse().size());
        logger.info("�������ʧ�ܣ�");
        
        logger.info("���һ������Ϊ��");
        prodSpec.addCharacteristic(null, false, false, validFor, "CPU");
        assertEquals(1,prodSpec.getProdSpecCharUse().size());
        logger.info("�������ʧ�ܣ�");
    }

    @Test
    public void testAttachCharacteristicValue() {
    	 ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	 prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
    	 
    	 ProductSpecCharacteristicValue charValue = this.createValue(TestProductSpecificationData.specCharValue[9]);
         logger.info("���һ������ֵ");
         prodSpec.attachCharacteristicValue(characteristic, charValue, false, validFor);
         assertEquals(1,prodSpec.getProdSpecCharUse().size());
         logger.info("�������ֵ�ɹ���");
         
         logger.info("���һ���Ѵ��ڵ�����ֵ");
         prodSpec.attachCharacteristicValue(characteristic, charValue, false, validFor);
         assertEquals(1,prodSpec.getProdSpecCharUse().size());
         logger.info("�������ֵʧ�ܣ�");
         
         logger.info("���һ������ֵΪ��");
         prodSpec.attachCharacteristicValue(characteristic, null, false, validFor);
         assertEquals(1,prodSpec.getProdSpecCharUse().size());
         logger.info("�������ֵʧ�ܣ�");
    }

    @Test
    public void testSpecifyDefaultCharacteristicValue() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	logger.info("�������");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 
   	 	ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
   	 	ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
   	 	logger.info("�������ֵ");
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
   	 	
   	 	logger.info("����Ĭ��ֵ");
		boolean retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic, charValue2);
		assertTrue("Ĭ��ֵ���óɹ�",retFlag);
		logger.info("Ĭ��ֵ���óɹ���");
		
		logger.info("����Ĭ��ֵ�������ֵ����Ϊnull");
		charValue2 = null;
		retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic,null);
		assertFalse("Ĭ��ֵ����ʧ��",retFlag);
		logger.info("Ĭ��ֵ����ʧ�ܣ�");
		
		logger.info("����Ĭ��ֵ�������ֵ����Ϊ���Ǹ��������е�");
		ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[11]);
		retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic,charValue3);
		assertFalse("Ĭ��ֵ����ʧ��",retFlag);
		logger.info("Ĭ��ֵ����ʧ�ܣ�");
    }

    @Test
    public void testClearDefaultCharacteristicValue(){
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	logger.info("�������");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 
   	 	ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
   	 	ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
   	 	logger.info("�������ֵ");
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
   	 	
		logger.info("���Ĭ��ֵ�������ֵ����Ϊnull");
		boolean retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic,null);
		assertFalse("Ĭ��ֵ���ʧ��",retFlag);
		logger.info("Ĭ��ֵ���ʧ�ܣ�");
		
		logger.info("���Ĭ��ֵ�������ֵ����Ϊ���Ǹ��������е�");
		ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[11]);
		retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic,charValue3);
		assertFalse("Ĭ��ֵ���ʧ��",retFlag);
		logger.info("Ĭ��ֵ���ʧ�ܣ�");
		
		logger.info("���Ĭ��ֵ");
		retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic, charValue1);
		assertTrue("Ĭ��ֵ����ɹ�",retFlag);
		logger.info("Ĭ��ֵ����ɹ���");
		
    }
    @Test
    public void testRetrieveCharacteristic() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
    	logger.info("�������");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 	prodSpec.addCharacteristic(characteristic2, false, false, validFor, "CPU");
   	 	
   	 	logger.info("��ѯ��ǰʱ��������");
   	 	List<ProductSpecCharUse> charUses = prodSpec.retrieveCharacteristic(new Date());
   	 	assertNotNull(charUses);
   	 	assertEquals(2, charUses.size());
   	 	logger.info("��ѯ�����ɹ���");
    }

    @Test
    public void testRetrieveCharacteristicValue() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	logger.info("�������");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 
   	 	ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
   	 	ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
   	 	logger.info("�������ֵ");
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
   	 	
   	 	logger.info("��ѯĳһ�����ڵ�ǰʱ����ֵ");
	   	List<ProdSpecCharValueUse> charValueUses = prodSpec.retrieveCharacteristicValue(characteristic, new Date());
	   	assertNotNull(charValueUses);
	 	assertEquals(2, charValueUses.size());
	 	logger.info("��ѯ�����ɹ���");
    }

    @Test
    public void testRetrieveRootCharacteristic() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
    	ProductSpecCharacteristic  characteristic3 = this.createChar(TestProductSpecificationData.specChar[6]);
    	ProductSpecCharacteristic  characteristic4 = this.createChar(TestProductSpecificationData.specChar[7]);
    	ProductSpecCharacteristic  characteristic5 = this.createChar(TestProductSpecificationData.specChar[8]);
    	ProductSpecCharacteristic  characteristic6 = this.createChar(TestProductSpecificationData.specChar[9]);
    	logger.info("���Char�ۺϹ�ϵ");
    	characteristic2.addRelatedCharacteristic(characteristic3, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	characteristic2.addRelatedCharacteristic(characteristic4, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	characteristic2.addRelatedCharacteristic(characteristic5, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	characteristic2.addRelatedCharacteristic(characteristic6, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	logger.info("�������");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 	prodSpec.addCharacteristic(characteristic2, false, false, validFor, "�ߴ������");
	   	prodSpec.addCharacteristic(characteristic3, false, false, validFor, "��");
	   	prodSpec.addCharacteristic(characteristic4, false, false, validFor, "��");
	   	prodSpec.addCharacteristic(characteristic5, false, false, validFor, "��");
	   	prodSpec.addCharacteristic(characteristic6, false, false, validFor, "����");
   	 
   	 	logger.info("��ѯ����һ������");
   	 	List<ProductSpecCharUse> rootCharUses = prodSpec.retrieveRootCharacteristic();
	   	assertNotNull(rootCharUses);
	 	assertEquals(2, rootCharUses.size());
	 	logger.info("��ѯ�����ɹ���");
    }

    @Test
    public void getLeafCharacteristic() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
    	ProductSpecCharacteristic  characteristic3 = this.createChar(TestProductSpecificationData.specChar[6]);
    	ProductSpecCharacteristic  characteristic4 = this.createChar(TestProductSpecificationData.specChar[7]);
    	ProductSpecCharacteristic  characteristic5 = this.createChar(TestProductSpecificationData.specChar[8]);
    	ProductSpecCharacteristic  characteristic6 = this.createChar(TestProductSpecificationData.specChar[9]);
    	logger.info("���Char�ۺϹ�ϵ");
    	characteristic2.addRelatedCharacteristic(characteristic3, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	characteristic2.addRelatedCharacteristic(characteristic4, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	characteristic2.addRelatedCharacteristic(characteristic5, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	characteristic2.addRelatedCharacteristic(characteristic6, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	logger.info("�������");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 	prodSpec.addCharacteristic(characteristic2, false, false, validFor, "�ߴ������");
	   	prodSpec.addCharacteristic(characteristic3, false, false, validFor, "��");
	   	prodSpec.addCharacteristic(characteristic4, false, false, validFor, "��");
	   	prodSpec.addCharacteristic(characteristic5, false, false, validFor, "��");
	   	prodSpec.addCharacteristic(characteristic6, false, false, validFor, "����");
   	 
   	 	logger.info("��ѯ����ĳһ������������");
   	 	List<ProductSpecCharUse> leafCharUses = prodSpec.retrieveLeafCharacteristic(characteristic2, new Date());
	   	assertNotNull(leafCharUses);
	 	assertEquals(4, leafCharUses.size());
	 	logger.info("��ѯ�������ɹ���");
	 	
	 	logger.info("��ѯ����ĳһ������������");
   	 	List<ProductSpecCharUse> leafCharUses2 = prodSpec.retrieveLeafCharacteristic(null, new Date());
	   	assertNull(leafCharUses2);
	 	logger.info("��ѯ������ʧ�ܣ�");
    }

    @Test
    public void testSpecifyCardinality() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
    	logger.info("�������");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 
   	 	ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
   	 	ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
   	 	logger.info("�������ֵ");
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
		
		logger.info("����ĳһ������Cardinality");
		boolean retFlag = prodSpec.specifyCardinality(characteristic,1,5);
		assertTrue(retFlag);
		logger.info("Cardinality���óɹ���");
		
		logger.info("����Cardinality������Ϊ��");
		retFlag = prodSpec.specifyCardinality(null,1,5);
		assertFalse(retFlag);
		logger.info("Cardinality����ʧ�ܣ�");
		
		logger.info("����Cardinality���������Ǳ��õ�");
		retFlag = prodSpec.specifyCardinality(characteristic2,1,5);
		assertFalse(retFlag);
		logger.info("Cardinality����ʧ�ܣ�");
    }

    private ProductSpecCharacteristicValue createValue(Object[] obj){
        ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue((String)obj[1], (boolean)obj[2],
        		(String)obj[3], (TimePeriod)obj[4], (String)obj[5]);
        return charValue;
    }

    private ProductSpecCharacteristic createChar(Object[] obj){
        ProductSpecCharacteristic specChar = new ProductSpecCharacteristic((String)obj[0], (String)obj[1],
        		(String)obj[2], (TimePeriod)obj[3], (String)obj[4],(Integer)obj[5],(Integer)obj[5]);
        return specChar;
    }
}
