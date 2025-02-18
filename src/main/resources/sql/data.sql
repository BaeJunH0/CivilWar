INSERT INTO member (id, email, password, nickname, admin)
VALUES (DEFAULT, 'admin@example.com', '00000000-0017-0842-ffff-ffffffe8f7bd', 'admin', true);

INSERT INTO member (id, email, password, nickname, admin)
VALUES
    (DEFAULT, 'user1@example.com', '00000000-0017-0842-ffff-ffffffe8f7be', 'user1', false),
    (DEFAULT, 'user2@example.com', '00000000-0017-0842-ffff-ffffffe8f7bf', 'user2', false),
    (DEFAULT, 'user3@example.com', '00000000-0017-0842-ffff-ffffffe8f7c0', 'user3', false),
    (DEFAULT, 'user4@example.com', '00000000-0017-0842-ffff-ffffffe8f7c1', 'user4', false),
    (DEFAULT, 'user5@example.com', '00000000-0017-0842-ffff-ffffffe8f7c2', 'user5', false),
    (DEFAULT, 'user6@example.com', '00000000-0017-0842-ffff-ffffffe8f7c3', 'user6', false),
    (DEFAULT, 'user7@example.com', '00000000-0017-0842-ffff-ffffffe8f7c4', 'user7', false),
    (DEFAULT, 'user8@example.com', '00000000-0017-0842-ffff-ffffffe8f7c5', 'user8', false),
    (DEFAULT, 'user9@example.com', '00000000-0017-0842-ffff-ffffffe8f7c6', 'user9', false),
    (DEFAULT, 'user10@example.com', '00000000-0017-0842-ffff-ffffffe8f7c7', 'user10', false);

INSERT INTO team (id, name, owner)
VALUES
    (DEFAULT, 'Team1', 'admin'),
    (DEFAULT, 'Team2', 'admin'),
    (DEFAULT, 'Team3', 'admin'),
    (DEFAULT, 'Team4', 'admin'),
    (DEFAULT, 'Team5', 'admin'),
    (DEFAULT, 'Team6', 'admin'),
    (DEFAULT, 'Team7', 'admin'),
    (DEFAULT, 'Team8', 'admin'),
    (DEFAULT, 'Team9', 'admin'),
    (DEFAULT, 'Team10', 'admin'),
    (DEFAULT, 'Team11', 'admin');

