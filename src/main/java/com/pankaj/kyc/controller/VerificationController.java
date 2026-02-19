package com.pankaj.kyc.controller;

import com.pankaj.kyc.dto.StatusResponse;
import com.pankaj.kyc.dto.UploadResponse;
import com.pankaj.kyc.entity.VerificationJob;
import com.pankaj.kyc.repository.VerificationJobRepository;
import com.pankaj.kyc.service.VerificationProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/verification")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationJobRepository repository;
    private final VerificationProcessingService processingService;

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> upload(
            @RequestParam("idImage") MultipartFile idImage,
            @RequestParam("selfie") MultipartFile selfie
    ) {
        // create job
        VerificationJob job = VerificationJob.builder().build();
        repository.save(job);

        // start async processing
        processingService.processJob(job.getId());

        UploadResponse resp = new UploadResponse(job.getId(), job.getStatus(), "Verification started successfully");
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusResponse> getStatus(@PathVariable UUID id) {
        return repository.findById(id)
                .map(job -> ResponseEntity.ok(
                        new StatusResponse(job.getId(), job.getStatus(), job.getConfidenceScore(), job.getVerdict())
                ))
                .orElse(ResponseEntity.notFound().build());
    }
}
