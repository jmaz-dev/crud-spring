package com.springcourses.crudspring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.springcourses.crudspring.dto.CourseDTO;
import com.springcourses.crudspring.dto.mapper.CourseMapper;
import com.springcourses.crudspring.exception.RecordNotFoundException;
import com.springcourses.crudspring.model.Course;
import com.springcourses.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {

        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;

    }

    public List<CourseDTO> list() {

        return courseRepository.findAll()
                .stream().map(courseMapper::toDTO)
                .collect(Collectors.toList());

    }

    public CourseDTO findById(@PathVariable("id") @NotNull @Positive Integer identificador) {

        return courseRepository.findById(identificador).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(identificador));

    }

    public CourseDTO create(@Valid @NotNull Course course) {

        return courseMapper.toDTO(courseRepository.save(course));

    }

    public CourseDTO edit(@Valid Course courseBody, @NotNull Integer id) {

        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setCategory(courseBody.getCategory());
                    recordFound.setName(courseBody.getName());
                    return courseMapper.toDTO(courseRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));

    }

    public void delete(Integer id) {

        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));

    }
}
