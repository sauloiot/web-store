package com.saulo.webstore.services;

import com.saulo.webstore.models.Cupom;
import com.saulo.webstore.repositories.CupomRepository;
import com.saulo.webstore.services.exceptions.DataIntegrityException;
import com.saulo.webstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CupomService {

    @Autowired
    private CupomRepository cupomRepository;

    public Cupom findByCodigo(String codigo){

            Cupom obj = cupomRepository.findByCodigo(codigo);
            if (obj == null){
               throw new ObjectNotFoundException(
                        "Cupom não encontrado! Código: " + codigo + ", Tipo: " + Cupom.class.getSimpleName());
            }
            return obj;


    }

    public Cupom findById(Integer id) {
        Optional<Cupom> obj = cupomRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Cupom não encontrado! Id: " + id + ", Tipo: " + Cupom.class.getSimpleName()));
    }

    public List<Cupom> findAll(){
        return cupomRepository.findAll();
    }

    public Cupom insert(Cupom cupom){
        cupom.setId(null);
        return cupomRepository.save(cupom);
    }

    public Cupom update(Cupom cupom){
        findById(cupom.getId());
        return cupomRepository.save(cupom);
    }

    public void deleteById(Integer id){
        findById(id);
        try {
            cupomRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir este Cupom!");
        }
    }
}
