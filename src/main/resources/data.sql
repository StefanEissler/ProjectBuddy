INSERT INTO budgets(id, name, date, amount) VALUES(1, 'Project1', '2022-10-02', 1000.00);
INSERT INTO bookings(id, title, timestamp, amount, category, budget_id) VALUES ( 2, 'Fahrtkosten', '2022-11-08', 250.00, 'Cost', 1);
INSERT INTO bookings(id, title, timestamp, amount, category, budget_id) VALUES ( 3, 'Belege', '2022-12-24', 100.00, 'Forecast', 1);


INSERT INTO budgets(id, name, date, amount) VALUES(2, 'ProjectMJS', '2022-11-17', 100000.00);
INSERT INTO bookings(id, title, timestamp, amount, category, budget_id) VALUES ( 10, 'Gehälter', '2022-11-08', 15000.00, 'Cost', 2);
INSERT INTO bookings(id, title, timestamp, amount, category, budget_id) VALUES ( 11, 'Belege', '2022-12-24', 100.00, 'Cost', 2);
INSERT INTO bookings(id, title, timestamp, amount, category, budget_id) VALUES ( 12, 'Miete', '2022-10-24', 4000.00, 'Cost', 2);
INSERT INTO bookings(id, title, timestamp, amount, category, budget_id) VALUES ( 13, 'Miete', '2022-11-24', 4000.00, 'Cost', 2);
INSERT INTO bookings(id, title, timestamp, amount, category, budget_id) VALUES ( 14, 'Miete', '2022-12-24', 4000.00, 'Forecast', 2);
INSERT INTO bookings(id, title, timestamp, amount, category, budget_id) VALUES ( 15, 'Projektabschluss', '2022-12-12', 50000.00, 'Forecast', 2);
INSERT INTO bookings(id, title, timestamp, amount, category, budget_id) VALUES ( 16, 'AWS Hosting', '2022-12-01', 55000.00, 'Forecast', 2);
INSERT INTO bookings(id, title, timestamp, amount, category, budget_id) VALUES ( 17, 'Büromaterial', '2022-10-24', 3500.00, 'Cost', 2);
INSERT INTO bookings(id, title, timestamp, amount, category, budget_id) VALUES ( 18, 'Lizensen', '2022-10-12', 10000.00, 'Cost', 2);
INSERT INTO bookings(id, title, timestamp, amount, category, budget_id) VALUES ( 19, 'Geschäftsessen', '2022-11-12', 150.00, 'Cost', 2);
INSERT INTO bookings(id, title, timestamp, amount, category, budget_id) VALUES ( 20, 'Werbekosten', '2022-11-12', 2500.00, 'Forecast', 2);

INSERT INTO users(id, email, name, password) VALUES (1, 'admin@mjs.mail', 'admin', '$2a$10$4Klsu4GZahSiLOe0SILVx.NSPR./zNbaDQx2qUbSrERGnLQo0D3rO');

INSERT INTO budgets_users(budget_list_id, users_id) VALUES (2, 1);