package com.springcourses.crudspring.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourses.crudspring.dto.CourseDTO;
import com.springcourses.crudspring.service.CourseService;

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

    private final CourseService courseService;

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<CourseDTO>> list() {

        return ResponseEntity.ok().body(courseService.list());

    }

    @GetMapping("buscarPorId/{id}")
    public CourseDTO findById(@PathVariable("id") Integer identificador) {

        return courseService.findById(identificador);

    }

    @PostMapping
    public CourseDTO create(@RequestBody @NotNull @Valid CourseDTO course) {

        return courseService.create(course);
        // System.out.println(course.getId()) //console.log
    }

    @PutMapping("edit/{id}")
    public CourseDTO edit(@RequestBody @Valid CourseDTO courseBody,
            @PathVariable @NotNull @Positive Integer id) {

        return courseService.edit(courseBody, id);

    }

    @DeleteMapping("delete/{id}")
    // @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable("id") @NotNull @Positive Integer id) {

        courseService.delete(id);

    }

}
