package com.chen.tian.papermgr.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by ChenTian on 2018/5/9.
 */
public class Utils {
    private static final String df1 = "yyyy-MM-dd HH:mm:ss";
    private static final String df2 = "yyyy-MM-dd";
    private static final String df3 = "HH:mm:ss";
    private static final String df4 = "MMddHHmmss";
    private static final String df5 = "yyyyMMdd";
    private static final String df6 = "HHmmss";
    private static final String df7 = "yyyyMMddHHmmss";
    private static final String df8 = "yyyyMMddHHmmssSSS";
    private static final String df9 = "yyyy/MM/dd HH:mm:ss";
    private static final String df10 = "yyyyMMddHHmm";

    public static boolean isNull(Object obj) {
        return obj == null ? true : false;
    }

    public static boolean isEmpty(Collection<?> c) {
        if (isNull(c)) {
            return true;
        }

        return c.size() < 1 ? true : false;
    }

    public static boolean isEmpty(Map<?, ?> m){
        if(isNull(m))
            return true;
        return m.isEmpty();
    }

    public static boolean isEmpty(String s) {
        if (isNull(s)) {
            return true;
        }
        return s.trim().length() < 1 ? true : false;
    }

    public static <T> boolean isEmpty(T[] objs) {
        if (isNull(objs)) {
            return true;
        }
        return objs.length <= 0;
    }

    /**
     * 把格式为：yyyyMMddHHmmss 转成Date
     * @param dateStr
     * @return
     */
    public static Date convertyyyyMMddHHmmssToDate(String dateStr) {
        if(!isEmpty(dateStr)){
            try {
                DateFormat sdf = new SimpleDateFormat(df7);
                Date date = sdf.parse(dateStr);
                return date;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}