package com.ikm.assessment.common.exceptionHandler;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID applicationId) {
        super("record not found with id: " + applicationId);
    }
}
