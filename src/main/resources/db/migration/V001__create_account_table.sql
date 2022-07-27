CREATE TABLE "account"
(
    id          SERIAL PRIMARY KEY NOT NULL,
    customer_id BIGINT             NOT NULL,
    country     VARCHAR(255),
    currencies  TEXT[],
    create_time TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    delete_time TIMESTAMP                   DEFAULT NULL
);

create TRIGGER "account_upd_trigger"
    BEFORE UPDATE
    ON "account"
    FOR EACH ROW
EXECUTE PROCEDURE upd_timestamp();

CREATE INDEX customer_id_idx ON "account" (customer_id);