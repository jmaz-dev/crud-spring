package com.springcourses.crudspring;

import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.context.annotation.Bean;

import com.springcourses.crudspring.enums.Category;
import com.springcourses.crudspring.model.Course;
import com.springcourses.crudspring.model.Lesson;
import com.springcourses.crudspring.repository.CourseRepository;

// import com.springcourses.crudspring.model.Course;
// import com.springcourses.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular com spring");
			c.setCategory(Category.BACKEND);

			Lesson l1 = new Lesson();
			l1.setName("Introdução");
			l1.setLink("watch?v=1");
			l1.setCourse(c);

			Lesson l2 = new Lesson();
			l2.setName("Aula 2");
			l2.setLink("watch?v=2");
			l2.setCourse(c);

			c.getLessons().add(l1);
			c.getLessons().add(l2);

			courseRepository.save(c);
		};
	}

}
