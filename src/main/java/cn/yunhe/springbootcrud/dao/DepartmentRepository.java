package cn.yunhe.springbootcrud.dao;

import cn.yunhe.springbootcrud.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
