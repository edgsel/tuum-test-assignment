CREATE TYPE transaction_types AS ENUM (
    'IN',
    'OUT'
    );

CREATE TABLE transactions
(
    id               SERIAL PRIMARY KEY NOT NULL,
    account_id       INT                NOT NULL,
    amount           DECIMAL(10, 2)              DEFAULT 0.00,
    currency         VARCHAR(3),
    transaction_type transaction_types  NOT NULL,
    create_time      TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time      TIMESTAMP                   DEFAULT CURRENT_TIMESTAMP,
    delete_time      TIMESTAMP                   DEFAULT NULL,
    CONSTRAINT fk_account_id
        FOREIGN KEY (account_id) REFERENCES "account" (id)
);

CREATE TRIGGER transaction_upd_trigger
    BEFORE UPDATE
    ON transactions
    FOR EACH ROW
EXECUTE PROCEDURE upd_timestamp();
