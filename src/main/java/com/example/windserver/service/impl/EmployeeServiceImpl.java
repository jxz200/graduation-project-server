package com.example.windserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.windserver.domain.Employee;
import com.example.windserver.service.EmployeeService;
import com.example.windserver.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author 江小舟
* @description 针对表【employee(员工信息)】的数据库操作Service实现
* @createDate 2023-03-11 17:11:15
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}
