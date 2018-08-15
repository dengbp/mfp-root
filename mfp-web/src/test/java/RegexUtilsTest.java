import com.yr.net.util.RegexUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/5/29
 * </pre>
 * <p>
 * </p>
 */
public class RegexUtilsTest
{

    public static void main(String[] args){

        if(!RegexUtils.checkMobile("16620832579")){
            System.out.println("手机号码校验失败");
        }

//        System.out.println(RegexUtils.checkIDNumber("452626199909091999"));
//        System.out.println(RegexUtils.checkMobile("13530051353"));
        System.out.println(RegexUtils.checkImages("公众号二围马.jpg"));


        String reg = ".*(.JPEG|.jpeg|.JPG|.jpg|.png)$";
        String imgp= "公众号二围马.jpg";

        System.out.println(Pattern.matches(reg, imgp));

        System.out.println(RegexUtils.checkMobile("13530051353"));

    }
}
