package com.yr.net.enums;

/**
 * 商品类型
 * @Author: pengjj
 * @Date: 2018/6/11 17:59
 */
public enum GoodsType {

    /**
     * 参加活动、约会定金
     */
    DEPOSIT(1, "一次性定金"),
    /**
     *加入会员
     */
    MEMBER(2, "会员"),
    /**
     * 上伊人头条
     */
    TOPLINE(3,"上头条诚意金");

    GoodsType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static GoodsType getById(Integer id){
        if(id == null){
            return null;
        }
        for(GoodsType e : GoodsType.values()){
            if(e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }

    /**
     * 根据Code获取Value
     *
     * @param code 键
     * @return 值
     */
    public static String getDescByCode(Integer code)
    {
        for(GoodsType enums : GoodsType.values())
        {
            if(enums.id.equals(code))
            {
                return enums.name;
            }
        }
        return "";
    }
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
