package com.example.demo.controller;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //返回所有员工的列表
    @GetMapping("/emps")
    public String list(ModelMap modelMap) {

        Collection<Employee> list = employeeDao.getAll();
        modelMap.addAttribute("emps", list);


        //thymeleaf默认拼接： classpath:/templates/ .html
        return "emp/list";
    }

    //来到添加员工页面
    @GetMapping("/emp")
    public String addEmployeePage(Model model) {
        //获取部门内容
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        return "emp/add";
    }

    //添加员工
    //springMVC会自动将请求和入参对象进行一一绑定
    @PostMapping("/emp")
    public String addEmployee(Employee employee) {
        System.out.println(employee);
        employeeDao.save(employee);

        //来到员工列表页面
        //redirect:表示重定向  /代表当前项目路径
        //forward:表示转发到一个地址
        return "redirect:/emps";
    }

    //修改页面显示
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        //回到修改页面（add是一个修改添加二合一页面）
        return "emp/add";
    }

    //修改员工信息
    @PutMapping("/emp")
    public String updateEmployee(Employee employee) {

        employeeDao.save(employee);
        return "redirect:/emps";
    }


    //删除
    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable("id")Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
