CREATE TABLE IF NOT EXISTS 'properties' (
  'invitationID' INTEGER DEFAULT NULL
);

INSERT INTO properties ('invitationID') VALUES ('1030011');

CREATE TABLE IF NOT EXISTS 'rooms' (
  id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  name TEXT NOT NULL UNIQUE,
  password TEXT DEFAULT NULL,
  type TEXT NOT NULL
);

INSERT INTO rooms ('id', 'name', 'password', 'type') VALUES (1, 'Main', NULL, 'HTML');

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

INSERT INTO users ('id', 'username', 'password', 'role', 'faculty', 'enabled', 'first_name', 'last_name') VALUES
  (2, 'admin', 'admin1234+', 'ROLE_ADMIN', 'Faculty of Information Technology', '1', 'Admin', 'Admin');


CREATE TABLE IF NOT EXISTS localization (
  id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  key TEXT NOT NULL,
  value TEXT NOT NULL,
  locale TEXT NOT NULL,
  dialog_name TEXT NOT NULL
);

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
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J13', 'Факультет информационных технологий', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J14', 'Факультет математики', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J15', 'Институт детства', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J16', 'Создать новый профиль', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J17', 'Назад', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J18', 'Поле не может быть пустым', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J19', 'Имя должно состоять из имени и фамилии, и они должны начинаться с большой буквы.', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J20', 'Пароль должен быть не менее 6 символов.', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J21', 'Код с картинки неверен.', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J22', 'Пользователь с таким именем уже существует.', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J23', 'Неправильный пригласительный ID.', 'ru', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J24', 'Психолого-педагогический факультет', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J25', 'Факультет изобразительного искусства', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J26', 'Факультет иностранных языков', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J27', 'Факультет коррекционной педагогики', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J28', 'Институт музыки, театра и хореографии', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J29', 'Факультет социальных наук', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J30', 'Факультет физики', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J31', 'Факультет физической культуры', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J32', 'Факультет философии человека', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J33', 'Факультет химии', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J34', 'Факультет русского языка как иностранного', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J35', 'Факультет технологии и предпринимательства', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J36', 'Факультет географии', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J37', 'Факультет безопасности жизнедеятельности', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J38', 'Факультет управления', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J39', 'Выборгский филиал', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J40', 'Факультет экономики', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J41', 'Юридический факультет', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J42', 'Общеуниверситетские курсы по выбору', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J43', 'Факультет биологии', 'ru', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J44', 'Филологический факультет', 'ru', 'faculties');
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
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J11', 'Retype password', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J12', 'Invitation ID', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J13', 'Faculty of Information Technology', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J14', 'Faculty of Mathematics', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J15', 'Institute of childhood', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J16', 'Create account', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J17', 'Go back', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J18', 'Field cannot be empty.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J19', 'Full name should consist from first and last name, and they should begin with a capital letter.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J20', 'Password should be less than 6 characters.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J21', 'Captcha is wrong.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J22', 'User with that name is already exist.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J23', 'Invitation ID is wrong.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J24', 'Faculty of Psychology and Education', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J25', 'Faculty of Fine Arts', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J26', 'Faculty of Foreign Languages', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J27', 'Faculty Correctional Pedagogy', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J28', 'Institute of Music, theater and choreography', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J29', 'Faculty of Social Sciences', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J30', 'Faculty of Physics', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J31', 'Faculty of Physical Culture', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J32', 'Faculty of Human Philosophy', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J33', 'Faculty of Chemistry', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J34', 'Faculty of Russian as a foreign language', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J35', 'Faculty of Technology and Entrepreneurship', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J36', 'Faculty of Geography', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J37', 'Faculty of Life Safety', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J38', 'Faculty of Management', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J39', 'Vyborg branch', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J40', 'Faculty of Economics', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J41', 'Faculty of law', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J42', 'General university of elective courses', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J43', 'Faculty of Biology', 'en', 'faculty');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J44', 'Faculty of Philology', 'en', 'faculties');
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