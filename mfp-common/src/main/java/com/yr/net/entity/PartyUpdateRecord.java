package com.yr.net.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name="yr_party_update_record")
public class PartyUpdateRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long partyId;

    private String name;

    private String partyContent;

    private Integer partyTypeId;

    private String partyTypeName;

    private Integer enrollMax;

    private Integer maleMax;

    private Integer femaleMax;

    private String conductAddr;

    private Date conductTime;

    private Date beginTime;

    private Date endTime;

    private Integer entryFee;

    private Long modifierId;

    private String modifier;

    private String remarks;

    private Date createTime;

    private static final long serialVersionUID = 1L;

}