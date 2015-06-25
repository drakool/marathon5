package com.asiainfo.baas.marathon.baseType;

import java.util.Date;

import com.asiainfo.baas.common.DateUtils;

/**
 * A base / value business entity used to represent a period of time, between
 * two time points
 */
public class TimePeriod {

    /**
     * An instant of time, starting at the TimePeriod
     * 
     * Notes: If null, then represents to the beginning of time
     */
    public Date startDateTime;
    /**
     * An instant of time, ending at the TimePeriod:
     * 
     * Notes: If null, then represents to the end of time
     */
    public Date endDateTime;

    /**
     * У��ʱ����Ƿ���TimePeriod��
     * 
     * 
     * 
     * @param d1
     * @param d2
     */
    public boolean isInPeriod(Date targetDate) {

        // ���ʱ���δ���ã�Ĭ��������Ч
        if (this.startDateTime != null && DateUtils.compareDate(targetDate, this.startDateTime) < 0) {
            return false;
        }

        if (this.endDateTime != null && DateUtils.compareDate(targetDate, this.endDateTime) > 0) {
            return false;
        }
        return true;
    }

}