package com.atguigu.crowd.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static com.atguigu.crowd.constant.CrowdConstant.YYYY_MM_DD_HH_MM_SS;

/**
 * @author shuyun
 * @date 2024-08-21 21:12:14
 */
public class DateUtil {


    /**
     * 格式化日期 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatDate(Date date){
        if (date == null){
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return sdf.format(date);
    }



    /**
     * 获取开始日期指定多少个月后的日期
     * @param startTime
     * @param months
     * @return
     */
    public static Date getDateAfterMonths(Date startTime, int months) {
        // 默认当前时间
        if (Objects.isNull(startTime)) {
            startTime = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        Date endDate = startTime;
        for (int i = 0; i < months; i++) {
            endDate = DateUtil.getNextMonthDate(endDate);
            calendar.setTime(endDate);
            if (i == months - 1) {
                return endDate;
            }
            // 再下一个月的开始日期要从下一天算起
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            endDate = calendar.getTime();
        }
        return endDate;
    }


    /**
     * 获取指定日期的下个月对应的日期
     *
     * @param startTime
     * @return
     */
    public static Date getNextMonthDate(Date startTime) {
        // 默认当前时间
        if (Objects.isNull(startTime)) {
            startTime = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);

        // 获取该月有多少天：下个月的天数置为0就是上月的最后一天
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 0);
        int days = calendar.get(Calendar.DAY_OF_MONTH);

        // 设置下个月对应的日期，即 当前日期+该月的天数-1 (减1是因为开始时间从当天算起)
        Calendar nextMonth = Calendar.getInstance();
        nextMonth.setTime(startTime);
        nextMonth.add(Calendar.DAY_OF_MONTH, days - 1);
        return nextMonth.getTime();
    }


}
