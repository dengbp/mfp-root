package com.yr.net.service;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.bean.UsersBean;
import com.yr.net.entity.Attachment;
import com.yr.net.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/5/7
 * </pre>
 * <p>
 *     用户管理服务接口
 * </p>
 */
public interface UserService {

    /**
     * 新增和修改方法
     * @param customer
     * @return 返回用户本身
     */
    Customer saveOrUpdate(Customer customer);

    /**
     * 根据用户id查询
     * @param id
     * @return 用户信息
     */
    UsersBean findById(Long id);

    /**
     * 根据用户id查询
     * @param id
     * @return 用户信息
     */
    Customer findCustomerById(Long id);

    /**
     * 根据openid查用户
     * @param openId
     * @return 用户信息
     */
    Customer findCustomerByOpenId(String openId);

    /**
     * 根据微信用户id查询
     * @param openId 微信用户id
     * @return 用户信息
     */
    UsersBean findByOpenId(String openId);

    /**
     * 根据微信用户用户手机号查询
     * @param phone 用户手机号
     * @return 用户信息
     */
    UsersBean findByPhone(String phone);

    /**
     * 更新用户表openid
     * @param id 用户id
     * @param openId 微信用户id
     * @return 更新状态 true:成功；false:失败
     */
    boolean updateOpenIdById(Long id,String openId);

    /**
     * 根据用户数据id查询头像
     * @param id
     * @return 用户头像url
     */
    String findHeadPortraitById(Long id);

    /**
     * 根据用户数据id查询背景图
     * @param id
     * @return 背景图url
     */
    String findBackdropById(Long id);

    /**
     * 根据用户数据id查询相册
     * @param id
     * @return 相册
     */
    String findAlbumById(Long id);

    /**
     * 根据用户数据id查询视频
     * @param id
     * @return 视频
     */
    String findVideoById(Long id);

    /**
     * 用户编辑
     * @param usersBean usersBean
     * @return AjaxResponse 更新结果
     */
    AjaxResponse updateUserById(UsersBean usersBean);

    /**
     * 无条件分页查询
     * @param page page
     * @param size size
     * @return 用户列表
     */
    Page<Customer> findUserNoCriteria(Integer page, Integer size);

    /**
     * 用条件分页查询
     * @param page page
     * @param size size
     * @param customerQuery key->value
     * @return Page<Customer>
     */
    Page<Customer> findUserCriteria(Integer page,Integer size,Map<String,Object> customerQuery);

    /**
     * 设置用户头像、背景图、相册、视频等
     * @param attachment 文件信息
     * @param purpose 1：相册;2：视频;3:头像;4:背景图
     * @return 成功：true;失败：false
     */
    boolean setUserMultimedia(Attachment attachment, int purpose);

    /**
     * 查检用户文件数
     * @param userId 用户id
     * @param fileType 用户文件类型1:图片；2:视频
     * @return boolean
     */
    boolean checkFileMaxLimit(Long userId,Integer fileType);

    /**
     * 检查用户信息是否完善
     * @param openId 用户openid
     * @return true:已经完善;false:未完善
     */
    boolean checkInfo(String openId);

    /**
     * 根据openid查用户信息
     * @param openId
     * @return
     */
    Customer searchByOpenId(String openId);




}
