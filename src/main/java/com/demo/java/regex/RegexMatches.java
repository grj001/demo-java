package com.demo.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class RegexMatches {
    private static String REGEX = "a*b";
    private static String INPUT = "aabfooaabfooabfoobkkk";
    private static String REPLACE = "-";


    public void test(){
        Pattern p = Pattern.compile(REGEX);
        // 获取 matcher 对象
        Matcher m = p.matcher(INPUT);
        StringBuffer sb = new StringBuffer();
        while(m.find()){
            m.appendReplacement(sb,REPLACE);
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }

    public void matchChinese(String chinese){
        boolean m = Pattern.matches("[\\u4e00-\\u9fa5]",chinese);
        System.out.println(m);
    }



    public static void main(String[] args) {
        RegexMatches r = new RegexMatches();
        r.matchChinese("汉");
    }
}
