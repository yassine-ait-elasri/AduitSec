package io.audit.audit_sec.rest;

import io.audit.audit_sec.model.StandardsDTO;
import io.audit.audit_sec.service.StandardsService;
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
@RequestMapping(value = "/api/standardss", produces = MediaType.APPLICATION_JSON_VALUE)
public class StandardsResource {

    private final StandardsService standardsService;

    public StandardsResource(final StandardsService standardsService) {
        this.standardsService = standardsService;
    }

    @GetMapping
    public ResponseEntity<List<StandardsDTO>> getAllStandardss() {
        return ResponseEntity.ok(standardsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardsDTO> getStandards(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(standardsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createStandards(
            @RequestBody @Valid final StandardsDTO standardsDTO) {
        final Long createdId = standardsService.create(standardsDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateStandards(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final StandardsDTO standardsDTO) {
        standardsService.update(id, standardsDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteStandards(@PathVariable(name = "id") final Long id) {
        standardsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
