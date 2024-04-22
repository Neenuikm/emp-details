package com.ikm.assessment.contract;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailsRequest {

    private UUID employeeId;

    @NotBlank(message = "name cannot be empty")
    private String employeeName;

    @NotNull(message = "salary cannot be empty")
    private Double salary;
}
