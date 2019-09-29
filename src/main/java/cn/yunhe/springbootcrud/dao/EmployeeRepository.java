package cn.yunhe.springbootcrud.dao;

import cn.yunhe.springbootcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
