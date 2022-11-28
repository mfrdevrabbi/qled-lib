package com.ba.ledlib.utils;

import com.ba.ledlib.model.ColorModel;

/**
 * @author FRabbi
 * Date: 26 Nov 2022
 */
public final class Utils {

    private static final boolean logPrint = false;

    public static boolean isValidHardwareId(int hwId) {
        if (hwId < 1 || 99 < hwId) {
            throw new RuntimeException("Device ID should be between 1 and 99");
        }
        return true;
    }


    public static String parseConfigData(int oldHardwareId, int newHardwareId, ConfigOption configOption, ColorModel color, LedBorder shape, String msg) {
        String oldHwdId = "" + oldHardwareId;
        String newHwdId = "" + newHardwareId;

        if (oldHardwareId < 10) {
            oldHwdId = "0" + oldHwdId;
        }

        if (newHwdId.length() < 10) {
            newHwdId = "0" + newHwdId;
        }

        //String msg = "FR01";
        //"1"+oldHwdId+"@@"+newHwdId+"CC"+newHwdId+"#0"+cn;
        logPrint("ConfigOption:: " + configOption.toString() + " Config Color:: " + color.toString() + " Config Shape:: " + shape.toString());
        if (configOption.equals(ConfigOption.MSG)) {
            return "1" + oldHwdId + "@@" + newHwdId + getConfigOptionAndColor(configOption, color, shape) + newHwdId + "#1 " + msg;
        }
        return "1" + oldHwdId + "@@" + newHwdId + getConfigOptionAndColor(configOption, color, shape) + newHwdId + "#0" + newHwdId + " " + msg;
    }

    private static String getConfigOptionAndColor(ConfigOption option, ColorModel color, LedBorder shape) {
        switch (option) {
            case MSG:
                return "M" + getColorInt(color.getMessageColor());
            case TOKEN:
                return "T" + getColorInt(color.getTokenColor());
            case TOKEN_BORDER_COLOR:
                return "S" + getColorInt(color.getTokenBorderColor());
            case TOKEN_BORDER_SHAPE:
                return "B" + getBorderShapeInt(shape);
        }

        throw new RuntimeException("Illegal ConfigOption");

    }

    private static int getColorInt(LedColor color) {
        switch (color) {
            case RED:
                return 2;
            default:
                return 1;
        }

    }


    private static int getBorderShapeInt(LedBorder shape) {
        switch (shape) {
            case SQUARE:
                return 2;
            default:
                return 1;
        }
    }


    public static String parseTokenData(int hardwareId, int counterNo, String token) {
        String hwId, cn;
        if (!isValidHardwareId(hardwareId)) {
            return "";
        }
        hwId = "" + hardwareId;
        cn = "" + counterNo;

        if (hardwareId < 10) {
            hwId = "0" + hardwareId;
        }

        if (counterNo < 10) {
            cn = "0" + counterNo;
        }


        return "000000000" + hwId + "#0" + cn + " " + token;
    }

    public static String parseTokenData(int hardwareId, String token) {
        String hwId;
        if (!isValidHardwareId(hardwareId)) {
            return "";
        }
        hwId = "" + hardwareId;

        if (hardwareId < 10) {
            hwId = "0" + hardwareId;
        }

        return "000000000" + hwId + "#0" + "" + " " + token;
    }

    public static String parseMessageData(int hardwareId, String message) {
        if (!isValidHardwareId(hardwareId)) {
            return "";
        }

        if (hardwareId < 10) {
            return "0000000000" + hardwareId + "#1 " + message;
        }

        return "000000000" + hardwareId + "#1 " + message;

    }

    public static final void logPrint(Object message) {
        if (logPrint) {
            System.out.println(message);
        }
    }

    public static void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
