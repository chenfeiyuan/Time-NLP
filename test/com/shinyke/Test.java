package com.shinyke;

import com.time.nlp.TimeNormalizer;
import com.time.nlp.TimeUnit;
import com.time.util.DateUtil;

import java.net.URL;

/**
 * @author cfy
 * @Description
 * @date 2022/6/25
 */
public class Test {
    public static void main(String[] args) throws Exception {
        URL url = TimeNormalizer.class.getResource("/TimeExp.m");
        TimeNormalizer normalizer = new TimeNormalizer(url.toURI().toString());
        normalizer.setPreferFuture(true);


        normalizer.parse("Hi，all.下周一下午三点开会");// 抽取时间
        TimeUnit[] unit = normalizer.getTimeUnit();
        System.out.println("Hi，all.下周一下午三点开会");
        System.out.println(DateUtil.formatDateDefault(unit[0].getTime()) + "-" + unit[0].getIsAllDayTime());

        normalizer.parse("早上六点起床");// 注意此处识别到6天在今天已经过去，自动识别为明早六点（未来倾向，可通过开关关闭：new TimeNormalizer(classPath+"/TimeExp.m", false)）
        unit = normalizer.getTimeUnit();
        System.out.println("早上六点起床");
        System.out.println(DateUtil.formatDateDefault(unit[0].getTime()) + "-" + unit[0].getIsAllDayTime());

        String str1 = "国内：登记人暂未填写该信息；";
        normalizer.parse(str1);
        unit = normalizer.getTimeUnit();
        System.out.println(str1
                + "==="
                + (unit.length > 0 ? DateUtil.formatDateDefault(unit[0].getTime()) : ""));

        String str2 = "国内：2021-12-10；";
        normalizer.parse(str2);
        unit = normalizer.getTimeUnit();
        System.out.println(str2
                + "==="
                + (unit.length > 0 ? DateUtil.formatDateDefault(unit[0].getTime()) : ""));
        String str3 = "国内：2021/10/11；";
        normalizer.parse(str3);
        unit = normalizer.getTimeUnit();
        System.out.println(str3
                + "==="
                + (unit.length > 0 ? DateUtil.formatDateDefault(unit[0].getTime()) : ""));

    }
}
