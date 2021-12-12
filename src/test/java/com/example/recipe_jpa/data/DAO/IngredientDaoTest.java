package com.example.recipe_jpa.data.DAO;

import com.example.recipe_jpa.model.entities.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IngredientDaoTest {

    @Autowired
    private IngredientDao testObject;

    @Autowired
    private TestEntityManager em;




    @Test
    void findByIngredientNameIgnoringCase() {
        Ingredient ingredient = em.persist(new Ingredient("Salt"));

        Ingredient result = testObject.findByIngredientNameIgnoringCase("salt");

        assertNotNull(result);

        assertEquals(ingredient,result);
    }

    @Test
    void findAllByIngredientNameContainingIgnoringCase(){
        List<Ingredient> ingredients = new ArrayList<>(
                Arrays.asList(
                        new Ingredient("Potato"),
                        new Ingredient("Tomato"),
                        new Ingredient("Salt"),
                        new Ingredient("Milk")
                )
        );

        ingredients.forEach(em::persist);

        List<Ingredient> result = testObject.findAllByIngredientNameContainingIgnoringCase("Ato");

        int expected = 2;

        assertEquals(2,result.size());
    }
}