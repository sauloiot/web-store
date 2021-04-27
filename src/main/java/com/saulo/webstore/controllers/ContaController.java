package com.saulo.webstore.controllers;

import com.saulo.webstore.dtos.CategoriaDTO;
import com.saulo.webstore.dtos.ContaDTO;
import com.saulo.webstore.dtos.ContaNoPWDTO;
import com.saulo.webstore.dtos.converter.CategoriaDTOConverter;
import com.saulo.webstore.dtos.converter.ContaDTOConverter;
import com.saulo.webstore.dtos.converter.ContaNoPWDTOConverter;
import com.saulo.webstore.models.Conta;
import com.saulo.webstore.models.Conta;
import com.saulo.webstore.services.CategoriaService;
import com.saulo.webstore.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<ContaNoPWDTO> findById(@PathVariable Integer id) {
        Conta obj = contaService.findById(id);
        ContaNoPWDTO dto = ContaNoPWDTOConverter.entityToDTO(obj);

        return ResponseEntity.ok().body(dto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ContaDTO dto){
        Conta obj = ContaDTOConverter.dtoToEntity(dto);
        obj = contaService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping( method=RequestMethod.GET)
    public ResponseEntity<List<ContaNoPWDTO>> findAll() {
        List<Conta> list = contaService.findAll();
        List<ContaNoPWDTO> dto = ContaNoPWDTOConverter.entityToDTOList(list);
        return ResponseEntity.ok().body(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Conta obj, @PathVariable Integer id ){
        obj.setId(id);
        obj = contaService.update(obj);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        contaService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}
