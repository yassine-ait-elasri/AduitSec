package io.audit.audit_sec.repos;

import io.audit.audit_sec.domain.Rapport;
import io.audit.audit_sec.service.PrimarySequenceService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class RapportListener extends AbstractMongoEventListener<Rapport> {

    private final PrimarySequenceService primarySequenceService;

    public RapportListener(final PrimarySequenceService primarySequenceService) {
        this.primarySequenceService = primarySequenceService;
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Rapport> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(primarySequenceService.getNextValue());
        }
    }

}
