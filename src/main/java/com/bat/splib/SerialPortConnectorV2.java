
package com.bat.splib;

import jssc.SerialPort;
import jssc.SerialPortException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author FRabbi
 * Date: 22 Nov 2022
 */
public final class SerialPortConnectorV2 {
    private static SerialPortConnectorV2 INSTANCE;

    private SerialPortConnectorV2() {
        // this class not instantiable other place
    }

    public static SerialPortConnectorV2 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SerialPortConnectorV2();
        }
        return INSTANCE;
    }

    private SerialPort serialPort;

    public void connect(String portname) throws Exception {
        serialPort = new SerialPort(portname);

        serialPort.openPort();//Open serial port
        serialPort.setParams(SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
    }

    public void writetoport(String data) {
        try {
            serialPort.writeBytes(data.getBytes());//Write data to port
        } catch (SerialPortException ex) {
            Logger.getLogger(SerialPortConnectorV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            serialPort.closePort();//Close serial port
        } catch (SerialPortException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * parsing data
     */

    public static String getParseData(String output) {

        JSONObject jsonObj = new JSONObject(output);
        JSONArray array = jsonObj.getJSONArray("data");
        String sendData = "";

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            String deviceId = obj.getString("deviceId");
            String moduleId = obj.getString("moduleId");

            String refdataStr = obj.getString("refData");
            if (!refdataStr.equalsIgnoreCase("")) {
                JSONObject refData = new JSONObject(refdataStr);
                String changeHeader = refData.getString("changeHeader");
                JSONObject changeHardObj = refData.getJSONObject("changeHardwareId");
                //            String oldId = changeHardObj.getString("oldId");
                //            String newId = changeHardObj.getString("newId");

                String color = refData.getString("colour");
                String scroll = refData.getString("scroll");

                JSONObject mainDataObject = refData.getJSONObject("data");


                JSONObject mainDataHeaderObject = mainDataObject.getJSONObject("header");
                String headerText = mainDataHeaderObject.getString("titleText");
                String headerTextSize = mainDataHeaderObject.getString("titleFontSize");


                JSONObject mainDataBodyObject = mainDataObject.getJSONObject("body");
                String bodyText = mainDataBodyObject.getString("bodyText");
                //String bodyTextSize = mainDataBodyObject.getString("bodyFontSize");

                sendData += deviceId + "," + moduleId + "," + changeHeader + "," + "00:00" + "," + color + "," + scroll + "," + headerText + "," + headerTextSize + "," + bodyText + "," + "1" + ";";

            }

        }

        System.out.println(sendData);

        return sendData;
    }

}
