import com.yr.net.common.ArticleType;

/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/4/26
 * </pre>
 * <p>
 * </p>
 */
public class EnumTest {

    public static void main(String[] args){
        System.out.println(ArticleType.EMOTION);
        System.out.println(ArticleType.EMOTION.ordinal());
        System.out.println(ArticleType.EMOTION.getType());
        System.out.println(ArticleType.EMOTION.name());
        System.out.println(ArticleType.EMOTION.getIndex());
    }
}
