package com.rtnotifier.websocket.endpoint;

import com.rtnotifier.utils.JSONUtil;
import com.rtnotifier.websocket.coder.StatusDecoder;
import com.rtnotifier.websocket.coder.StatusEncoder;
import com.rtnotifier.websocket.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/ws/status", encoders = {StatusEncoder.class}, decoders = {StatusDecoder.class})
public class StatusEndpoint {

    public static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @Autowired
    @Qualifier("JSONUtil")
    JSONUtil jSONUtil;

    @OnOpen
    public void open(Session session){
        System.out.println("session opened");
        sessions.add(session);
    }

    @OnClose
    public void close(Session session){
        sessions.remove(session);
    }

    @OnMessage
    public void broadcast(Status status, Session session) throws IOException, EncodeException {
        System.out.println("Got message " + status.getStatus());
        for (Session peer : sessions) {
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendObject(status);
            }
        }
    }
}
