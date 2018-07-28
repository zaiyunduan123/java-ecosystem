package com.jesper.basis.jvm.escape;

/**
 * Created by jiangyunxiong on 2018/7/19.
 */
public class EscapeDemo {

    public static StringBuffer createStringBuffer(String s1, String s2){
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb;
    }
    public static String createStringBuffer2(String s1, String s2){
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);

        return sb.toString();
    }
}
