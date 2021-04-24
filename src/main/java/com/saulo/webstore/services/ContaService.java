package com.saulo.webstore.services;

import com.saulo.webstore.models.Conta;
import com.saulo.webstore.repositories.ContaRepository;
import com.saulo.webstore.repositories.ProdutoRepository;
import com.saulo.webstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta findById(Integer id) {
        Optional<Conta> obj = contaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Conta.class.getSimpleName()));
    }

    public List<Conta> findAll(){
        return contaRepository.findAll();
    }

    public Conta insert(Conta produto){
        produto.setId(null);
        return contaRepository.save(produto);
    }

    public Conta update(Conta produto){
        findById(produto.getId());
        return contaRepository.save(produto);
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
