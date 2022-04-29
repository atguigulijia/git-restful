package com.atguigu.mvc.controller;

import com.atguigu.mvc.bean.Employee;
import com.atguigu.mvc.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * @author lijia
 * @create 2022-04-04 22:21
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    //查看所有员工信息
    @GetMapping("/employee")
    public ModelAndView getAllEmployees(ModelAndView modelAndView){
        Collection<Employee> employees = employeeDao.getAll();
        modelAndView.addObject("employee_List",employees);
        modelAndView.setViewName("employee_list");
        return modelAndView;
    }
    //根据id查询员工
    @GetMapping("/employee/{id}")
    public ModelAndView getEmployeeByID(@PathVariable Integer id,ModelAndView modelAndView){
        Employee employee = employeeDao.get(id);
        //设置request域对象的共享信息
        modelAndView.addObject("employee",employee);
        //请求转发到employee_update.html
        modelAndView.setViewName("employee_update");
        return modelAndView;
    }
    //根据指定id删除员工信息
    @DeleteMapping("/employee/{id}")
    public String deleteByID(@PathVariable Integer id){
        employeeDao.delete(id);
        //重定向到员工信息表
        return "redirect:/employee";
    }
    //添加员工信息
    @PostMapping("/employee")
    public String addEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/employee";
    }
    //修改员工信息(需要回显当前员工信息)
    @PutMapping("/employee")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/employee";
    }
}
