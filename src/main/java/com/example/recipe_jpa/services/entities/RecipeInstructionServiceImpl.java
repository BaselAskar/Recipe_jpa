package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.data.DAO.RecipeInstructionDao;
import com.example.recipe_jpa.model.dto.facade.RecipeInstructionDto;
import com.example.recipe_jpa.model.dto.form.RecipeInstructionForm;
import com.example.recipe_jpa.model.entities.RecipeInstruction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeInstructionServiceImpl implements RecipeInstructionService{


    private final RecipeInstructionDao recipeInstructionDao;
    private final ModelMapper mapper;

    @Autowired
    public RecipeInstructionServiceImpl(RecipeInstructionDao recipeInstructionDao, ModelMapper mapper) {
        this.recipeInstructionDao = recipeInstructionDao;
        this.mapper = mapper;
    }

    @Override
    public RecipeInstructionDto create(RecipeInstructionForm recipeInstructionForm) {
        if (recipeInstructionForm == null) throw new IllegalArgumentException("recipe instruction is null!!");

        if (recipeInstructionDao.findById(recipeInstructionForm.getId()).isPresent()) {
            throw new IllegalArgumentException("recipe instruction has already existed!");
        }

        RecipeInstruction recipeInstruction = mapper.map(recipeInstructionForm,RecipeInstruction.class);

        recipeInstruction = recipeInstructionDao.save(recipeInstruction);

        return mapper.map(recipeInstruction,RecipeInstructionDto.class);
    }

    @Override
    public RecipeInstructionDto update(String id, RecipeInstructionForm recipeInstructionForm) {
        if (id == null || recipeInstructionForm == null) throw new IllegalArgumentException("the arguments is not correct!!");

        if (recipeInstructionForm.getId().equals(id)) throw new IllegalArgumentException("id  is not the same!!");

        if (!recipeInstructionDao.findById(id).isPresent()) throw new IllegalArgumentException("recipe ingredient is not existed!!");

        RecipeInstruction recipeInstruction = mapper.map(recipeInstructionForm,RecipeInstruction.class);

        recipeInstruction = recipeInstructionDao.save(recipeInstruction);

        return mapper.map(recipeInstruction, RecipeInstructionDto.class);

    }

    @Override
    public RecipeInstructionDto findById(String id) {
        if (id == null) throw new IllegalArgumentException("id is not correct!!");

        Optional<RecipeInstruction> recipeInstruction = recipeInstructionDao.findById(id);

        if (recipeInstruction.isPresent()){
            return (mapper.map(recipeInstruction.get(),RecipeInstructionDto.class));
        }else {
            throw new IllegalArgumentException("recipe instruction is not found!!");
        }
    }

    @Override
    public List<RecipeInstructionDto> findAll() {
        return recipeInstructionDao.findAll().stream()
                .map(recipeInstruction -> mapper.map(recipeInstruction,RecipeInstructionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(String id) {
        if (id == null) throw new IllegalArgumentException("id  is null!!");

        recipeInstructionDao.deleteById(id);

        return !recipeInstructionDao.findById(id).isPresent();
    }
}
