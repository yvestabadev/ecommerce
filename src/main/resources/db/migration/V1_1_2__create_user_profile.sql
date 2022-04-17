CREATE TABLE user_profile (
user_email VARCHAR(127),
profile_id VARCHAR(15),
FOREIGN KEY (user_email) REFERENCES users (email),
FOREIGN KEY (profile_id) REFERENCES profile (id)
);