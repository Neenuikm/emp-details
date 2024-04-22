package com.ikm.assessment.controller;


import com.ikm.assessment.common.contract.CommonResponse;
import com.ikm.assessment.common.contract.Response;
import com.ikm.assessment.contract.EmployeeDetailsRequest;
import com.ikm.assessment.contract.EmployeeDetailsResponse;
import com.ikm.assessment.model.EmployeeDetails;
import com.ikm.assessment.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assessment")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @SneakyThrows
    @Operation(summary = "Save employee Details request")
    @PostMapping(value = {"/save-employee-details"})
    public ResponseEntity<?> saveEmployeeDetails(@Valid @RequestBody EmployeeDetailsRequest request){
        CommonResponse employeeDetailsResponse =
                employeeService.saveOrUpdateEmployeeDetails(request,
                        false);
        return new ResponseEntity<>(
                Response.<CommonResponse>builder()
                        .payload(employeeDetailsResponse)
                        .message("Saved Successfully")
                        .build(),
                HttpStatus.CREATED);
    }
    @SneakyThrows
    @Operation(summary = "Update employee Details request")
    @PutMapping(value = {"/save-employee-details"})
    public ResponseEntity<?> updateEmployeeDetails(@Valid @RequestBody EmployeeDetailsRequest request){
        CommonResponse employeeDetailsResponse =
                employeeService.saveOrUpdateEmployeeDetails(request,
                        true);
        return new ResponseEntity<>(
                Response.<CommonResponse>builder()
                        .payload(employeeDetailsResponse)
                        .message("update Successfully")
                        .build(),
                HttpStatus.CREATED);
    }

    @GetMapping("/fetch-by-id/{employeeId}")
    public ResponseEntity<Response<EmployeeDetailsResponse>> getBirthApplicationById(
            @PathVariable("employeeId") UUID employeeId) {
        return new ResponseEntity<>(
                Response.<EmployeeDetailsResponse>builder()
                        .payload(employeeService.getEmployeeById(employeeId))
                        .message("record fetched successfully.")
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/below10000")
    public List<EmployeeDetails> getSalariesBelow10000() {
        return employeeService.getSalariesBelow10000();
    }

    @GetMapping("/between10000and20000")
    public List<EmployeeDetails> getSalariesBetween10000And20000() {
        return employeeService.findSalariesBetween20000And25000();
    }

    @GetMapping("/above25000")
    public List<EmployeeDetails> getSalariesAbove25000() {
        return employeeService.findSalariesAbove25000();
    }
}
