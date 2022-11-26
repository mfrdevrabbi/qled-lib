package com.bat.splib;

import com.bat.splib.configure.LEDConfig;
import com.bat.splib.connector.LEDCommunicator;
import com.bat.splib.connector.LEDConnector;

/**
 * @author FRabbi
 * Date: 22 Nov 2022
 */
public class Main {
    public static void main(String[] args) throws Exception {
        LEDCommunicator connector = LEDConnector.builder()
                        .portName("COM1")
                        .build();

        LEDConfig config = connector.getLEDConfig();

        if(connector.isConnected()){
            System.out.println("LED port is connected.....");
        }else {
            System.out.println("LED port is not connected.....");
        }

        connector.displayMessage(1,"helloow");

    }
}