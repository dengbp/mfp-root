package com.yr.net.service.impl;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.bean.UsersBean;
import com.yr.net.entity.Attachment;
import com.yr.net.entity.Customer;
import com.yr.net.repository.CustomerRepository;
import com.yr.net.service.UserService;
import com.yr.net.util.AgeUtils;
import com.yr.net.util.RegexUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/5/7
 * </pre>
 * <p>
 *     用户管理服务
 * </p>
 */
@Service
public class UserServiceImpl implements UserService{
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    CustomerRepository customerRepository;
    @Resource
    AttachmentService attachmentService;

    @Override
    public Customer saveOrUpdate(Customer customer) {
        Optional<Customer> optional = customerRepository.findByPhone(customer.getPhone());
        if(optional.isPresent()) {
            return optional.get();
        }
        customer.setCreateTime(new Date());
        customer.setAge(0);
        customer.setLoginTimes(0);
        return  customerRepository.save(customer);
    }

    @Override
    public UsersBean findById(Long id) {
        Optional optional = customerRepository.findById(id);
        if(optional.isPresent()){
            Customer customer = (Customer)optional.get();
            String IdNumber = customer.getIdNumber();
            UsersBean usersBean = new UsersBean();
            BeanUtils.copyProperties(customer,usersBean);
            if(customer.getIdNumber()!=null){//45262619840409
                String birthday = IdNumber.substring(10,12).concat("-").concat(IdNumber.substring(12,14));
                usersBean.setBirthday(birthday);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                Date date = null;
                try {
                    date = dateFormat.parse(IdNumber.substring(6,14));
                    Integer age = AgeUtils.getAgeByDate(date);
                    usersBean.setAge(age);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                usersBean.setIdNumber("***");
            }
            if(customer.getPassword()!=null){
                usersBean.setPassword("***");
            }
            return usersBean;
        }
        return null;
    }

    /**
     * 根据用户id查询
     * @param id
     * @return 用户信息
     */
    @Override
    public Customer findCustomerById(Long id) {
        Optional optional = customerRepository.findById(id);
        Customer customer = null;
        if(optional.isPresent()) {
            customer = (Customer) optional.get();
        }
        return customer;
    }

    /**
     * 根据openid查用户
     * @param openId
     * @return 用户信息
     */
    @Override
    public Customer findCustomerByOpenId(String openId) {
        return customerRepository.findByOpenId(openId);
    }

    @Override
    public UsersBean findByOpenId(String openId) {
        Customer customer = customerRepository.findByOpenId(openId);
        UsersBean usersBean = new UsersBean();
        if(customer != null){
            BeanUtils.copyProperties(customer,usersBean);
        }else{
            return null;
        }
        return usersBean;
    }

    @Override
    public UsersBean findByPhone(String phone) {
        Optional<Customer> optional = customerRepository.findByPhone(phone);
        UsersBean usersBean = new UsersBean();
        if(optional.isPresent()){
            Customer customer = (Customer)optional.get();
            BeanUtils.copyProperties(customer,usersBean);
        }else{
            return null;
        }
        return usersBean;
    }

    @Override
    public boolean updateOpenIdById(Long id, String openId) {
        customerRepository.setUserOpenId(openId,id);
        return true;
    }

    @Override
    public String findHeadPortraitById(Long id) {
        Optional optional = customerRepository.findById(id);
        if(optional.isPresent()) {
            Customer customer = (Customer) optional.get();
            return customer.getHeadPic();
        }
        return null;
    }

    @Override
    public String findBackdropById(Long id) {
        Optional optional = customerRepository.findById(id);
        if(optional.isPresent()) {
            Customer customer = (Customer) optional.get();
            return customer.getBackground();
        }
        return null;
    }

    @Override
    public String findAlbumById(Long id) {
        Optional optional = customerRepository.findById(id);
        if(optional.isPresent()) {
            Customer customer = (Customer) optional.get();
            String album = customer.getPic();
            if(StringUtils.isNotBlank(album)){
                String[] albumUrls = album.split(",");
                if(albumUrls.length>0 && StringUtils.isBlank(albumUrls[0])){
                    album = album.substring(1);
                }
                if (albumUrls.length>1 && StringUtils.isBlank(albumUrls[1])){
                    album = album.substring(1);
                }
                return album;
            }
        }
        return null;
    }

    @Override
    public String findVideoById(Long id) {
        Optional optional = customerRepository.findById(id);
        if(optional.isPresent()) {
            Customer customer = (Customer) optional.get();
            String videos = customer.getVideo();
            if(StringUtils.isNotBlank(videos)){
                String[] videoUrls = videos.split(",");
                if(videoUrls.length>0 && StringUtils.isBlank(videoUrls[0])){
                    videos = videos.substring(1);
                }
                if (videoUrls.length>1 && StringUtils.isBlank(videoUrls[1])){
                    videos = videos.substring(1);
                }
                return videos;
            }
        }
        return null;
    }

    @Override
    public AjaxResponse updateUserById(UsersBean usersBean) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        if (usersBean.getId()==null){
            ajaxResponse.setCode(1);
            ajaxResponse.setMsg("用户id为空");
            return ajaxResponse;
        }
        ajaxResponse.setCode(0);
        ajaxResponse.setMsg("信息修改成功");
        Optional optional = customerRepository.findById(usersBean.getId());
        if(optional.isPresent()){
            Customer customer = (Customer)optional.get();
            String IdNumber = customer.getIdNumber();
            String password = customer.getPassword();
            BeanUtils.copyProperties(usersBean,customer);
            if(StringUtils.isNotBlank(IdNumber)){
                customer.setIdNumber(IdNumber);
            }else{
                if (usersBean.getIdNumber() != null && !RegexUtils.checkIDNumber(usersBean.getIdNumber())){
                    ajaxResponse.setCode(1);
                    ajaxResponse.setMsg("身份证号码有误");
                    return ajaxResponse;
                }
            }
            if (StringUtils.isNotBlank(password)){
                customer.setPassword(password);
            }
            customer.setUpdateTime(new Date());
            customerRepository.save(customer);
            return ajaxResponse;
        }
        return ajaxResponse;
    }

