package com.setur.report.service;

import com.setur.report.domain.repository.ReportRepository;
import com.setur.report.exception.ReportNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final ReportRepository reportRepository;

    @Transactional
    public void generateReport(UUID reportId) {
        var report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ReportNotFoundException(reportId.toString() + " id li rapor bulunamadı."));
        System.out.println("Report: " + report.getDate());
    }


}
