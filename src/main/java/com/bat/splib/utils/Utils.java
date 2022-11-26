package com.bat.splib.utils;
/**
 * @author FRabbi
 * Date: 26 Nov 2022
 */
public class Utils {
    public static boolean isValidDeviceId(int hwId){
        if(hwId < 1 || 99 < hwId){
            throw new RuntimeException("Device ID should be between 1 and 99");
        }
        return false;
    }


    public static String parseConfigData(int oldHardwareId, int newHardwareId, int counterNo) {
        String oldHwdId = ""+oldHardwareId;
        String newHwdId = ""+newHardwareId;
        String cn = ""+counterNo;

        if(oldHardwareId<10){
            oldHwdId = "0"+oldHwdId;
        }

        if(newHwdId.length()<10){
            newHwdId = "0"+newHwdId;
        }

        if(counterNo<10){
            cn = "0"+counterNo;
        }

        return "1"+oldHwdId+"@@"+newHwdId+"CC"+newHwdId+"#0"+cn;
    }


    public static String parseTokenData(int hardwareId, int counterNo, String token) {
        String hwId,cn;
        if(isValidDeviceId(hardwareId)){
            return "";
        }
        hwId = ""+hardwareId;
        cn = ""+counterNo;

        if(hardwareId<10){
            hwId = "0"+hardwareId;
        }

        if(counterNo<10){
            cn = "0"+counterNo;
        }


        return "000000000"+hwId+"#0"+counterNo+" "+token;
    }

    public static String parseMessageData(int hardwareId, String message) {
        if(isValidDeviceId(hardwareId)){
            return "";
        }

        if(hardwareId<10){
            return "0000000000"+hardwareId+"#1 +"+message;
        }

        return "000000000"+hardwareId+"#1 "+message;

    }

}
