package com.yr.net.service;

import com.yr.net.entity.PartyItem;
import com.yr.net.entity.PartyTheme;
import com.yr.net.repository.PartyItemRepository;
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
public class PartyItemService {

    @Resource
    private PartyItemRepository partyItemRepository;

    /**
     * 查找主题信息
     * @param themeId 主题id
     * @return 约会项目列表
     */
    public List<PartyItem> findByThemeId(Long themeId){
        return partyItemRepository.findByThemeId(themeId);
    }
}
