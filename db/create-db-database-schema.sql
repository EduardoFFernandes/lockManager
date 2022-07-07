CREATE DATABASE dblockmanager_desenv
    WITH 
    OWNER = dblockmanager
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
DROP SCHEMA public;

CREATE SCHEMA lockmanager AUTHORIZATION dblockmanager;
  
ALTER DATABASE dblockmanager_desenv SET search_path TO lockmanager;


    
    
  