SET SCHEMA 'jviewer_main';

-- User --
CREATE TABLE IF NOT EXISTS "user" (
  username TEXT PRIMARY KEY,
  password TEXT NOT NULL,
  role TEXT NOT NULL DEFAULT 'ROLE_USER'
);

-- INSERT INTO "jviewer_main"."user" (username, password, role, department, first_name, last_name) VALUES ('admin', '$2a$11$z8Ym9XENSKNkVTy.XL5/aOzORzYyvtlc/b22PsQQBmX2p7Dlm/XwK', 'ROLE_ADMIN', 'IT', 'Admin', 'Admin');

-- Config --
DROP TABLE IF EXISTS config;
CREATE TABLE config (
  name	TEXT UNIQUE NOT NULL,
  value	TEXT DEFAULT NULL,
  description TEXT NOT NULL
);
INSERT INTO config (name, value, description) VALUES ('invitationID', '$2a$11$xHcnk0MN5oZ9ROJIUlWmW.HNyMj5pu.slIvs4oISWhvw7ijHP0nL2', 'Value of invitation id for admin registration');

-- Rooms --
DROP TABLE IF EXISTS room;
CREATE TYPE ROOM_TYPE as ENUM ('HTML', 'JAVA');

CREATE TABLE IF NOT EXISTS room (
  name TEXT PRIMARY KEY,
  type ROOM_TYPE NOT NULL
);

-- Quiz --
CREATE TABLE IF NOT EXISTS quiz (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  questions_to_answer_to_pass INTEGER NOT NULL
);
INSERT INTO quiz (id, name, questions_to_answer_to_pass) VALUES (26, 'Java SE junior quiz', 2);
INSERT INTO quiz (id, name, questions_to_answer_to_pass) VALUES (27, 'Sample quiz', 3);

-- Question --
CREATE TABLE IF NOT EXISTS question (
  id SERIAL PRIMARY KEY,
  quiz_id INTEGER NOT NULL,
  text TEXT NOT NULL,
  answers_type TEXT NOT NULL,
  correct_textual_answer TEXT NOT NULL
);
INSERT INTO question (id, quiz_id, text, answers_type, correct_textual_answer) VALUES (39, 26, 'What is better in sense of search: ArrayList or LinkedList?', 'RADIO_BUTTON', '');
INSERT INTO question (id, quiz_id, text, answers_type, correct_textual_answer) VALUES (59, 26, 'Choose JMS implementations from the list.', 'CHECK_BOX', '');
INSERT INTO question (id, quiz_id, text, answers_type, correct_textual_answer) VALUES (61, 26, 'What is not the method of the Object class?', 'RADIO_BUTTON', '');
INSERT INTO question (id, quiz_id, text, answers_type, correct_textual_answer) VALUES (62, 27, 'To be or not to be?', 'RADIO_BUTTON', '');
INSERT INTO question (id, quiz_id, text, answers_type, correct_textual_answer) VALUES (63, 27, 'Who lives in a pineapple under the sea?', 'CHECK_BOX', '');
INSERT INTO question (id, quiz_id, text, answers_type, correct_textual_answer) VALUES (64, 27, '2 + 2 = ?', 'TEXT_FIELD', '4');

