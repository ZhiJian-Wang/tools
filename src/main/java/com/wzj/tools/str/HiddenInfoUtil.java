package com.wzj.tools.str;

/**
 * @author ZhiJian.Wang
 * @package com.wzj.tools.str
 * @description 隐藏信息处理工具类
 * @date 2023-01-12 16:56
 */
public class HiddenInfoUtil {

    public static String hideName(String name){
        if (name==null|| "".equals(name)){
            return name;
        }
        if (name.length()>=3){
            name=name.charAt(0)+"**";
        }else {
            name=name.charAt(0)+"*";
        }
        return name;
    }

    public static void main(String[] args) {
        System.out.println(hideName("张三"));
    }
}
