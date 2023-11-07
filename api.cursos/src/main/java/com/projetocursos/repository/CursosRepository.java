package com.projetocursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetocursos.entities.Cursos;

public interface CursosRepository  extends JpaRepository<Cursos, Long>{

}
