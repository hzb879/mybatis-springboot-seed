package com.company.project.web;
import com.company.project.core.ResponseData;
import com.company.project.model.Permissions;
import com.company.project.service.PermissionsService;
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
@RequestMapping("/permissions")
public class PermissionsController {

    @Resource
    private PermissionsService permissionsService;

    @PostMapping("/add")
    public ResponseData<String> add(Permissions permissions) {
        permissionsService.save(permissions);
        return ResponseData.successSign();
    }

    @PostMapping("/delete")
    public ResponseData<String> delete(@RequestParam Integer id) {
        permissionsService.deleteById(id);
        return ResponseData.successSign();
    }

    @PostMapping("/update")
    public ResponseData<String> update(Permissions permissions) {
        permissionsService.update(permissions);
        return ResponseData.successSign();
    }

    @PostMapping("/detail")
    public ResponseData<Permissions> detail(@RequestParam Integer id) {
        Permissions permissions = permissionsService.findById(id);
        return ResponseData.success(permissions);
    }

    @PostMapping("/list")
    public ResponseData<PageInfo<Permissions>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        return ResponseData.success(new PageInfo<>(permissionsService.findAll()));
    }
}
