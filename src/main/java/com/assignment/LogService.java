package com.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assignment.LogModel.*;
import com.mysql.cj.log.Log;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogService {
    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    // Existing methods

    public List<com.mysql.cj.log.Log> searchLogs(String level, String message, String resourceId, Instant start, Instant end, String filter) {
        if (filter != null) {
            return logRepository.findByFilter(filter);
        } else if (level != null && message != null && resourceId != null && start != null && end != null) {
            return logRepository.findByLevelAndResourceIdAndTimestampBetween(level, resourceId, start, end);
        } else if (start != null && end != null) {
            return logRepository.findByTimestampBetween(start, end);
        } else if (message != null) {
            return logRepository.findByMessageContainingIgnoreCase(message);
        } else {
            // Handle other combinations or return all logs
            return logRepository.findAll();
        }
    }

	public void ingestLog(LogModel log) {
		
		   if (log.getTimestamp() == null) {
		            log.setTimestamp(Instant.now());
		        }

		        // Additional processing
		        preprocessLog(log);

		        // Save the log entry
		        logRepository.save(log);
		    }

		    private void preprocessLog(LogModel log) {
		        // Example: Truncate long messages to fit into the database
		        if (log.getMessage() != null && log.getMessage().length() > 255) {
		            log.setMessage(log.getMessage().substring(0, 255));
		        }

		        // Example: Add a default value for missing metadata
		        if (log.getMetadata() == null) {
		            log.setMetadata(new HashMap<>()); // Using HashMap constructor
		            log.getMetadata().put("defaultKey", "defaultValue");
		        }

		       
		    }
		}

		
		
	