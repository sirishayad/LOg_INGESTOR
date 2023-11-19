package com.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.log.Log;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private LogService logService;

    // Existing methods

    @MessageMapping("/ingest-log")
    public void ingestLogViaWebSocket(LogModel log) {
        logService.ingestLog(log);
        messagingTemplate.convertAndSend("/topic/logs", log);
    }
}
