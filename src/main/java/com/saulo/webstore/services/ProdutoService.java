package com.saulo.webstore.services;

import com.saulo.webstore.models.Produto;
import com.saulo.webstore.repositories.ProdutoRepository;
import com.saulo.webstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto findById(Integer id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getSimpleName()));
    }

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto insert(Produto produto){
        produto.setId(null);
        return produtoRepository.save(produto);
    }

    public Produto update(Produto produto){
        findById(produto.getId());
        return produtoRepository.save(produto);
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
