package com.example.recipe_jpa.data.DAO;

import com.example.recipe_jpa.model.entities.*;
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
    private IngredientDao ingredientDao;

    @Autowired
    private TestEntityManager em;


    private Ingredient ingredientPotato = new Ingredient("Potato");
    private Ingredient ingredientTomato = new Ingredient("Tomato");
    private Ingredient ingredientSalt = new Ingredient("Salt");
    private Ingredient ingredientPepper = new Ingredient("Pepper");

    //First constants
    public List<RecipeIngredient> recipeIngredients1() {return new ArrayList<>(Arrays.asList(
            new RecipeIngredient(500, Measurement.G,ingredientPotato),
            new RecipeIngredient(2.5,Measurement.G,ingredientSalt)
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
                new RecipeIngredient(500, Measurement.G,ingredientPotato),
                new RecipeIngredient(250, Measurement.G,ingredientTomato),
                new RecipeIngredient(20, Measurement.G,ingredientPepper)
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

        Ingredient persistedIngredientPotato = em.persist(ingredientPotato);
        Ingredient persistedIngredientTomato = em.persist(ingredientTomato);
        Ingredient persistedIngredientSalt = em.persist(ingredientSalt);
        Ingredient persistedIngredientPepper = em.persist(ingredientPepper);

        //First arguments
        List<RecipeIngredient> persistedRecipeIngredient1 = recipeIngredients1().stream()
                .map(ri -> {
                    ri.setIngredient(ingredientDao.findByIngredientName(ri.getIngredient().getIngredientName()));
                    return em.persist(ri);
                })
                .collect(Collectors.toList());




        List<RecipeCategory> persistedCategories1 = categories1().stream()
                .map(em::persist)
                .collect(Collectors.toList());


        RecipeInstruction persistedInstruction1 = em.persist(instruction1());


        //Second arguments
        List<RecipeIngredient> persistedRecipeIngredient2 = recipeIngredients2().stream()
                .map(ri -> {
                    ri.setIngredient(ingredientDao.findByIngredientName(ri.getIngredient().getIngredientName()));
                    return em.persist(ri);
                })
                .collect(Collectors.toList());

        List<RecipeCategory> persistedCategories2 = categories2().stream()
                .map(em::persist)
                .collect(Collectors.toList());

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
        int expected = 2;

        List<Recipe> recipes = testObject.findAllByCategoriesCategory("Potato food");

        assertEquals(expected,recipes.size());
    }

    @Test
    void searchByCategory() {
        int expected = 2;

        List<Recipe> recipes = testObject.searchByCategory("Potato food");

        assertEquals(expected,recipes.size());

    }

    @Test
    void searchByAnyCategories() {

        int expected = 2;

        Set<Recipe> recipes = testObject.searchByAnyCategories("Potato food","Lunch food");

        assertEquals(expected,recipes.size());

    }
}