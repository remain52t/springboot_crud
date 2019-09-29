package cn.yunhe.springbootcrud.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
@Entity //声明实体类
@Table  //声明和数据库表映射
public class Employee {
    @Id //声明主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//声明主键生成方式
	private Integer id;
    private String lastName;

    private String email;
    //1 male, 0 female
    private Integer gender;
    //声明一对多  外键在多的一方.外键就由多的一方维护 所以没有mappedBy
    @ManyToOne(targetEntity = Department.class)//声明一的实体类
    @JoinColumn(name = "d_id")//声明外键的名字 用来指定与所操作实体或实体集合相关联的数据库表中的列字段。
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//级联保存 添加修改时 自动修改另一张表
    private Department department;
    /**
     * 级联在编写触发器时经常用到，触发器的作用是当 主控表信息改变时，用来保证其关联表中数据同步更新。
     * 若对触发器来修改或删除关联表相记录，必须要删除对应的关联表信息，否则，会存有脏数据。
     * 所以，适当的做法是，删除主表的同时，关联表的信息也要同时删除，在hibernate中，只需设置cascade属性值即可。
     * */
    private Date birth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public Employee(Integer id, String lastName, String email, Integer gender,
                    Department department) {
        super();
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.birth = new Date();
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", department=" + department +
                ", birth=" + birth +
                '}';
    }
	
	
}
