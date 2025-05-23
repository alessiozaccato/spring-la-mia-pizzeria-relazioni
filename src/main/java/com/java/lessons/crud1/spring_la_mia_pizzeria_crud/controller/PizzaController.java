package com.java.lessons.crud1.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.lessons.crud1.spring_la_mia_pizzeria_crud.model.Pizza;
import com.java.lessons.crud1.spring_la_mia_pizzeria_crud.repositories.PizzaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model){
        List <Pizza> pizzas= repository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model){
        Pizza pizza= repository.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizzas/show";
    }

    @GetMapping("/searchByName")
    public String searchByName(@RequestParam(name="name") String name,Model model){
        List <Pizza> pizzas= repository.findByNameContaining(name);
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza",new Pizza());
        return"pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindimgResult, Model model) {
        if (bindimgResult.hasErrors()) {
            return "pizzas/create";
        }

        repository.save(formPizza);

        return"redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id,Model model) {
        model.addAttribute("pizza",repository.findById(id).get());
        return"pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindimgResult, Model model) {
        if (bindimgResult.hasErrors()) {
            return "pizzas/edit";
        }

        repository.save(formPizza);

        return"redirect:/pizzas";
    }

    @PostMapping ("/delete/{id}")
    public String delete(@PathVariable int id){
        repository.deleteById(id);
        return "redirect:/pizzas";
    }
}
