package com.saulo.webstore.services;

import com.saulo.webstore.models.Pedido;
import com.saulo.webstore.repositories.PedidoRepository;
import com.saulo.webstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido findById(Integer id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getSimpleName()));
    }

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido insert(Pedido categoria){
        categoria.setId(null);
        return pedidoRepository.save(categoria);
    }

    public Pedido update(Pedido categoria){
        findById(categoria.getId());
        return pedidoRepository.save(categoria);
    }

//    public void deleteById(Integer category_id){
//        findById(category_id);
//        try {
//            pedidoRepository.deleteById(category_id);
//        }catch (DataIntegrityViolationException exception){
//            throw new DataIntegrityException("+-=Custom Error=-+ Not is possible delete category with sub-categories");
//        }
//    }
}
