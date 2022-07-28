CREATE TABLE accounts
(
    id          SERIAL PRIMARY KEY NOT NULL,
    customer_id VARCHAR(255)       NOT NULL,
    country     VARCHAR(255),
    currencies  TEXT[],
    create_time TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    delete_time TIMESTAMP                   DEFAULT NULL
);

create TRIGGER "account_upd_trigger"
    BEFORE UPDATE
    ON accounts
    FOR EACH ROW
EXECUTE PROCEDURE upd_timestamp();

CREATE INDEX customer_id_idx ON accounts (customer_id);
