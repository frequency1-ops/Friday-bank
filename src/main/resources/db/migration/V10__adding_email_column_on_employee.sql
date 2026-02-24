-- 1️ Add the column as nullable
ALTER TABLE Employee
ADD COLUMN email VARCHAR(255);

-- 2️ Backfill ONLY current rows
UPDATE Employee
SET email = CONCAT(LOWER(firstname), '@company.com')
WHERE email IS NULL;

-- 3️ Make it NOT NULL
ALTER TABLE Employee
MODIFY email VARCHAR(255) NOT NULL;

-- 4️ Add UNIQUE constraint
ALTER TABLE Employee
ADD CONSTRAINT uk_employee_email UNIQUE (email);
