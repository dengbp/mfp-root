import com.yr.net.Application;
import com.yr.net.entity.Industry;
import com.yr.net.entity.Interest;
import com.yr.net.service.impl.IndustryService;
import com.yr.net.service.impl.InterestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/5/28
 * </pre>
 * <p>
 * </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
//@Transactional
@Rollback(value = false)
public class InterestCRUDTest {
    @Autowired
    private InterestService interestService;

    @Test
    public void getInterest(){
        List<Interest> interests =  interestService.findAll();
        interests.forEach(e-> System.out.println(e.getInterest()));
    }

}
