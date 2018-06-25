package com.yr.net.repository;

import com.yr.net.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/5/28
 * </pre>
 * <p>
 *     性格dao
 * </p>
 */
public interface CharacterRepository extends JpaRepository<Character, Long> {
}
