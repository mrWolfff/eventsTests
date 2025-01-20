package com.cresol.eventsTest.service;

import com.cresol.eventsTest.domain.Institution;
import com.cresol.eventsTest.domain.InstitutionDTO;
import com.cresol.eventsTest.repository.InstitutionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitutionService {

    @Autowired
    private InstitutionRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<InstitutionDTO> getInstitutions() {
        return repository.findAll()
                .stream()
                .map(i -> modelMapper.map(i, InstitutionDTO.class))
                .collect(Collectors.toList());
    }

    public InstitutionDTO getInstitutionById(Long id) {
        Institution institution = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(institution, InstitutionDTO.class);
    }

    public InstitutionDTO createInstitution(InstitutionDTO dto) {
        Institution institution = modelMapper.map(dto, Institution.class);
        repository.save(institution);
        return modelMapper.map(institution, InstitutionDTO.class);
    }

    public void deleteInstitution(Long id) {
        repository.deleteById(id);
    }
}
