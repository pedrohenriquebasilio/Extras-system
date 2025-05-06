CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE event (
       id BIGINT PRIMARY KEY,
       extra_id BIGINT NOT NULL,
       usuario_id BIGINT NOT NULL,
       nome VARCHAR(255) NOT NULL,
       data_inicio TIMESTAMP NOT NULL,
       data_fim TIMESTAMP NOT NULL,
       local VARCHAR(255),
       descricao TEXT,
       funcao VARCHAR(100) NOT NULL,
       preco DECIMAL(10, 2) NOT NULL,
       ativo BOOLEAN NOT NULL DEFAULT TRUE,
       criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
       atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
       CONSTRAINT fk_trabalhos_extras FOREIGN KEY (extra_id) REFERENCES extras(id),
       CONSTRAINT fk_trabalhos_usuarios FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
