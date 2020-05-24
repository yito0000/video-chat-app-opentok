package com.yito0000.videochat.backend.exception;

import com.opentok.exception.OpenTokException;

public class DriverException extends Exception {
    public DriverException(Exception e) {
        super(e);
    }
}
