-- Create accounts table
CREATE TABLE IF NOT EXISTS accounts (
    id BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(50) UNIQUE NOT NULL,
    customer_id VARCHAR(50) NOT NULL,
    account_type VARCHAR(50),
    balance DOUBLE PRECISION,
    currency VARCHAR(10),
    status VARCHAR(20),
    opening_date TIMESTAMP,
    last_updated TIMESTAMP
);

-- Create customer_relationships table
CREATE TABLE IF NOT EXISTS customer_relationships (
    id BIGSERIAL PRIMARY KEY,
    customer_id VARCHAR(50) NOT NULL,
    relationship_type VARCHAR(50),
    related_customer_id VARCHAR(50),
    relationship_status VARCHAR(20),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    last_updated TIMESTAMP
);

-- Create casa_transactions table
CREATE TABLE IF NOT EXISTS casa_transactions (
    id BIGSERIAL PRIMARY KEY,
    transaction_id VARCHAR(50) UNIQUE NOT NULL,
    account_number VARCHAR(50) NOT NULL,
    transaction_type VARCHAR(50),
    amount DECIMAL(19,4),
    currency VARCHAR(10),
    transaction_date TIMESTAMP,
    description TEXT,
    status VARCHAR(20),
    balance_after DECIMAL(19,4),
    created_at TIMESTAMP
);

-- Create card_transactions table
CREATE TABLE IF NOT EXISTS card_transactions (
    id BIGSERIAL PRIMARY KEY,
    transaction_id VARCHAR(50) UNIQUE NOT NULL,
    card_number VARCHAR(50) NOT NULL,
    customer_id VARCHAR(50) NOT NULL,
    transaction_type VARCHAR(50),
    amount DECIMAL(19,4),
    currency VARCHAR(10),
    merchant_name VARCHAR(100),
    merchant_category VARCHAR(50),
    transaction_date TIMESTAMP,
    status VARCHAR(20),
    created_at TIMESTAMP
); 