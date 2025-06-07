package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.TransportMethod.TransportMethodDTO;
import com.lehoaikhiem.dto.TransportMethod.TransportMethodMapper;
import com.lehoaikhiem.entity.TransportMethod;
import com.lehoaikhiem.repository.TransportMethodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        TransportMethod entity = transportMethodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transport method not found with ID: " + id));
        return transportMethodMapper.toDto(entity);
    }


    public TransportMethodDTO save(TransportMethodDTO transportMethodDTO) {
        TransportMethod transportMethod = transportMethodMapper.toEntity(transportMethodDTO);
        return transportMethodMapper.toDto(transportMethodRepository.save(transportMethod));
    }

    public void deleteById(Long id) {
        if (!transportMethodRepository.existsById(id)) {
            throw new EntityNotFoundException("Transport method not found with ID: " + id);
        }
        transportMethodRepository.deleteById(id);
    }

}
