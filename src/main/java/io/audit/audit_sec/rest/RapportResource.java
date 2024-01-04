package io.audit.audit_sec.rest;

import io.audit.audit_sec.model.RapportDTO;
import io.audit.audit_sec.service.RapportService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/rapports", produces = MediaType.APPLICATION_JSON_VALUE)
public class RapportResource {

    private final RapportService rapportService;

    public RapportResource(final RapportService rapportService) {
        this.rapportService = rapportService;
    }

    @GetMapping
    public ResponseEntity<List<RapportDTO>> getAllRapports() {
        return ResponseEntity.ok(rapportService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RapportDTO> getRapport(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(rapportService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createRapport(@RequestBody @Valid final RapportDTO rapportDTO) {
        final Long createdId = rapportService.create(rapportDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateRapport(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final RapportDTO rapportDTO) {
        rapportService.update(id, rapportDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteRapport(@PathVariable(name = "id") final Long id) {
        rapportService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
