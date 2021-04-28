package com.saulo.webstore.dtos.converter;

import com.saulo.webstore.dtos.ContaDTO;
import com.saulo.webstore.models.Conta;

import java.util.List;
import java.util.stream.Collectors;

public class ContaDTOConverter {

    public static ContaDTO entityToDTO(Conta conta){
        ContaDTO dto = new ContaDTO();
        dto.setId(conta.getId());
        dto.setNome(conta.getNome());
        dto.setEmail(conta.getEmail());
        dto.setSenha(conta.getSenha());
        dto.setTipoConta(conta.getTipoConta());

        return dto;
    }

    public static List<ContaDTO> entityToDTOList(List<Conta> category){

        return category.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());
    }

    public static Conta dtoToEntity(ContaDTO contaDTO){
        Conta entity = new Conta();
        entity.setId(contaDTO.getId());
        entity.setNome(contaDTO.getNome());
        entity.setEmail(contaDTO.getEmail());
        entity.setSenha(contaDTO.getSenha());
        entity.setTipoConta(contaDTO.getTipoConta());

        return entity;
    }

    public static List<Conta> dtoToEntityList(List<ContaDTO> categoryWSubCatDTO){
        return categoryWSubCatDTO.stream().map(x ->  dtoToEntity(x)).collect(Collectors.toList());
    }
}
