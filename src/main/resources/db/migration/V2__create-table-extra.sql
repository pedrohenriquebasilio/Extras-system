CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE extra (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cpf VARCHAR(255) NOT NULL,
    pis VARCHAR(255) NOT NULL,
    date_birth DATE NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(50) NOT NULL,
    esocial VARCHAR(50) NOT NULL,
    sefip VARCHAR(50) NOT NULL,
    sindicate VARCHAR(50) NOT NULL,
    is_available BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
