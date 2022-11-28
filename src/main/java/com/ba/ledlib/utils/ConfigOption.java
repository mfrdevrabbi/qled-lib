package com.ba.ledlib.utils;
/**
 * @author FRabbi
 * Date: 28 Nov 2022
 */
public enum ConfigOption {
    MSG, TOKEN, TOKEN_BORDER_COLOR, TOKEN_BORDER_SHAPE;

    @Override
    public String toString() {
        return name();
    }
}
