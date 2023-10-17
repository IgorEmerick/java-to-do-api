CREATE TABLE IF NOT EXISTS tasks (
  id UUID NOT NULL DEFAULT uuid_generate_v4(),
  created_at TIMESTAMP NOT NULL DEFAULT now(),
  user_id UUID NOT NULL,
  "description" VARCHAR,
  title VARCHAR NOT NULL,
  start_time TIMESTAMP,
  finish_time TIMESTAMP,
  "priority" VARCHAR,
  CONSTRAINT "PK_taskId" PRIMARY KEY (id),
  CONSTRAINT "FK_tasksUser" FOREIGN KEY (user_id) REFERENCES public.users(id)
)