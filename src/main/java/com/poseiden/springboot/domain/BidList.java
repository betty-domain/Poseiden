package com.poseiden.springboot.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


/**
 * Bid List Entity
 */
@Entity
@Data
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidListId", nullable = false)
    private Integer bidListId;

    @NotBlank(message = "Account is mandatory")
    @Size(max = 30)
    @Column(length = 30, nullable = false)
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Size(max = 30)
    @Column(length = 30, nullable = false)
    private String type;

    private Double bidQuantity;

    private Double askQuantity;

    private Double bid;

    private Double ask;

    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String benchmark;

    private LocalDateTime bidListDate;

    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String commentary;

    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String security;

    @Size(max = 10)
    @Column(length = 10, nullable = true)
    private String status;

    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String trader;

    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String book;

    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String creationName;

    private LocalDateTime creationDate;

    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String revisionName;

    private LocalDateTime revisionDate;

    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String dealName;

    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String dealType;

    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String sourceListId;

    @Size(max = 125)
    @Column(length = 125, nullable = true)
    private String side;

}
