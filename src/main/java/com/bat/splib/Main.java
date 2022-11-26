package com.bat.splib;
/**
 * @author FRabbi
 * Date: 22 Nov 2022
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SerialPortConnectorV2 serialPortConnectorV2 = SerialPortConnectorV2.getInstance();
        System.out.println("Successfully executed....");
        serialPortConnectorV2.connect("COM1");
        serialPortConnectorV2.writetoport("sR");
        serialPortConnectorV2.disconnect();
    }
}