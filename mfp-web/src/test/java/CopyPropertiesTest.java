import com.yr.net.bean.UsersBean;
import com.yr.net.entity.Customer;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/5/27
 * </pre>
 * <p>
 * </p>
 */
public class CopyPropertiesTest {

    public static void main(String[] args){
        Customer customer = new Customer();
        customer.setAddr("旧地址");
        customer.setId(1L);
        customer.setCharacterType("活泼");
        customer.setBirthDate(new Date());
        UsersBean usersBean = new UsersBean();
        usersBean.setId(1L);
        usersBean.setAddr("新地址");
        usersBean.setEducation(3);
        usersBean.setAnnualSalary(new Double(30));
        //usersBean.setCharacterType("");

        BeanUtils.copyProperties(usersBean,customer);
        System.out.println(customer.getId());
        System.out.println(customer.getAddr());
        System.out.println(customer.getEducation());
        System.out.println(customer.getAnnualSalary());
        System.out.println(customer.getIdNumber());
        System.out.println(customer.getCharacterType());
    }
}
