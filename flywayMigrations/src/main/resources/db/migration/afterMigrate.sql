INSERT INTO users
  (username, firstName, lastName, admin)
VALUES
  ('username1', 'firstname1', 'lastname1', false),
  ('username2', 'firstname2', 'lastname2', false),
  ('username3', 'firstname3', 'lastname3', true),
  ('username4', 'firstname4', 'lastname4', false);

INSERT INTO posts
  (title, content, userid)
VALUES
  ('title1', 'content1', 1),
  ('title2', 'content2', 2),
  ('title3', 'content3', 3),
  ('title4', 'content4', 4);