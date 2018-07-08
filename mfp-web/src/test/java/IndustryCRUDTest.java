import com.Application;
import com.yr.net.repository.IndustryRepository;
import com.yr.net.service.impl.IndustryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
@Rollback(value = false)
public class IndustryCRUDTest {
    @Autowired
    private IndustryService industryService;

    @Autowired
    private IndustryRepository industryRepository;

    @Test
    public void getIndustry(){
//        Stream<Industry> industryStream = industryRepository.findAllByIndustryQueryAndStream();
//        industryStream.forEach(e-> System.out.println(e.getIndustry()));
    }

}
