package com.rtnotifier.websocket.coder;

import com.rtnotifier.websocket.model.Status;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class StatusDecoder implements Decoder.Text<Status> {

    @Override
    public Status decode(String s) throws DecodeException {
        System.out.println("decoding: " + s);
        JsonObject jsonObject = Json.createReader(new StringReader(s)).readObject();
        return new Status();
    }

    @Override
    public boolean willDecode(String s) {
        try {
            Json.createReader(new StringReader(s)).readObject();
            return true;
        } catch (JsonException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        
    }

    @Override
    public void destroy() {
        
    }
}
