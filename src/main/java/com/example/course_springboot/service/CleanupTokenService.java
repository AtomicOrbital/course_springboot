package com.example.course_springboot.service;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.course_springboot.repository.InvalidatedTokenRepository;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CleanupTokenService {
    InvalidatedTokenRepository invalidatedTokenRepository;

    @Transactional
    @Scheduled(cron = "0 0 * * * *")
    public void cleanupExpiredTokens() {
        invalidatedTokenRepository.deleteByExpiryTimeBefore(new Date());
    }
}
