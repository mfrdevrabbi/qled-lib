package com.bat.splib.connector;

import com.bat.splib.configure.LEDConfig;
/**
 * @author FRabbi
 * Date: 26 Nov 2022
 */
public interface LEDCommunicator {

    boolean isConnected();
    void displayMessage(int hwId,String message);
    void displayToken(int hwId,int counterNo,String token);

    void sendConfigCode(String configCode);

    LEDConfig getLEDConfig();

    boolean disconnect();
}
