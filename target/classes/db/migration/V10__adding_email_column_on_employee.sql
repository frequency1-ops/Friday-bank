-- 1️ Add the column if it does not exist
ALTER TABLE employee
ADD COLUMN email VARCHAR(255);

-- 2️ Backfill existing rows only
UPDATE employee
SET email = CONCAT(LOWER(first_name), '@company.com')
WHERE email IS NULL;

-- 3️ Make it NOT NULL (only if still nullable)
ALTER TABLE employee
MODIFY COLUMN email VARCHAR(255) NOT NULL;

-- 4️ Add UNIQUE index if it does not exist
ALTER TABLE employee
ADD UNIQUE INDEX uk_employee_email (email);
