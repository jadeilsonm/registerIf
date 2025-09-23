package br.edu.ifpe.register.register.repository;

import br.edu.ifpe.register.register.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DisciplineRepository extends JpaRepository<Discipline, UUID> {
}
