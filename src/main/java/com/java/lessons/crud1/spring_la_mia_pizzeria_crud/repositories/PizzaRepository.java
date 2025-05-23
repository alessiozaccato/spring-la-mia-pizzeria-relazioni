package com.java.lessons.crud1.spring_la_mia_pizzeria_crud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.lessons.crud1.spring_la_mia_pizzeria_crud.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

    public List<Pizza> findByNameContaining(String name);
}
