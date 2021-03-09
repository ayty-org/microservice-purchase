CREATE TABLE tb_purchase (
    id int8 NOT NULL,
    specific_id_user VARCHAR(500) NOT NULL,
    specific_id_books VARCHAR NOT NULL,
    price_to_pay float8 NOT NULL,
    purchase_completed BIT NOT NULL,
    CONSTRAINT purchase_id PRIMARY KEY (id)
);
