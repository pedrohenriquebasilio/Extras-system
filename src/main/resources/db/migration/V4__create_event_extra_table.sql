CREATE TABLE event_extra (
    event_id UUID NOT NULL,
    extra_id UUID NOT NULL,
    PRIMARY KEY (event_id, extra_id),
    CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES event(id) ON DELETE CASCADE,
    CONSTRAINT fk_extra FOREIGN KEY (extra_id) REFERENCES extra(id) ON DELETE CASCADE
);
