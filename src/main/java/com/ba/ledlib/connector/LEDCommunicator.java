package com.ba.ledlib.connector;

import com.ba.ledlib.configure.LEDConfig;
/**
 * @author FRabbi
 * Date: 26 Nov 2022
 */
public interface LEDCommunicator {

    /**
     * it's provide port connected status
     * @return
     */
    boolean isConnected();

    /**
     * hwId:deviceHardwareId
     * @param hwId
     * @param message
     */
    void displayMessage(int hwId,String message);

    /**
     * hwId:deviceHardwareId
     * @param hwId
     * @param counterNo
     * @param token
     */
    void displayToken(int hwId,int counterNo,String token);

    /**
     * rawData:: rawData should contain message with LED's required code
     * @param rawData
     */
    void writeRawData(String rawData);

    /**
     * it's require for device configuration
     * @return
     */
    LEDConfig getLEDConfig();

    /**
     * this is used for close sp port.
     * @return
     */
    boolean disconnect();
}
