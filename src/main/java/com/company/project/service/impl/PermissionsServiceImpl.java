package com.company.project.service.impl;

import com.company.project.dao.PermissionsMapper;
import com.company.project.model.Permissions;
import com.company.project.service.PermissionsService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/02/03.
 */
@Service
@Transactional
public class PermissionsServiceImpl extends AbstractService<Permissions> implements PermissionsService {

    @Resource
    private PermissionsMapper permissionsMapper;

}
