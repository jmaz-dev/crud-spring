package com.springcourses.crudspring.model;

import java.sql.Timestamp;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

// @Getter
// @Setter
@Data
@Entity
@Table(name = "cursos")
@SQLDelete(sql = "UPDATE cursos SET data_de_exclusao = CURRENT_DATE WHERE id = ?")

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Integer id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(name = "nome", length = 100, nullable = false)
    private String name;

    @NotNull
    @Length(min = 5, max = 10)
    @Pattern(regexp = "Back-end|Front-end")
    @Column(name = "categoria", length = 10, nullable = false)
    private String category;

    @Column(name = "data_de_exclusao", length = 30, nullable = true)
    private Timestamp exclusionDate = null;

}
