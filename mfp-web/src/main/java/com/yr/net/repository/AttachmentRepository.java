package com.yr.net.repository;

import com.yr.net.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/6/13
 * </pre>
 * <p>
 *     附件dao
 * </p>
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Long>,JpaSpecificationExecutor<Attachment> {


    public List<Attachment> findByBusinessId(Long businessId);

    public List<Attachment> findByBusinessIdAndFileType(Long businessId,Integer fileType);

    public Integer countByBusinessIdAndFileType(Long businessId,Integer fileType);

    public Attachment findFirstByBusinessIdAndFileTypeOrderByIdDesc(Long businessId,Integer fileType);

}
