package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.data.DAO.RecipeCategoryDao;
import com.example.recipe_jpa.model.dto.form.RecipeCategoryFrom;
import com.example.recipe_jpa.model.entities.RecipeCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeCategoryCreationImpl implements RecipeCategoryCreation{

    private RecipeCategoryDao recipeCategoryDao;

    @Autowired
    public RecipeCategoryCreationImpl(RecipeCategoryDao recipeCategoryDao) {
        this.recipeCategoryDao = recipeCategoryDao;
    }

    @Override
    public RecipeCategory save(RecipeCategoryFrom recipeCategoryFrom) {
        RecipeCategory recipeCategory = new RecipeCategory();

        recipeCategory.setId(recipeCategory.getId());
        recipeCategory.setCategory(recipeCategory.getCategory());

        return recipeCategoryDao.save(recipeCategory);
    }

    @Override
    public RecipeCategory findById(int id) {
        Optional<RecipeCategory> recipeCategory = recipeCategoryDao.findById(id);
        return recipeCategory.orElseThrow(() -> new RuntimeException("recipe category not found!"));
    }

    @Override
    public List<RecipeCategory> findAll() {
        return recipeCategoryDao.findAll();
    }

    @Override
    public void delete(int id) {
        recipeCategoryDao.deleteById(id);
    }
}
