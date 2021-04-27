package com.saulo.webstore.services.validation;

import com.saulo.webstore.controllers.exceptions.FieldMessage;
import com.saulo.webstore.dtos.ContaDTO;
import com.saulo.webstore.models.Conta;
import com.saulo.webstore.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ContaInsertValidator implements ConstraintValidator<ContaInsert, ContaDTO> {
    @Autowired
    private ContaRepository contaRepository;

    @Override
    public void initialize(ContaInsert ann) {
    }

    @Override
    public boolean isValid(ContaDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Conta conta = contaRepository.findByEmail(objDto.getEmail());
        if (conta != null) {
            list.add( new FieldMessage("email", "Este Email ja está cadastrado no sistema"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}