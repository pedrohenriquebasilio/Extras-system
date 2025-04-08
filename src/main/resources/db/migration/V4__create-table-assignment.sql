CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE assignment (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    event_id UUID NOT NULL,
    extra_id UUID NOT NULL,
    role VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (event_id) REFERENCES event(id),
    FOREIGN KEY (extra_id) REFERENCES extra(id)
);
