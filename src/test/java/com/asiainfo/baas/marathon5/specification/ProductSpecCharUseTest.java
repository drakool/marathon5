package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProdSpecCharValueUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;

public class ProductSpecCharUseTest {
	private Logger logger = Logger.getLogger(ProductSpecCharUseTest.class);
	private  ProductSpecCharUse specCharUse = null;
	private  TimePeriod validFor = null;
	@Before
	public void initSpecCharAndValue(){
		validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
		ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "processor(������)", "Number",validFor, "unique",1,1);
		specCharUse = new ProductSpecCharUse(specChar,false,false,validFor,"������");
	}
	@Test
	public void testAddValue(){
		logger.info("���һ��ֵ");
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "8");
		boolean retFlag = specCharUse.addValue(charValue,false,validFor);
		 assertTrue("��ӳɹ�",retFlag);
		 logger.info("��ӳɹ�");
		logger.info("���һ���Ѿ����ڵ�ֵ");
		retFlag = specCharUse.addValue(charValue,false,validFor);
		assertFalse("���ʧ��",retFlag);
		logger.info("���һ����ֵ");
		retFlag = specCharUse.addValue(null, false, validFor);
		assertFalse("���ʧ��",retFlag);
	}
	
	@Test
	public void testSpecifyDefaultCharacteristicValueUse(){
		ProductSpecCharacteristicValue defaultValue = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		logger.info("���ֵ");
		specCharUse.addValue(defaultValue,false,validFor);
		specCharUse.addValue(charValue,true,validFor);
		
		logger.info("����Ĭ��ֵ");
		boolean retFlag = specCharUse.specifyDefaultCharacteristicValueUse(defaultValue);
		assertTrue("Ĭ��ֵ���óɹ�",retFlag);
		logger.info("Ĭ��ֵ���óɹ���");
		
		logger.info("����Ĭ��ֵ�������ֵ����Ϊnull");
		defaultValue = null;
		retFlag = specCharUse.specifyDefaultCharacteristicValueUse(defaultValue);
		assertFalse("Ĭ��ֵ����ʧ��",retFlag);
		logger.info("Ĭ��ֵ����ʧ��");
		
		logger.info("����Ĭ��ֵ�������ֵ����Ϊ���Ǹ��������е�");
		ProductSpecCharacteristicValue defaultValue2 = new ProductSpecCharacteristicValue("number", false, "GB", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "32" );
		retFlag = specCharUse.specifyDefaultCharacteristicValueUse(defaultValue2);
		assertFalse("Ĭ��ֵ����ʧ��",retFlag);
		logger.info("Ĭ��ֵ����ʧ��");
	}
	
	@Test
	public void testRetrieveDefaultCharacteristicValueUse(){
		ProductSpecCharacteristicValue charValue1 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		logger.info("���ֵ");
		specCharUse.addValue(charValue1,false,validFor);
		specCharUse.addValue(charValue2,true,validFor);
		
		logger.info("��ѯĬ��ֵ");
		List<ProdSpecCharValueUse> charValue = specCharUse.retrieveDefaultCharacteristicValueUse();
		assertNotNull("��ѯ��ֵ��", charValue);
		assertEquals(1, charValue.size());
		logger.info("Ĭ��ֵ��ѯ�ɹ���");
	}
	
	@Test
	public void testSetCardinality(){
		ProductSpecCharacteristicValue charValue1 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		logger.info("���ֵ");
		specCharUse.addValue(charValue1,false,validFor);
		specCharUse.addValue(charValue2,true,validFor);
		
		logger.info("����Cardinality");
		specCharUse.setCardinality(1, 5);
		assertEquals(1, specCharUse.getMinCardinality());
		logger.info("MinCardinality���óɹ���");
		assertEquals(5, specCharUse.getMaxCardinality());
		logger.info("MaxCardinality���óɹ���");
	}
	@Test
	public void testClearDefaultValue(){
		ProductSpecCharacteristicValue charValue1 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		ProductSpecCharacteristicValue charValue3 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "32");
		logger.info("���ֵ");
		specCharUse.addValue(charValue1,false,validFor);
		specCharUse.addValue(charValue2,true,validFor);
		
		logger.info("���Ĭ��ֵ������һ������Ĭ��ֵ��ֵ");
		boolean retFlag = specCharUse.clearDefaultValueUse(charValue1);
		assertFalse("���Ĭ��ֵ����", retFlag);
		logger.info("���Ĭ��ֵʧ�ܣ�");
		
		logger.info("���Ĭ��ֵ������һ��û�б�ʹ�õ�ֵ");
		retFlag = specCharUse.clearDefaultValueUse(charValue3);
		assertFalse("���Ĭ��ֵ����", retFlag);
		logger.info("���Ĭ��ֵʧ�ܣ�");
		
		logger.info("���Ĭ��ֵ������һ��null");
		retFlag = specCharUse.clearDefaultValueUse(null);
		assertFalse("���Ĭ��ֵ����", retFlag);
		logger.info("���Ĭ��ֵʧ�ܣ�");
		
		logger.info("���Ĭ��ֵ");
		retFlag = specCharUse.clearDefaultValueUse(charValue2);
		assertTrue("���Ĭ��ֵ����", retFlag);
		logger.info("���Ĭ��ֵ�ɹ���");
		
		
	}

}
