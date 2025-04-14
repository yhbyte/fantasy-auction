-- liquibase formatted sql

-- changeset yehorhavryliuk:1743947251121-16
ALTER TABLE items DROP COLUMN IF EXISTS type_id;

