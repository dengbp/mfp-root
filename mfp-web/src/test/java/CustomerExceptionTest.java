import com.yr.net.service.ServiceException;

/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/5/11
 * </pre>
 * <p>
 * </p>
 */
public class CustomerExceptionTest {
    public static void main(String[] args){
        throw new ServiceException("121212","错了");
    }
}
