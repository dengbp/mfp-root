package com.yr.net.service.impl;

import com.yr.net.entity.Attachment;
import com.yr.net.repository.AttachmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
 *     附件服务类
 * </p>
 */
@Service
public class AttachmentService {
    Logger logger = LoggerFactory.getLogger(AttachmentService.class);
    @Resource
    private AttachmentRepository attachmentRepository;
    private  static int MAX_LIMiT = 6;

    /**
     * 保存附件
     * @param attachment
     * @return
     */
    public Attachment save(Attachment attachment){
        return attachmentRepository.save(attachment);
    }

    public void deleteBatch(List<Attachment> list){
        list.forEach(attachment -> attachmentRepository.delete(attachment));
    }

    public List<Attachment> findByBusinessId(Long businessId){
        return attachmentRepository.findByBusinessId(businessId);
    }

    public List<Attachment> findByCondition(Long businessId,Integer fileType){
        return attachmentRepository.findByBusinessIdAndFileType(businessId,fileType);
    }

    /**
     * 判断用户上传的文件是否超过限制数量
     * @param businessId 用户id
     * @param fileType 文件类型
     * @return 是否超过
     */
    public boolean isMaxLimit(Long businessId,Integer fileType){
        Integer count = attachmentRepository.countByBusinessIdAndFileType(businessId,fileType);
        if (count.intValue()>MAX_LIMiT){
            return true;
        }
        return false;
    }

}
