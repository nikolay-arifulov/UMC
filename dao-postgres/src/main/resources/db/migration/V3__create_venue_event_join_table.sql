CREATE TABLE event_venue (
    event_id     BIGINT NOT NULL,
    brand        VARCHAR(255) NOT NULL,
    provider     VARCHAR(255) NOT NULL,
    external_id  VARCHAR(255) NOT NULL,
    PRIMARY KEY (event_id, brand, provider, external_id),
    CONSTRAINT fk_event FOREIGN KEY (event_id)
        REFERENCES event (id),
    CONSTRAINT fk_venue FOREIGN KEY (brand, provider, external_id)
        REFERENCES venue (brand, provider, external_id)
        ON DELETE CASCADE
);