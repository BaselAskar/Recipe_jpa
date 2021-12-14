package com.example.recipe_jpa;

import com.example.recipe_jpa.data.DAO.RecipeDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CommandLine implements CommandLineRunner{

    private RecipeDao recipeDao;


    @Override
    public void run(String... args) throws Exception {


    }
}