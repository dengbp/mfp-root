package com.yr.net.service;

import com.yr.net.entity.PartyRefuse;
import com.yr.net.repository.PartyRefuseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/9/29
 * </pre>
 * <p>
 * </p>
 */
@Slf4j
@Service
public class PartyRefuseService {
    @Resource
    private PartyRefuseRepository partyRefuseRepository;

    /**
     * 取拒绝信息
     * @param refuseType refuseType
     * @return
     */
    public List<PartyRefuse> queryByType(Integer refuseType){
        return partyRefuseRepository.findByTypeId(refuseType);
    }
}
