package org.myProject.crm.exceptions;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ErrorMessage {
    private int statusCode;
    private LocalDateTime timesTamp;
    private String message;
}
