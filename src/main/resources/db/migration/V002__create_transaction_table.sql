CREATE TABLE transactions
(
    id               SERIAL PRIMARY KEY NOT NULL,
    account_id       INT                NOT NULL,
    amount           DECIMAL(10, 2)              DEFAULT 0.00,
    currency         VARCHAR,
    transaction_type VARCHAR            NOT NULL,
    create_time      TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time      TIMESTAMP                   DEFAULT CURRENT_TIMESTAMP,
    delete_time      TIMESTAMP                   DEFAULT NULL
);

CREATE TRIGGER transaction_upd_trigger
    BEFORE UPDATE
    ON transactions
    FOR EACH ROW
EXECUTE PROCEDURE upd_timestamp();
