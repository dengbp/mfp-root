package com.yr.net.common;

/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/4/26
 * </pre>
 * <p>
 *     文章类型
 * </p>
 */
public enum ArticleType {
    //1：情感；2：生活；3旅游;4:官方活动类型;5:用户相亲类型;6:商品广告
    EMOTION("EMOTION",1),LIFE("LIFE",2),TOUR("tour",3),PARTY("PARTY",4),FINDFRIEND("FINDFRIEND",5),ADVERTISING("ADVERTISING",6);
    private String type;
    private int index;
    ArticleType(String type,int index){
        this.type = type;
        this.index = index;
    }

    /**
     * 通过值取对应类型
     * @param index
     * @return
     */
    public static String getName(int index) {
        for (ArticleType a : ArticleType.values()) {
            if (a.getIndex() == index) {
                return a.type;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }
}
