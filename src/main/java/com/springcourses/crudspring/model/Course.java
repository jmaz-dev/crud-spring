package com.springcourses.crudspring.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

// @Getter
// @Setter
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
    @Column(name = "categoria", length = 12, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @Column(name = "data_de_exclusao", nullable = true)
    private Timestamp exclusionDate = null;

    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotBlank @NotNull @Length(min = 5, max = 100) String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(@NotNull Category category) {
        this.category = category;
    }

    public Timestamp getExclusionDate() {
        return exclusionDate;
    }

    public void setExclusionDate(Timestamp exclusionDate) {
        this.exclusionDate = exclusionDate;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", category=" + category + ", exclusionDate=" + exclusionDate
                + ", lessons=" + lessons + "]";
    }

}
// public Integer getId() {
// return id;
// }

// public void setId(Integer id) {
// this.id = id;
// }

// public String getName() {
// return name;
// }

// public void setName(@NotBlank @NotNull @Length(min = 5, max = 100) String
// name) {
// this.name = name;
// }

// public Category getCategory() {
// return category;
// }

// public void setCategory(@NotNull @Length(max = 20) Category category) {
// this.category = category;
// }

// public Timestamp getExclusionDate() {
// return exclusionDate;
// }

// public void setExclusionDate(Timestamp exclusionDate) {
// this.exclusionDate = exclusionDate;
// }

// public List<Lesson> getLessons() {
// return lessons;
// }

// public void setLessons(@NotNull @NotEmpty @Valid List<Lesson> lessons) {
// this.lessons = lessons;
// }

// @Override
// public String toString() {
// return "Course [id=" + id + ", name=" + name + ", category=" + category + ",
// exclusionDate=" + exclusionDate
// + ", lessons=" + lessons + "]";
// }
