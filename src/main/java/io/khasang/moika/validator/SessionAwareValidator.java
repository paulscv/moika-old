package io.khasang.moika.validator;

import org.hibernate.Session;

public interface SessionAwareValidator {
    void setSession(Session session);
}
