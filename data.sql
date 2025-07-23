CREATE TABLE tb_user (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    USER_ROLE VARCHAR(50) NOT NULL
);


CREATE TABLE tb_wallet (
    id UUID PRIMARY KEY,
    user_email VARCHAR(255) UNIQUE NOT NULL,
    balance DOUBLE PRECISION
);

CREATE TABLE tb_transactions (
    id UUID PRIMARY KEY,
    source_user_id VARCHAR(255) NOT NULL,
    destination_user_id VARCHAR(255) NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP
);