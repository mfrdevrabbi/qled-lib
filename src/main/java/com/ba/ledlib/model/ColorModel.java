package com.ba.ledlib.model;

import com.ba.ledlib.utils.LedColor;

public class ColorModel {
    LedColor forMsg = LedColor.GREEN;
    LedColor forToken = LedColor.RED;
    LedColor forTokenCounterBorder = LedColor.GREEN;

    public ColorModel() {

    }

    public LedColor getMessageColor() {
        return forMsg;
    }

    public void messageColor(LedColor forMsg) {
        this.forMsg = forMsg;
    }

    public LedColor getTokenColor() {
        return forToken;
    }

    public void tokenColor(LedColor forToken) {
        this.forToken = forToken;
    }

    public LedColor getTokenBorderColor() {
        return forTokenCounterBorder;
    }

    public void tokenBorderColor(LedColor forTokenBorder) {
        this.forTokenCounterBorder = forTokenBorder;
    }

    @Override
    public String toString() {
        return "ColorModel{" +
                "forMsg=" + forMsg +
                ", forToken=" + forToken +
                ", forTokenCounterBorder=" + forTokenCounterBorder +
                '}';
    }
}
