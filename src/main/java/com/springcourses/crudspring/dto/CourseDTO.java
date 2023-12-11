package com.springcourses.crudspring.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
                @JsonProperty("_id") Integer id,
                @NotBlank @NotNull @Length(min = 5, max = 100) String name,
                @NotNull @Length(max = 10) @Pattern(regexp = "Back-end|Front-end|0|1") String category,
                List<LessonDTO> lessons) {
}
