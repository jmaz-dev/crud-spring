package com.springcourses.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

public record LessonDTO(
                Integer id,
                @Length(min = 3, max = 100) @NotNull String name,
                @NotNull @Length(max = 100) String link) {
}