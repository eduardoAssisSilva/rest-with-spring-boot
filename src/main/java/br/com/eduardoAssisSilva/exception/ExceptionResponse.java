package br.com.eduardoAssisSilva.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ExceptionResponse(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp,
        String message,
        String details
) {
    public static ExceptionResponse of(String message, String details) {
        return new ExceptionResponse(LocalDateTime.now(), message, details);
    }
}
