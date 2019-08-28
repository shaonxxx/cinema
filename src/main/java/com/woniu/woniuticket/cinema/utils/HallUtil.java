package com.woniu.woniuticket.cinema.utils;

import java.util.Arrays;

/**
 * 放映厅数据转换工具
 */
public class HallUtil {

    /**
     * 添加时将座位转为结构图
     * @param str
     * @return
     */
    public  static String seattransmap(String str){
        String[] sts=str.split(",");
        StringBuilder sb=new StringBuilder();
        for (int i = 1; i <=10 ; i++) {
            for (int j = 1; j <=10 ; j++) {
                sb.append(i+"-"+j+",");
            }
        }
        str=sb.toString();
        str=str.substring(0,str.length()-1);
        String[] sts1=str.split(",");
        sb.setLength(0);
        for (int i = 0; i <sts1.length ; i++) {
            for (int j = 0; j <sts.length ; j++) {
                if(sts1[i].equals(sts[j])){
                    sb.append("c");
                    break;
                }
                if(j==sts.length-1&&!sts1[i].equals(sts[j])){
                    sb.append("_");
                }
            }
            if(sb.length()==10||sb.length()==21||sb.length()==32||sb.length()==43||sb.length()==54||sb.length()==65||sb.length()==76||sb.length()==87||sb.length()==98){
                sb.append(",");
            }
        }
        str=sb.toString();
        String[] ss=str.split(",");
        sb.setLength(0);
        for (int i = 0; i <ss.length ; i++) {
            if(ss[i].contains("c")){
                sb.append(ss[i]+",");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    /**
     * 将结构图转为座位
     * @param str
     * @return
     */
    public static String seat(String str){
        String[] sts=str.split(",");
        String str1=new String();
        StringBuilder sb=new StringBuilder();
        int s=1;
        for (int i = 0; i <sts.length ; i++) {
            str1=sts[i];
            str1=str1.replace("_","");
            for (int j = 1; j <=str1.length(); j++) {
                sb.append(s+"-"+j+",");
            }
            s++;
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    /**
     * 获取座位的数量
     * @param str
     * @return
     */
    public static int seattotal(String str){
        String[] sts=str.split(",");
        return sts.length;
    }

    /**
     * 找出已生成订单中的座位
     * @param str
     * @return
     */
    public static String orderSeat(String str){
        String[] sts=str.split(",");
        for (int i = 0; i <sts.length ; i++) {
            sts[i]=sts[i].replace("-","_");
        }
        return Arrays.toString(sts);
    }
}
