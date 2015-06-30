package com.asiainfo.baas.marathon5.apple;

import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.marathon.baseType.TimePeriod;

public class TestProductSpecificationData {

    // ���� ��ID��name��
    public static Object[][] specChar = {
            { "1", "��ʾ��", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false },
            { "11", "Retina ��ʾ��", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1,
                    1, false },
            { "12", "��ʼ�ֱ���", "text", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1,
                    false },
            { "13", "��չ�ֱ���", "text", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1,
                    false },
            { "2", "������", "text", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, true },
            { "3", "�ߴ������", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1,
                    false },
            { "31", "��", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false },
            { "32", "��", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false },
            { "33", "��", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false },
            { "34", "����", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false } };
    // ����ֵ
    public static Object[][] specCharValue = {
            { 1, "number", true, "Ӣ��", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "13.3" },
            { 1, "number", false, "Ӣ��", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "15.4" },
            { 2, "text", true, "���� (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "2560 x 1600" },
            { 2, "text", false, "���� (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "2880 x 1800" },
            { 3, "text", false, "���� (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1680 x 1050" },
            { 3, "text", false, "���� (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1440 x 900" },
            { 3, "text", false, "���� (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1024 x 640" },
            { 3, "text", false, "���� (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1920 x 1200" },
            { 3, "text", false, "���� (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1280 x 800" },
            { 4, "text", false, "", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "2.7GHz\n2.7GHz ˫�� Intel \nCore i5 ������ \n(Turbo Boost �ߴ� 3.1GHz)���䱸 3MB ������������" },
            { 4, "text", false, "", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "2.9GHz\n2.9GHz ˫�� Intel \nCore i5 ������ \n(Turbo Boost �ߴ�3.3GHz)���䱸 3MB ������������" },
            { 4, "text", false, "", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "3.1GHz\n3.1GHz ˫�� Intel \nCore i7 ������ \n(Turbo Boost �ߴ� 3.4GHz)���䱸 4MB ������������" },
            { 6, "number", false, "����", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "21.9" },
            { 6, "number", false, "����", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "24.71" },
            { 7, "number", false, "����", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "31.4" },
            { 7, "number", false, "����", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "35.89" },
            { 8, "number", false, "����", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "1.8" },
            { 9, "number", false, "ǧ��", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "1.58" },
            { 9, "number", false, "ǧ��", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "2.04" } };

    public static Object[][] relateSpecChar = {
            { "1", "11", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") },
            { "1", "12", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") },
            { "1", "13", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") },
            { "3", "31", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") },
            { "3", "32", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") },
            { "3", "33", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") },
            { "3", "34", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") } };
    // ʹ������ֵ��13��mac
    // pro����charId,canBeOveridden,isPackage,validFor,name,unique,minCardinality,maxCardinality,extensible,description,isHaveValue��
    public static Object[][] one_charData = {
            { "1", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "��ʾ��", "unique", 1, 1,
                    true, "", false, null },
            { "11", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "Retina ��ʾ��", "unique",
                    1, 1, true, "13.3 Ӣ�� (�Խ���) LED ������ʾ�� (���� IPS ����)���ֱ��� 2560 x 1600 (227 ppi)��֧��������ɫ��", true,
                    new int[] { 0 } },
            { "12", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "Retina ��ʾ��", "unique",
                    1, 1, true, "13.3 Ӣ�� (�Խ���) LED ������ʾ�� (���� IPS ����)���ֱ��� 2560 x 1600 (227 ppi)��֧��������ɫ��", true,
                    new int[] { 0 } },
            { "13", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "Retina ��ʾ��", "unique",
                    1, 1, true, "13.3 Ӣ�� (�Խ���) LED ������ʾ�� (���� IPS ����)���ֱ��� 2560 x 1600 (227 ppi)��֧��������ɫ��", true,
                    new int[] { 0 } }

    };
    // ʹ������ֵ
    public static Object[][] two_charData = {
            { "processor(������)", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), true,
                    new int[] { 1 }, "cpu", "unique", 1, 1, true, "CPU" },
            { "memory", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), true,
                    new int[] { 1 }, "memory", "unique", 1, 1, true, "memory" } };

    public static Object[] specParameter = new Object[] { "mac pro-13-9288", "13Ӣ���䱸Retina��ʾ��", "MacPro", "in_active",
            new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), null, "1.0.0", "", "min", 109 };

    public static Object[] specParameter2 = new Object[] { "11", "2.7GHz ������ 256 GB �洢����", "apple", "in_active",
            new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), null, "2.0.0", "", "min", 109 };

}
