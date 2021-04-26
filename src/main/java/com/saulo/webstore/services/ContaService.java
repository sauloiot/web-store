package com.saulo.webstore.services;

import com.saulo.webstore.models.Conta;
import com.saulo.webstore.repositories.ContaRepository;
import com.saulo.webstore.services.exceptions.DataIntegrityException;
import com.saulo.webstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Conta insert(Conta conta){
        conta.setId(null);
        return contaRepository.save(conta);
    }

    public Conta update(Conta conta){
        findById(conta.getId());
        return contaRepository.save(conta);
    }

    public void deleteById(Integer id){
        findById(id);
        try {
            contaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma conta com pedidos");
        }
    }
}
