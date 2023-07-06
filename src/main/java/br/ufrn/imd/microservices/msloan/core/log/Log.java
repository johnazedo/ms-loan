package br.ufrn.imd.microservices.msloan.core.log;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.logging.LogLevel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Log {

    private final String timestamp;
    private final LogLevel level;
    private final String microservice;
    private final String thread;
    @JsonProperty("class")
    private final String clazz;
    private final String method;
    private final String message;
    private final String context;
    private final String ip;

    public String getTimestamp() {
        return timestamp;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getMicroservice() {
        return microservice;
    }

    public String getThread() {
        return thread;
    }

    public String getClazz() {
        return clazz;
    }

    public String getMethod() {
        return method;
    }

    public String getMessage() {
        return message;
    }

    public String getContext() {
        return context;
    }

    public String getIp() {
        return ip;
    }

    private Log(LogBuilder logBuilder) {
        this.timestamp = logBuilder.timestamp;
        this.level = logBuilder.level;
        this.microservice = logBuilder.microservice;
        this.thread = logBuilder.thread;
        this.clazz = logBuilder.clazz;
        this.method = logBuilder.method;
        this.message = logBuilder.message;
        this.context = logBuilder.context;
        this.ip = logBuilder.ip;
    }

    public static class LogBuilder {

        private final String timestamp;
        private LogLevel level;
        private final String microservice;
        private final String thread;
        private String clazz;
        private String method;
        private String message;
        private String context;
        private String ip;

        public LogBuilder() {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            this.timestamp = LocalDateTime.now().format(dtf);
            this.microservice = "loan";
            this.thread = Thread.currentThread().getName();
            this.context = "default";
        }

        public LogBuilder setLevel(LogLevel level) {
            this.level = level;
            return this;
        }

        public LogBuilder setClazz(String clazz) {
            this.clazz = clazz;
            return this;
        }

        public LogBuilder setMethod(String method) {
            this.method = method;
            return this;
        }

        public LogBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public LogBuilder setContext(String context) {
            this.context = context;
            return this;
        }

        public LogBuilder setIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Log build() {
            return new Log(this);
        }
    }
}
