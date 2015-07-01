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
import com.asiainfo.baas.marathon.specification.ConfigurableProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;

public class ProductSpecCharacteristicTest {
	
	private ProductSpecCharacteristic specChar;
	private ProductSpecCharacteristic configSpecChar;
	private static TimePeriod validFor;
	private Logger logger=Logger.getLogger(ProductSpecCharacteristicTest.class);
	@BeforeClass
	public static void setUpBeforeClass(){
		  validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
		  
	}
	@Before
	public void initValue(){
		specChar = new ProductSpecCharacteristic("1", "����ͷ", CharacristicValueType.TEXT.getValue(),validFor, "unique",1,1);
		configSpecChar=new ConfigurableProductSpecCharacteristic("2", "Memory", CharacristicValueType.NUMBER.getValue(),validFor, "unique",1,1);
		ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.7");
		configSpecChar.addValue(value);
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",validFor,"2.9");
		configSpecChar.addValue(value);
	}
	@Test
	public void testAddValue(){
		boolean result=false;
		logger.info("ProductSpecCharacteristic�������ֵ:");
		
		logger.info("\t1:ProductSpecCharacteristic�������ֵΪnull");
		ProductSpecCharacteristicValue value  =null;
		specChar.addValue(value);
		
		logger.info("\t2:ProductSpecCharacteristic����µ�����ֵ,����������ֵ��valueType����ͬ");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",validFor,"2.7");
		result=specChar.addValue(value);
		assertEquals(false,result);
		
		logger.info("\t3:ProductSpecCharacteristic����µ�����ֵ,����������ֵ��valueType��ͬ");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),true,"",validFor,"720pFace TimeHD��������ͷ");
		result=specChar.addValue(value);
		assertEquals(true,result);
		assertEquals(true,specChar.getProductSpecCharacteristicValue().contains(value));
		
		logger.info("\t4:ProductSpecCharacteristic����ظ�������ֵ");
		result=specChar.addValue(value);
		assertEquals(false,result);
		
		

	}
	@Test
	public void testRetrieveValue(){
		logger.info("ProductSpecCharacteristic��ѯ����ֵ");
		
		logger.info("\t1:ProductSpecCharacteristic��ѯ����ֵ,ʱ��Ϊnull");
		Date time=null;
		List<ProductSpecCharacteristicValue> prodSpecCharValues=specChar.retrieveValue(time);
		assertEquals(null, prodSpecCharValues);
		
		logger.info("\t2:ProductSpecCharacteristic��ѯ����ֵ,ʱ��Ϊ��ȷʱ��ֵ����������������ֵ");
		time=new Date();
		prodSpecCharValues=specChar.retrieveValue(time);
		assertEquals(null, prodSpecCharValues);
		
		logger.info("\t3:ProductSpecCharacteristic��ѯ����ֵ,ʱ��Ϊ��ȷʱ��ֵ��������������ֵ");
		prodSpecCharValues=configSpecChar.retrieveValue(time);
		assertEquals(2,prodSpecCharValues.size());
		logger.info("��ѯ�ɹ�,��������2������ֵ");
	}
	@Test
	public void testSpecifyDefaultValue(){
		boolean result=false;
		logger.info("ProductSpecCharacteristicָ��Ĭ������ֵ:");
		
		logger.info("\t1:ProductSpecCharacteristicָ��Ĭ������ֵ,����ֵΪ��");
		ProductSpecCharacteristicValue value  =null;
		result=specChar.specifyDefaultValue(value);
		assertEquals(false,result);
		
		logger.info("\t2:ProductSpecCharacteristicָ��Ĭ������ֵ,��ǰ����������ֵ");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),true,"",validFor,"2.7");
		result=specChar.specifyDefaultValue(value);
		assertEquals(false,result);
		
		logger.info("\t3:ProductSpecCharacteristicָ��Ĭ������ֵ,ָ������ֵ������ΪĬ��ֵ");
		specChar.addValue(value);
		result=specChar.specifyDefaultValue(value);
		assertEquals(true,result);
		
		logger.info("\t4:ProductSpecCharacteristicָ��Ĭ������ֵ,ָ������ֵ��Ϊ��ǰ������ֵ");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.9");
		result=specChar.specifyDefaultValue(value);
		assertEquals(false,result);
		logger.info("\t5:ProductSpecCharacteristicָ��Ĭ������ֵ,ָ������ֵ������ǰ������ֵ�����Ҳ�ΪĬ��ֵ");
		specChar.addValue(value);
		result=specChar.specifyDefaultValue(value);
		assertEquals(true,result);
	}
	@Test
	public void testRetrieveDefaultValue(){
		logger.info("ProductSpecCharacteristic��ѯĬ������ֵ:");
		logger.info("\t1.ProductSpecCharacteristic��ѯĬ������ֵ,û������ֵ");
		List<ProductSpecCharacteristicValue> defaultCharValue=specChar.retrieveDefaultValue();
		assertEquals(null,defaultCharValue);
		
		logger.info("\t2.ProductSpecCharacteristic��ѯĬ������ֵ,û��Ĭ��ֵ");
		ProductSpecCharacteristicValue value =new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.7");
		specChar.addValue(value);
		defaultCharValue=specChar.retrieveDefaultValue();
		assertEquals(null,defaultCharValue);
		logger.info("\t2.ProductSpecCharacteristic��ѯĬ������ֵ,����Ĭ��ֵ");
		defaultCharValue=configSpecChar.retrieveDefaultValue();
		assertNotNull(defaultCharValue);
		
	}
	@Test
	public void testClearDefaultValue(){
		logger.info("ProductSpecCharacteristicȡ��Ĭ��ֵ ");
		logger.info("\t1.ProductSpecCharacteristicȡ��Ĭ������ֵ,û������ֵ");
		ProductSpecCharacteristicValue value =new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.7");
		boolean result=specChar.clearDefaultValue(value);
		assertEquals(false,result);
		
		logger.info("\t2.ProductSpecCharacteristicȡ��Ĭ������ֵ,��������ֵ����Ĭ��ֵ");
		specChar.addValue(value);
		result=specChar.clearDefaultValue(value);
		assertEquals(false,result);
		
		logger.info("\t3.ProductSpecCharacteristicȡ��Ĭ������ֵ,��������ֵ����Ĭ��ֵ��ָ��ֵ����������ֵ");
		result=configSpecChar.clearDefaultValue(value);
		assertEquals(false,result);
		
		logger.info("\t4.ProductSpecCharacteristicȡ��Ĭ������ֵ,��������ֵ����Ĭ��ֵ��ָ��ֵ��������ֵ,����Ĭ��ֵ");
		 value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.7");
		result=configSpecChar.clearDefaultValue(value);
		assertEquals(false,result);
		
		logger.info("\t5.ProductSpecCharacteristicȡ��Ĭ������ֵ,��������ֵ����Ĭ��ֵ��ָ��ֵ��������ֵ,��Ĭ��ֵ");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",validFor,"2.9");
		result=configSpecChar.clearDefaultValue(value);
		assertEquals(true,result);
		logger.info("����ɹ�");
	}
	@Test
	public void testAddRelatedCharacteristic(){
		logger.info("ProductSpecCharacteristic����������������");
		
		logger.info("\t1.ProductSpecCharacteristic��������������,ָ������Ϊnull");
		boolean result=specChar.addRelatedCharacteristic(null, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals(false, result);
		
		logger.info("\t2.ProductSpecCharacteristic��������������,ָ�������뵱ǰ������ͬ");
		result=specChar.addRelatedCharacteristic(specChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals(false, result);
		
		logger.info("\t3.ProductSpecCharacteristic��������������,ָ�������뵱ǰ��������ͬ");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals(true, result);
		logger.info("��ӳɹ�");
		logger.info("\t4.ProductSpecCharacteristic��������������,ָ�������Ѿ������ۺϹ�ϵ");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals(false, result);
		logger.info("\t5.ProductSpecCharacteristic��������������,ָ�������Ѿ������ۺϹ�ϵ,�Ƿ���Խ���������ϵ");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.DEPENDENCY.getValue(), 1, validFor);
		assertEquals(false, result);
		
	}
	@Test
	public void testRetrieveRelatedCharacteristic(){
		logger.info("ProductSpecCharacteristic��ѯ�������������");
		
		logger.info("\t1.ProductSpecCharacteristic��ѯ�����������,ָ������Ϊnull");
		List<ProductSpecCharacteristic> specChars=specChar.retrieveRelatedCharacteristic(null);
		assertNull(specChars);
		
		logger.info("\t2.ProductSpecCharacteristic��ѯ�����������,��ǰ����û��ָ����ϵ������");
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue());
		assertNull(specChars);
		
		logger.info("\t3.ProductSpecCharacteristic��ѯ�����������,��ǰ�������ھۺϹ�ϵ����");
		specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue());
		assertNotNull(specChars);
		logger.info("\t4.ProductSpecCharacteristic��ѯ�����������,��ǰ�������ھۺϹ�ϵ����,������������ϵ");
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.DEPENDENCY.getValue());
		assertNull(specChars);
	}
	

}
