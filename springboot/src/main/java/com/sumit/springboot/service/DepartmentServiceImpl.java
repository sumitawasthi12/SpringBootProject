package com.sumit.springboot.service;

import com.sumit.springboot.entity.Department;
import com.sumit.springboot.error.DepartmentNotFoundException;
import com.sumit.springboot.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements  DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department=departmentRepository.findById(departmentId);
        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Avaliable");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department dpDB=departmentRepository.findById(departmentId).get();
        if(Objects.nonNull(department.getDepartmentName()) &&
        !"".equalsIgnoreCase(department.getDepartmentName())){
            dpDB.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode())){
            dpDB.setDepartmentCode(department.getDepartmentCode());
        }
        if(Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress())){
            dpDB.setDepartmentAddress(department.getDepartmentAddress());
        }
        return departmentRepository.save(dpDB);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase( departmentName);
    }
}
