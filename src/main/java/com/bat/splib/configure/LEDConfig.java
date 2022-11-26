package com.bat.splib.configure;

import com.bat.splib.connector.LEDCommunicator;

import static com.bat.splib.utils.Utils.isValidDeviceId;
import static com.bat.splib.utils.Utils.parseConfigData;
/**
 * @author FRabbi
 * Date: 26 Nov 2022
 */
public class LEDConfig {
    private final LEDCommunicator ledCommunicator;


    public LEDConfig(LEDCommunicator ledCommunicator) {
        this.ledCommunicator = ledCommunicator;
    }


    public void changeDeviceHardwareId(int oldHardwareId, int newHardwareId, int counterNo) {
        if (!isValidDeviceId(oldHardwareId) || !isValidDeviceId(newHardwareId) || ledCommunicator == null) {
            return;
        }
        String parseData = parseConfigData(oldHardwareId, newHardwareId, counterNo);

        ledCommunicator.sendConfigCode(parseData);

    }


}
