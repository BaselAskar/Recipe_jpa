package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.data.DAO.RecipeIngredientDao;
import com.example.recipe_jpa.model.dto.form.RecipeIngredientForm;
import com.example.recipe_jpa.model.entities.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeIngredientCreationImpl implements RecipeIngredientCreation{


    private RecipeIngredientDao recipeIngredientDao;

    @Autowired
    public RecipeIngredientCreationImpl(RecipeIngredientDao recipeIngredientDao) {
        this.recipeIngredientDao = recipeIngredientDao;
    }

    @Override
    public RecipeIngredient save(RecipeIngredientForm recipeIngredientForm) {
        RecipeIngredient recipeIngredient = new RecipeIngredient();

        recipeIngredient.setId(recipeIngredientForm.getId());
        recipeIngredient.setAmount(recipeIngredientForm.getAmount());
        recipeIngredient.setMeasurement(recipeIngredientForm.getMeasurement());
        recipeIngredient.setIngredient(recipeIngredientForm.getIngredient());

        return recipeIngredientDao.save(recipeIngredient);
    }

    @Override
    public RecipeIngredient findById(String id) {
        Optional<RecipeIngredient> recipeIngredient = recipeIngredientDao.findById(id);
        return recipeIngredient.orElseThrow(() -> new RuntimeException("recipe ingredient is not found!!"));
    }

    @Override
    public List<RecipeIngredient> findAll() {
        return recipeIngredientDao.findAll();
    }

    @Override
    public void delete(String id) {
        recipeIngredientDao.deleteById(id);
    }
}
