
INSERT INTO household (eircode, number_of_occupants, max_number_of_occupants, owner_occupied) VALUES
                                                                                                  ('D02XY45', 3, 5, 1),
                                                                                                  ('A94B6F3', 4, 6, 0),
                                                                                                  ('T12AB34', 2, 4, 1),
                                                                                                  ('C15DE67', 5, 7, 1),
                                                                                                  ('F12GH89', 1, 2, 0),
                                                                                                  ('B78IJ01', 3, 5, 1),
                                                                                                  ('M34KL56', 4, 6, 0),
                                                                                                  ('P90QR78', 2, 4, 1),
                                                                                                  ('V23ST01', 5, 7, 1),
                                                                                                  ('X45UV67', 1, 2, 0),
                                                                                                  ('Y67WX89', 3, 5, 1),
                                                                                                  ('Z01YZ23', 4, 6, 0),
                                                                                                  ('Q45AB78', 2, 4, 1),
                                                                                                  ('R67CD01', 5, 7, 1),
                                                                                                  ('S23EF45', 1, 2, 0);


INSERT INTO pets (name, animal_type, breed, age) VALUES ('Buddy', 'Dog', 'Golden Retriever', 3);
INSERT INTO pets (name, animal_type, breed, age) VALUES ('Mittens', 'Cat', 'Siamese', 2);
INSERT INTO pets (name, animal_type, breed, age) VALUES ('Charlie', 'Dog', 'Beagle', 4);
INSERT INTO pets (name, animal_type, breed, age) VALUES ('Whiskers', 'Cat', 'Persian', 5);
INSERT INTO pets (name, animal_type, breed, age) VALUES ('Coco', 'Rabbit', 'Holland Lop', 1);
INSERT INTO pets (name, animal_type, breed, age) VALUES ('Goldie', 'Fish', 'Goldfish', 1);
INSERT INTO pets (name, animal_type, breed, age) VALUES ('Polly', 'Bird', 'Parakeet', 2);
INSERT INTO pets (name, animal_type, breed, age) VALUES ('Max', 'Dog', 'German Shepherd', 5);
INSERT INTO pets (name, animal_type, breed, age) VALUES ('Luna', 'Cat', 'Maine Coon', 3);
INSERT INTO pets (name, animal_type, breed, age) VALUES ('Nibbles', 'Hamster', 'Syrian Hamster', 1);


UPDATE pets SET household_eircode = 'D02XY45' WHERE name = 'Buddy';
UPDATE pets SET household_eircode = 'A94B6F3' WHERE name = 'Mittens';
UPDATE pets SET household_eircode = 'T12AB34' WHERE name = 'Charlie';
UPDATE pets SET household_eircode = 'C15DE67' WHERE name = 'Whiskers';
UPDATE pets SET household_eircode = 'F12GH89' WHERE name = 'Coco';
UPDATE pets SET household_eircode = 'B78IJ01' WHERE name = 'Goldie';
UPDATE pets SET household_eircode = 'M34KL56' WHERE name = 'Polly';
UPDATE pets SET household_eircode = 'P90QR78' WHERE name = 'Max';
UPDATE pets SET household_eircode = 'V23ST01' WHERE name = 'Luna';
UPDATE pets SET household_eircode = 'X45UV67' WHERE name = 'Nibbles';

INSERT INTO household (eircode, number_of_occupants, max_number_of_occupants, owner_occupied)
VALUES ('E10XY45', 0, 5, 1), -- Empty house
       ('F10YZ56', 4, 4, 1); -- Full house


INSERT INTO myusers (email, password, first_name, last_name, role, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES ('alice.wonderland@example.com', '$2a$10$YT6lk23zMFRxBgrrngCDXuaUVf0gXnQDrhLwa5ywnQ5QIV3lhPBOm', 'Alice', 'Wonderland', 'ROLE_ADMIN', TRUE, TRUE, TRUE, TRUE);

INSERT INTO myusers (email, password, first_name, last_name, role, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES ('bob.builder@example.com', '$2a$10$jPOV8aXOKupAi7UCJVvMaeEzBXjjLEblskQkB3ZAtNww2ATQjp8QK', 'Bob', 'Builder', 'ROLE_API', TRUE, TRUE, TRUE, TRUE);

INSERT INTO myusers (email, password, first_name, last_name, role, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES ('charlie.brown@example.com', '$2a$10$jPOV8aXOKupAi7UCJVvMaeEzBXjjLEblskQkB3ZAtNww2ATQjp8QK', 'Charlie', 'Brown', 'ROLE_USER', TRUE, TRUE, TRUE, TRUE);

INSERT INTO myusers (email, password, first_name, last_name, role, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES ('diana.prince@example.com', '$2a$10$jPOV8aXOKupAi7UCJVvMaeEzBXjjLEblskQkB3ZAtNww2ATQjp8QK', 'Diana', 'Prince', 'ROLE_USER', TRUE, TRUE, TRUE, TRUE);

INSERT INTO myusers (email, password, first_name, last_name, role, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES ('edward.snow@example.com', '$2a$10$jPOV8aXOKupAi7UCJVvMaeEzBXjjLEblskQkB3ZAtNww2ATQjp8QK', 'Edward', 'Snow', 'ROLE_ADMIN', TRUE, TRUE, TRUE, FALSE);
