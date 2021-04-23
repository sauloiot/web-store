package com.saulo.webstore.services;

import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElse(null);
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Categoria insert(Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria){
        findById(categoria.getId());
        return categoriaRepository.save(categoria);
    }

//    public void deleteById(Integer category_id){
//        findById(category_id);
//        try {
//            categoriaRepository.deleteById(category_id);
//        }catch (DataIntegrityViolationException exception){
//            throw new DataIntegrityException("+-=Custom Error=-+ Not is possible delete category with sub-categories");
//        }
//    }
}