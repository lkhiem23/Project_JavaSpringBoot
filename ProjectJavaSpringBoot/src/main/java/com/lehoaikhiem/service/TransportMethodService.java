package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.TransportMethod.TransportMethodMapper;
import com.lehoaikhiem.repository.TransportMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportMethodService {
    @Autowired
    private TransportMethodRepository transportMethodRepository;

    @Autowired
    private TransportMethodMapper transportMethodMapper;
}
