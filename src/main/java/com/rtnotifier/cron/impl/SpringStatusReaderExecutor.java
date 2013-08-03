package com.rtnotifier.cron.impl;

import com.rtnotifier.cron.ICronExecutor;
import com.rtnotifier.websocket.reader.IStatusReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringStatusReaderExecutor  implements ICronExecutor{

    @Autowired
    @Qualifier("statusReader")
    IStatusReader reader;

    @Override
    @Scheduled(fixedRate = 5000)
    public void execute() {
        reader.readAndSendStatus();

    }
}
