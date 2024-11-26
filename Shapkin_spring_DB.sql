
DROP TABLE IF EXISTS packages CASCADE;
DROP TABLE IF EXISTS payments CASCADE;
DROP TABLE IF EXISTS reviews CASCADE;
DROP TABLE IF EXISTS bookings CASCADE;
DROP TABLE IF EXISTS flights CASCADE;
DROP TABLE IF EXISTS hotels CASCADE;
DROP TABLE IF EXISTS destinations CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE destinations (
    destination_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE hotels (
    hotel_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    destination_id INT REFERENCES destinations(destination_id) ON DELETE CASCADE,
    rating DECIMAL(2, 1),
    price_per_night DECIMAL(10, 2)
);

CREATE TABLE flights (
    flight_id SERIAL PRIMARY KEY,
    airline VARCHAR(100) NOT NULL,
    from_destination_id INT REFERENCES destinations(destination_id) ON DELETE CASCADE,
    to_destination_id INT REFERENCES destinations(destination_id) ON DELETE CASCADE,
    departure_time TIMESTAMP,
    arrival_time TIMESTAMP,
    price DECIMAL(10, 2)
);

CREATE TABLE bookings (
    booking_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    hotel_id INT REFERENCES hotels(hotel_id) ON DELETE CASCADE,
    flight_id INT REFERENCES flights(flight_id) ON DELETE CASCADE,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL(10, 2)
);

CREATE TABLE reviews (
    review_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    hotel_id INT REFERENCES hotels(hotel_id) ON DELETE CASCADE,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE payments (
    payment_id SERIAL PRIMARY KEY,
    booking_id INT REFERENCES bookings(booking_id) ON DELETE CASCADE,
    amount DECIMAL(10, 2),
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method VARCHAR(50)
);

CREATE TABLE packages (
    package_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2),
    duration_days INT,
    hotel_id INT REFERENCES hotels(hotel_id) ON DELETE SET NULL
);

INSERT INTO users (username, email, password) VALUES
('john_doe', 'john@example.com', 'password123'),
('jane_smith', 'jane@example.com', 'password456'),
('alice_johnson', 'alice@example.com', 'password789');

INSERT INTO destinations (name, country, description) VALUES
('Paris', 'France', 'The city of light, known for its art, fashion, and culture.'),
('Tokyo', 'Japan', 'A bustling metropolis blending tradition and modernity.'),
('New York', 'USA', 'The Big Apple, famous for its skyline and vibrant culture.');

INSERT INTO hotels (name, destination_id, rating, price_per_night) VALUES
('Hotel de Paris', 1, 4.5, 250.00),
('Shinjuku Granbell Hotel', 2, 4.0, 150.00),
('The Standard, High Line', 3, 4.2, 300.00);

INSERT INTO flights (airline, from_destination_id, to_destination_id, departure_time, arrival_time, price) VALUES
('Air France', 1, 2, '2024-12-01 10:00:00', '2024-12-01 18:00:00', 600.00),
('Japan Airlines', 2, 3, '2024-12-05 09:00:00', '2024-12-05 17:00:00', 700.00),
('Delta Airlines', 3, 1, '2024-12-10 08:00:00', '2024-12-10 16:00:00', 550.00);

INSERT INTO bookings (user_id, hotel_id, flight_id, total_price) VALUES
(1, 1, 1, 850.00),
(2, 2, 2, 850.00),
(3, 3, 3, 850.00);

INSERT INTO reviews (user_id, hotel_id, rating, comment) VALUES
(1, 1, 5, 'Amazing experience! Highly recommend.'),
(2, 2, 4, 'Great hotel, but a bit noisy.'),
(3, 3, 5, 'Loved the view and the service!');

INSERT INTO payments (booking_id, amount, payment_method) VALUES
(1, 850.00, 'Credit Card'),
(2, 850.00, 'PayPal'),
(3, 850.00, 'Debit Card');

INSERT INTO packages (name, description, price, duration_days, hotel_id) VALUES
('Romantic Getaway', 'A perfect package for couples.', 1200.00, 5, 1),
('Cultural Experience', 'Explore the rich culture of Japan.', 1000.00, 7, 2),
('City Adventure', 'Discover the best of New York City.', 1500.00, 6, 3);


SELECT * FROM users;
SELECT * FROM reviews;
SELECT * FROM payments;
SELECT * FROM packages;
SELECT * FROM hotels;
SELECT * FROM flights;
SELECT * FROM destinations;
SELECT * FROM bookings;
