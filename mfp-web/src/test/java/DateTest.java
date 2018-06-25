import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.common.base.Preconditions;
import com.yr.net.util.AgeUtils;

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
public class DateTest {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("1984-04-09");

        int age = AgeUtils.getAgeByDate(date);
        System.out.println(age);

        System.out.println(date.getTime());
                Preconditions.checkNotNull(args);
    }
}
