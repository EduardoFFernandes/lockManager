CREATE SEQUENCE lockmanager.tb_lock_id_seq INCREMENT 1 MINVALUE 0 MAXVALUE 9223372036854775807 START 0 CACHE 1;

CREATE SEQUENCE lockmanager.tb_client_id_seq INCREMENT 1 MINVALUE 0 MAXVALUE 9223372036854775807 START 0 CACHE 1;

CREATE SEQUENCE lockmanager.tb_warehouse_id_seq INCREMENT 1 MINVALUE 0 MAXVALUE 9223372036854775807 START 0 CACHE 1;

CREATE SEQUENCE lockmanager.tb_address_id_seq INCREMENT 1 MINVALUE 0 MAXVALUE 9223372036854775807 START 0 CACHE 1;

CREATE SEQUENCE lockmanager.tb_model_id_seq INCREMENT 1 MINVALUE 0 MAXVALUE 9223372036854775807 START 0 CACHE 1;

CREATE TABLE lockmanager.tb_lock (
    id BIGINT DEFAULT nextval('tb_lock_id_seq') NOT NULL,
    serial_number CHARACTER VARYING(10)  NOT NULL,
    registry_date TIMESTAMP DEFAULT now()  NOT NULL,
    update_date TIMESTAMP,
    id_client BIGINT,
    id_address BIGINT,
    id_model BIGINT,
    acquired_date TIMESTAMP,
    CONSTRAINT pk_tb_lock PRIMARY KEY (id)
);

CREATE TABLE lockmanager.tb_client (
    id BIGINT DEFAULT nextval('tb_client_id_seq') NOT NULL,
    registry_date TIMESTAMP NOT NULL DEFAULT now(),
    update_date TIMESTAMP,    
    name CHARACTER VARYING(255) NOT NULL,
    cellphone CHARACTER VARYING(20) NOT NULL,
    email CHARACTER VARYING(255) NOT NULL,
    identifier CHARACTER VARYING(30),
    id_address BIGINT,
    CONSTRAINT pk_tb_client PRIMARY KEY (id)
)

CREATE TABLE lockmanager.tb_warehouse (
    id BIGINT DEFAULT nextval('tb_warehouse_id_seq') NOT NULL,
    name CHARACTER VARYING(255) NOT NULL,
    registry_date TIMESTAMP DEFAULT now()  NOT NULL,
    update_date TIMESTAMP DEFAULT now()  NOT NULL,
    id_address BIGINT,
    CONSTRAINT pk_tb_warehouse PRIMARY KEY (id)
);

CREATE TABLE lockmanager.tb_address (
    id BIGINT DEFAULT nextval('tb_address_id_seq')  NOT NULL,
    address CHARACTER VARYING(150),
    city CHARACTER VARYING(50),
    state CHARACTER VARYING(50),
    zip_code CHARACTER VARYING(10),
    country_name CHARACTER VARYING(50) NOT NULL,
    country_iso2 CHARACTER VARYING(2)  NOT NULL,
    country_time_zone CHARACTER VARYING(50),
    country_utc_offset CHARACTER VARYING(6),
    CONSTRAINT pk_tb_address PRIMARY KEY (id)
);
CREATE TABLE lockmanager.tb_lock_model (
    id BIGINT DEFAULT nextval('tb_model_id_seq')  NOT NULL,
    name CHARACTER VARYING(150),
    firmware_version CHARACTER VARYING(8),
    CONSTRAINT pk_tb_model PRIMARY KEY (id)
);