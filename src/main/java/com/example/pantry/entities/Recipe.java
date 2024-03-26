package com.example.pantry.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {

    private @Id @GeneratedValue Long id;

    public Recipe(String name, List<Ingredient> ingredients, String link) {
        this.name = name;
        this.ingredients = ingredients;
        this.link = link;
    }

    public Recipe(){}

    private String name;

    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients;

    private String link;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getLink() {return link;}

    public static Recipe build(String name, List<Ingredient> ingredients, String link){
        return new Recipe(name, ingredients, link);
    }
}