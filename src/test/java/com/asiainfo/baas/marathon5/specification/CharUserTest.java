package com.asiainfo.baas.marathon5.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProductSpecCharUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;
import com.asiainfo.baas.marathon5.common.CommonUtils;

public class CharUserTest {
	
	TimePeriod validFor = new TimePeriod();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	/**
	 * ����Ĭ��ֵ
	 */
	@Test
	public void setDefaultValue(){
		try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			String id = "1112";
			String name = "��ɫ" ;
			String valueType = "Text";
			String unique = "��";
			int minCardinality = 1; 
			int maxCardinality = 3;
			ProductSpecCharacteristic specChar = new ProductSpecCharacteristic(id,name,valueType,validFor,unique,minCardinality,maxCardinality);
			ProductSpecCharUse charUse = new ProductSpecCharUse(specChar,false,false,validFor);
			boolean isDefault = false;
			String unitOfMeasure = "";
			String value = "��";
			ProductSpecCharacteristicValue defaultValue = new ProductSpecCharacteristicValue(valueType,isDefault,unitOfMeasure,validFor,value);
			charUse.specifyDefaultCharacteristicValue(defaultValue);
			CommonUtils.printProperty(null, null,charUse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * ���ʹ��ֵ
	 */
	@Test
	public void addValueUse(){
		try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			String id = "1112";
			String name = "��ɫ" ;
			String valueType = "Text";
			String unique = "��";
			int minCardinality = 1; 
			int maxCardinality = 3;
			ProductSpecCharacteristic specChar = new ProductSpecCharacteristic(id,name,valueType,validFor,unique,minCardinality,maxCardinality);
			ProductSpecCharUse charUse = new ProductSpecCharUse(specChar,false,false,validFor);
			boolean isDefault = false;
			String unitOfMeasure = "";
			String value = "��";
			ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue(valueType,isDefault,unitOfMeasure,validFor,value);
			charUse.addValue(charValue, false, validFor);
			CommonUtils.printProperty(null, null,charUse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���������С����
	 */
	@Test
	public void setCardinality(){
		try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			String id = "1112";
			String name = "��ɫ" ;
			String valueType = "Text";
			String unique = "��";
			int minCardinality = 1; 
			int maxCardinality = 3;
			ProductSpecCharacteristic specChar = new ProductSpecCharacteristic(id,name,valueType,validFor,unique,minCardinality,maxCardinality);
			ProductSpecCharUse charUse = new ProductSpecCharUse(specChar,false,false,validFor);
			int minCardinality2 = 2; 
			int maxCardinality2 = 4;
			charUse.setCardinality(minCardinality2, maxCardinality2);
			CommonUtils.printProperty(null, null,charUse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
