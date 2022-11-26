package com.bat.splib.connector;

import com.bat.splib.configure.LEDConfig;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.bat.splib.utils.Utils.*;
/**
 * @author FRabbi
 * Date: 26 Nov 2022
 */
class LEDCommunicatorImp implements LEDCommunicator {

    private final SerialPortConnectorV2 serialPortConnector;
    private final boolean isConnected;

    private final CompositeDisposable disposables = new CompositeDisposable();

    LEDCommunicatorImp(SerialPortConnectorV2 serialPortConnector, boolean isConnected) {
        this.serialPortConnector = serialPortConnector;
        this.isConnected = isConnected;
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public void displayMessage(int hwId, String message) {
        System.out.println("Message send ====> "+message);
        String messageCode = parseMessageData(hwId, message);
        writeToPortForReload(messageCode, true);
    }


    @Override
    public void displayToken(int hwId, int counterNo, String token) {
        System.out.println("Token send ====> "+token);
        String tokenCode = parseTokenData(hwId, counterNo, token);
        writeToPortForReload(tokenCode, true);
    }

    @Override
    public void sendConfigCode(String configCode) {
        writeToPortForReload(configCode, true);
    }

    @Override
    public LEDConfig getLEDConfig() {
        return new LEDConfig(this);
    }


    @Override
    public boolean disconnect() {
        disposables.clear();
        return serialPortConnector.disconnect();
    }

    private void writeToPortForReload(final String data, boolean isReload) {
        sendDataToLed(data);

        try {
            if (isReload) {
                Thread.sleep(1000);
            } else {
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendDataToLed(final String data) {
        System.out.println("-------------------------------------------------------------");
        disposables.add(Single
                .fromCallable(() -> {
                    serialPortConnector.writetoport(data);
                    return "Write to port done";
                })
                .subscribeOn(Schedulers.io())
                .subscribe(result -> System.out.println("" + result), error -> error.printStackTrace()));
    }


}
