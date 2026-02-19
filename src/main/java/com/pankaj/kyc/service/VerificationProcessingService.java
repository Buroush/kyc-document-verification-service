package com.pankaj.kyc.service;

import com.pankaj.kyc.entity.VerificationJob;
import com.pankaj.kyc.repository.VerificationJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationProcessingService {

    private final VerificationJobRepository repository;

    @Async
    public void processJob(UUID jobId) {

        try {
            VerificationJob job = repository.findById(jobId).orElseThrow();

            job.setStatus("PROCESSING");
            repository.save(job);

            // Simulate heavy ML processing
            Thread.sleep(5000);

            Random random = new Random();
            double score = 0.5 + (random.nextDouble() * 0.5);

            job.setConfidenceScore(score);

            if (score > 0.8) {
                job.setVerdict("APPROVED");
            } else if (score > 0.6) {
                job.setVerdict("MANUAL_REVIEW");
            } else {
                job.setVerdict("REJECTED");
            }

            job.setStatus("COMPLETED");
            repository.save(job);

        } catch (Exception e) {
            repository.findById(jobId).ifPresent(job -> {
                job.setStatus("FAILED");
                repository.save(job);
            });
        }
    }
}

