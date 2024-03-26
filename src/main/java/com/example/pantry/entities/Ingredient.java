package com.example.pantry.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    private @Id @GeneratedValue Long id;

    @ManyToOne
    @JoinColumn( name = "recipe_id")
    @JsonBackReference
    private Recipe recipe;

    private String name;

    private Double quantity;

    private String unit;

    public Ingredient(Long id, Recipe recipe, String name, Double quantity, String unit) {
        this.id = id;
        this.recipe = recipe;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Ingredient(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
