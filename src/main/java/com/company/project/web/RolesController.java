package com.company.project.web;
import com.company.project.core.ResponseData;
import com.company.project.model.Roles;
import com.company.project.service.RolesService;
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
@RequestMapping("/roles")
public class RolesController {

    @Resource
    private RolesService rolesService;

    @PostMapping("/add")
    public ResponseData<String> add(Roles roles) {
        rolesService.save(roles);
        return ResponseData.successSign();
    }

    @PostMapping("/delete")
    public ResponseData<String> delete(@RequestParam Integer id) {
        rolesService.deleteById(id);
        return ResponseData.successSign();
    }

    @PostMapping("/update")
    public ResponseData<String> update(Roles roles) {
        rolesService.update(roles);
        return ResponseData.successSign();
    }

    @PostMapping("/detail")
    public ResponseData<Roles> detail(@RequestParam Integer id) {
        Roles roles = rolesService.findById(id);
        return ResponseData.success(roles);
    }

    @PostMapping("/list")
    public ResponseData<PageInfo<Roles>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        return ResponseData.success(new PageInfo<>(rolesService.findAll()));
    }
}
