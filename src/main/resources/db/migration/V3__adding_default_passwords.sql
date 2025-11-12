UPDATE Customer
SET password = 'default123'
WHERE password IS NULL OR password = '';

UPDATE Employee
SET password = 'password123'
WHERE password IS NULL OR password = '';