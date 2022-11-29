package com.ba.ledlib.connector;

/**
 * @author FRabbi
 * Date: 26 Nov 2022
 */
public class LEDConnector {
    private SerialPortConnectorV2 serialPortConnector;
    private static LEDConnector ledConnector;

    private String portName = "COM";

    private LEDConnector() {
        serialPortConnector = SerialPortConnectorV2.getInstance();
    }

    public static LEDConnector builder() {
        if (ledConnector == null) {
            return new LEDConnector();
        }
        return ledConnector;
    }

    /**
     * @param portName
     * @return
     */
    public LEDConnector portName(String portName) {
        this.portName = portName;
        return ledConnector;
    }

    /**
     * @return LEDCommunicator
     */
    public LEDCommunicator connect() {

        if (serialPortConnector == null) {
            throw new RuntimeException("Serial-port connector null reference..");
        }

        try {
            serialPortConnector.connect(portName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return new LEDCommunicatorImp(serialPortConnector, true);

    }
}
