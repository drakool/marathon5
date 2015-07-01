package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;

@RunWith(Parameterized.class)
public class ProductSpecCharacteristicValueTest2 {
	private Logger logger = Logger.getLogger(ProductSpecCharacteristicValueTest2.class);
	private ProductSpecCharacteristicValue specCharValue = null;
	private ProductSpecCharacteristicValue specCharValueFromTo = null;
	private ProductSpecCharacteristicValue targetSpecCharValue = null;
	private boolean retFlag = false;
	private String retMsg = "";
	private String valueFlag = "";
	@Before
	public  void initSpecCharValue(){
		TimePeriod validFor1 = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
		specCharValue = new ProductSpecCharacteristicValue("Text",false,"",validFor1,"��");
		specCharValueFromTo = new ProductSpecCharacteristicValue("Number",false,"GB",validFor1,"5","15","open");
	}
	@Parameters
	public static Iterable<Object[]> data() {  
       return Arrays.asList(new Object[][] { 
    		    {"value",true,"��������ֵ��ͬ�Ƚ�", "Text", false, "", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "��","","",""},
    		    {"value",false,"��������ֵ����ͬ�Ƚ�","Text", false, "", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "��","","",""},
    		    {"notValue",true,"��������ֵ������ͬ�Ƚ�","Number",false,"GB",new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"),"","5","15","open"},
    		    {"notValue",false,"��������ֵ����ͬ�Ƚ�","Number",false,"GB",new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"),"","10","20","open"}
               });  
	}  
	
	public ProductSpecCharacteristicValueTest2(String valueFlag,boolean retFlag,String retMsg,String valueType,boolean isDefault,String unitOfMeasure,TimePeriod validFor,String value,String valueFrom,String valueTo,String rangeInterval){
		if("value".equals(valueFlag)){
		this.targetSpecCharValue = new ProductSpecCharacteristicValue( valueType, isDefault, unitOfMeasure, validFor, value);
		}else{
			this.targetSpecCharValue = new ProductSpecCharacteristicValue( valueType, isDefault, unitOfMeasure, validFor, valueFrom,valueTo,rangeInterval);	
		}
		this.retFlag = retFlag;
		this.retMsg = retMsg;
		this.valueFlag = valueFlag;
	}


	@Test
	public void testEquals() {
		//TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
		//ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue("Text",false,"",validFor,"��");
		boolean realRetFlag = false;
		if("value".equals(this.valueFlag)){
			realRetFlag = specCharValue.equals(targetSpecCharValue);
		}else{
			realRetFlag = specCharValueFromTo.equals(targetSpecCharValue);
		}
		assertEquals(this.retFlag,realRetFlag);
		System.out.println(this.retMsg+" �ȽϽ����"+realRetFlag);
		
		/*logger.info("��������ֵ����ͬ�Ƚ�");
		ProductSpecCharacteristicValue diffcharValue = new ProductSpecCharacteristicValue("Text",false,"",validFor,"��");
		boolean diffRetFlag = specCharValue.equals(diffcharValue);
		assertEquals(false,diffRetFlag);
		//fail("����һ��");
		
		logger.info("��������ֵ������ͬ�Ƚ�");
		ProductSpecCharacteristicValue charValueFormTo = new ProductSpecCharacteristicValue("Number",false,"GB",validFor,"5","15","open");
		boolean fromToDiffRetFlag = specCharValueFromTo.equals(charValueFormTo);
		assertEquals(true,fromToDiffRetFlag);
		
		logger.info("һ��ֵ�����ֵ���������ͬ�Ƚ�");
		ProductSpecCharacteristicValue charValueFormTo2 = new ProductSpecCharacteristicValue("Number",false,"GB",validFor,"5","15","open");
		boolean fromToRetFlag = specCharValue.equals(charValueFormTo2);
		assertEquals(false,fromToRetFlag);
		//fail("����һ��");
		
		logger.info("��������ֵ����ͬ�Ƚ�");
		ProductSpecCharacteristicValue diffcharValueFormTo = new ProductSpecCharacteristicValue("Number",false,"GB",validFor,"10","20","open");
		boolean fromToDiffRetFlag2 = specCharValueFromTo.equals(diffcharValueFormTo);
		assertEquals(false,fromToDiffRetFlag2);
		//fail("����һ��");
*/	}

}
