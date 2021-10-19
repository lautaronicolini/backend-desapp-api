--this file contains data for tables
spring.h2.console.enabled=true

INSERT INTO oauth_client_details
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ('client_android', '$2a$04$rRIQoDbmYiQf9QlKHJ3BXu30vgFv1QcLVJoKq9335mA7BKwiFplv6', 'foo,read,write','password,client_credentials,refresh_token', null, null, 36000, 36000, null, true),
    ('client_ios', '$2a$04$AnYUYnPT66TLY5fRh29FaO6Bj7bZf9g5HTlHdMF0cODpYgV7eliie', 'foo,read,write','password,client_credentials,refresh_token', null, null, 36000, 36000, null, true);

INSERT INTO user (user_id, name, lastName, email, address, password, CVU, walletAddress)
VALUES ('1','Pepe', 'Golondrina','user@example.com', 'fakeStreet123','$2a$04$uBjpP8uyZ9I1SFhuT8L2ousq8V8OVla0AoooWDOXh589A7zfNG6QS', 1234567890123456789012,12345678)


