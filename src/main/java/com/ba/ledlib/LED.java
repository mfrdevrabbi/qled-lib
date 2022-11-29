package com.ba.ledlib;

import com.ba.ledlib.configure.LEDConfig;
import com.ba.ledlib.connector.LEDCommunicator;
import com.ba.ledlib.connector.LEDConnector;
import com.ba.ledlib.utils.LedBorder;
import com.ba.ledlib.utils.LedColor;
import com.ba.ledlib.utils.Utils;

/**
 * @author FRabbi
 * Date: 22 Nov 2022
 */
public class LED {
    public static void main(String[] args) {
        System.out.println("program is running.......");

        LEDCommunicator connector = LEDConnector.builder()
                .portName("COM1")
                .connect();

    LEDConnector.builder()
                .portName("COM1");

//        LEDConfig ledConfig = connector.getLEDConfig();
//        ledConfig.getConfigBuilder()
//                .changeHardwareId(2,1)
//                .messageColor(LedColor.GREEN)
//                .borderColor(LedColor.GREEN)
//                .tokenColor(LedColor.RED)
//                .borderShape(LedBorder.ROUND)
//                .config();

//        if (connector.isConnected()) {
//            System.out.println("LED port is connected.....");
//        } else {
//            System.out.println("LED port is not connected.....");
//        }
//
//        connector.displayMessage(2, "Welcome to  message");
//
//        connector.displayToken(2, 1, "Close");

    }
}