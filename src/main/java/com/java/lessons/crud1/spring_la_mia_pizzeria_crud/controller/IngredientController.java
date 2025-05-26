package com.java.lessons.crud1.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.lessons.crud1.spring_la_mia_pizzeria_crud.model.Ingredient;
import com.java.lessons.crud1.spring_la_mia_pizzeria_crud.model.Pizza;
import com.java.lessons.crud1.spring_la_mia_pizzeria_crud.repositories.IngredientRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String getMethodName(Model model) {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Ingredient ingredient = ingredientRepository.findById(id).get();
        model.addAttribute("ingredient", ingredient);
        return "ingredients/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }

        ingredientRepository.save(formIngredient);

        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        model.addAttribute("edit", true);
        return "ingredients/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }

        ingredientRepository.save(formIngredient);

        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Ingredient ingredientToRemove = ingredientRepository.findById(id).get();

        for (Pizza linkedPizza : ingredientToRemove.getPizzas()) {
            linkedPizza.getIngredients().remove(ingredientToRemove);
        }
        ingredientRepository.delete(ingredientToRemove);

        return"redirect:/ingredients";
    }

}
