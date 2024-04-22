package com.ikm.assessment.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDetailsResponse {

    private UUID employeeId;
    private String employeeName;
    private Double salary;

}
