package com.setur.report.domain.repository;

import com.setur.report.domain.entity.ReportDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportDetailRepository extends JpaRepository<ReportDetail, UUID> {

}
