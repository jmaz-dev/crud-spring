package com.springcourses.crudspring.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springcourses.crudspring.dto.CourseDTO;
import com.springcourses.crudspring.dto.LessonDTO;
import com.springcourses.crudspring.enums.Category;
import com.springcourses.crudspring.model.Course;
import com.springcourses.crudspring.model.Lesson;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;

        }
        List<LessonDTO> lessons = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getLink()))
                .collect(Collectors.toList());

        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getCategory().getValue(),
                lessons);
    }

    public Course toEntity(CourseDTO courseDTO) {
        Course course = new Course();

        if (courseDTO == null) {
            return null;
        }

        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }

        List<Lesson> lessons = courseDTO.lessons().stream().map(lessonDTO -> {
            Lesson lesson = new Lesson();
            lesson.setId(lessonDTO.id());
            lesson.setName(lessonDTO.name());
            lesson.setLink(lessonDTO.link());

            lesson.setCourse(course);

            return lesson;
        }).collect(Collectors.toList());

        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        course.setLessons(lessons);

        return course;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case "Front-end" -> Category.FRONTEND;

            case "Back-end" -> Category.BACKEND;

            case "Full-stack" -> Category.FULLSTACK;

            case "Mobile" -> Category.MOBILE;

            case "0" -> Category.BACKEND;

            case "1" -> Category.FRONTEND;

            case "2" -> Category.FULLSTACK;

            case "3" -> Category.MOBILE;

            default -> throw new IllegalArgumentException("Categoria inválida" + value);
        };
    }
}
