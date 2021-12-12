package com.example.recipe_jpa.data.DAO;

import com.example.recipe_jpa.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class RecipeDaoTest {

    @Autowired
    private RecipeDao testObject;

    @Autowired
    private TestEntityManager em;


    //First constants
    public List<RecipeIngredient> recipeIngredients1() {return new ArrayList<>(Arrays.asList(
            new RecipeIngredient(500, Measurement.G,new Ingredient("Potato")),
            new RecipeIngredient(2.5,Measurement.G,new Ingredient("Salt"))
    ));
    }

    public List<RecipeCategory> categories1(){ return new ArrayList<>(Arrays.asList(
            new RecipeCategory("Lunch food"),
            new RecipeCategory("Potato food")
    ));
    }

    public RecipeInstruction instruction1(){return new RecipeInstruction("It's cooked carefully");}



    //Second constants
    public List<RecipeIngredient> recipeIngredients2()

    {
        return new ArrayList<>(Arrays.asList(
                new RecipeIngredient(500, Measurement.G, new Ingredient("Potato")),
                new RecipeIngredient(250, Measurement.G, new Ingredient("Tomato")),
                new RecipeIngredient(20, Measurement.G, new Ingredient("Pepper"))
        ));
    }

    public RecipeInstruction instruction2() { return new RecipeInstruction("it has to eaten hot");}

    public List<RecipeCategory> categories2() {
        return new ArrayList<>(Arrays.asList(
                new RecipeCategory("Hot food"),
                new RecipeCategory("Potato food")
        ));
    }


    private List<Recipe> recipeList;

    private List<Recipe> persistedRecipes;

    private Recipe firstRecipe;



    @BeforeEach
    void setUp() {

        //First arguments
        List<RecipeIngredient> persistedRecipeIngredient1 = new ArrayList<>();
        recipeIngredients1().forEach(ri -> {
           ri = em.persist(ri);
            persistedRecipeIngredient1.add(ri);
        });



        List<RecipeCategory> persistedCategories1 = new ArrayList<>();
        categories1().forEach(ca -> {
            ca = em.persistAndFlush(ca);
            persistedCategories1.add(ca);
        });

        RecipeInstruction persistedInstruction1 = em.persist(instruction1());


        //Second arguments
        List<RecipeIngredient> persistedRecipeIngredient2 = new ArrayList<>();
        recipeIngredients2().forEach(ri -> {
            ri = em.persist(ri);
            em.refresh(ri);
            persistedRecipeIngredient1.add(ri);
        });


        List<RecipeCategory> persistedCategories2 = new ArrayList<>();
        categories2().forEach(ca -> {
            ca = em.persistAndFlush(ca);
            persistedCategories1.add(ca);
        });

        RecipeInstruction persistedInstruction2 = em.persist(instruction2());



        recipeList = new ArrayList<>(Arrays.asList(
                new Recipe("Aloo tikki",persistedRecipeIngredient1,persistedCategories1,persistedInstruction1),
                new Recipe("Aloo gobi",persistedRecipeIngredient2,persistedCategories2,persistedInstruction2)
        ));

        persistedRecipes = recipeList.stream()
                .map(em::persist)
                .collect(Collectors.toList());



       firstRecipe = persistedRecipes.get(0);
    }

    @Test
    void findAllByRecipeNameContainingIgnoringCase() {
        int expected = 1;

        List<Recipe> testRecipes = testObject.findAllByRecipeNameContainingIgnoringCase("go");

        assertEquals(expected,testRecipes.size());
    }

    @Test
    void searchByRecipeName() {
        int expected = 2;

        List<Recipe> testRecipes = testObject.searchByRecipeName("aloo");

        assertEquals(expected,testRecipes.size());

    }

    @Test
    void findAllByRecipeIngredientsIngredientIngredientNameIgnoringCase() {
        int expected = 1;

        List<Recipe> testRecipes = testObject.findAllByRecipeIngredientsIngredientIngredientNameIgnoringCase("Tomato");

        assertEquals(expected,testRecipes.size());
    }

    @Test
    void searchByIngredientName() {
        int expected = 1;

        List<Recipe> testRecipes = testObject.searchByIngredientName("Salt");


        assertEquals(expected,testRecipes.size());

    }

    @Test
    void findAllByCategoriesCategory() {
    }

    @Test
    void searchByCategory() {
    }

    @Test
    void searchByAnyCategories() {
    }
}