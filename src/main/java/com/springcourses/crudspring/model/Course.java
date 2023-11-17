package com.springcourses.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// @Getter
// @Setter
@Data
@Entity
@Table(name = "cursos")
public class Course {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonProperty("_id")
    private Integer id;

    @Column(name = "nome", length = 200, nullable = false)
    private String name;

    @Column(name = "categoria", length = 10, nullable = false)
    private String category;

}
