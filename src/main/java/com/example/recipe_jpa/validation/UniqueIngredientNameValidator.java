package com.example.recipe_jpa.validation;

import com.example.recipe_jpa.data.DAO.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueIngredientNameValidator implements ConstraintValidator<UniqueIngredientName,String> {

    private final IngredientDao ingredientDao;

    @Autowired
    public UniqueIngredientNameValidator(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null){
            return true;
        }

        return ingredientDao.findByIngredientName(value) != null;
    }
}
