package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.data.DAO.RecipeDao;
import com.example.recipe_jpa.exception.NotFoundException;
import com.example.recipe_jpa.model.dto.view.RecipeDTO;
import com.example.recipe_jpa.model.dto.form.RecipeForm;
import com.example.recipe_jpa.model.entities.Recipe;import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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

        if (!recipeDao.findById(id).isPresent()) throw new NotFoundException("recipe is not existed!!");

        Recipe recipe = mapper.map(recipeForm,Recipe.class);

        recipe = recipeDao.save(recipe);

        return mapper.map(recipe,RecipeDTO.class);
    }

    @Override
    public RecipeDTO findById(Integer id) {
        if (id <= 0) throw new IllegalArgumentException("id  is not correct!");

        Optional<Recipe> recipe = recipeDao.findById(id);

        if (recipe.isPresent()){
            return mapper.map(recipe.get(),RecipeDTO.class);
        }else {
            throw new NotFoundException("recipe has not found!!");
        }
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

    @Override
    public List<RecipeDTO> findByRecipeName(String recipeName) {
        if (recipeName == null) throw new IllegalArgumentException("recipe name is null!");

        return recipeDao.searchByRecipeName(recipeName).stream()
                .map(recipe -> mapper.map(recipe,RecipeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeDTO> findByIngredientName(String ingredientName) {
        if (ingredientName == null) throw new IllegalArgumentException("ingredient name is null!!");

        return recipeDao.searchByIngredientName(ingredientName).stream()
                .map(recipe -> mapper.map(recipe,RecipeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeDTO> findByCategory(String category) {
        if (category == null) throw new IllegalArgumentException("category is null");

        return recipeDao.searchByCategory(category).stream()
                .map(recipe -> mapper.map(recipe,RecipeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Set<RecipeDTO> findByCategories(String... categories) {
        if (categories == null) throw new IllegalArgumentException("categories is null");
        if (categories.length == 0) throw new IllegalArgumentException("There is no categories to search!!");

        return recipeDao.searchByAnyCategories(categories).stream()
                .map(recipe -> mapper.map(recipe,RecipeDTO.class))
                .collect(Collectors.toSet());
    }
}
