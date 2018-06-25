package com.yr.net.repository;

import com.yr.net.entity.Character;
import com.yr.net.entity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/5/28
 * </pre>
 * <p>
 *     活动报名dao
 * </p>
 */
public interface EnrollRepository extends JpaRepository<Enroll, Long> {

    Optional<Enroll> findByPartyCodeAndPhone(String partyCode, String phone);
}
