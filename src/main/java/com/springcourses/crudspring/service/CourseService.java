package com.springcourses.crudspring.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.springcourses.crudspring.model.Course;
import com.springcourses.crudspring.repository.CourseRepository;
import com.springcourses.exception.RecordNotFoundException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {

        this.courseRepository = courseRepository;

    }

    public List<Course> list() {

        List<Course> courses = courseRepository.findAll();

        return courses.stream()
                .filter(c -> c.getExclusionDate() == null)
                .collect(Collectors.toList());

    }

    public Course findById(@PathVariable("id") @NotNull @Positive Integer identificador) {

        return courseRepository.findById(identificador).orElseThrow(() -> new RecordNotFoundException(identificador));

    }

    public Course create(@Valid Course course) {

        return courseRepository.save(course);

    }

    public Course edit(@Valid Course courseBody, Integer id) {

        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setCategory(courseBody.getCategory());
                    recordFound.setName(courseBody.getName());
                    return courseRepository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));

    }

    public void delete(Integer id) {

        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));

    }
}
