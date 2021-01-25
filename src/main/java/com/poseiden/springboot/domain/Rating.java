package com.poseiden.springboot.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Rating entity
 */
@Data
@Entity
@Table(name = "rating")
public class Rating {
    // DONE: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 125)
    @Column(name = "moodysrating")
    private String moodyRating;

    @Size(max = 125)
    @Column(name="sandprating")
    private String sandRating;

    @NotBlank(message = "FitchRating is mandatory")
    @Size(max = 125)
    private String fitchRating;

    @NotNull(message = "must not be null")
    private Integer orderNumber;

    /**
     * Empty constructor
     */
    public Rating(){}

    /**
     * Constructor with properties initialization
     * @param moodyRating moodysRating
     * @param sandpRating sandpRating
     * @param fitchRating fitchRating
     * @param orderNumber orderNumer
     */
    public Rating(String moodyRating, String sandRating, String fitchRating, Integer orderNumber)
    {
        this.moodyRating = moodyRating;
        this.sandRating =sandRating;
        this.fitchRating=fitchRating;
        this.orderNumber = orderNumber;
    }


}
