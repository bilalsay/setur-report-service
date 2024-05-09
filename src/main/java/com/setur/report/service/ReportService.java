package com.setur.report.service;

import com.setur.report.domain.entity.ReportDetail;
import com.setur.report.domain.repository.ReportDetailRepository;
import com.setur.report.domain.repository.ReportRepository;
import com.setur.report.enums.ReportStatus;
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

    private final ReportDetailRepository reportDetailRepository;

    @Transactional
    public void generateReport(UUID reportId) {
        var report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ReportNotFoundException(reportId + " id li rapor bulunamadı."));
        reportDetailRepository.getReportDetails().forEach(reportDetail -> {
            var reportDetailEntity = ReportDetail.builder()
                    .report(report)
                    .location(reportDetail.getLocation())
                    .contactCount(reportDetail.getContactCount())
                    .contactPhoneCount(reportDetail.getContactPhoneCount())
                    .build();
            reportDetailRepository.save(reportDetailEntity);
        });
        report.setStatus(ReportStatus.COMPLETED);
    }
}
