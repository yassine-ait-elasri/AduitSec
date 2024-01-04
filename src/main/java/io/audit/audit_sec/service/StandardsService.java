package io.audit.audit_sec.service;

import io.audit.audit_sec.domain.Standards;
import io.audit.audit_sec.model.StandardsDTO;
import io.audit.audit_sec.repos.StandardsRepository;
import io.audit.audit_sec.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class StandardsService {

    private final StandardsRepository standardsRepository;

    public StandardsService(final StandardsRepository standardsRepository) {
        this.standardsRepository = standardsRepository;
    }

    public List<StandardsDTO> findAll() {
        final List<Standards> standardses = standardsRepository.findAll(Sort.by("id"));
        return standardses.stream()
                .map(standards -> mapToDTO(standards, new StandardsDTO()))
                .toList();
    }

    public StandardsDTO get(final Long id) {
        return standardsRepository.findById(id)
                .map(standards -> mapToDTO(standards, new StandardsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final StandardsDTO standardsDTO) {
        final Standards standards = new Standards();
        mapToEntity(standardsDTO, standards);
        return standardsRepository.save(standards).getId();
    }

    public void update(final Long id, final StandardsDTO standardsDTO) {
        final Standards standards = standardsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(standardsDTO, standards);
        standardsRepository.save(standards);
    }

    public void delete(final Long id) {
        standardsRepository.deleteById(id);
    }

    private StandardsDTO mapToDTO(final Standards standards, final StandardsDTO standardsDTO) {
        standardsDTO.setId(standards.getId());
        standardsDTO.setNom(standards.getNom());
        return standardsDTO;
    }

    private Standards mapToEntity(final StandardsDTO standardsDTO, final Standards standards) {
        standards.setNom(standardsDTO.getNom());
        return standards;
    }

}
