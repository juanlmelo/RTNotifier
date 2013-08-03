package com.rtnotifier.websocket.reader.impl;

import com.rtnotifier.utils.JSONUtil;
import com.rtnotifier.websocket.endpoint.StatusEndpoint;
import com.rtnotifier.websocket.model.Status;
import com.rtnotifier.websocket.reader.IStatusReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Component("statusReader")
public class MysqlStatusReaderImpl implements IStatusReader {

    public static final String STATUS_QUERY = "select max(idstatus) idstatus from status;";
    @Autowired
    @Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("JSONUtil")
    JSONUtil jSONUtil;

    @Override
    public void readAndSendStatus() {
        Status status = jdbcTemplate.queryForObject(STATUS_QUERY, new StatusMapper());
        System.out.println("read message " + status );
        try {
            for (Session peer: StatusEndpoint.sessions){
                peer.getBasicRemote().sendText(jSONUtil.mapToJSON(status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class StatusMapper implements RowMapper<Status>{

        @Override
        public Status mapRow(ResultSet resultSet, int i) throws SQLException {
            Status status = new Status();
            status.setStatus(resultSet.getInt("idstatus"));
            status.setValue(50 + (int)(Math.random() * (1000 -50)));
            status.setDate(new Date().toString());
            return status;
        }
    }

}
