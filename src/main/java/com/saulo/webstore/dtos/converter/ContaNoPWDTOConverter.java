package com.saulo.webstore.dtos.converter;

import com.saulo.webstore.dtos.ContaNoPWDTO;
import com.saulo.webstore.models.Conta;

import java.util.List;
import java.util.stream.Collectors;

public class ContaNoPWDTOConverter {

    public static ContaNoPWDTO entityToDTO(Conta conta){
        ContaNoPWDTO dto = new ContaNoPWDTO();
        dto.setId(conta.getId());
        dto.setNome(conta.getNome());
        dto.setEmail(conta.getEmail());
        dto.setTipoConta(conta.getTipoConta());

        return dto;
    }

    public static List<ContaNoPWDTO> entityToDTOList(List<Conta> category){

        return category.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());
    }

    public static Conta dtoToEntity(ContaNoPWDTO contaNoPWDTO){
        Conta entity = new Conta();
        entity.setId(contaNoPWDTO.getId());
        entity.setNome(contaNoPWDTO.getNome());
        entity.setEmail(contaNoPWDTO.getEmail());
        entity.setTipoConta(contaNoPWDTO.getTipoConta());

        return entity;
    }

    public static List<Conta> dtoToEntityList(List<ContaNoPWDTO> categoryWSubCatDTO){
        return categoryWSubCatDTO.stream().map(x ->  dtoToEntity(x)).collect(Collectors.toList());
    }
}
