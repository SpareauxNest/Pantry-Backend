package com.example.pantry;

import com.example.pantry.entities.Ingredient;
import com.example.pantry.entities.Recipe;
import com.example.pantry.repositories.IngredientRepository;
import com.example.pantry.repositories.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class Controller {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    Controller(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @CrossOrigin
    @GetMapping("/recipe/getAll")
    public ResponseEntity<List<Recipe>> getData(){
        try {
            List<Recipe> result = recipeRepository.findAll();
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id){
        Optional<Recipe> output = recipeRepository.findById(id);
        if(output.isPresent()){ return new ResponseEntity<Recipe>(output.get(), HttpStatus.FOUND);}
        else {return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);}
    }

    @GetMapping("/ingredient/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Long id){
        Optional<Ingredient> output = ingredientRepository.findById(id);
        if(output.isPresent()){ return new ResponseEntity<Ingredient>(output.get(), HttpStatus.FOUND);}
        else {return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);}
    }

    @PostMapping("/recipe/add")
    public ResponseEntity<Recipe> newRecipe(@RequestBody Recipe recipe){
        try {
            recipe.getIngredients().stream().forEach(ingredient -> {
                List<Recipe> recipes;
                if(Objects.nonNull(ingredient.getRecipes())){ recipes = ingredient.getRecipes();}
                else{recipes = new ArrayList<>();}
                recipes.add(recipe);
                ingredient.setRecipes(recipes);
                ingredientRepository.save(ingredient);
            });
            Recipe output = recipeRepository.save(recipe);
            return new ResponseEntity<>(output, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable Long id){
        try {
            recipeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
