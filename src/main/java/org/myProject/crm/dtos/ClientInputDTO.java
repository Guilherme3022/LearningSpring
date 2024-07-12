package org.myProject.crm.dtos;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import org.myProject.crm.dtos.validations.Ssn;

@Data
public class ClientInputDTO {
private Integer live;

   //@Ssn
    private String ssn;

    private String fullName;
    private String email;

    private String birthDate;

    private String address;

    private String phoneNumber;

}
