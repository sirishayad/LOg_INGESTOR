package com.assignment;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Entity
public class LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level;
    private String message;
    private String resourceId;
    private Instant timestamp;
    private String traceId;
    private String spanId;
    private String commit;

    @ElementCollection
    private Map<String, String> metadata = new HashMap<>();

    // Constructors, getters, and setters

    public LogModel() {
        // Default constructor for JPA
    }

    public LogModel(String level, String message, String resourceId, Instant timestamp, String traceId, String spanId, String commit, Map<String, String> metadata) {
        this.level = level;
        this.message = message;
        this.resourceId = resourceId;
        this.timestamp = timestamp;
        this.traceId = traceId;
        this.spanId = spanId;
        this.commit = commit;
        this.metadata = metadata;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
