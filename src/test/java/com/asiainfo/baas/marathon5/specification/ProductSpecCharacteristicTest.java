package com.asiainfo.baas.marathon5.specification;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private static SimpleDateFormat format;
	private Logger logger=Logger.getLogger(ProductSpecCharacteristicTest.class);
	@BeforeClass
	public static void setUpBeforeClass(){
		  format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		  validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
		  
	}
	@Before
	public void initValue(){
		specChar = new ProductSpecCharacteristic("1", "����ͷ", CharacristicValueType.TEXT.getValue(),validFor, "unique",1,1);
		configSpecChar=new ConfigurableProductSpecCharacteristic("2", "Memory", CharacristicValueType.NUMBER.getValue(),validFor, "unique",1,1);
		ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.7");
		configSpecChar.addValue(value);
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",new TimePeriod("2015-05-03 12:00:00","2015-07-21 23:59:59"),"2.9");
		configSpecChar.addValue(value);
	}
	@Test
	public void testAddValue(){
		boolean result=false;
		logger.info("ProductSpecCharacteristic�������ֵ:");
		
		logger.info("1:��ӵ�����ֵΪnull");
		ProductSpecCharacteristicValue value  =null;
		result=specChar.addValue(value);
		assertEquals("��ӵ�����ֵΪnull",false,result);
		
		logger.info("2:����µ�����ֵ,����������ֵ��valueType����ͬ");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",validFor,"2.7");
		result=specChar.addValue(value);
		assertEquals("����µ�����ֵ,����������ֵ��valueType����ͬ",false,result);
		
		logger.info("3:����µ�����ֵ,����������ֵ��valueType��ͬ");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),true,"",validFor,"720pFace TimeHD��������ͷ");
		result=specChar.addValue(value);
		assertEquals("����µ�����ֵ,����������ֵ��valueType��ͬ",true,result);
		assertEquals("����µ�����ֵ,����������ֵ��valueType��ͬ",true,specChar.getProductSpecCharacteristicValue().contains(value));
		
		logger.info("4:ProductSpecCharacteristic����ظ�������ֵ");
		result=specChar.addValue(value);
		assertEquals("����ظ�������ֵ",false,result);
		
		logger.info("5:����µ�����ֵ��ֵ������������ӵ�����ֵ��ͬ");
		ProductSpecCharacteristicValue newValue=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),true,"",validFor,"720pFace TimeHD��������ͷ");
		result=specChar.addValue(newValue);
		assertEquals("����µ�����ֵ��ֵ������������ӵ�����ֵ��ͬ",false,result);

	}
	
	@Test
	public void testRetrieveValue() throws ParseException{
		logger.info("ProductSpecCharacteristic��ѯ����ֵ");
		
		logger.info("1:ProductSpecCharacteristic��ѯ����ֵ,ʱ��Ϊnull");
		Date time=null;
		List<ProductSpecCharacteristicValue> prodSpecCharValues=specChar.retrieveValue(time);
		assertEquals("��ѯ����ֵ,ʱ��Ϊnull",null, prodSpecCharValues);
		
		logger.info("2:ProductSpecCharacteristic��ѯ����ֵ,������ȷ,��������������ֵ");
		time=new Date();
		prodSpecCharValues=specChar.retrieveValue(time);
		assertEquals("��ѯ����ֵ,������ȷ����������������ֵ",null, prodSpecCharValues);
		
		logger.info("3:ProductSpecCharacteristic��ѯ����ֵ:������ȷ,������������ֵ(ʱ�������ʱ�����)");
		prodSpecCharValues=configSpecChar.retrieveValue(time);
		assertEquals("��ѯ����ֵ:������ȷ,������������ֵ(ʱ�������ʱ�����)",2,prodSpecCharValues.size());
		
		logger.info("4:ProductSpecCharacteristic��ѯ����ֵ��������ȷ,������������ֵ(ʱ�����һ��ʱ�����)");
		prodSpecCharValues=configSpecChar.retrieveValue(format.parse("2015-02-03 12:00:00"));
		assertEquals("��ѯ����ֵ��������ȷ,������������ֵ(ʱ�����һ��ʱ�����)",1,prodSpecCharValues.size());
		
		logger.info("5:ProductSpecCharacteristic��ѯ����ֵ:������ȷ,������������ֵ(ʱ��㲻������ʱ�����)");
		prodSpecCharValues=configSpecChar.retrieveValue(format.parse("2015-01-03 12:00:00"));
		assertEquals("��ѯ����ֵ:������ȷ,������������ֵ(ʱ��㲻������ʱ�����)",0,prodSpecCharValues.size());
	}
	@Test
	public void testSpecifyDefaultValue(){
		boolean result=false;
		logger.info("ProductSpecCharacteristicָ��Ĭ������ֵ:");
		
		logger.info("1:ProductSpecCharacteristicָ��Ĭ������ֵ������ֵΪ��");
		ProductSpecCharacteristicValue value  =null;
		result=specChar.specifyDefaultValue(value);
		assertEquals("ָ��Ĭ������ֵ������ֵΪ��",false,result);
		
		logger.info("2:ProductSpecCharacteristicָ��Ĭ������ֵ����ǰ����������ֵ");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),true,"",validFor,"2.7");
		result=specChar.specifyDefaultValue(value);
		assertEquals("ָ��Ĭ������ֵ����ǰ����������ֵ",false,result);
		
		logger.info("3:ProductSpecCharacteristicָ��Ĭ������ֵ:ָ������ֵ�����ڵ�ǰ����");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.9");
		result=specChar.specifyDefaultValue(value);
		assertEquals("ָ��Ĭ������ֵ:ָ������ֵ�����ڵ�ǰ����",false,result);
		
		logger.info("4:ProductSpecCharacteristicָ��Ĭ������ֵ��ָ������ֵ���ڵ�ǰ���������Ҳ�ΪĬ��ֵ(��ǰ����û��Ĭ��ֵ)");
		specChar.addValue(value);
		result=specChar.specifyDefaultValue(value);
		assertEquals("ָ��Ĭ������ֵ��ָ������ֵ���ڵ�ǰ���������Ҳ�ΪĬ��ֵ(��ǰ����û��Ĭ��ֵ)",true,result);
		assertEquals("ָ��Ĭ������ֵ��ָ������ֵ���ڵ�ǰ���������Ҳ�ΪĬ��ֵ(��ǰ����û��Ĭ��ֵ)",1,specChar.retrieveDefaultValue().size());
		
		logger.info("5:ProductSpecCharacteristicָ��Ĭ������ֵ��ָ������ֵ���ڵ�ǰ���������Ҳ�ΪĬ��ֵ(��ǰ��������1��Ĭ��ֵ)");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.7");
		specChar.addValue(value);
		result=specChar.specifyDefaultValue(value);
		assertEquals("ָ��Ĭ������ֵ��ָ������ֵ���ڵ�ǰ���������Ҳ�ΪĬ��ֵ(��ǰ��������1��Ĭ��ֵ)",true,result);
		assertEquals("ָ��Ĭ������ֵ��ָ������ֵ���ڵ�ǰ���������Ҳ�ΪĬ��ֵ(��ǰ��������1��Ĭ��ֵ)",2,specChar.retrieveDefaultValue().size());
		
		
		logger.info("6:ProductSpecCharacteristicָ��Ĭ������ֵ��ָ��������ֵ�ѱ�����ΪĬ��ֵ");
		specChar.addValue(value);
		result=specChar.specifyDefaultValue(value);
		assertEquals("ָ��Ĭ������ֵ��ָ��������ֵ�ѱ�����ΪĬ��ֵ",true,result);
		assertEquals(2,specChar.retrieveDefaultValue().size());
	}
	
	@Test
	public void testRetrieveDefaultValue(){
		logger.info("ProductSpecCharacteristic��ѯĬ������ֵ:");
		logger.info("1.ProductSpecCharacteristic��ѯĬ������ֵ������û������ֵ");
		List<ProductSpecCharacteristicValue> defaultCharValue=specChar.retrieveDefaultValue();
		assertNull("��ѯĬ������ֵ������û������ֵ",defaultCharValue);
		
		logger.info("2.ProductSpecCharacteristic��ѯĬ������ֵ:��������ֵ ��û��Ĭ��ֵ");
		ProductSpecCharacteristicValue value =new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.7");
		specChar.addValue(value);
		defaultCharValue=specChar.retrieveDefaultValue();
		assertEquals("��ѯĬ������ֵ:��������ֵ ��û��Ĭ��ֵ",0,defaultCharValue.size());
		
		
		logger.info("3.ProductSpecCharacteristic��ѯĬ������ֵ,����Ĭ��ֵ");
		defaultCharValue=configSpecChar.retrieveDefaultValue();
		assertNotNull("��ѯĬ������ֵ,����Ĭ��ֵ",defaultCharValue);
		assertEquals("��ѯĬ������ֵ,����Ĭ��ֵ",2,defaultCharValue.size());
	}
	
	@Test
	public void testClearDefaultValue(){
		logger.info("ProductSpecCharacteristicȡ��Ĭ��ֵ ");
		
		logger.info("1.ProductSpecCharacteristicȡ��Ĭ������ֵ������ֵΪNULL");
		ProductSpecCharacteristicValue value =null;
		boolean result=specChar.clearDefaultValue(value);
		assertEquals("ȡ��Ĭ������ֵ������ֵΪNULL",false,result);

		logger.info("2.ProductSpecCharacteristicȡ��Ĭ������ֵ:������ȷ��������û������ֵ");
		 value =new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.7");
		 result=specChar.clearDefaultValue(value);
		assertEquals("ȡ��Ĭ������ֵ:û������ֵ",false,result);
		
		logger.info("3.ProductSpecCharacteristicȡ��Ĭ������ֵ����������,��������ֵ����Ĭ��ֵ");
		specChar.addValue(value);
		result=specChar.clearDefaultValue(value);
		assertEquals("ȡ��Ĭ������ֵ����������,��������ֵ����Ĭ��ֵ",false,result);
		
		logger.info("4.ProductSpecCharacteristicȡ��Ĭ������ֵ:��������,��������ֵ����Ĭ��ֵ��ָ��ֵ����������");
		result=configSpecChar.clearDefaultValue(value);
		assertEquals("ȡ��Ĭ������ֵ:��������,��������ֵ����Ĭ��ֵ��ָ��ֵ����������",false,result);
		
		logger.info("5.ProductSpecCharacteristicȡ��Ĭ������ֵ����������,��������ֵ����Ĭ��ֵ��ָ��ֵ��������,����Ĭ��ֵ");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.7");
		result=configSpecChar.clearDefaultValue(value);
		assertEquals("ȡ��Ĭ������ֵ����������,��������ֵ����Ĭ��ֵ��ָ��ֵ��������,����Ĭ��ֵ",false,result);
		
		logger.info("6.ProductSpecCharacteristicȡ��Ĭ������ֵ:��������,��������ֵ����Ĭ��ֵ��ָ��ֵ��������ֵ,��Ĭ��ֵ");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",validFor,"2.9");
		result=configSpecChar.clearDefaultValue(value);
		assertEquals("ȡ��Ĭ������ֵ:��������,��������ֵ����Ĭ��ֵ��ָ��ֵ��������ֵ,��Ĭ��ֵ",true,result);
		assertEquals("ȡ��Ĭ������ֵ:��������,��������ֵ����Ĭ��ֵ��ָ��ֵ��������ֵ,��Ĭ��ֵ",false,configSpecChar.retrieveDefaultValue().contains(value));
		logger.info("����ɹ�");
	}
	@Test
	public void testAddRelatedCharacteristic(){
		logger.info("ProductSpecCharacteristic����������������");
		
		logger.info("1.ProductSpecCharacteristic��������������:ָ������Ϊnull");
		boolean result=specChar.addRelatedCharacteristic(null, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals("��������������:ָ������Ϊnull",false, result);
		
		logger.info("2.ProductSpecCharacteristic��������������:ָ�������뵱ǰ������ͬ");
		result=specChar.addRelatedCharacteristic(specChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals("��������������:ָ�������뵱ǰ������ͬ",false, result);
		
		logger.info("3.ProductSpecCharacteristic��������������:ָ�������뵱ǰ��������ͬ");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals("��������������:ָ�������뵱ǰ��������ͬ",true, result);
		assertEquals("��������������:ָ�������뵱ǰ��������ͬ",true, specChar.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue()).contains(configSpecChar));
		logger.info("��ӳɹ�");
		
		logger.info("4.ProductSpecCharacteristic����������������ָ�������Ѿ������ۺϹ�ϵ(ͬһʱ���)");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals(false, result);
		
		logger.info("5.ProductSpecCharacteristic����������������ָ�������Ѿ������ۺϹ�ϵ,ʱ��β�ͬ");
		TimePeriod  other_validFor = new TimePeriod("2015-07-22 12:00:00","2015-07-25 23:59:59");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, other_validFor);
		assertEquals(true, result);
		
		logger.info("6.ProductSpecCharacteristic����������������ָ�������Ѿ������ۺϹ�ϵ,ʱ����н���");
		other_validFor = new TimePeriod("2015-07-20 12:00:00","2015-07-25 23:59:59");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, other_validFor);
		assertEquals("����������������ָ�������Ѿ������ۺϹ�ϵ,ʱ����н���",false, result);
		
		logger.info("7.ProductSpecCharacteristic����������������ָ�������Ѿ������ۺϹ�ϵ,ʱ��ΰ���ԭ����ͬ��ϵ��ʱ���");
		other_validFor = new TimePeriod("2015-01-20 12:00:00","2015-07-30 23:59:59");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, other_validFor);
		assertEquals("����������������ָ�������Ѿ������ۺϹ�ϵ,ʱ��ΰ���ԭ����ͬ��ϵ��ʱ���",false, result);
		
		logger.info("8.ProductSpecCharacteristic����������������ָ�������Ѿ������ۺϹ�ϵ,ʱ���С��ԭ����ͬ��ϵ��ʱ���");
		other_validFor = new TimePeriod("2015-01-20 12:00:00","2015-03-30 23:59:59");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, other_validFor);
		assertEquals("����������������ָ�������Ѿ������ۺϹ�ϵ,ʱ���С��ԭ����ͬ��ϵ��ʱ���",false, result);
		
		logger.info("9.ProductSpecCharacteristic��������������,ָ�������Ѿ������ۺϹ�ϵ,�Ƿ���Խ���������ϵ");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.DEPENDENCY.getValue(), 1, validFor);
		assertEquals(false, result);
		
	}
	@Test
	public void testRetrieveRelatedCharacteristic(){
		logger.info("ProductSpecCharacteristic��ѯ�������������");
		
		logger.info("1.ProductSpecCharacteristic��ѯ�����������,ָ������Ϊnull");
		List<ProductSpecCharacteristic> specChars=specChar.retrieveRelatedCharacteristic(null);
		assertNull(specChars);
		
		logger.info("2.ProductSpecCharacteristic��ѯ�����������,��ǰ����û��ָ����ϵ������");
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue());
		assertNull(specChars);
		
		logger.info("3.ProductSpecCharacteristic��ѯ�����������,��ǰ�������ھۺϹ�ϵ����");
		specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue());
		assertNotNull(specChars);
		logger.info("4.ProductSpecCharacteristic��ѯ�����������,��ǰ�������ھۺϹ�ϵ����,������������ϵ");
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.DEPENDENCY.getValue());
		assertNull(specChars);
	}
	

}
