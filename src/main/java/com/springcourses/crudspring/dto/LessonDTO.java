package com.springcourses.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

public record LessonDTO(
        Integer id,
        @Length(max = 100) @NotNull String name,
        @NotNull @Length(max = 100) String link) {
}