package com.projetojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetojpa.entities.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
