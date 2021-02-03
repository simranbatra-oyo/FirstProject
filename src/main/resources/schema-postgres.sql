DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users(user_id serial PRIMARY KEY,password VARCHAR(20), user_name VARCHAR(20),user_phone_no VARCHAR(10),user_email_id VARCHAR(20));

DROP TABLE IF EXISTS hotels CASCADE;
DROP TYPE IF EXISTS av_oc CASCADE;
CREATE TYPE av_oc AS ENUM ('single', 'double');
CREATE TABLE hotels(hotel_id serial PRIMARY KEY, hotel_name VARCHAR(20),hotel_contact_no VARCHAR(10),hotel_address VARCHAR(40),occupancies av_oc,minimum_price float,hotel_ratings float);

DROP TABLE IF EXISTS feedbacks;
CREATE TABLE feedbacks(feedback_id serial PRIMARY KEY,user_id serial,FOREIGN KEY (user_id) REFERENCES users(user_id),hotel_id serial,FOREIGN KEY (hotel_id) REFERENCES hotels(hotel_id),rating float, review VARCHAR(100));