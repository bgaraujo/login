package com.home.login.service;

import com.home.login.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesService {
    @Autowired
    private ServiceRepository serviceRepository;


}
