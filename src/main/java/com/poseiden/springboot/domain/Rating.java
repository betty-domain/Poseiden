package com.poseiden.springboot.domain;

import lombok.Data;

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
    private String moodysRating;

    @Size(max = 125)
    private String sandpRating;

    @NotBlank(message = "FitchRating is mandatory")
    @Size(max = 125)
    private String fitchRating;

    @NotNull(message = "must not be null")
    private Integer orderNumber;
}
