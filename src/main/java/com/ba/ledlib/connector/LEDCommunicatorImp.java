package com.ba.ledlib.connector;

import com.ba.ledlib.configure.LEDConfig;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.ba.ledlib.utils.Utils.*;

/**
 * @author FRabbi
 * Date: 26 Nov 2022
 */
class LEDCommunicatorImp implements LEDCommunicator {
    private static boolean isWcMessageAlreadyLoaded;
    private static  String oldMessage;
    private final SerialPortConnectorV2 serialPortConnector;
    private boolean isConnected;

    private final CompositeDisposable disposables = new CompositeDisposable();


    LEDCommunicatorImp(SerialPortConnectorV2 serialPortConnector, boolean isConnected) {
        this.serialPortConnector = serialPortConnector;
        this.isConnected = isConnected;
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    /**
     *
     * @param hwId
     * @param message
     */
    @Override
    public void displayMessage(int hwId, String message) {
        System.out.println("Message send ====> " + message);
        if (!isValidMessageText(message)) {
            throw new RuntimeException(
                    "message can't be null || empty || message length should be minimum 8 characters"
            );
        }
        String messageCode = parseMessageData(hwId, message);
        logPrint("message code::: "+messageCode);

/*        String tokenData = parseTokenData(hwId, "");
        if( oldMessage !=null && !oldMessage.equals(message)){
            logPrint("message changes");
            writeToPortForReload(tokenData,false); //led bug thats why it's call
            writeToPortForReload(messageCode, false);
        }else {
            writeToPortForReload(messageCode, true);
        }*/

        writeToPortForReload(messageCode, true);
        oldMessage = message;
    }

    /**
     *
     * @param message
     * @return
     */
    private boolean isValidMessageText(String message) {
        if (message == null || message.isEmpty() || message.length() < 8) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param hwId
     * @param counterNo
     * @param token
     */
    @Override
    public void displayToken(int hwId, int counterNo, String token) {
        System.out.println("Token send ====> " + token);
        String tokenCode = parseTokenData(hwId, counterNo, token);
        logPrint("token code::: "+tokenCode);
        writeToPortForReload(tokenCode, true);
    }

    /**
     *
     * @param rawData
     */
    @Override
    public void writeRawData(String rawData) {
        writeToPortForReload(rawData, true);
    }

    /**
     *
     * @return
     */
    @Override
    public LEDConfig getLEDConfig() {
        return new LEDConfig(this);
    }

    /**
     *
     * @return port close status
     */
    @Override
    public boolean disconnect() {
        disposables.clear();
        boolean disconnect = serialPortConnector.disconnect();
        this.isConnected = disconnect;
        return disconnect;
    }

    /**
     *
     * @param data
     * @param isReload
     */
    private void writeToPortForReload(final String data, boolean isReload) {
        sendDataToLed(data);

        if (isReload) {
            threadSleep(1000);
        } else {
            threadSleep(500);
        }

    }

    /**
     *
     * @param data
     */

    private void sendDataToLed(final String data) {
        disposables.add(Single
                .fromCallable(() -> {
                    serialPortConnector.writetoport(data);
                    return "Write to port done";
                })
                .subscribeOn(Schedulers.io())
                .subscribe(
                        result ->{

                        }
                        , error -> error.printStackTrace()));
    }


}
