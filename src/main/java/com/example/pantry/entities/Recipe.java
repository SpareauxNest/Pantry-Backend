package com.example.pantry.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {

    private @Id @GeneratedValue Long id;

    //TODO: Change over to ManyToMany
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ingredient> ingredients;
    private String name;

    private String link;

    public Recipe(Long id, List<Ingredient> ingredients, String name, String link) {
        this.id = id;
        this.ingredients = ingredients;
        this.name = name;
        this.link = link;
    }

    public Recipe(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}