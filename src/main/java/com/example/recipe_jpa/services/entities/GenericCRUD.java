package com.example.recipe_jpa.services.entities;

import java.util.List;

public interface GenericCRUD <T,R,TID>{

    R create(T t);
    R update(TID id,T t);
    R findById(TID id);
    List<R> findAll();
    boolean delete(TID id);

}
