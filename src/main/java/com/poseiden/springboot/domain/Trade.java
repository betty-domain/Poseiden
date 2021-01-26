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

@Data
@Entity
@Table(name = "trade")
public class Trade {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tradeId;

    @NotBlank(message = "Account is mandatory")
    @Size(max = 30)
    @Column(length = 30, nullable = false)
    private String account;

    @NotBlank(message="Type is mandatory")
    @Size(max = 30)
    @Column(length = 30, nullable = false)
    private String type;

    private Double buyQuantity;

    private Double sellQuantity;

    private Double buyPrice;

    private Double sellPrice;

    private LocalDateTime tradeDate;

    @Size(max = 125)
    @Column(length = 125)
    private String security;

    @Size(max=10)
    @Column(length = 10)
    private String status;

    @Size(max = 125)
    @Column(length = 125)
    private String trader;

    @Size(max = 125)
    @Column(length = 125)
    private String benchmark;

    @Size(max = 125)
    @Column(length = 125)
    private String book;

    @Size(max = 125)
    @Column(length = 125)
    private String creationName;

    private LocalDateTime creationDate;

    @Size(max = 125)
    @Column(length = 125)
    private String revisionName;

    private LocalDateTime revisionDate;

    @Size(max = 125)
    @Column(length = 125)
    private String dealName;

    @Size(max = 125)
    @Column(length = 125)
    private String dealType;

    @Size(max = 125)
    @Column(length = 125)
    private String sourceListId;

    @Size(max = 125)
    @Column(length = 125)
    private String side;

    /**
     * Constructor without properties initialization
     */
    public Trade(){}

    public Trade(String account, String type)
    {
        this.account = account;
        this.type = type;
    }
}
