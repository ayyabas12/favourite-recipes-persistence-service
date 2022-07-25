package com.nl.cgi.ps.dao.repository;

import com.nl.cgi.ps.dao.model.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DishesRepository extends JpaRepository<Dishes, Long> {


    public List<Dishes> findByInstructionsContainingIgnoreCase(String instructions);
}
