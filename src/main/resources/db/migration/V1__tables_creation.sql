
-- Branch table
CREATE TABLE Branch (
    branch_id BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    branch_name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    city VARCHAR(255),
    bank_manager_id BINARY(16),
    CONSTRAINT fk_branch_manger
        FOREIGN KEY (bank_manager_id)
        REFERENCES Employee(employee_id)
        ON DELETE SET NULL
);

-- Customer table
CREATE TABLE Customer (
    customer_id BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(20) UNIQUE,
    address VARCHAR(255),
    date_of_birth DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Account table
create table Account(
    account_id BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    customer_id BINARY(16),
    branch_id BINARY(16),
    account_number   VARCHAR(20) UNIQUE NOT NULL,
    account_type     ENUM('SAVINGS', 'CURRENT', 'CREDIT'),
    balance          DECIMAL(15,2) DEFAULT 0.0,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
        ON DELETE CASCADE,
    FOREIGN KEY (branch_id) REFERENCES Branch(branch_id)
        ON DELETE SET NULL
);

-- Transaction table
CREATE TABLE Transaction (
    transaction_id   BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    account_id       BINARY(16),
    type             ENUM('DEPOSIT', 'WITHDRAWAL', 'TRANSFER'),
    amount           DECIMAL(15,2) NOT NULL,
    timestamp        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description      VARCHAR(255),
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
        ON DELETE CASCADE
);

-- 5. Card table
CREATE TABLE Card (
    card_id          BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    customer_id      BINARY(16),
    account_id       BINARY(16),
    card_number      VARCHAR(16) UNIQUE NOT NULL,
    card_type        ENUM('DEBIT', 'CREDIT'),
    expiry_date      DATE NOT NULL,
    cvv              CHAR(3) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
        ON DELETE CASCADE,
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
        ON DELETE CASCADE
);

-- 6. Loan table
CREATE TABLE Loan (
    loan_id          BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    customer_id      BINARY(16),
    amount           DECIMAL(15,2) NOT NULL,
    interest_rate    DECIMAL(5,2),
    start_date       DATE,
    end_date         DATE,
    status           ENUM('ACTIVE', 'CLOSED', 'DEFAULTED'),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
        ON DELETE CASCADE
);

-- 7. Employee table
CREATE TABLE Employee (
    employee_id      BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    branch_id        BINARY(16),
    first_name       VARCHAR(100),
    last_name        VARCHAR(100),
    position         VARCHAR(100),
    hire_date        DATE,
    salary           DECIMAL(15,2),
    FOREIGN KEY (branch_id) REFERENCES Branch(branch_id)
        ON DELETE SET NULL
);









