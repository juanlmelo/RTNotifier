package com.rtnotifier.websocket.coder;

import com.rtnotifier.websocket.model.Status;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class StatusEncoder implements Encoder.Text<Status> {
    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public String encode(Status status) throws EncodeException {
        return null;
    }
}
