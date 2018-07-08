package com.yr.net.repository;

import com.yr.net.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/4/19
 * </pre>
 * <p>
 *     用户信息dao
 * </p>
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>,JpaSpecificationExecutor<Customer> {

    List<Customer> findByUserName(String userName);

    List<Customer> findByIsOnline(boolean isOnline);

    @Override
    Optional<Customer> findById(Long id);

    /**
     * 根据id或openid查用户
     * @param id
     * @param openId
     * @return
     */
    Optional<Customer> findByIdOrOpenId(Long id,String openId);

    Optional<Customer> findByPhone(String phone);

    Customer findByOpenId(String openId);

    @Modifying
    @Query("update yr_customer u set u.headPic = ?1 where u.id = ?2")
    public void setUserHeadPic(String headPic,Long id);

    @Modifying
    @Query("update yr_customer u set u.background = ?1 where u.id = ?2")
    public void setUserBackground(String background,Long id);

    @Modifying
    @Query("update yr_customer u set u.video = CONCAT(IFNULL(u.video,','),',',?1) where u.id = ?2")
    public void setUserVideo(String video,Long id);

    @Modifying
    @Query("update yr_customer u set u.pic = CONCAT(IFNULL(u.pic,',') ,',',?1) where u.id = ?2")
    public void setUserPic(String pic,Long id);

    @Modifying
    @Query("update yr_customer u set u.openId = ?1 where u.id = ?2")
    public void setUserOpenId(String openId,Long id);

}
