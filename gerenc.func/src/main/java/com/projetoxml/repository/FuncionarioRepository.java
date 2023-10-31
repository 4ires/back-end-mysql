package com.projetoxml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoxml.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
