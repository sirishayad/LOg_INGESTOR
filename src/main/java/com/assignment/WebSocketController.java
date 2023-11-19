package com.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.mysql.cj.log.Log;

@Controller
public class WebSocketController {

    @Autowired
    private LogService logService;

    @MessageMapping("/ingest-log")
    @SendTo("/topic/logs")
    public LogModel ingestLog(LogModel log) {
        logService.ingestLog(log);
        return log;
    }
}
