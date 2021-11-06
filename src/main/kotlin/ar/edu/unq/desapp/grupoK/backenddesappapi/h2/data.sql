USE  jdbc:h2:mem:usersdb;

INSERT INTO User (user_id, name, lastName, email, address, password, CVU, walletAddress)
VALUES ('1','Pepe', 'Golondrina','user@example.com', 'fakeStreet123','$2a$04$uBjpP8uyZ9I1SFhuT8L2ousq8V8OVla0AoooWDOXh589A7zfNG6QS', 1234567890123456789012,12345678)

