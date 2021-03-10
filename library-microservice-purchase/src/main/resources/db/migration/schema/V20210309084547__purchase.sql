CREATE TABLE tb_purchase (
    id int8 NOT NULL,
    specificIdUser VARCHAR(500) NOT NULL,
    specificIdBooks VARCHAR(500) NOT NULL,
    priceToPay float8 NOT NULL,
    purchaseCompleted BIT NOT NULL,
    CONSTRAINT purchase_id PRIMARY KEY (id)
);
