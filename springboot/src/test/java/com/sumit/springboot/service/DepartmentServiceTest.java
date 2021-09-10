package com.sumit.springboot.service;

import com.sumit.springboot.entity.Department;
import com.sumit.springboot.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;
    @BeforeEach
    void setUp() {

        Department department=
                Department.builder()
                .departmentName("IT")
                .departmentAddress("Hydrabad")
                .departmentCode("IT-5")
                .departmentId(1L)
                .build();
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
                .thenReturn(department);
    }
    @Test
    @DisplayName("Get Data based on the Valid Department Name")
    public void whenDepartmentName_thenDepartmentShouldFound(){
       String departmentName="IT";
        Department found= departmentService.
                fetchDepartmentByName(departmentName);
        assertEquals(departmentName,found.getDepartmentName());
    }
}