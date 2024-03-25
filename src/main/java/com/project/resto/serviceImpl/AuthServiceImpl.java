package com.project.resto.serviceImpl;

import com.project.resto.dao.AssetDao;
import com.project.resto.dao.AuthDao;
import com.project.resto.dto.AssetDto;
import com.project.resto.dto.AuthDto;
import com.project.resto.service.AssetService;
import com.project.resto.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;

    @Override
    public int add(AuthDto authDto) {
        int add = authDao.add(authDto);
        return add;
    }

    @Override
    public List<AuthDto> get (AuthDto authDto) {
        List<AuthDto> list = authDao.get(authDto);
        return list;
    }

    @Override
    public int update(AuthDto authDto) {
        int update = authDao.update(authDto);
        return update;
    }

    @Override
    public AuthDto findCodeByEmail(AuthDto authDto) {
        AuthDto code = authDao.findCodeByEmail(authDto);
        return code;
    }
}
