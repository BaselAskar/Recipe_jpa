package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.data.DAO.IngredientDao;
import com.example.recipe_jpa.model.dto.facade.IngredientDto;
import com.example.recipe_jpa.model.dto.form.IngredientForm;
import com.example.recipe_jpa.model.entities.Ingredient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredientDao ingredientDao;
    private final ModelMapper mapper;

    @Autowired
    public IngredientServiceImpl(IngredientDao ingredientDao, ModelMapper mapper) {
        this.ingredientDao = ingredientDao;
        this.mapper = mapper;
    }

    @Override
    public IngredientDto create(IngredientForm ingredientForm) {
        if (ingredientForm == null) throw new IllegalArgumentException("ingredient is null!!");

        if (!ingredientDao.findById(ingredientForm.getId()).isPresent()) throw new IllegalArgumentException("ingredient is already existed!!");

        Ingredient ingredient = mapper.map(ingredientForm,Ingredient.class);

        ingredient = ingredientDao.save(ingredient);

        return mapper.map(ingredient,IngredientDto.class);

    }

    @Override
    public IngredientDto update(Integer id, IngredientForm ingredientForm) {
        if (id == null || id <= 0 ||ingredientForm == null) throw new IllegalArgumentException("the arguments is not right!!");

        if (ingredientForm.getId() != id) throw new IllegalArgumentException("Id is not the same!!");

        if (ingredientDao.findById(id).isPresent()) throw new IllegalArgumentException("the ingredient is not found!!");

        Ingredient ingredient = mapper.map(ingredientForm,Ingredient.class);

        ingredient = ingredientDao.save(ingredient);

        return mapper.map(ingredient,IngredientDto.class);
    }

    @Override
    public IngredientDto findById(Integer id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("id is not right!!");

        Optional<Ingredient> ingredient = ingredientDao.findById(id);

        if (ingredient.isPresent()){
            return mapper.map(ingredient.get(),IngredientDto.class);
        }else {
            throw new IllegalArgumentException("ingredient is not found!!");
        }
    }

    @Override
    public List<IngredientDto> findAll() {
        return ingredientDao.findAll().stream()
                .map(ingredient -> mapper.map(ingredient,IngredientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("id is not allowed!!");

        ingredientDao.deleteById(id);

        return !ingredientDao.findById(id).isPresent();
    }
}