package io.audit.audit_sec.repos;

import io.audit.audit_sec.domain.Standards;
import io.audit.audit_sec.service.PrimarySequenceService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class StandardsListener extends AbstractMongoEventListener<Standards> {

    private final PrimarySequenceService primarySequenceService;

    public StandardsListener(final PrimarySequenceService primarySequenceService) {
        this.primarySequenceService = primarySequenceService;
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Standards> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(primarySequenceService.getNextValue());
        }
    }

}
