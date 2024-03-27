package com.example.pantry.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ingredients")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Ingredient {
    private @Id @GeneratedValue Long id;

    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes;

    private String name;

    private Double quantity;

    private String unit;

    public Ingredient(Long id, List<Recipe> recipes, String name, Double quantity, String unit) {
        this.id = id;
        this.recipes = recipes;
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

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
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
