INSERT INTO coupons (name, discount, end_date)
VALUES ('KORVOPREMIUM', 25, '2022-05-15');

INSERT INTO coupons (name, discount, end_date)
VALUES ('KORVOSTUDENT', 50, '2022-05-30');


INSERT INTO ticket_types (ticket_type, start_date, end_date, price)
VALUES ('EARLY_BIRD', '2022-04-01', '2022-04-30', 500);

INSERT INTO ticket_types (ticket_type, start_date, end_date, price)
VALUES ('REGULAR', '2022-05-01', '2022-06-30', 850);

INSERT INTO ticket_types (ticket_type, start_date, end_date, price)
VALUES ('LAGGARD', '2022-07-01', '2022-07-30', 1250);

INSERT INTO conferences (name, start_date, address, description)
VALUES ('Java Day', '2022-08-01', 'Istanbul', 'Best Java Conference');

INSERT INTO speakers (name, photo_url, topics, conference_id)
VALUES ('Jeff Bezos', 'https://www.google.com', 'Java, Spring', 1);


