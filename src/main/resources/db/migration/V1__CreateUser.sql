CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users (
  id UUID NOT NULL DEFAULT uuid_generate_v4(),
  created_at timestamp NOT NULL DEFAULT now(),
  username VARCHAR NOT NULL UNIQUE,
  "name" VARCHAR NOT NULL,
  "password" VARCHAR NOT NULL,
  CONSTRAINT "PK_userId" PRIMARY KEY (id),
  CONSTRAINT "UQ_userUsername" UNIQUE (username)
);