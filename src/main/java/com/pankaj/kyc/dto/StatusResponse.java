package com.pankaj.kyc.dto;

import java.util.UUID;

public class StatusResponse {
    private UUID jobId;
    private String status;
    private Double confidenceScore;
    private String verdict;

    public StatusResponse(UUID jobId, String status, Double confidenceScore, String verdict) {
        this.jobId = jobId;
        this.status = status;
        this.confidenceScore = confidenceScore;
        this.verdict = verdict;
    }

    public UUID getJobId() { return jobId; }
    public String getStatus() { return status; }
    public Double getConfidenceScore() { return confidenceScore; }
    public String getVerdict() { return verdict; }
}
