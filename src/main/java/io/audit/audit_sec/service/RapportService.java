package io.audit.audit_sec.service;

import io.audit.audit_sec.domain.Rapport;
import io.audit.audit_sec.model.RapportDTO;
import io.audit.audit_sec.repos.RapportRepository;
import io.audit.audit_sec.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class RapportService {

    private final RapportRepository rapportRepository;

    public RapportService(final RapportRepository rapportRepository) {
        this.rapportRepository = rapportRepository;
    }

    public List<RapportDTO> findAll() {
        final List<Rapport> rapports = rapportRepository.findAll(Sort.by("id"));
        return rapports.stream()
                .map(rapport -> mapToDTO(rapport, new RapportDTO()))
                .toList();
    }

    public RapportDTO get(final Long id) {
        return rapportRepository.findById(id)
                .map(rapport -> mapToDTO(rapport, new RapportDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final RapportDTO rapportDTO) {
        final Rapport rapport = new Rapport();
        mapToEntity(rapportDTO, rapport);
        return rapportRepository.save(rapport).getId();
    }

    public void update(final Long id, final RapportDTO rapportDTO) {
        final Rapport rapport = rapportRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(rapportDTO, rapport);
        rapportRepository.save(rapport);
    }

    public void delete(final Long id) {
        rapportRepository.deleteById(id);
    }

    private RapportDTO mapToDTO(final Rapport rapport, final RapportDTO rapportDTO) {
        rapportDTO.setId(rapport.getId());
        rapportDTO.setRwx(rapport.getRwx());
        rapportDTO.setQuestions(rapport.getQuestions());
        rapportDTO.setResponses(rapport.getResponses());
        return rapportDTO;
    }

    private Rapport mapToEntity(final RapportDTO rapportDTO, final Rapport rapport) {
        rapport.setRwx(rapportDTO.getRwx());
        rapport.setQuestions(rapportDTO.getQuestions());
        rapport.setResponses(rapportDTO.getResponses());
        return rapport;
    }

}
