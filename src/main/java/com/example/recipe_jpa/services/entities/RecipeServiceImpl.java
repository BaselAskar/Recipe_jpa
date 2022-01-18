package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.data.DAO.RecipeDao;
import com.example.recipe_jpa.model.dto.facade.RecipeCategoryDto;
import com.example.recipe_jpa.model.dto.facade.RecipeDTO;
import com.example.recipe_jpa.model.dto.form.RecipeForm;
import com.example.recipe_jpa.model.entities.Recipe;
import com.example.recipe_jpa.model.entities.RecipeCategory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService{


    private final RecipeDao recipeDao;
    private final ModelMapper mapper;

    @Autowired
    public RecipeServiceImpl(RecipeDao recipeDao, ModelMapper mapper) {
        this.recipeDao = recipeDao;
        this.mapper = mapper;
    }

    @Override
    public RecipeDTO create(RecipeForm recipeForm) {
        if (recipeForm == null) throw new IllegalArgumentException("recipe category is null!!");

        if (recipeDao.findById(recipeForm.getId()).isPresent()){
            throw new IllegalArgumentException("recipe has already existed!!");
        }

        Recipe recipe = mapper.map(recipeForm,Recipe.class);

        recipe = recipeDao.save(recipe);

        return mapper.map(recipe, RecipeDTO.class);
    }

    @Override
    public RecipeDTO update(Integer id, RecipeForm recipeForm) {
        if (id <= 0 || recipeForm == null) throw new IllegalArgumentException("the arguments is not correct!!");

        if (recipeForm.getId() != id) throw new IllegalArgumentException("id  is not the same!!");

        if (!recipeDao.findById(id).isPresent()) throw new IllegalArgumentException("recipe is not existed!!");

        Recipe recipe = mapper.map(recipeForm,Recipe.class);

        recipe = recipeDao.save(recipe);

        return mapper.map(recipe,RecipeDTO.class);
    }

    @Override
    public RecipeDTO findById(Integer id) {
        if (id <= 0) throw new IllegalArgumentException("id  is not correct!");

        Optional<Recipe> recipe = recipeDao.findById(id);

        return mapper.map(recipe.get(),RecipeDTO.class);
    }

    @Override
    public List<RecipeDTO> findAll() {
        return recipeDao.findAll().stream()
                .map(recipe -> mapper.map(recipe,RecipeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {
        if (id <= 0) throw new IllegalArgumentException("id is not allowed!!");

        recipeDao.deleteById(id);

        return !recipeDao.findById(id).isPresent();
    }
}
