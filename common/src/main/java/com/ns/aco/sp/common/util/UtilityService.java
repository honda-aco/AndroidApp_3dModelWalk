package com.ns.aco.sp.common.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

public class UtilityService {
    // サービスの存在確認
    public static boolean existsService(Context context, String serviceName){
        ActivityManager activityManager = (ActivityManager)context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningService = activityManager.getRunningServices(Integer.MAX_VALUE);
        if(runningService.equals(null) == false) {
            for(ActivityManager.RunningServiceInfo srvInfo : runningService) {
                if(srvInfo.service.getShortClassName().equals(serviceName)){
                    return true;
                }
            }
        }
        return false;
    }
}
