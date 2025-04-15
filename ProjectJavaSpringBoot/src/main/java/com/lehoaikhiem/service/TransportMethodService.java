package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.TransportMethod.TransportMethodDTO;
import com.lehoaikhiem.dto.TransportMethod.TransportMethodMapper;
import com.lehoaikhiem.entity.TransportMethod;
import com.lehoaikhiem.repository.TransportMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportMethodService {
    @Autowired
    private TransportMethodRepository transportMethodRepository;

    @Autowired
    private TransportMethodMapper transportMethodMapper;

    public List<TransportMethodDTO> findAll() {
       return transportMethodMapper.toDtoList(transportMethodRepository.findAll());
    }

    public TransportMethodDTO findById(Long id) {
        Optional<TransportMethod> transportMethod = transportMethodRepository.findById(id);
        return transportMethod.map(transportMethodMapper::toDto).orElse(null);
    }

    public TransportMethodDTO save(TransportMethodDTO transportMethodDTO) {
        TransportMethod transportMethod = transportMethodMapper.toEntity(transportMethodDTO);
        return transportMethodMapper.toDto(transportMethodRepository.save(transportMethod));
    }

    public void deleteById(Long id) {
        transportMethodRepository.deleteById(id);
    }
}
