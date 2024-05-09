package com.setur.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDetailDto implements ReportDetailProjection, Serializable {

    private static final long serialVersionUID = 7104533194213231182L;

    private String location;

    private Integer contactCount;

    private Integer contactPhoneCount;
}
