package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.asiainfo.baas.common.CharacristicValueType;
import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;

public class ProductSpecCharacteristicValueTest2 {
	

	private ProductSpecCharacteristic specChar;
	private ProductSpecCharacteristicValue memoryCharValue;
	private ProductSpecCharacteristicValue charValue;
	private static TimePeriod validFor;
	private Logger logger=Logger.getLogger(ProductSpecCharacteristicValueTest2.class);

	@BeforeClass
	public static void setUpBeforeClass(){
		  validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
		  
	}
	@Before
	public void initValue(){
		specChar = new ProductSpecCharacteristic("1", "����ͷ", CharacristicValueType.NUMBER.getValue(),validFor, "unique",1,1);
		memoryCharValue=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.7");
		charValue=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.9");

	}
	@Test
	public void testAddRelatedCharacteristic(){
		logger.info("ProductSpecCharacteristicValue��������������ֵ��");
		
		logger.info("\t1.ProductSpecCharacteristicValue��������������ֵ,ָ������ֵΪnull");
		boolean result=memoryCharValue.addRelatedCharValue(null, RelationshipType.EXCLUSIVITY.getValue(), validFor);
		assertEquals(false, result);
		
		logger.info("\t2.ProductSpecCharacteristicValue��������������ֵ,ָ������ֵ�뵱ǰ����ֵ��ͬ");
		 result=memoryCharValue.addRelatedCharValue(memoryCharValue, RelationshipType.EXCLUSIVITY.getValue(), validFor);
		assertEquals(false, result);
		
		logger.info("\t3.ProductSpecCharacteristicValue��������������ֵ,ָ������ֵ�뵱ǰ����ֵ����ͬ");
		result=memoryCharValue.addRelatedCharValue(charValue, RelationshipType.EXCLUSIVITY.getValue(), validFor);
		assertEquals(true, result);
		logger.info("��ӳɹ�");
		logger.info("\t4.ProductSpecCharacteristicValue��������������ֵ,ָ������ֵ�Ѿ����������ϵ");
		result=memoryCharValue.addRelatedCharValue(charValue, RelationshipType.EXCLUSIVITY.getValue(), validFor);		
		assertEquals(false, result);
		logger.info("\t5.ProductSpecCharacteristicValue��������������ֵ,ָ������ֵ�Ѿ����������ϵ,�Ƿ���Խ���������ϵ");
		result=memoryCharValue.addRelatedCharValue(charValue, RelationshipType.DEPENDENCY.getValue(), validFor);	
		assertEquals(false, result);
	}
	@Test
	public void testQueryRelatedCharValue(){
		logger.info("ProductSpecCharacteristicValue��ѯ�����������ֵ��");
		
		logger.info("\t1.ProductSpecCharacteristicValue��ѯ�����������,ָ�����ͺ�ʱ��Ϊnull");
		List<ProductSpecCharacteristicValue> charValues=memoryCharValue.queryRelatedCharValue(null,null);
		assertNull(charValues);
		
		logger.info("\t2.ProductSpecCharacteristicValue��ѯ�����������,��ǰ����ֵû��ָ����ϵ������");
		 charValues=memoryCharValue.queryRelatedCharValue(RelationshipType.EXCLUSIVITY.getValue(),new Date());
		assertNull(charValues);
		
		logger.info("\t3.ProductSpecCharacteristicValue��ѯ�����������,��ǰ����ֵ���ڻ����ϵ����");
		memoryCharValue.addRelatedCharValue(charValue, RelationshipType.EXCLUSIVITY.getValue(), validFor);
		charValues=memoryCharValue.queryRelatedCharValue(RelationshipType.EXCLUSIVITY.getValue(),new Date());
		assertNotNull(charValues);
		logger.info("\t4.ProductSpecCharacteristicValue��ѯ�����������ֵ,��ǰ����ֵ���ڻ����ϵ����,������������ϵ");
		charValues=memoryCharValue.queryRelatedCharValue(RelationshipType.DEPENDENCY.getValue(),new Date());
		assertNull(charValues);
	}
}
