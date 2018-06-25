package com.yr.net.service.impl;

import com.yr.net.entity.Character;
import com.yr.net.repository.CharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/5/28
 * </pre>
 * <p>
 *     性格服务类
 * </p>
 */
@Service
public class CharacterService {
    Logger logger = LoggerFactory.getLogger(CharacterService.class);
    @Resource
    private CharacterRepository characterRepository;

    /**
     * 获取性格列表
     * @return 性格列表
     */
    public List<Character> findAll(){
        return characterRepository.findAll();
    }

}
