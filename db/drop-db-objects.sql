DROP SCHEMA lockmanager;

DROP SEQUENCE lockmanager.tb_lock_id_seq;
DROP SEQUENCE lockmanager.tb_client_id_seq;
DROP SEQUENCE lockmanager.tb_warehouse_id_seq;
DROP SEQUENCE lockmanager.tb_model_id_seq;
DROP SEQUENCE lockmanager.tb_purchase_id_seq;
DROP SEQUENCE lockmanager.tb_item_id_seq;

DROP TABLE tb_lock;
DROP TABLE tb_client;
DROP TABLE tb_warehouse;
DROP TABLE tb_lock_model;
DROP TABLE tb_item;
DROP TABLE tb_purchase;