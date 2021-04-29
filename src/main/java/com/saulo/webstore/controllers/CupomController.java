package com.saulo.webstore.controllers;

import com.saulo.webstore.dtos.CupomDTO;
import com.saulo.webstore.models.Cupom;
import com.saulo.webstore.services.CupomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cupom")
public class CupomController {

    @Autowired
    private CupomService cupomService;

    @RequestMapping( method=RequestMethod.GET)
    public ResponseEntity<List<Cupom>> findAll() {
        List<Cupom> list = cupomService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value="/codigo/{codigo}", method=RequestMethod.GET)
    public ResponseEntity<Cupom> findByCodigo(@PathVariable String codigo) {
        Cupom obj = cupomService.findByCodigo(codigo);

        return ResponseEntity.ok().body(obj);
    }


    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Cupom> findById(@PathVariable Integer id) {
        Cupom obj = cupomService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CupomDTO dto){
        Cupom obj = new Cupom();
        obj.setCodigo(dto.getCodigo());
        obj.setDesconto(dto.getDesconto());
        obj.setTipo(dto.getTipo());

        obj = cupomService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Cupom obj, @PathVariable Integer id ){
        obj.setId(id);
        obj = cupomService.update(obj);
        return ResponseEntity.noContent().build();

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        cupomService.deleteById(id);
        return ResponseEntity.noContent().build();

    }


}
