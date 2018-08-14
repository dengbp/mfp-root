package com.yr.net.service;

import com.yr.net.entity.PartyTheme;
import com.yr.net.repository.PartyThemeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/8/8
 * </pre>
 * <p>
 *     约会、主题服务类
 * </p>
 */
@Service
public class PartyThemeService {

    @Resource
    private PartyThemeRepository partyThemeRepository;

    /**
     * 查找主题信息
     * @param themeType
     * @return
     */
    public List<PartyTheme> findByType(Integer themeType){
        if (null == themeType){
            return partyThemeRepository.findAll();
        }
        return partyThemeRepository.findByThemeType(themeType);
    }
}
