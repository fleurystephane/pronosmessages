TRUNCATE conversation CASCADE;
TRUNCATE message CASCADE;

INSERT INTO conversation (id, userId, creatorId)
values (1, 4, 1);
INSERT INTO message (id, text, authorId, creationDate, conversation_id, deleted, message_order)
values (1, 'Coucou1', 1, '2023-03-05T10:00:00.003', 1, false, 3);
INSERT INTO message (id, text, authorId, creationDate, conversation_id, deleted, message_order)
values (2, 'Coucou2', 1, '2023-03-04T10:00:00.001', 1, false, 2);
INSERT INTO message (id, text, authorId, creationDate, conversation_id, deleted, message_order)
values (3, 'Coucou3', 1, '2023-03-07T10:00:00.002', 1, false, 1);