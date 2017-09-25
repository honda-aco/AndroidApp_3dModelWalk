package com.ns.aco.sp.common.util;

public class UtilityString {

    static public boolean isInteger(String checkString){
        try {
            Integer.parseInt(checkString);
            return  true;
        }catch (NumberFormatException  e){
            return  false;
        }
    }

    static public String replace(String format, String replaceValue){
        return format.replace("?", String.valueOf(replaceValue));
    }
}
