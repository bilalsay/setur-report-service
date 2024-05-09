package com.setur.report.scenario;

import com.setur.report.domain.entity.Report;
import com.setur.report.dto.ReportDetailDto;
import com.setur.report.dto.ReportDetailProjection;
import lombok.experimental.UtilityClass;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@UtilityClass
public class ReportScenarios {

    public static ReportDetailDto getReportDetailDto() {
        return ReportDetailDto.builder()
                .location("1234, 1223")
                .contactCount(3)
                .contactPhoneCount(5)
                .build();
    }

    public static Report getReport(UUID id) {
        return Report.builder()
                .id(id)
                .build();
    }

    public static ReportDetailProjection getReportDetailProjection() {
        var factory = new SpelAwareProxyProjectionFactory();
        var projectionMap = new HashMap<String, Object>();
        projectionMap.put("location", "1234, 1223");
        projectionMap.put("contactCount", 3);
        projectionMap.put("contactPhoneCount", 5);
        return factory.createProjection(ReportDetailProjection.class, projectionMap);
    }

    public static List<ReportDetailProjection> getReportDetailProjectionList() {
        return List.of(getReportDetailProjection());
    }

}
