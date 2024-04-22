package com.ikm.assessment.service;

import com.ikm.assessment.common.contract.CommonResponse;
import com.ikm.assessment.common.exceptionHandler.NotFoundException;
import com.ikm.assessment.contract.EmployeeDetailsRequest;
import com.ikm.assessment.contract.EmployeeDetailsResponse;
import com.ikm.assessment.model.EmployeeDetails;
import com.ikm.assessment.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private  final ModelMapper modelMapper = new ModelMapper();

    public CommonResponse saveOrUpdateEmployeeDetails(EmployeeDetailsRequest request, boolean isUpdate){
        EmployeeDetails employeeDetails;
        if(isUpdate) {

            employeeDetails = employeeRepository.findById(request.getEmployeeId())
                    .orElseThrow(
                            () -> new IllegalArgumentException("id not fount" + request.getEmployeeId())
                    );

        }
        else {
            employeeDetails=new EmployeeDetails();
        }
        employeeDetails = modelMapper.map(request,EmployeeDetails.class);
        employeeRepository.save(employeeDetails);
        return CommonResponse.builder()
                .id(employeeDetails.getEmployeeId())
                .name(employeeDetails.getEmployeeName())
                .build();

    }

    public EmployeeDetailsResponse getEmployeeById(UUID employeeId) {
        return mapToResponse(
                employeeRepository
                        .findById(employeeId)
                        .orElseThrow(() -> new NotFoundException(employeeId)));
    }

    private EmployeeDetailsResponse mapToResponse(EmployeeDetails employeeDetails) {
        EmployeeDetailsResponse response =
                modelMapper.map(employeeDetails, EmployeeDetailsResponse.class);

        return response;
    }

    public List<EmployeeDetails> getSalariesBelow10000() {
        return employeeRepository.findAllBySalaryLessThan(10000);

    }

    public List<EmployeeDetails> findSalariesBetween20000And25000() {
        return employeeRepository.findBySalaryBetween(20000, 25000);

    }

    public List<EmployeeDetails> findSalariesAbove25000() {
        return employeeRepository.findBySalaryGreaterThan(25000);
    }
}
