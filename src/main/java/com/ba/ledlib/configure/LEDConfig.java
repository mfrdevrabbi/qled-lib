package com.ba.ledlib.configure;

import com.ba.ledlib.model.ColorModel;
import com.ba.ledlib.connector.LEDCommunicator;
import com.ba.ledlib.utils.ConfigOption;
import com.ba.ledlib.utils.LedBorder;
import com.ba.ledlib.utils.LedColor;
import io.reactivex.disposables.CompositeDisposable;

import static com.ba.ledlib.utils.Utils.*;

/**
 * @author FRabbi
 * Date: 26 Nov 2022
 */
public class LEDConfig {
    private final LEDCommunicator ledCommunicator;


    public LEDConfig(LEDCommunicator ledCommunicator) {
        this.ledCommunicator = ledCommunicator;
    }

    public ConfigBuilder getConfigBuilder() {
        return ConfigBuilder.builder(ledCommunicator);
    }


    public static class ConfigBuilder {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        private final LEDCommunicator ledCommunicator;
        private int oldHardwareId = 1;
        private int newHardwareId = 1;
        private LedColor messageColor = LedColor.DEFAULT;
        private LedColor tokenColor = LedColor.DEFAULT;
        private LedColor borderColor = LedColor.DEFAULT;
        private LedBorder borderShape = LedBorder.DEFAULT;

        private ConfigBuilder(final LEDCommunicator ledCommunicator) {
            this.ledCommunicator = ledCommunicator;
        }

        public static ConfigBuilder builder(LEDCommunicator ledCommunicator) {
            return new ConfigBuilder(ledCommunicator);
        }

        public void config() {
            buildTask();
            System.out.println("Configuration task completed...");
        }

        private void buildTask() {
            configRequestMsg();
            //  threadSleep(500);
            configRequestToken();
            //  threadSleep(500);
            configRequestCounterBorderColor();
            //  threadSleep(500);
            configRequestCounterBorderShape();
        }


        public ConfigBuilder changeHardwareId(int oldHardwareId, int newHardwareId) {
            this.oldHardwareId = oldHardwareId;
            this.newHardwareId = newHardwareId;
            return this;
        }

        public ConfigBuilder messageColor(LedColor messageColor) {
            this.messageColor = messageColor;
            return this;
        }


        public ConfigBuilder tokenColor(LedColor tokenColor) {
            this.tokenColor = tokenColor;
            return this;
        }


        public ConfigBuilder borderColor(LedColor borderColor) {
            this.borderColor = borderColor;
            return this;
        }


        public ConfigBuilder borderShape(LedBorder borderShape) {
            this.borderShape = borderShape;
            return this;
        }


        /* ================================================*/

        private void configRequestMsg() {
            if (!isValidHardwareId(oldHardwareId) || !isValidHardwareId(newHardwareId) || ledCommunicator == null) {
                return;
            }
            String parseData = parseConfigData(oldHardwareId, newHardwareId, ConfigOption.MSG, getColorModel(), borderShape, "Welcome to Business Automation");
            logPrint("configRequestMsg:: Config Code ::" + parseData);
            ledCommunicator.writeRawData(parseData);
        }

        private void configRequestToken() {
            if (!isValidHardwareId(oldHardwareId) || !isValidHardwareId(newHardwareId) || ledCommunicator == null) {
                return;
            }
            String parseData = parseConfigData(oldHardwareId, newHardwareId, ConfigOption.TOKEN, getColorModel(), borderShape, "FR01");
            logPrint("configRequestToken :: Config Code ::" + parseData);
            ledCommunicator.writeRawData(parseData);
        }

        private void configRequestCounterBorderColor() {
            if (!isValidHardwareId(oldHardwareId) || !isValidHardwareId(newHardwareId) || ledCommunicator == null) {
                return;
            }
            String parseData = parseConfigData(oldHardwareId, newHardwareId, ConfigOption.TOKEN_BORDER_COLOR, getColorModel(), borderShape, "FR02");
            logPrint("configRequestCounterBorderColor ::Config Code ::" + parseData);
            ledCommunicator.writeRawData(parseData);
        }


        private void configRequestCounterBorderShape() {
            if (!isValidHardwareId(oldHardwareId) || !isValidHardwareId(newHardwareId) || ledCommunicator == null) {
                return;
            }
            String parseData = parseConfigData(oldHardwareId, newHardwareId, ConfigOption.TOKEN_BORDER_SHAPE, getColorModel(), borderShape, "FR03");
            logPrint("configRequestCounterBorderShape Config Code ::" + parseData);
            ledCommunicator.writeRawData(parseData);
        }

        private ColorModel getColorModel() {
            ColorModel colorModel = new ColorModel();
            colorModel.messageColor(messageColor);
            colorModel.tokenColor(tokenColor);
            colorModel.tokenBorderColor(borderColor);
            return colorModel;
        }

    }
}
