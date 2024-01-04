package io.audit.audit_sec.domain;

import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Rapport {

    @Id
    private Long id;

    @Size(max = 255)
    private String rwx;

    @Size(max = 255)
    private String questions;

    @Size(max = 255)
    private String responses;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getRwx() {
        return rwx;
    }

    public void setRwx(final String rwx) {
        this.rwx = rwx;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(final String questions) {
        this.questions = questions;
    }

    public String getResponses() {
        return responses;
    }

    public void setResponses(final String responses) {
        this.responses = responses;
    }

}
