package com.setur.report.service;

import com.setur.report.domain.repository.ReportDetailRepository;
import com.setur.report.domain.repository.ReportRepository;
import com.setur.report.exception.ReportNotFoundException;
import com.setur.report.logger.TestLoggerExtension;
import com.setur.report.mockito.MockitoExtension;
import com.setur.report.scenario.ReportScenarios;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(TestLoggerExtension.class)
class ReportServiceTest {

    @InjectMocks
    private ReportService reportService;

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private ReportDetailRepository reportDetailRepository;


    @Test
    void shouldGenerateReportSuccess_whenThereIsNoError() {
        //given
        var id = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        var report = ReportScenarios.getReport(id);
        var reportDetailDto = ReportScenarios.getReportDetailDto();
        var reportDetailProjectionList = ReportScenarios.getReportDetailProjectionList();

        //when
        when(reportRepository.findById(id))
                .thenReturn(Optional.ofNullable(report));
        when(reportDetailRepository.getReportDetails())
                .thenReturn(reportDetailProjectionList);

        //then
        reportService.generateReport(id);

        verify(reportRepository, times(1)).findById(id);
        verify(reportDetailRepository, times(1)).getReportDetails();
    }

    @Test
    void shouldNotGenerateReportSuccess_whenReportNotFoundException() {
        //given
        var id = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        var report = ReportScenarios.getReport(id);
        var reportDetailDto = ReportScenarios.getReportDetailDto();
        var reportDetailProjectionList = ReportScenarios.getReportDetailProjectionList();

        //when
        when(reportRepository.findById(id))
                .thenReturn(Optional.empty());
        when(reportDetailRepository.getReportDetails())
                .thenReturn(reportDetailProjectionList);

        //then
        assertThrows(ReportNotFoundException.class, () -> reportService.generateReport(id));

        verify(reportRepository, times(1)).findById(id);
        verify(reportDetailRepository, never()).getReportDetails();
    }

}