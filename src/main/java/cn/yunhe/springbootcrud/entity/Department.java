package cn.yunhe.springbootcrud.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity //声明实体类
@Table //声明和数据库表对应 默认实体类名
public class Department {
    @Id //声明主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) //声明主键的生成方式
	private Integer id;
	private String departmentName;
	//声明一对多  多的一方实体:实体名,对方实体承载的对象的名字 被映射的对象名字
	@OneToMany(targetEntity = Employee.class,mappedBy = "department")
    private Set<Employee> employeeSet = new HashSet<Employee>();

	public Set<Employee> getEmployeeSet() {
		return employeeSet;
	}

	public void setEmployeeSet(Set<Employee> employeeSet) {
		this.employeeSet = employeeSet;
	}

	public Department() {
	}
	
	public Department(int i, String string) {
		this.id = i;
		this.departmentName = string;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + "]";
	}
	
}
