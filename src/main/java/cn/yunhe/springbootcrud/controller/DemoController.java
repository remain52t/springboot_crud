package cn.yunhe.springbootcrud.controller;

import cn.yunhe.springbootcrud.dao.DepartmentRepository;
import cn.yunhe.springbootcrud.dao.EmployeeRepository;
import cn.yunhe.springbootcrud.entity.Department;
import cn.yunhe.springbootcrud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class DemoController {
    @Autowired
    private EmployeeRepository employeeDao;

    @Autowired
    private DepartmentRepository departmentDao;

    //  访问模板
    //1.首页
    @RequestMapping({"/", "/index"})
    public String index() {

        return "login"; //使用的是thymeleaf的模板引擎.在thymeleafProperties中已经定义了前缀和后缀

    }

    //2.登录
    @RequestMapping("/login")
    public String login(String username, String password, Model model, HttpSession httpSession) {


        if (username.equals("admin") && password.equals("123")) {
            //登录成功 防止表单重复提交 重定向到登录成功页面
            //将用户信息保存到session
            httpSession.setAttribute("user", username);
            //重定向 发送main请求.这个在webMvc扩展里配置了 MyMVCConfig
            return "redirect:/main";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }


    }
    //3.在crud案例中,使用restful风格.相同的访问路径,根据请求方式不同,获得的资源不同

    /**
     * 请求方式不同:
     * 添加--POST
     * 查询--GET
     * 修改--PUT
     * 删除--DELETE
     */
    //4查询所有员工
    //GetMapping:点进去可以发现 这就是将RequestMapping method=GET
    @GetMapping("/emps")
    public String getEmps(Model model) {
        Collection<Employee> empList = employeeDao.findAll();
        model.addAttribute("empList", empList);
        return "list";

    }

    //跳转到添加的页面
    @GetMapping("/emp")
    public String addPage(Model model) {
        //查询所有部门 放入model
        Collection<Department> departments = departmentDao.findAll();
        model.addAttribute("departments", departments);
        return "add";
    }

    //5.员工的添加
    @PostMapping("/emp")
    public String save(Employee employee) {
        employeeDao.save(employee);
        //重定向 发出查询请求
        return "redirect:/emps";
    }

    //6.修改页面 接收路径中的参数
    @GetMapping("/emp/{id}")
    public String updatePage(@PathVariable Integer id, Model model) {
        //查询
        Employee employee = employeeDao.findById(id).get();
        //存入域对象
        model.addAttribute("emp", employee);
        //因为回显需要显示5个部门 而不是单独查询的一个部门.所以部门那里要从这里取出数据
        Collection<Department> departments = departmentDao.findAll();
        model.addAttribute("departments", departments);
        //返回到add页面
        return "add";
    }

    //7.修改 请求还是/emp 只是方法不同
    @PutMapping("/emp")
    public String update(Employee employee) {
        employeeDao.save(employee);
        //重定向到查询
        return "redirect:/emps";

    }

    //8.删除
    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable Integer id) {
        employeeDao.deleteById(id);
        return "redirect:/emps";
    }


}
