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
 * @Author:     dengbp
 * @CreateDate: 2018/5/7
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
    private
    AttachmentService attachmentService;
    private static final String DEFAULTPWD = "yr123456";

    @Override
    public Customer saveOrUpdate(Customer customer) {
        Optional<Customer> optional = customerRepository.findByPhone(customer.getPhone());
        if(optional.isPresent()) {
            return optional.get();
        }
        customer.setCreateTime(new Date());
        customer.setAge(0);
        customer.setLoginTimes(0);
        if(StringUtils.isBlank(customer.getPassword())){
            customer.setPassword(DEFAULTPWD);
        }
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
            if(customer.getIdNumber()!=null){
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
     * @param openId openId
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

    /**
     * 用户编辑
     * @param usersBean usersBean
     * @return AjaxResponse 更新结果
     */
    @Override
    public AjaxResponse updateUserById(UsersBean usersBean) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        if (validate(ajaxResponse,usersBean)){
            return ajaxResponse;
        }
        ajaxResponse.setCode(0);
        ajaxResponse.setMsg("信息修改成功");
        Optional optional = customerRepository.findById(usersBean.getId());
        /*用户已存在*/
        if(optional.isPresent()){
            Customer customer = (Customer)optional.get();
            String IdNumber = customer.getIdNumber();
            String password = customer.getPassword();
            Integer sex = customer.getSex();
            if(StringUtils.isBlank(usersBean.getPassword())){
                usersBean.setPassword(DEFAULTPWD);
            }
            BeanUtils.copyProperties(usersBean,customer);
            if(StringUtils.isNotBlank(IdNumber)){
                customer.setIdNumber(IdNumber);
            }else{
                if(StringUtils.isBlank(usersBean.getIdNumber())){
                    ajaxResponse.setCode(1);
                    ajaxResponse.setMsg("身份证号码为空");
                    return ajaxResponse;
                }else
                if (!RegexUtils.checkIDNumber(usersBean.getIdNumber())){
                    ajaxResponse.setCode(1);
                    ajaxResponse.setMsg("身份证号码有误");
                    return ajaxResponse;
                }
            }
            if (StringUtils.isNotBlank(password)){
                customer.setPassword(password);
            }
            if (sex!=null && (sex.intValue()==1||sex.intValue()==2)){
                customer.setSex(sex);
            }
            customer.setUpdateTime(new Date());
            customerRepository.save(customer);
            return ajaxResponse;
        }
        return ajaxResponse;
    }

    private boolean validate(AjaxResponse ajaxResponse,UsersBean usersBean){
        ajaxResponse.setCode(1);
        if (usersBean.getId()==null){
            ajaxResponse.setMsg("用户id为空");
            return true;
        }
        if(StringUtils.isBlank(usersBean.getUserName())){
            ajaxResponse.setMsg("用户名称为空");
            return true;
        }
        if(usersBean.getHeight()==null || usersBean.getHeight()<=0){
            ajaxResponse.setMsg("身高为空");
            return true;
        }

        if(usersBean.getSex()==null){
            ajaxResponse.setMsg("性别为空");
            return true;
        }
        if(usersBean.getSex().intValue()!=1 && usersBean.getSex().intValue()!=2){
            ajaxResponse.setMsg("性别为空");
            return true;
        }
        if(usersBean.getState()==null){
            return true;
        }
        if(usersBean.getState().intValue()!=1 && usersBean.getState().intValue()!=2 && usersBean.getState().intValue()!=3){
            ajaxResponse.setMsg("婚姻状况为空");
            return true;
        }
        if(StringUtils.isBlank(usersBean.getLoverRequire())){
            ajaxResponse.setMsg("征友要求为空");
            return true;
        }
        if(usersBean.getEducation()==null){
            ajaxResponse.setMsg("教育程度为空");
            return true;
        }
        return false;
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
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean setUserMultimedia(Attachment attachment, int purpose) {
        switch (purpose){
            /*相册*/
            case 1:
                attachmentService.save(attachment);
                return true;
            /*视频*/
            case 2:
                attachmentService.save(attachment);
                return true;
            /*头像*/
            case 3:
                customerRepository.setUserHeadPic(attachment.getUrl(),attachment.getBusinessId());
                return true;
            /*背景图*/
            case 4:
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
        if(StringUtils.isBlank(customer.getLoverRequire())){
            return false;
        }
        return true;
    }

    @Override
    public Customer searchByOpenId(String openId) {
        return customerRepository.findByOpenId(openId);
    }

    @Override
    public boolean updateRoleById(Long id, Integer role) {
        customerRepository.setUserRole(id,role);
        return true;
    }

}
