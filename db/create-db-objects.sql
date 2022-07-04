CREATE SEQUENCE lockmanager.tb_lock_id_seq INCREMENT 1 MINVALUE 0 MAXVALUE 9223372036854775807 START 0 CACHE 1;

CREATE SEQUENCE lockmanager.tb_client_id_seq INCREMENT 1 MINVALUE 0 MAXVALUE 9223372036854775807 START 0 CACHE 1;

CREATE SEQUENCE lockmanager.tb_warehouse_id_seq INCREMENT 1 MINVALUE 0 MAXVALUE 9223372036854775807 START 0 CACHE 1;

CREATE SEQUENCE lockmanager.tb_model_id_seq INCREMENT 1 MINVALUE 0 MAXVALUE 9223372036854775807 START 0 CACHE 1;

CREATE SEQUENCE lockmanager.tb_purchase_id_seq INCREMENT 1 MINVALUE 0 MAXVALUE 9223372036854775807 START 0 CACHE 1;

CREATE SEQUENCE lockmanager.tb_item_id_seq INCREMENT 1 MINVALUE 0 MAXVALUE 9223372036854775807 START 0 CACHE 1;

CREATE TABLE lockmanager.tb_lock (
    id BIGINT DEFAULT nextval('lockmanager.tb_lock_id_seq') NOT NULL,
    serial_number CHARACTER VARYING(10)  NOT NULL,
    firmware_version CHARACTER VARYING(8),
    registry_date TIMESTAMP DEFAULT now()  NOT NULL,
    update_date TIMESTAMP,
    id_model BIGINT,
    id_warehouse BIGINT,
    acquired_date TIMESTAMP,
    CONSTRAINT pk_tb_lock PRIMARY KEY (id)

);

CREATE TABLE lockmanager.tb_client (
    id BIGINT DEFAULT nextval('lockmanager.tb_client_id_seq') NOT NULL,
    registry_date TIMESTAMP NOT NULL DEFAULT now(),
    update_date TIMESTAMP,    
    name CHARACTER VARYING(255) NOT NULL,
    cellphone CHARACTER VARYING(20) NOT NULL,
    email CHARACTER VARYING(255) NOT NULL,
    identifier CHARACTER VARYING(30),
    address CHARACTER VARYING(150),
    CONSTRAINT pk_tb_client PRIMARY KEY (id)
);

CREATE TABLE lockmanager.tb_warehouse (
    id BIGINT DEFAULT nextval('lockmanager.tb_warehouse_id_seq') NOT NULL,
    registry_date TIMESTAMP DEFAULT now()  NOT NULL,
    update_date TIMESTAMP,
    name CHARACTER VARYING(255) NOT NULL,
    address CHARACTER VARYING(150),
    CONSTRAINT pk_tb_warehouse PRIMARY KEY (id)

);

CREATE TABLE lockmanager.tb_lock_model (
    id BIGINT DEFAULT nextval('lockmanager.tb_model_id_seq')  NOT NULL,
    name CHARACTER VARYING(150),
    CONSTRAINT pk_tb_model PRIMARY KEY (id)

);

CREATE TABLE lockmanager.tb_purchase (
    id BIGINT DEFAULT nextval('tb_purchase_id_seq') NOT NULL,
    registry_date TIMESTAMP DEFAULT now()  NOT NULL,
    update_date TIMESTAMP,
    id_client BIGINT,
    purchase_date TIMESTAMP,
    due_date TIMESTAMP,
    CONSTRAINT pk_tb_purchase PRIMARY KEY (id)
);

CREATE TABLE lockmanager.tb_item (
    id BIGINT DEFAULT nextval('tb_item_id_seq') NOT NULL,
    registry_date TIMESTAMP DEFAULT now()  NOT NULL,
    update_date TIMESTAMP,
    id_purchase BIGINT,
    id_lock BIGINT,
    note TEXT,
    sensor BOOLEAN DEFAULT TRUE NOT NULL,
    status INTEGER DEFAULT 0  NOT NULL,
    price NUMERIC(10,2),
    installation_location CHARACTER VARYING(150),
    CONSTRAINT pk_tb_item PRIMARY KEY (id)
);