    @Override
    public Page<Customer> findUserNoCriteria(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "createTime");
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findUserCriteria(Integer page, Integer size, Map<String, Object> customerQuery) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        Page<Customer> customerPage = customerRepository.findAll(new Specification<Customer>(){
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                customerQuery.forEach((k,v)->list.add(criteriaBuilder.equal(root.get(k).as(Integer.class), v)));
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);
        return customerPage;
    }

    /**
     * 设置用户头像、背景图、相册、视频等
     * @param attachment 文件对象
     * @param purpose 1：相册;2：视频;3:头像;4:背景图
     * @return 成功：true;失败：false
     */
    @Transactional
    @Override
    public boolean setUserMultimedia(Attachment attachment, int purpose) {
        switch (purpose){
            case 1://相册
                attachmentService.save(attachment);
                //customerRepository.setUserPic(url,id);
                return true;
            case 2://视频
                attachmentService.save(attachment);
                //customerRepository.setUserVideo(url,id);
                return true;
            case 3://头像
                customerRepository.setUserHeadPic(attachment.getUrl(),attachment.getBusinessId());
                return true;
            case 4://背景图
                customerRepository.setUserBackground(attachment.getUrl(),attachment.getBusinessId());
                return true;
            default:
                logger.warn("没有找到对应的类型[{}]", purpose);
                break;
        }
        return false;
    }

    @Override
    public boolean checkFileMaxLimit(Long userId, Integer fileType) {
        return false;
    }

    @Override
    public boolean checkInfo(String openId) {
        Customer customer = customerRepository.findByOpenId(openId);
        if(customer == null){
            return false;
        }
        if(customer.getIdNumber()==null){
            return false;
        }
        if (customer.getSex()==null){
            return false;
        }
        if(customer.getEducation()==null){
            return false;
        }
        return true;
    }

    @Override
    public Customer searchByOpenId(String openId) {
        return customerRepository.findByOpenId(openId);
    }

}
