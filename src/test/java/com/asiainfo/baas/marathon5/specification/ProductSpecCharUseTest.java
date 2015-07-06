package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private ProductSpecCharacteristic specChar = null;
	@Before
	public void initSpecCharAndValue(){
		validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
		specCharUse = new ProductSpecCharUse(specChar,false,false,validFor,"������");
	}
	@Test
	public void testAddValue(){
		//���һ��ֵ
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "8");
		boolean retFlag = specCharUse.addValue(charValue,false,validFor);
		ProductSpecCharUse exceptSpecCharUse = new ProductSpecCharUse(specChar,false,false,validFor,"������");
		Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue,false,validFor);
        expectCharValueUse.add(charValueUse);
		exceptSpecCharUse.setProdSpecCharValueUse(expectCharValueUse);
		assertEquals("���һ��ֵ", exceptSpecCharUse,specCharUse);
		 
		//���һ���Ѿ����ڵ�ֵ
		retFlag = specCharUse.addValue(charValue,false,validFor);
		assertEquals("���һ���Ѿ����ڵ�ֵ", exceptSpecCharUse,specCharUse);
		
		//���һ����ֵ
		try{
			specCharUse.addValue(null, false, validFor);
			fail("���һ����ֵ");
		}catch(Exception e){}
	}
	
	@Test
	public void testSpecifyDefaultCharacteristicValueUse(){
		ProductSpecCharacteristicValue defaultValue = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		//���ֵ
		specCharUse.addValue(defaultValue,false,validFor);
		specCharUse.addValue(charValue,true,validFor);
		
		ProductSpecCharUse exceptSpecCharUse = new ProductSpecCharUse(specChar,false,false,validFor,"������");
		Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue,false,validFor);
        ProdSpecCharValueUse defcharValueUse = new ProdSpecCharValueUse(defaultValue,true,validFor);
        expectCharValueUse.add(charValueUse);
        expectCharValueUse.add(defcharValueUse);
		exceptSpecCharUse.setProdSpecCharValueUse(expectCharValueUse);
		
		//����Ĭ��ֵ
		boolean retFlag = specCharUse.specifyDefaultCharacteristicValueUse(defaultValue);
		assertEquals("����Ĭ��ֵ", exceptSpecCharUse,specCharUse);
		
		//����Ĭ��ֵ�������ֵ����Ϊnull
		defaultValue = null;
		try{
			specCharUse.specifyDefaultCharacteristicValueUse(defaultValue);
			fail("����Ĭ��ֵ�������ֵ����Ϊnull");
		}catch(Exception e){
		}
		
		//����Ĭ��ֵ�������ֵ����Ϊ���Ǹ��������е�
		ProductSpecCharacteristicValue defaultValue2 = new ProductSpecCharacteristicValue("number", false, "GB", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "32" );
		specCharUse.specifyDefaultCharacteristicValueUse(defaultValue2);
		assertEquals("����Ĭ��ֵ�������ֵ����Ϊ���Ǹ��������е�", exceptSpecCharUse,specCharUse);
	}
	
	@Test
	public void testRetrieveDefaultCharacteristicValueUse(){
		ProductSpecCharacteristicValue charValue1 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		//���ֵ
		specCharUse.addValue(charValue1,false,validFor);
		specCharUse.addValue(charValue2,true,validFor);
		
		 ProdSpecCharValueUse exceptSDfcharValueUse = new ProdSpecCharValueUse(charValue2,true,validFor);
		
		//��ѯĬ��ֵ
		List<ProdSpecCharValueUse> charValue = specCharUse.retrieveDefaultCharacteristicValueUse();
		assertNotNull("��ѯ��ֵ��", charValue);
		assertEquals("��ѯĬ��ֵ",exceptSDfcharValueUse, charValue);
	}
	
	@Test
	public void testSetCardinality(){
		ProductSpecCharacteristicValue charValue1 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		//���ֵ
		specCharUse.addValue(charValue1,false,validFor);
		specCharUse.addValue(charValue2,true,validFor);
		
		ProductSpecCharUse exceptSpecCharUse = new ProductSpecCharUse(specChar,false,false,validFor,"������");
		Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue1,false,validFor);
        ProdSpecCharValueUse defcharValueUse = new ProdSpecCharValueUse(charValue2,true,validFor);
        expectCharValueUse.add(charValueUse);
        expectCharValueUse.add(defcharValueUse);
		exceptSpecCharUse.setProdSpecCharValueUse(expectCharValueUse);
		//����Cardinality
		specCharUse.setCardinality(1, 5);
		//assertEquals(1, specCharUse.getMinCardinality());
		//assertEquals(5, specCharUse.getMaxCardinality());
		assertEquals("��ѯĬ��ֵ",exceptSpecCharUse, specCharUse);
	}
	@Test
	public void testClearDefaultValue(){
		ProductSpecCharacteristicValue charValue1 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		ProductSpecCharacteristicValue charValue3 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "32");
		//���ֵ
		specCharUse.addValue(charValue1,false,validFor);
		specCharUse.addValue(charValue2,true,validFor);
		
		//���Ĭ��ֵ������һ������Ĭ��ֵ��ֵ
		boolean retFlag = specCharUse.clearDefaultValueUse(charValue1);
		assertFalse("���Ĭ��ֵ����", retFlag);
		
		//���Ĭ��ֵ������һ��û�б�ʹ�õ�ֵ
		retFlag = specCharUse.clearDefaultValueUse(charValue3);
		assertFalse("���Ĭ��ֵ����", retFlag);
		
		//���Ĭ��ֵ������һ��null
		retFlag = specCharUse.clearDefaultValueUse(null);
		assertFalse("���Ĭ��ֵ����", retFlag);
		
		//���Ĭ��ֵ
		retFlag = specCharUse.clearDefaultValueUse(charValue2);
		assertTrue("���Ĭ��ֵ����", retFlag);
		
		
	}

}
