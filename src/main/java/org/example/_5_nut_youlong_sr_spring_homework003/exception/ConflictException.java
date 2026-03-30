package org.example._5_nut_youlong_sr_spring_homework003.exception;

public class ConflictException extends RuntimeException {
    private final String type;

    public ConflictException(String message, String type) {
        super(message);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