-- Answer --
CREATE TABLE IF NOT EXISTS answer (
  id SERIAL PRIMARY KEY,
  question_id INTEGER NOT NULL,
  text TEXT NOT NULL,
  correct BOOLEAN NOT NULL
);
INSERT INTO answer (id, question_id, text, correct) VALUES (25, 39, 'ArrayList', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (26, 39, 'LinkedList', FALSE);
INSERT INTO answer (id, question_id, text, correct) VALUES (34, 59, 'TIBCO EMS', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (35, 59, 'Websphere MQ', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (36, 59, 'Solace JMS', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (37, 59, 'Fiorano MQ', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (38, 59, 'Sonic MQ', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (39, 59, 'Active MQ', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (40, 61, 'clone', FALSE);
INSERT INTO answer (id, question_id, text, correct) VALUES (41, 61, 'equals', FALSE);
INSERT INTO answer (id, question_id, text, correct) VALUES (42, 61, 'finalize', FALSE);
INSERT INTO answer (id, question_id, text, correct) VALUES (43, 61, 'close', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (44, 62, 'To be', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (45, 62, 'Not to be', FALSE);
INSERT INTO answer (id, question_id, text, correct) VALUES (46, 63, 'Sponge Bob Square Pants', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (47, 63, 'Earthworm Jim', FALSE);
INSERT INTO answer (id, question_id, text, correct) VALUES (48, 63, 'Princess Nesmeyana', FALSE);

-- Localization --
DROP TABLE IF EXISTS localization;

CREATE TABLE localization (
  id SERIAL PRIMARY KEY,
  key TEXT UNIQUE NOT NULL,
  value TEXT NOT NULL,
  locale TEXT NOT NULL,
  dialog_name TEXT NOT NULL
);

-- Russian --
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J1', 'Добро пожаловать на JViewer', 'ru', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J2', 'Создать новый профиль', 'ru', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J3', 'Войти', 'ru', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J4', 'Имя', 'ru', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J5', 'Пароль', 'ru', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J6', 'Регистрация прошла успешно, теперь вы можете войти.', 'ru', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J7', 'Создать новый профиль', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J8', 'Введите ваши данные', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J9', 'Имя', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J10', 'Пароль', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J11', 'Повторите пароль', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J12', 'Пригласительный ID', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J13', 'Факультет', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J14', 'Минимальная длина имени составляет 5 символов', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J16', 'Создать новый профиль', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J17', 'Назад', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J18', 'Поле не может быть пустым', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J19', 'Минимальная длина имени составляет 5 символов', 'ru', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J20', 'Пароль должен быть не менее 6 символов.', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J21', 'Код с картинки неверен.', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J22', 'Пользователь с таким именем уже существует.', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J23', 'Неправильный пригласительный ID.', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J45', 'Управление комнатами', 'ru', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J46', 'Управление комнатами', 'ru', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J47', 'С возвращением', 'ru', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J49', 'Создать новую комнату', 'ru', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J50', 'Удалить', 'ru', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J51', 'Войти', 'ru', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J52', 'Выйти', 'ru', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J53', 'Создать новую комнату', 'ru', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J54', 'Управление комнатами', 'ru', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J55', 'Имя', 'ru', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J56', 'Создать', 'ru', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J57', 'Назад', 'ru', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J58', 'Удалить выбранную комнату', 'ru', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J59', 'Управление комнатами', 'ru', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J60', 'Вы действительно хотите удалить комнату', 'ru', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J61', 'Да', 'ru', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J62', 'Нет', 'ru', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J63', 'Текущая комната', 'ru', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J64', 'Выполнить', 'ru', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J65', 'Очистить', 'ru', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J66', 'Список комнат', 'ru', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J67', 'Выйти', 'ru', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J68', 'JViewer. Комната:', 'ru', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J69', 'Сохранить результат', 'ru', 'viewer');

-- English --
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J1', 'Welcome to JViewer', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J2', 'Create new account', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J3', 'Login', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J4', 'Username', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J5', 'Password', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J6', 'Registration was successful, now you can login.', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J7', 'Create new account', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J8', 'Enter your data', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J9', 'Username', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J10', 'Password', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J11', 'Confirm password', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J12', 'Invitation ID', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J13', 'Department', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J14', 'Name must be less than 5 characters', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J16', 'Create account', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J17', 'Go back', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J18', 'Field cannot be empty.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J19', 'Name must be less than 5 characters', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J20', 'Password must be less than 6 characters.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J21', 'Captcha is wrong.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J22', 'User with that name is already exist.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J23', 'Invitation ID is wrong.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J45', 'Rooms management page', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J46', 'Rooms management page', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J47', 'Welcome back', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J49', 'Create new room', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J50', 'Delete', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J51', 'Enter', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J52', 'Logout', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J53', 'Create new room', 'en', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J54', 'Rooms management page', 'en', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J55', 'Room name', 'en', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J56', 'Create', 'en', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J57', 'Go back', 'en', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J58', 'Delete selected room', 'en', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J59', 'Rooms management page', 'en', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J60', 'Are you sure that you want to delete', 'en', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J61', 'Yes', 'en', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J62', 'No', 'en', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J63', 'Current room', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J64', 'Execute', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J65', 'Clear', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J66', 'Rooms', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J67', 'Logout', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J68', 'JViewer. Room:', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J69', 'Export result', 'en', 'viewer');

-- News --
CREATE TABLE IF NOT EXISTS news (
  id SERIAL PRIMARY KEY,
  topic TEXT NOT NULL,
  text TEXT NOT NULL,
  date DATE NOT NULL,
  author TEXT NOT NULL
)