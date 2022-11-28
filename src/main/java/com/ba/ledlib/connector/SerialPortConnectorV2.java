
package com.ba.ledlib.connector;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author FRabbi
 * Date: 22 Nov 2022
 */
final class SerialPortConnectorV2 {
    private static SerialPortConnectorV2 INSTANCE;

    private SerialPortConnectorV2() {
        // this class not instantiable other place
    }

    static SerialPortConnectorV2 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SerialPortConnectorV2();
        }
        return INSTANCE;
    }

    private SerialPort serialPort;

    void connect(String portname) throws Exception {
        serialPort = new SerialPort(portname);

        serialPort.openPort();//Open serial port
        serialPort.setParams(SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
    }

    void writetoport(String data) {
        try {
            serialPort.writeBytes(data.getBytes());//Write data to port
        } catch (SerialPortException ex) {
            Logger.getLogger(SerialPortConnectorV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean disconnect() {
        try {
            serialPort.closePort();//Close serial port
            return true;
        } catch (SerialPortException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
