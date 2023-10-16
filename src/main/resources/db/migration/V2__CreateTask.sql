CREATE TABLE IF NOT EXISTS tasks (
  id UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
  created_at TIMESTAMP NOT NULL DEFAULT now(),
  user_id UUID NOT NULL,
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
  "description" VARCHAR,
  title VARCHAR NOT NULL,
  start_time TIMESTAMP,
  finish_time TIMESTAMP,
  "priority" VARCHAR
)