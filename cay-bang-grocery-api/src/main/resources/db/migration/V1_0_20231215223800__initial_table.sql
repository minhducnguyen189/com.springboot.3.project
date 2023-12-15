
CREATE TABLE product (
    id uuid NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    product_number BIGINT NOT NULL,
    name varchar(255) NULL,
    summary varchar(255) NULL,
    description varchar(5000) NULL,
    price BIGINT NULL,
    rating INTEGER NULL,
    categories varchar(5000) NULL
);

CREATE SEQUENCE product_number_sequence
    START 100000
    INCREMENT 1
    MINVALUE 100000
    MAXVALUE 9223372036854775807
    NO CYCLE
    OWNED BY product.product_number;

ALTER TABLE product
ADD CONSTRAINT product_primary_key PRIMARY KEY(id);

ALTER TABLE product
ADD CONSTRAINT product_number_unique UNIQUE(product_number);

ALTER TABLE product
ALTER COLUMN product_number SET DEFAULT nextval('product_number_sequence');

CREATE TABLE file_storage (
    id uuid NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    file_name varchar(255) NULL,
    file_extension varchar(100) NULL,
    media_type varchar(255) NULL,
    file_data bytea NULL,
    product_id uuid
);

ALTER TABLE file_storage
ADD CONSTRAINT file_storage_primary_key PRIMARY KEY(id);

ALTER TABLE file_storage
ADD CONSTRAINT product_id_foreign_key
FOREIGN KEY(product_id)
REFERENCES product(id);

