INSERT INTO users(user_id,user_name,user_phone_no,user_email_id) VALUES(1,'Rajeev Singh','7623467423','rajeev@gmail.com');
INSERT INTO users(user_id,user_name,user_phone_no,user_email_id) VALUES(2,'Geetika Joshi','9845673452','geetika@gmail.com');

INSERT INTO hotels(hotel_id,hotel_name, hotel_contact_no, hotel_address, occupancies, minimum_price, hotel_ratings) VALUES (1,'Hotel Indraprasth','8867896754','Near AIR Office, Shramik Nagar, Indore','single',700,3.2);
INSERT INTO hotels(hotel_id,hotel_name, hotel_contact_no, hotel_address, occupancies, minimum_price, hotel_ratings) VALUES (2,'Hotel Ashirwad','8902456123','Gandhi Nagar,Gujarat','double',1000,4.5);

INSERT INTO feedbacks(feedback_id, user_id, hotel_id, rating, review) VALUES (1,1,1,4,'Located just near the fort in city(perfect location).');