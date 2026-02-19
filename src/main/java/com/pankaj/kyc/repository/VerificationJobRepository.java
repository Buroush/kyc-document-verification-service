package com.pankaj.kyc.repository;

import com.pankaj.kyc.entity.VerificationJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VerificationJobRepository extends JpaRepository<VerificationJob, UUID> {
}

