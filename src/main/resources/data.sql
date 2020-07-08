INSERT INTO country (iso_code, name, creation_timestamp, modification_timestamp, version_number) 
VALUES ('AR','ARGENTINA', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1);

INSERT INTO country (iso_code, name, creation_timestamp, modification_timestamp, version_number) 
VALUES ('BR','BRASIL', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1);

INSERT INTO country (iso_code, name, creation_timestamp, modification_timestamp, version_number) 
VALUES ('UY','URUGUAY', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1);

INSERT INTO country (iso_code, name, creation_timestamp, modification_timestamp, version_number) 
VALUES ('CH','CHILE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1);

--INSERT FOR CURRENCY_TYPE
INSERT INTO currency_type(code, name, description) 
VALUES ('ARS', 'Peso', 'Peso argentino');

INSERT INTO currency_type(code, name, description) 
VALUES ('USD', 'Dolar', 'United States dollar');

INSERT INTO currency_type(code, name, description) 
VALUES ('EUR', 'Euro', 'European euro');

--INSERT FOR OPERATION_TYPE
INSERT INTO operation_type(code, name, description) 
VALUES ('DEB', 'Débito', 'Débito en cuenta');

INSERT INTO operation_type(code, name, description) 
VALUES ('CRE', 'Crédito','Crédito en cuenta');

--INSERT FOR MOVEMENT_TYPE
INSERT INTO movement_type(operation_type_id, code, name, description) 
VALUES (1, 'DEB', 'Débito', 'Débito en cuenta');

INSERT INTO movement_type(operation_type_id, code, name, description) 
VALUES (2, 'CRE', 'Crédito','Crédito en cuenta');

--INSERT INTO account_movement_type(code, name, description) 
--VALUES (3, 'DBA', 'Débito Automático','Débito automático en cuenta');