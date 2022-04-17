INSERT INTO profile(auth) VALUES ('ROLE_USER');
INSERT INTO profile(auth) VALUES ('ROLE_PACKAGER');
INSERT INTO profile(auth) VALUES ('ROLE_TRANSPORTER');

INSERT INTO users(email, password, name) VALUES('yvestaba@hotmail.com', '$2a$10$ijlcAQ4x9pwq.Tt5uhZMAO4aifFxj8iUXck3SCJitOJWYayEpanz.', 'User');
INSERT INTO users(email, password, name) VALUES('packager@hotmail.com', '$2a$10$ijlcAQ4x9pwq.Tt5uhZMAO4aifFxj8iUXck3SCJitOJWYayEpanz.', 'Packager');
INSERT INTO users(email, password, name) VALUES('transporter@hotmail.com', '$2a$10$ijlcAQ4x9pwq.Tt5uhZMAO4aifFxj8iUXck3SCJitOJWYayEpanz.', 'Transporter');

INSERT INTO user_profile(user_email, profile_id) VALUES ('yvestaba@hotmail.com', 1);
INSERT INTO user_profile(user_email, profile_id) VALUES ('packager@hotmail.com', 2);
INSERT INTO user_profile(user_email, profile_id) VALUES ('transporter@hotmail.com', 3);