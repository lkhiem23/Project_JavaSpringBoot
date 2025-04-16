package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.Configuration.ConfigurationDTO;
import com.lehoaikhiem.dto.Configuration.ConfigurationMapper;
import com.lehoaikhiem.entity.Configuration;
import com.lehoaikhiem.entity.TransportMethod;
import com.lehoaikhiem.repository.ConfigurationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private ConfigurationMapper configurationMapper;

    public List<ConfigurationDTO> findAll() {
        return configurationMapper.toDtoList(configurationRepository.findAll());
    }

    public ConfigurationDTO findById(Long id) {
        Configuration entity = configurationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Configuration not found with id " + id));
        return configurationMapper.toDto(entity);
    }

}
