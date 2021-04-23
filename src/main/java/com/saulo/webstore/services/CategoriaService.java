package com.saulo.webstore.services;

import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElse(null);
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Categoria insert(Categoria obj){
//        obj.setCategory_id(null);
        return categoriaRepository.save(obj);
    }
}
