# Java To Do List API

A Java back-end API to manager to-do tasks.

# Features

## Users

**Create user**
- [x] Should be able to create an user sendind name, email and password.
- [x] Shouldn't accept weak passwords (less than 10 characters or without numbers/special characters).
- [x] Shouldn't create two users with same email.
- [x] Shuoldn't create an user with invalid email.

***

**Authenticate user**
- [x] Should be able to authenticate an user and create an authorization token.
- [x] The authorization token should be valid for 1 or 30 days, according with user option.
- [x] Must block user after 3 invalid authentication attempts at last 24 hours.

## Project

**Create project**
- [ ] Should be able to create a project containing:
  - name;
  - description (optional);
  - members;
- [ ] Must request authentication;
- [ ] Shouldn't create two projects with same name for same user.

***

**Add members**
- [ ] Should be able to add members to projetc by emails.
- [ ] If doesn't exists an user with provided email, or if the found user already is a project member, no action need to be taken.
- [ ] Must be a project owner to add a member.

***

**Remove members**

- [ ] Should be able to remove members from project by emails.
- [ ] If doesn't exists an user with provided emails, or if the found user isn't a project member, no action need to be taken.
- [ ] Must be a project owner to remove a member.

## Task stages

**Create stage**

- [ ] Should be able to create a stage containing:
  - name;
- [ ] Must be a project member to create a stage.

***

**Update stage**

- [ ] Should be able to update stage.
- [ ] Must be a project member to update a stage.

***

**Delete stage**

- [ ] Should be able to delete a stage.
- [ ] Shoudn't delete a stage that still have tasks on it.
- [ ] Must be a project member to delete stage.

## Tasks

**Create task**

- [ ] Should be able to create a task containing:
  - title;
  - description (optional);
  - priority (optional);
  - due date (optional);
  - members;
- [ ] Must be a project member to create a task.

***

**Update task**

- [ ] Should be able to update a task.
- [ ] Must be a task member.

***

**Delete task**

- [ ] Should be able to delete a task.
- [ ] Must be a project owner or task member to delete task.