package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;

@RunWith(Parameterized.class)
public class ProductSpecCharacteristicTest {
		private ProductSpecCharacteristic specChar = null;
		private ProductSpecCharacteristic targetSpecChar = null;
		private boolean retFlag = false;
		private String retMsg = "";
		@Before
		public  void initSpecCharValue(){
			TimePeriod validFor1 = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
			//id,name,valueType,validFor,unique,minCardinality,maxCardinality
			specChar = new ProductSpecCharacteristic("1", "processor(������)", "Number",validFor1, "unique",1,1);
		}
		@Parameters
		public static Iterable<Object[]> data() {  
	       return Arrays.asList(new Object[][] { 
	    		    {true,"������ͬ��������Ƚ�", "1", "processor(������)", "Number", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "unique",1,1},
	    		    {false,"��������ͬ��������Ƚ�", "2", "memory", "text", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "", 1, 1 }
	               });  
		}  
		
		public ProductSpecCharacteristicTest(boolean retFlag,String retMsg,String id,String name,String valueType,TimePeriod validFor,String unique,int minCardinality,int maxCardinality){
			this.targetSpecChar = new ProductSpecCharacteristic( id,name,valueType,validFor, unique,minCardinality,maxCardinality);
			this.retFlag = retFlag;
			this.retMsg = retMsg;
		}


		@Test
		public void testEquals() {
			boolean realRetFlag = false;
			realRetFlag = specChar.equals(this.targetSpecChar);
			assertEquals(this.retFlag,realRetFlag);
			System.out.println(this.retMsg+" �ȽϽ����"+realRetFlag);
		}

	}


