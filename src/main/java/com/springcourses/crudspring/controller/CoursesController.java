package com.springcourses.crudspring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourses.crudspring.model.Course;
import com.springcourses.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Validated
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CoursesController {

    private final CourseRepository courseRepository;

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<Course>> list() {
        List<Course> lista = new ArrayList<>(courseRepository.findAll());

        return ResponseEntity.ok().body(lista);

    }

    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<Course> findById(@PathVariable("id") Integer identificador) {

        return courseRepository.findById(identificador)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.noContent().build());

    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody @Valid Course course) {

        return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
        // System.out.println(course.getId()) //console.log
    }

    @DeleteMapping("delete/{id}")
    // @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> delete(@PathVariable("id") @NotNull @Positive Integer id) {

        return courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(recordFound.getId());
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Course> edit(@RequestBody @Valid Course courseBody,
            @PathVariable @NotNull @Positive Integer id) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setCategory(courseBody.getCategory());
                    recordFound.setName(courseBody.getName());
                    Course updated = courseRepository.save(recordFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.badRequest().build());

    }

}
