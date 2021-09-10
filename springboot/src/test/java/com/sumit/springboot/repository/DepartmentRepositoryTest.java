package com.sumit.springboot.repository;

import com.sumit.springboot.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    @BeforeEach
    void setUp() {

        Department department=Department
                .builder()
                .departmentName("Computer Department")
                .departmentCode("CE-011")
                .departmentAddress("Delhi")
                .build();
        testEntityManager.persist(department);
    }
    @Test
    public void whenFindById_thenReturnDepartment(){
        Department department=departmentRepository.findById(1L).get();
        assertEquals(department.getDepartmentName(),"Computer Department");
    }
}