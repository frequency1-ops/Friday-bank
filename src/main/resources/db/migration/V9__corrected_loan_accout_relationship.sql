ALTER TABLE Loan
DROP FOREIGN KEY loan_ibfk_1,
DROP COLUMN customer_id,
ADD COLUMN account_id BINARY(16) NOT NULL,
ADD CONSTRAINT fk_loan_account
    FOREIGN KEY (account_id)
    REFERENCES Account(account_id)
    ON DELETE CASCADE;
