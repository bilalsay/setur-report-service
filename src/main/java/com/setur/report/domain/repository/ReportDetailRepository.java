package com.setur.report.domain.repository;

import com.setur.report.domain.entity.ReportDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDetailRepository extends JpaRepository<ReportDetail, Long> {

}
