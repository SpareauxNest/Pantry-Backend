package com.example.pantry;

import com.example.pantry.entities.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    private final RecipeRepository recipeRepository;

    Controller(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipe/getAll")
    public ResponseEntity<List<Recipe>> getData(){
        try {
            List<Recipe> result = recipeRepository.findAll();
            return new ResponseEntity<List<Recipe>>(result, HttpStatus.FOUND);
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

    @PostMapping("/recipe/add")
    public ResponseEntity<Recipe> newRecipe(@RequestBody Recipe recipe){
        try {
            Recipe output = recipeRepository.save(recipe);
            return new ResponseEntity<Recipe>(output, HttpStatus.CREATED);
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
