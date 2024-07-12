package org.myProject.crm.dtos.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SsnValidation implements ConstraintValidator<Ssn,String> {
    @Override
public boolean isValid(String ssn, ConstraintValidatorContext context) {
    if (ssn == null) {
        return false;
    }
    // Verifica se o SSN tem 11 caracteres no formato XXX-XX-XXXX
    if (!ssn.matches("\\d{3}-\\d{2}-\\d{4}")) {
        return false;
    }
    // Verifica se os primeiros três dígitos não são "000", "666", ou entre "900" e "999"
    String areaNumber = ssn.substring(0, 3);
    if (areaNumber.equals("000") || areaNumber.equals("666") || Integer.parseInt(areaNumber) >= 900) {
        return false;
    }
    // Verifica se o grupo do meio não é "00"
    String groupNumber = ssn.substring(4, 6);
    if (groupNumber.equals("00")) {
        return false;
    }
    // Verifica se o último grupo não é "0000"
    String serialNumber = ssn.substring(7, 11);
    if (serialNumber.equals("0000")) {
        return false;
    }
    return true;
}
}
