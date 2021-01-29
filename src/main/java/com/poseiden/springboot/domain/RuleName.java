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

/**
 * RuleName Entity
 */
@Data
@Entity
@Table(name = "rulename")
public class RuleName {
    // DONE: Map columns in data table RULENAME with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 125)
    @Column(length = 125)
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 125)
    @Column(length = 125)
    private String description;

    @Size(max = 125)
    @Column(length = 125)
    private String json;

    @Size(max = 512)
    @Column(length = 512)
    private String template;

    @Size(max = 125)
    @Column(length = 125)
    private String sqlStr;

    @Size(max = 125)
    @Column(length = 125)
    private String sqlPart;

    /**
     * Constructor without properties initialization
     */
    public RuleName()
    {}

    /**
     * Constructor with properties initialization
     * @param name name
     * @param description description
     * @param json json
     * @param template template
     * @param sqlStr sqlStr
     * @param sqlPart sqlPart
     */
    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart){
        this.name = name;
        this.description = description;
        this.json = json;
        this.template=template;
        this.sqlPart=sqlPart;
        this.sqlStr=sqlStr;
    }
}
