package com.example.pantry.repositories;

import com.example.pantry.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

//http://localhost:8080/h2-console
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
