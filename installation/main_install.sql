CREATE TABLE IF NOT EXISTS 'properties' (
  'invitationID' INTEGER DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS 'rooms' (
  id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  name TEXT NOT NULL UNIQUE,
  password TEXT DEFAULT NULL,
  type TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
  id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  username TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL,
  role TEXT NOT NULL DEFAULT 'ROLE_USER',
  faculty TEXT NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT 1,
  first_name TEXT NOT NULL,
  last_name	TEXT NOT NULL
);

DROP TABLE localization;

CREATE TABLE localization (
  id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  key TEXT NOT NULL,
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
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J48', 'Выберите действие', 'ru', 'rooms');
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
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J63', 'Текущая комната:', 'ru', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J64', 'Выполнить', 'ru', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J65', 'Очистить', 'ru', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J66', 'Список комнат', 'ru', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J67', 'Выйти', 'ru', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J68', 'JViewer. Комната:', 'ru', 'viewer');

-- English --
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J1', 'Welcome to JViewer', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J2', 'Create new account', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J3', 'Login', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J4', 'Full name', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J5', 'Password', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J6', 'Registration was successful, now you can login.', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J7', 'Create new account', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J8', 'Enter your data', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J9', 'Full name', 'en', 'registration');
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
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J48', 'What do you want to do?', 'en', 'rooms');
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
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J63', 'Current room:', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J64', 'Execute', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J65', 'Clear', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J66', 'Rooms', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J67', 'Logout', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J68', 'JViewer. Room:', 'en', 'viewer');