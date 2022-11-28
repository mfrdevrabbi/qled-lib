package com.ba.ledlib.connector;
/**
 * @author FRabbi
 * Date: 26 Nov 2022
 */
public class LEDConnector {
    private SerialPortConnectorV2 serialPortConnector;

    private String portName = "COM";

    private LEDConnector() {
        serialPortConnector = SerialPortConnectorV2.getInstance();
    }

    public static LEDConnector builder(){
        return new LEDConnector();
    }

    public LEDConnector portName(String portName) {
        this.portName = portName;
        return this;
    }

    public LEDCommunicator connect(){

        if(serialPortConnector == null){
            return null;
        }

        try {
            serialPortConnector.connect(portName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return new LEDCommunicatorImp(serialPortConnector, true);

    }
}
