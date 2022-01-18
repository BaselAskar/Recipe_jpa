package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.data.DAO.RecipeIngredientDao;
import com.example.recipe_jpa.model.dto.facade.RecipeCategoryDto;
import com.example.recipe_jpa.model.dto.facade.RecipeIngredientDTO;
import com.example.recipe_jpa.model.dto.form.RecipeIngredientForm;
import com.example.recipe_jpa.model.entities.Recipe;
import com.example.recipe_jpa.model.entities.RecipeCategory;
import com.example.recipe_jpa.model.entities.RecipeIngredient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService{

    private final RecipeIngredientDao recipeIngredientDao;
    private final ModelMapper mapper;

    @Autowired
    public RecipeIngredientServiceImpl(RecipeIngredientDao recipeIngredientDao, ModelMapper mapper) {
        this.recipeIngredientDao = recipeIngredientDao;
        this.mapper = mapper;
    }

    @Override
    public RecipeIngredientDTO create(RecipeIngredientForm recipeIngredientForm) {
        if (recipeIngredientForm == null) throw new IllegalArgumentException("recipe ingredient is null!!");

        if (recipeIngredientDao.findById(recipeIngredientForm.getId()).isPresent()) {
            throw new IllegalArgumentException("recipe ingredient has already existed!!");
        }

        RecipeIngredient recipeIngredient = mapper.map(recipeIngredientForm,RecipeIngredient.class);

        recipeIngredient = recipeIngredientDao.save(recipeIngredient);

        return mapper.map(recipeIngredient,RecipeIngredientDTO.class);

    }

    @Override
    public RecipeIngredientDTO update(String id, RecipeIngredientForm recipeIngredientForm) {
        if (id == null || recipeIngredientForm == null) throw new IllegalArgumentException("the arguments is not correct!!");

        if (!recipeIngredientForm.getId().equals(id)) throw new IllegalArgumentException("id  is not the same!!");

        if (!recipeIngredientDao.findById(id).isPresent()) throw new IllegalArgumentException("recipe ingredient is not existed!!");

        RecipeIngredient recipeIngredient = mapper.map(recipeIngredientForm,RecipeIngredient.class);

        recipeIngredient = recipeIngredientDao.save(recipeIngredient);

        return mapper.map(recipeIngredient, RecipeIngredientDTO.class);
    }

    @Override
    public RecipeIngredientDTO findById(String id) {
        if (id == null) throw new IllegalArgumentException("id  is null!!");

        Optional<RecipeIngredient> recipeIngredient = recipeIngredientDao.findById(id);

        if (recipeIngredient.isPresent()){
            return mapper.map(recipeIngredient.get(),RecipeIngredientDTO.class);
        }else {
            throw new IllegalArgumentException("recipe ingredient is not found!!");
        }
    }

    @Override
    public List<RecipeIngredientDTO> findAll() {
        return recipeIngredientDao.findAll().stream()
                .map(recipeIngredient -> mapper.map(recipeIngredient, RecipeIngredientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(String id) {
        if (id == null) throw new IllegalArgumentException("id must not be null!!");

        recipeIngredientDao.deleteById(id);

        return !recipeIngredientDao.findById(id).isPresent();
    }
}
