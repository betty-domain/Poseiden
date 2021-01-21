package com.nnk.springboot.domain;

import lombok.Data;
import org.hibernate.annotations.CollectionId;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "bidlist")
public class BidList {

    //BidListId tinyint(4) NOT NULL AUTO_INCREMENT,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "BidListId")
    private Integer bidListId;

    //account VARCHAR(30) NOT NULL,
    @NotBlank
    @Size(max = 30)
    @Column(length = 30, nullable = false)
    private String account;

    //type VARCHAR(30) NOT NULL,
    @NotBlank
    @Size(max = 30)
    @Column(length = 30, nullable = false)
    private String type;

    //bidQuantity DOUBLE,
    private Double bidQuantity;

    //askQuantity DOUBLE,
    private Double askQuantity;

    //bid DOUBLE ,
    private Double bit;

    //ask DOUBLE,
    private Double ask;

    //benchmark VARCHAR(125),
    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String benchmark;

    //bidListDate TIMESTAMP,
    private LocalDateTime bidListDate;

    //commentary VARCHAR(125),
    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String commentary;

    //security VARCHAR(125),
    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String security;

    //status VARCHAR(10),
    @Size(max = 10)
    @Column(length = 10, nullable = true)
    private String status;

    //trader VARCHAR(125),
    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String trader;

    //book VARCHAR(125),
    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String book;

    //creationName VARCHAR(125),
    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String creationName;

    //creationDate TIMESTAMP ,
    private LocalDateTime creationDate;

    //revisionName VARCHAR(125),
    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String revisionName;

    //revisionDate TIMESTAMP ,
    private LocalDateTime revisionDate;

    //dealName VARCHAR(125),
    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String dealName;

    //dealType VARCHAR(125),
    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String dealType;

    //sourceListId VARCHAR(125),
    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String sourceListId;

    //side VARCHAR(125),
    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String side;

}
