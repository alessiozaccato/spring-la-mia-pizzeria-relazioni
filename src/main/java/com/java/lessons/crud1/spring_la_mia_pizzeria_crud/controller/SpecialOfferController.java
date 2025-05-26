package com.java.lessons.crud1.spring_la_mia_pizzeria_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.lessons.crud1.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import com.java.lessons.crud1.spring_la_mia_pizzeria_crud.repositories.SpecialOfferRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/specialOffers")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "specialOffers/create";
        }

        specialOfferRepository.save(formSpecialOffer);

        return "redirect:/pizzas/" + formSpecialOffer.getPizza().getId();
    }

    @GetMapping("/{id}/edit")
    public String edit (@PathVariable int id,  Model model){

    model.addAttribute("specialOffer", specialOfferRepository.findById(id).get());
    model.addAttribute("edit", true);
    return "specialOffers/create";

    }

    @PostMapping("/{id}/edit")
    public String patch (@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult, Model model){

         if (bindingResult.hasErrors()) {
            return "specialOffers/create";
        }

        specialOfferRepository.save(formSpecialOffer);

        return "redirect:/pizzas/" + formSpecialOffer.getPizza().getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        specialOfferRepository.deleteById(id);
        
        return "redirect:/pizzas";
    }
    
}
