package com.example.windserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.windserver.common.R;
import com.example.windserver.domain.Employee;
import com.example.windserver.service.EmployeeService;
import com.example.windserver.utils.PasswordEncryptionUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        // 查询数据库，获取当前用户信息
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        // 如果查询不到，返回登录失败结果对象
        if (emp == null) {
            return R.error("查询不到该用户");
        }
        // 对用户输入密码进行SHA-256加密
        String password = employee.getPassword();
        // 如果查询到的密码和用户输入密码不匹配，就返回登录失败
        if (!PasswordEncryptionUtil.isPasswordMatching(password, emp.getPassword(), emp.getSalt())) {
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

    @PostMapping
    public R<String> addEmployee(HttpServletRequest request, @RequestBody Employee employee) {
        final String salt = PasswordEncryptionUtil.generateSalt();
        employee.setPassword(PasswordEncryptionUtil.hashPassword("123456", salt));
        employee.setSalt(salt);
        final Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        employeeService.save(employee);
        return R.success("新增员工成功");
    }

    @GetMapping("/page")
    public R<Page<Employee>> page(int page, int pageSize, String name) {
        Page<Employee> pageInfo = new Page<>(page, pageSize);
        final LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        final Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateUser(empId);
        employeeService.updateById(employee);
        return R.success("修改员工信息成功");
    }
}
