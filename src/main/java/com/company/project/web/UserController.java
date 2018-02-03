package com.company.project.web;
import com.company.project.core.ResponseData;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* Created by CodeGenerator on 2018/02/03.
*/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public ResponseData<String> add(User user) {
        userService.save(user);
        return ResponseData.successSign();
    }

    @PostMapping("/delete")
    public ResponseData<String> delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResponseData.successSign();
    }

    @PostMapping("/update")
    public ResponseData<String> update(User user) {
        userService.update(user);
        return ResponseData.successSign();
    }

    @PostMapping("/detail")
    public ResponseData<User> detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        return ResponseData.success(user);
    }

    @PostMapping("/list")
    public ResponseData<PageInfo<User>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        return ResponseData.success(new PageInfo<>(userService.findAll()));
    }
}
