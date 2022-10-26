package com.example.demospring.restapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private final Integer status;
    private final String message;
    private final OffsetDateTime timestamp;
    private List<Field> fields;

    public ApiError(Integer status, String message, OffsetDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ApiError(Integer status, String message, OffsetDateTime timestamp, List<Field> fields) {
        this(status, message, timestamp);
        this.fields = fields;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public List<Field> getFields() {
        return fields;
    }

    public static class Field {
        private final String name;
        private final String message;

        public Field(String name, String message) {
            this.name = name;
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }
    }
}
