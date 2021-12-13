package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.data.DAO.IngredientDao;
import com.example.recipe_jpa.model.dto.form.IngredientForm;
import com.example.recipe_jpa.model.entities.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IngredientCreationImpl implements IngredientCreation{



    private IngredientDao ingredientDao;

    @Autowired
    public IngredientCreationImpl(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Override
    public Ingredient save(IngredientForm ingredientForm) {
        Ingredient ingredient = new Ingredient();

        ingredient.setId(ingredientForm.getId());
        ingredient.setIngredientName(ingredientForm.getIngredientName());

        return ingredientDao.save(ingredient);
    }

    @Override
    public Ingredient findById(int id) {
        return ingredientDao.findById(id).isPresent() ? ingredientDao.findById(id).get() : null;
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredientDao.findAll();
    }

    @Override
    public void delete(int id) {
        ingredientDao.deleteById(id);
    }
}
