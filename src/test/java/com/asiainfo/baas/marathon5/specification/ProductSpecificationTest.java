package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private  ProductSpecification expectProdSpec = null;
	private  TimePeriod validFor = null;
	@Before
	public  void initProdSpec(){
		validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
		prodSpec = new AtomicProductSpecification("mac-13", "13-inch MacBook Pro", "apple",ProductSpecificationStatus.PLANNED.getValue());
		expectProdSpec = new AtomicProductSpecification("mac-13", "13-inch MacBook Pro", "apple",ProductSpecificationStatus.PLANNED.getValue());
	}
	@Test
    public void testAddCharacteristic() {
        ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[4]);
        Set<ProductSpecCharUse> expectCharUse = new HashSet<ProductSpecCharUse>();
        ProductSpecCharUse charUse = new ProductSpecCharUse(characteristic,false, false, validFor, "CPU");
        ProductSpecCharUse charUse2 = new ProductSpecCharUse(characteristic,false, false, validFor, "������(CPU)");
        expectCharUse.add(charUse);
       //���һ������
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        assertEquals("���һ������",expectCharUse,prodSpec.getProdSpecCharUse());
        
        //���һ���Ѵ��ڵ�����
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "CPU");
        assertEquals("���һ���Ѵ��ڵ�����",expectCharUse,prodSpec.getProdSpecCharUse());
        
        //���һ���Ѵ��ڵ�������ʹ�����ֲ�ͬ
        expectCharUse.add(charUse2);
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "������(CPU)");
        assertEquals("���һ���Ѵ��ڵ�������ʹ�����ֲ�ͬ",expectCharUse,prodSpec.getProdSpecCharUse());
        
        //���һ������Ϊ��
        prodSpec.addCharacteristic(null, false, false, validFor, "CPU");
        assertEquals("���һ������Ϊ��",expectCharUse,prodSpec.getProdSpecCharUse());
    }

    @Test
    public void testAttachCharacteristicValue() {
    	 ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	 ProductSpecCharacteristic  characteristic1 = this.createChar(TestProductSpecificationData.specChar[4]);
    	 ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
    	 ProductSpecCharacteristic  characteristic3 = this.createChar(TestProductSpecificationData.specChar[0]);
    	 //�������  //��ʾ��
    	 prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
    	 prodSpec.addCharacteristic(characteristic2, false, false, validFor, "�ߴ������");
    	 
    	 ProductSpecCharacteristicValue charValue = this.createValue(TestProductSpecificationData.specCharValue[9]);
    	 ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[9]);
    	 ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[10]);
    	 ProductSpecCharacteristicValue charValue4 = this.createValue(TestProductSpecificationData.specCharValue[10]);
    	 
    	 characteristic.addValue(charValue);
    	 characteristic.addValue(charValue3);
    	 
    	 Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
         ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue,false,validFor);
         expectCharValueUse.add(charValueUse);
    	 
         //���һ������ֵ
         prodSpec.attachCharacteristicValue(characteristic, charValue, false, validFor);
         assertEquals("���һ������ֵ",expectCharValueUse, prodSpec.getProdSpecCharUse().iterator().next());
         
         //���һ���Ѵ��ڵ�����ֵ
         prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
         assertEquals("���һ���Ѵ��ڵ�����ֵ",expectCharValueUse,prodSpec.getProdSpecCharUse().iterator().next());
         
         //���һ������ֵ,����ֵ�����ڸ�����
         prodSpec.attachCharacteristicValue(characteristic, charValue4, false, validFor);
         assertEquals("���һ������ֵ,����ֵ�����ڸ�����",expectCharValueUse,prodSpec.getProdSpecCharUse().iterator().next());
         
         //���һ������ֵ,���������ڸù��
         prodSpec.attachCharacteristicValue(characteristic3, charValue3, false, validFor);
         assertEquals("���һ������ֵ,���������ڸù��",expectCharValueUse,prodSpec.getProdSpecCharUse().iterator().next());
         
         //���һ������ֵΪ��
         prodSpec.attachCharacteristicValue(characteristic, null, false, validFor);
         assertEquals("���һ������ֵΪ��",expectCharValueUse,prodSpec.getProdSpecCharUse().iterator().next());
    }

    @Test
    public void testSpecifyDefaultCharacteristicValue() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	//�������
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 
   	 	ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
   	 	ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
   	 	//�������ֵ
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
	   	 Set<ProductSpecCharUse> expectCharUse = new HashSet<ProductSpecCharUse>();
	     ProductSpecCharUse charUse = new ProductSpecCharUse(characteristic,false, false, validFor, "CPU");
	     ProductSpecCharUse charUse2 = new ProductSpecCharUse(characteristic,false, false, validFor, "������(CPU)");
	     
	   	 Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
	     ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue2,true,validFor);
	     expectCharValueUse.add(charValueUse);
	     charUse.setProdSpecCharValueUse(expectCharValueUse);
	     
	     expectCharUse.add(charUse);
     
   	 	//����ĳһ������Ĭ��ֵ
		boolean retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic, charValue2);
		assertTrue("����ĳһ������Ĭ��ֵ",retFlag);
		
		//����ĳһ������Ĭ��ֵ�������ֵ����Ϊnull
		charValue2 = null;
		retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic,null);
		assertFalse("����ĳһ������Ĭ��ֵ�������ֵ����Ϊnull",retFlag);
		
		//����ĳһ������Ĭ��ֵ�������ֵ����Ϊ���Ǹ��������е�
		ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[11]);
		retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic,charValue3);
		assertFalse("����ĳһ������Ĭ��ֵ�������ֵ����Ϊ���Ǹ��������е�",retFlag);
    }

    @Test
    public void testRetrieveDefaultCharacteristicValue(){
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[4]);
    	ProductSpecCharacteristic  characteristic3 = this.createChar(TestProductSpecificationData.specChar[5]);
    	ProductSpecCharacteristic  characteristic4 = this.createChar(TestProductSpecificationData.specChar[5]);
    	ProductSpecCharacteristic  characteristic5 = this.createChar(TestProductSpecificationData.specChar[6]);
    	ProductSpecCharacteristic  characteristic6 = this.createChar(TestProductSpecificationData.specChar[6]);
    	logger.info("�������");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 	prodSpec.addCharacteristic(characteristic3, false, false, validFor, "��ɫ");
   	 	prodSpec.addCharacteristic(characteristic5, false, false, validFor, "�ֱ���");
   	 
   	 	ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
   	 	ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
   	 	ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[7]);
   	 	logger.info("�������ֵ");
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic5, charValue3, false, validFor);
   	 	
   	 	logger.info("��ѯĳһ������Ĭ��ֵ");
   	 	List<ProdSpecCharValueUse> defaultCharValues = prodSpec.retrieveDefaultCharacteristicValue(characteristic2);
		assertNotNull("��ѯĬ��ֵ",defaultCharValues);
		assertEquals("��ѯĬ��ֵ",1,defaultCharValues.size());
		logger.info("Ĭ��ֵ��ѯ�ɹ���");
		
		logger.info("��ѯĳһ������Ĭ��ֵ�����������û��ֵ");
		defaultCharValues = prodSpec.retrieveDefaultCharacteristicValue(characteristic4);
		assertNotNull("��ѯĳһ������Ĭ��ֵ�����������û��ֵ",defaultCharValues);
		assertEquals("��ѯĳһ������Ĭ��ֵ�����������û��ֵ",0,defaultCharValues.size());
		logger.info("Ĭ��ֵ��ѯʧ�ܣ�");
		
		logger.info("��ѯĳһ������Ĭ��ֵ�����������û��Ĭ��ֵ");
		defaultCharValues = prodSpec.retrieveDefaultCharacteristicValue(characteristic6);
		assertNotNull("��ѯĳһ������Ĭ��ֵ�����������û��Ĭ��ֵ",defaultCharValues);
		assertEquals("��ѯĳһ������Ĭ��ֵ�����������û��Ĭ��ֵ",0,defaultCharValues.size());
		logger.info("Ĭ��ֵ��ѯʧ�ܣ�");
		
		
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
   	 	
		logger.info("���ĳһ������Ĭ��ֵ�������ֵ����Ϊnull");
		boolean retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic,null);
		assertFalse("���ĳһ������Ĭ��ֵ�������ֵ����Ϊnull",retFlag);
		logger.info("Ĭ��ֵ���ʧ�ܣ�");
		
		logger.info("���ĳһ������Ĭ��ֵ�������ֵ����Ϊ���Ǹ��������е�");
		ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[11]);
		retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic,charValue3);
		assertFalse("���ĳһ������Ĭ��ֵ�������ֵ����Ϊ���Ǹ��������е�",retFlag);
		logger.info("Ĭ��ֵ���ʧ�ܣ�");
		
		logger.info("���ĳһ������Ĭ��ֵ");
		retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic, charValue1);
		assertTrue("���ĳһ������Ĭ��ֵ",retFlag);
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
   	 	assertNotNull("��ѯ��ǰʱ��������",charUses);
   	 	assertEquals("��ѯ��ǰʱ��������",2, charUses.size());
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
	   	assertNotNull("��ѯĳһ�����ڵ�ǰʱ����ֵ",charValueUses);
	 	assertEquals("��ѯĳһ�����ڵ�ǰʱ����ֵ",2, charValueUses.size());
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
	   	assertNotNull("��ѯ����һ������",rootCharUses);
	 	assertEquals("��ѯ����һ������",2, rootCharUses.size());
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
	   	assertNotNull("��ѯ����ĳһ������������",leafCharUses);
	 	assertEquals("��ѯ����ĳһ������������",4, leafCharUses.size());
	 	logger.info("��ѯ�������ɹ���");
	 	
	 	logger.info("��ѯ����ĳһ������������");
   	 	List<ProductSpecCharUse> leafCharUses2 = prodSpec.retrieveLeafCharacteristic(null, new Date());
	   	assertNull("��ѯ����ĳһ������������",leafCharUses2);
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
		assertTrue("����ĳһ������Cardinality",retFlag);
		logger.info("Cardinality���óɹ���");
		
		logger.info("����Cardinality������Ϊ��");
		retFlag = prodSpec.specifyCardinality(null,1,5);
		assertFalse("����Cardinality������Ϊ��",retFlag);
		logger.info("Cardinality����ʧ�ܣ�");
		
		logger.info("����Cardinality���������Ǳ��õ�");
		retFlag = prodSpec.specifyCardinality(characteristic2,1,5);
		assertFalse("����Cardinality���������Ǳ��õ�",retFlag);
		logger.info("Cardinality����ʧ�ܣ�");
    }

    private void setExcetpProdSpec(ProductSpecCharacteristic characteristic, boolean canBeOveridden, boolean isPackage,
            TimePeriod validFor,String name,ProductSpecCharacteristicValue prodSpecCharVal, boolean isDefault){
    	 Set<ProductSpecCharUse> prodSpecCharUse = new HashSet<ProductSpecCharUse>();
         ProductSpecCharUse charUse = new ProductSpecCharUse(characteristic,canBeOveridden,isPackage,validFor,name);
         //����Value
         if(null != prodSpecCharVal){
        	 Set<ProdSpecCharValueUse> prodSpecCharValueUse = new HashSet<ProdSpecCharValueUse>();
	         ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(prodSpecCharVal,isDefault,validFor);
	         prodSpecCharValueUse.add(charValueUse);
	         charUse.setProdSpecCharValueUse(prodSpecCharValueUse);
         }
         prodSpecCharUse.add(charUse);
         expectProdSpec.setProdSpecCharUse(prodSpecCharUse);
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
