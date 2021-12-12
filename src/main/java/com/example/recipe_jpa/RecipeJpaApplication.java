package com.example.recipe_jpa;

import com.example.recipe_jpa.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RecipeJpaApplication {

    public static void main(String[] args) {

        SpringApplication.run(RecipeJpaApplication.class, args);
    }

}
