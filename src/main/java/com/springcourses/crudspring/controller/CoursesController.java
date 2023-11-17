package com.springcourses.crudspring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourses.crudspring.model.Course;
import com.springcourses.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
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
    public ResponseEntity<Course> create(@RequestBody Course course) {
        if (course.getName().isEmpty() || course.getCategory().isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
        // System.out.println(course.getId()) //console.log
    }

    @DeleteMapping("delete/{id}")
    // @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {

        courseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Course> edit(@RequestBody Course courseBody, @PathVariable Integer id) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setCategory(courseBody.getCategory());
                    recordFound.setName(courseBody.getName());
                    return ResponseEntity.ok().body(courseRepository.save(recordFound));
                })
                .orElse(ResponseEntity.badRequest().build());

    }

}
