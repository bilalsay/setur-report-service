package com.setur.report.domain.repository;

import com.setur.report.domain.entity.ReportDetail;
import com.setur.report.dto.ReportDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportDetailRepository extends JpaRepository<ReportDetail, UUID> {

    @Query(value = """
            select cm.value                                       as location,
                   COUNT(distinct ct.id)                          as contactCount,
                   COUNT(CASE WHEN pcm.id is not null THEN 1 END) as contactPhoneCount
            from communication cm
                     inner join contact ct on ct.id = cm.contact_id
                     left join communication pcm on pcm.contact_id = ct.id and pcm.type = 'PHONE'
            where cm.type = 'LOCATION'
            group by cm.value           
            """, nativeQuery = true)
    List<ReportDetailProjection> getReportDetails();

}
