import com.Application;
import com.yr.net.bean.UsersBean;
import com.yr.net.entity.Customer;
import com.yr.net.repository.CustomerRepository;
import com.yr.net.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/4/19
 * </pre>
 * <p>
 * </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
//@Transactional
@Rollback(value = false)
public class CustomerCRUDTest {
    static Logger logger = LoggerFactory.getLogger(CustomerCRUDTest.class);

    @Autowired
    private CustomerRepository customerRepository;
    @Resource
    UserService userService;
    @Test
    public void insert(){
        for (int i=100;i<999;i++){
            Customer customer = new Customer();
            customer.setAddr("我家在东北");
            customer.setAnnualSalary(2384.3);
            customer.setCharacterType("1,2,3,4,3");
            customer.setCompanyAddr("公司地址在深圳");
            customer.setCreateTime(new Date());
            customer.setEducation(1);
            customer.setHeadPic("D:/dfd/p/dfd.jpg");
            customer.setHeight(165.00);
            customer.setHuseholdRegister("中国香港");
            customer.setIdNumber("452626374657463"+i);
            customer.setIndustryType("服务业");
            customer.setInterest("3,2,4,5");
            customer.setIntroduce("自我介绍");
            customer.setJobTitle("大堂经理");
            customer.setLevel(1);
            customer.setLikeType("5,6,45");
            customer.setOrigin("深圳");
            customer.setPassword("123456");
            customer.setPhone("13530051353");
            customer.setPic("{1:D:/dfdf/dj.jpg;2:D:/dfdf/dfd.jpg}");
            customer.setSex(1);
            customer.setState(2);
            customer.setUpdateTime(new Date());
            customer.setUserName("dengbangpag"+i);
            customer.setVideo("{1:D:/dfdf/d.av}");
            customer.setWeight(new Double(120L));
            customer.setIsOnline(new Boolean(true));
            customer.setComeFrom("通过关注官方服务号加入");
            customerRepository.save(customer);
            logger.info("数据保存成功...");
        }
    }

    @Test
    public void updateUser(){
        UsersBean usersBean = new UsersBean();
        usersBean.setId(920L);
        usersBean.setAddr("深圳");
        userService.updateUserById(usersBean);
    }

    @Test
    public void selectByOnline(){
        List<Customer> customers = customerRepository.findByIsOnline(false);
        logger.info("result size:{}",customers.size());
        customers.forEach(e->logger.info(e.getUserName()));

    }

    @Test
    public void selectById(){
        Optional<Customer> customer = customerRepository.findById(901L);
        if(customer.isPresent()){
            Customer c = customer.get();
            logger.info("用户名：{}",c.getUserName());
        }

    }

    @Test
    public void selectBySex(){
        Map<String,Object> criteria = new HashMap<String,Object>();
        criteria.put("sex",new Integer(2));
        Page<Customer> page =  userService.findUserCriteria(0,10, criteria);
        System.out.println("长度："+page.getSize());
        if (page.hasContent()){
            page.getContent().forEach(customer -> System.out.println(MessageFormat.format("用户名：{0}相片：{1}", customer.getUserName() , customer.getPic())));
        }

    }

    @Test
    public void selectAll(){
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        logger.info("result size:{}",customers.size());
        customers.forEach(e->logger.info(e.getUserName()));

    }

    @Test
    public void setUserMultimedia(){
        //userService.setUserMultimedia("http://193.112.214.225/pic/11150470_854883741235667_6158693685562016581_n.jpg",920L,3);
    }

    @Test
    public void updateRole(){
        userService.updateRoleById(new Long(924),0);
        logger.info("成功");
    }
}
