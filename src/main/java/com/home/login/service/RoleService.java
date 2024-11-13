package com.home.login.service;

import com.home.dtos.user.RoleDTO;
import com.home.login.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<RoleDTO> getAll(){
        return modelMapper.map(
                roleRepository.findAll(),
                new TypeToken<List<RoleDTO>>(){}.getType()
        );
    }
}
