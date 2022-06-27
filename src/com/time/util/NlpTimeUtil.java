package com.time.util;

import com.time.nlp.TimeNormalizer;
import com.time.nlp.TimeUnit;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

/**
 * @author cfy
 * @Description
 * @date 2022/6/27
 */

public class NlpTimeUtil {
    private static TimeNormalizer normalizer;

    static {
        try {
            URL url = TimeNormalizer.class.getResource("/TimeExp.m");
            normalizer = new TimeNormalizer(url.toURI().toString());
            normalizer.setPreferFuture(true);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public static String getDate(String text) {
        if (StringUtil.isEmpty(text)) {
            return null;
        }
//        TimeUnit[] unit = normalizer.parse(text);
//         =normalizer.getTimeUnit();
        Date date = normalizer.parseNew(text);
        return date == null ?
                null :
                DateUtil.formatDateDefault(date);
    }

    public static String getDate(String text, String format) {
        if (StringUtil.isEmpty(text)) {
            return null;
        }
//        TimeUnit[] unit = normalizer.parse(text);
//         =normalizer.getTimeUnit();
        Date date = normalizer.parseNew(text);
        return date == null ?
                null :
                DateUtil.formatDate(date, format);
    }


    public static void main(String[] args) throws Exception {


        System.out.println(getDate("国内：2021-12-10；fadfa"));
        System.out.println(getDate("2021-12-10"));
        System.out.println(getDate("2021/12/10"));
        System.out.println(getDate("20211210"));
        System.out.println(getDate("erqewr20211210fadfad lfad"));
//        System.out.println(getDate("2021 12 10"));
        System.out.println(getDate(""));
        System.out.println(getDate(null));
        System.out.println(getDate("国内：登记人暂未填写该信息；"));
        System.out.println(getDate("国内：登记人暂未填2021/10/11fdafadfaererqw"));

//        URL url = TimeNormalizer.class.getResource("/TimeExp.m");
//        TimeNormalizer normalizer = new TimeNormalizer(url.toURI().toString());
//        normalizer.setPreferFuture(true);
//
//
//        normalizer.parse("Hi，all.下周一下午三点开会");// 抽取时间
//        TimeUnit[] unit = normalizer.getTimeUnit();
//        System.out.println("Hi，all.下周一下午三点开会");
//        System.out.println(DateUtil.formatDateDefault(unit[0].getTime()) + "-" + unit[0].getIsAllDayTime());
//
//        normalizer.parse("早上六点起床");// 注意此处识别到6天在今天已经过去，自动识别为明早六点（未来倾向，可通过开关关闭：new TimeNormalizer(classPath+"/TimeExp.m", false)）
//        unit = normalizer.getTimeUnit();
//        System.out.println("早上六点起床");
//        System.out.println(unit.length);
//        System.out.println(DateUtil.formatDateDefault(unit[0].getTime()) + "-" + unit[0].getIsAllDayTime());
//
//        String str1 = "国内：登记人暂未填写该信息；";
//        normalizer.parse(str1);
//        unit = normalizer.getTimeUnit();
//
//        System.out.println(str1);
//        System.out.println(unit.length);
//        System.out.println(DateUtil.formatDateDefault(unit[0].getTime()) + "-" + unit[0].getIsAllDayTime());
    }
}
