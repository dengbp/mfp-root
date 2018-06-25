import org.apache.commons.lang.StringUtils;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/6/8
 * </pre>
 * <p>
 * </p>
 */
public class StringHandleTest {

    public static void main(String[] args){
        String  s = ",,http://www.goddesses.net.cn/pic/user/13009686493460.jpg,http://www.goddesses.net.cn/pic/user/13009686493460.jpg";
        String[] urls = s.split(",");
        if(StringUtils.isBlank(urls[0])){
            s = s.substring(1);
            System.out.println(s);
        }
        if (StringUtils.isBlank(urls[1])){
            s = s.substring(1);
            System.out.println(s);
        }
        System.out.println();
        System.out.println(StringUtils.isBlank(urls[1]));
        System.out.println(StringUtils.isBlank(urls[2]));

        String code = "123";
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx4b41a2ab9419b42a&secret=8c3668c7528e338940482f053c4af09b&code=CODE&grant_type=authorization_code";
        url = url.replace("CODE",code);
        System.out.println(url);
    }
}
