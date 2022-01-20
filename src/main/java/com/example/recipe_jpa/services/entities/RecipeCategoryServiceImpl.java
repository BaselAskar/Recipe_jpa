package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.data.DAO.RecipeCategoryDao;
import com.example.recipe_jpa.exception.NotFoundException;
import com.example.recipe_jpa.model.dto.view.RecipeCategoryDto;
import com.example.recipe_jpa.model.dto.form.RecipeCategoryFrom;
import com.example.recipe_jpa.model.entities.RecipeCategory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeCategoryServiceImpl implements RecipeCategoryService {

    private final RecipeCategoryDao recipeCategoryDao;
    private final ModelMapper mapper;

    @Autowired
    public RecipeCategoryServiceImpl(RecipeCategoryDao recipeCategoryDao, ModelMapper mapper) {
        this.recipeCategoryDao = recipeCategoryDao;
        this.mapper = mapper;
    }

    @Override
    public RecipeCategoryDto create(RecipeCategoryFrom recipeCategoryFrom) {
        if (recipeCategoryFrom == null) throw new IllegalArgumentException("recipe category is null!!");

        if (recipeCategoryDao.findById(recipeCategoryFrom.getId()).isPresent()){
            throw new IllegalArgumentException("recipe category has already existed!!");
        }

        RecipeCategory recipeCategory = mapper.map(recipeCategoryFrom,RecipeCategory.class);

        recipeCategory = recipeCategoryDao.save(recipeCategory);

        return mapper.map(recipeCategory,RecipeCategoryDto.class);
    }

    @Override
    public RecipeCategoryDto update(Integer id, RecipeCategoryFrom recipeCategoryFrom) {
        if (id <= 0 || recipeCategoryFrom == null) throw new IllegalArgumentException("the arguments is not correct!!");

        if (recipeCategoryFrom.getId() != id) throw new IllegalArgumentException("id  is not the same!!");

        if (!recipeCategoryDao.findById(id).isPresent()) throw new NotFoundException("recipe ingredient is not existed!!");

        RecipeCategory recipeCategory = mapper.map(recipeCategoryFrom,RecipeCategory.class);

        recipeCategory = recipeCategoryDao.save(recipeCategory);

        return mapper.map(recipeCategory,RecipeCategoryDto.class);
    }

    @Override
    public RecipeCategoryDto findById(Integer id) {
        if (id <= 0) throw new IllegalArgumentException("id  is not correct!!");

        Optional<RecipeCategory> recipeCategory = recipeCategoryDao.findById(id);

        if (recipeCategory.isPresent()){
            return mapper.map(recipeCategory.get(),RecipeCategoryDto.class);
        }else {
            throw new NotFoundException("recipe category is not found!!");
        }
    }

    @Override
    public List<RecipeCategoryDto> findAll() {
        return recipeCategoryDao.findAll().stream()
                .map(recipeCategory -> mapper.map(recipeCategory,RecipeCategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {
        if (id <= 0) throw new IllegalArgumentException("id  is not correct!!");

        recipeCategoryDao.deleteById(id);

        return !recipeCategoryDao.findById(id).isPresent();
    }
}
