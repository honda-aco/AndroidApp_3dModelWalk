package com.ns.aco.sp.common.util;

import android.widget.NumberPicker;

/**
 * Created by ns on 2016/12/25.
 */

public class UtilityNumberPicker {

    static public Integer getIdInNumberPicker(NumberPicker numberPicker,
                                                CharSequence targetText){
        Integer id = null;
        if (numberPicker == null || targetText == null){
            return id;
        }
        // 引数の文字列とナンバーピッカーの表示文字列を比較し、最初に一致する表示文字列のIDを返す
        String[] displayValues = numberPicker.getDisplayedValues();
        for(int i = 0; i < displayValues.length; i++){
            if (displayValues[i].equals(targetText)){
                id = numberPicker.getMinValue() + i;
                break;
            }
        }
        return id;
    }

}
