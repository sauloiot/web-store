package com.saulo.webstore.services;

import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.models.Produto;
import com.saulo.webstore.repositories.ProdutoRepository;
import com.saulo.webstore.services.exceptions.DataIntegrityException;
import com.saulo.webstore.services.exceptions.ObjectNotFoundException;
import com.saulo.webstore.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        produto.setCodigo(Utils.code5L7N());
        return produtoRepository.save(produto);
    }

    public Produto update(Produto produto){
        findById(produto.getId());
        String cod = findById(produto.getId()).getCodigo();
        if (produto.getCategoria() == null){
            Categoria categoria = findById(produto.getId()).getCategoria();
            produto.setCategoria(categoria);
        }
        produto.setCodigo(cod);
        return produtoRepository.save(produto);
    }

    public void deleteById(Integer id){
        findById(id);
        try {
            produtoRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir este produto, ele está associado a pedidos!");
        }
    }
}
