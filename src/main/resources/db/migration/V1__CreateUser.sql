CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users (
  id UUID NOT NULL DEFAULT uuid_generate_v4(),
  created_at timestamp NOT NULL DEFAULT now(),
  updated_at timestamp NOT NULL DEFAULT now(),
  "name" VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  "password" VARCHAR NOT NULL,
  CONSTRAINT "PK_userId" PRIMARY KEY (id),
  CONSTRAINT "UQ_userEmail" UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS projects (
  id UUID NOT NULL DEFAULT uuid_generate_v4(),
  created_at timestamp NOT NULL DEFAULT now(),
  updated_at timestamp NOT NULL DEFAULT now(),
  owner_id UUID NOT NULL,
  "name" VARCHAR NOT NULL,
  "description" VARCHAR,
  CONSTRAINT "PK_projectId" PRIMARY KEY (id),
  CONSTRAINT "FK_projectOwnerId" FOREIGN KEY (owner_id) REFERENCES public.users(id),
  CONSTRAINT "UQ_projectOwnerId_projectName" UNIQUE (owner_id, "name")
);

CREATE TABLE IF NOT EXISTS projects_members (
  id UUID NOT NULL DEFAULT uuid_generate_v4(),
  created_at timestamp NOT NULL DEFAULT now(),
  project_id UUID NOT NULL,
  user_id UUID NOT NULL,
  CONSTRAINT "PK_projectMemberId" PRIMARY KEY (id),
  CONSTRAINT "FK_projectMemberProjectId" FOREIGN KEY (project_id) REFERENCES public.projects(id),
  CONSTRAINT "FK_projectMemberUserId" FOREIGN KEY (user_id) REFERENCES public.users(id),
  CONSTRAINT "UQ_projectMembersProjectId_projectMemberUserId" UNIQUE (project_id, user_id)
);

CREATE TABLE IF NOT EXISTS stages (
  id UUID NOT NULL DEFAULT uuid_generate_v4(),
  created_at timestamp NOT NULL DEFAULT now(),
  updated_at timestamp NOT NULL DEFAULT now(),
  "name" VARCHAR NOT NULL,
  project_id UUID NOT NULL,
  CONSTRAINT "PK_stageId" PRIMARY KEY (id),
  CONSTRAINT "FK_stageProjectId" FOREIGN KEY (project_id) REFERENCES public.projects(id)
);

CREATE TABLE IF NOT EXISTS tasks (
  id UUID NOT NULL DEFAULT uuid_generate_v4(),
  created_at timestamp NOT NULL DEFAULT now(),
  updated_at timestamp NOT NULL DEFAULT now(),
  title VARCHAR NOT NULL,
  "description" VARCHAR,
  "priority" VARCHAR,
  due_date TIMESTAMP,
  stage_id UUID NOT NULL,
  CONSTRAINT "PK_taskId" PRIMARY KEY (id),
  CONSTRAINT "FK_taskStageId" FOREIGN KEY (stage_id) REFERENCES public.stages(id)
);

CREATE TABLE IF NOT EXISTS tasks_members (
  id UUID NOT NULL DEFAULT uuid_generate_v4(),
  created_at timestamp NOT NULL DEFAULT now(),
  task_id UUID NOT NULL,
  user_id UUID NOT NULL,
  CONSTRAINT "PK_taskMemberId" PRIMARY KEY (id),
  CONSTRAINT "FK_taskMemberTaskId" FOREIGN KEY (task_id) REFERENCES public.tasks(id),
  CONSTRAINT "FK_taskMemberUserId" FOREIGN KEY (user_id) REFERENCES public.users(id)
);