package com.example.pantry.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    private @Id @GeneratedValue Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Ingredient(){}

    public Ingredient(String name, double quantity, String unit){
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
    }

    private String name;

    public String getName() {
        return name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    private Double quantity;

    private String unit;

    public static Ingredient build(String name, double quantity, String unit){
        return new Ingredient(name, quantity, unit);
    }
}
