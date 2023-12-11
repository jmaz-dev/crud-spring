package com.springcourses.crudspring.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcourses.crudspring.enums.Category;
import com.springcourses.crudspring.enums.converters.CategoryConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "categoria", nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @Column(name = "data_de_exclusao", nullable = true)
    private Timestamp exclusionDate = null;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();

}
