package com.example.windserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.windserver.common.R;
import com.example.windserver.domain.Employee;
import com.example.windserver.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        // 对用户输入密码进行md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 查询数据库，获取当前用户信息
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        // 如果查询不到，返回登录失败结果对象
        if (emp == null) {
            return R.error("查询不到该用户");
        }
        // 如果查询到的密码和用户输入密码不匹配，就返回登录失败
        if (!emp.getPassword().equals(password)) {
            return R.error("用户名密码不匹配");
        }
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用");
        }
        // 将id存入session，返回查询结果
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        // 删除session
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
}
