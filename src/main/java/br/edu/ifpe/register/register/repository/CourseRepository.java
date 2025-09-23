package br.edu.ifpe.register.register.repository;

import br.edu.ifpe.register.register.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}
