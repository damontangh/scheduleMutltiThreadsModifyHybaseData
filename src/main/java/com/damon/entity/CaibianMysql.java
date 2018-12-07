package com.damon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "xwcmexternalpubdocrecord")
public class CaibianMysql implements Serializable{

    //@Column(name = "EXTERNALPUBDOCRECORDID")
    private String EXTERNALPUBDOCRECORDID;

    //@Column(name = "MLFSITEID")
    private Integer MLFSITEID;

    //@Column(name = "DOCID")
    private Integer DOCID;

    //@Column(name = "EXTPUBID")
    private Integer EXTPUBID;

    //@Column(name = "EXTURL")
    private String EXTURL;

    //@Column(name = "CrUser")
    private String CrUser;

   /* @Column(name = "CrTime")
    private Date CrTime;*/




}
