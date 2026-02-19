package com.pankaj.kyc.dto;

import java.util.UUID;

public class UploadResponse {
    private UUID jobId;
    private String status;
    private String message;

    public UploadResponse(UUID jobId, String status, String message) {
        this.jobId = jobId;
        this.status = status;
        this.message = message;
    }

    public UUID getJobId() { return jobId; }
    public String getStatus() { return status; }
    public String getMessage() { return message; }
}
