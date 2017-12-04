﻿Приветствуем в репозитории проекта videoLib!
********************************************

Проект создается в рамках программы обучения NetCracker.
Проект представляет из себя программу, необходимую для создания каталога DVD-дисков.
Проект поможет с выбором - какой фильм посмотреть сегодня, будет полезен, если Вы забудете,
кому отдали тот или иной диск. Приятного пользования!



О проекте
*********

Проект состоит из следующих основных файлов:
---------------------------------------------



Название файла  | Содержание файла
----------------|----------------------
Client.java     | В этом файле описан класс, реализующий работу со списком клиентов (людей, арендующих диски)
Disk.java       | Класс, реализующий работу с объектами "Диск", поля которых содержат полную информацию о дисках
MainForm.form   | Файл, содержащий в себе пользовательский интерфейс
MainForm.java   | Главный класс, обеспечивающий работу приложения 



Инструкция по работе с программой
*********************************

После запуске приложения, пользователь видит главное окно с базой загруженной из последнего открытого данным приложением файла. Если файл не был найден по сохраненному пути, то автоматически будет создана пустая база и этот файл. В левой части окна находится список каталогизированных дисков. В правой части окна находится список клиентов. При выборе диска по названию, в полях по середине отобразиться информация о диске. Если пользователь хочет открыть другую базу, то он может сделать это, выбрав соответствующий пункт меню по нажатию на кнопку "Файл" в левом верхнем углу. Там же он может выбрать пункт меню, которое соединит текущую открытую базу с выбранной. Одинаковые фильмы добавлены не будут.



Добавление фильма:
------------------

Для добавления фильма в каталог необходимо нажать кнопку "Добавить диск". При этом поля, содержащие информацию о фильме очистятся, а в конце списка добавится строчка, в которой будет присутствовать только ID диска, присваиваемый ему автоматически. Строка с добавленным фильмом будет автоматически выбрана, поэтому можно сразу приступить к заполнению информацию о фильме в полях справа от таблицы (подробнее об изменении информации о диске см. раздел "Изменение информации о фильме").


Информация о диске, доступная к заполнению:
-------------------------------------------


Название поля     | Содержание поля
------------------|----------------------
Название          | В это поле вносится оригинальное название фильма
Русское название  | Это поле содержит русское название фильма
Режиссёр(ы)       | Поле содержит информацию о режиссёре (если их несколько — вводятся через запятую)
Жанры             | Информация о жанрах, к которым имеет отношение фильм (если несколько — вводятся через запятую)
Длительность      | Поле содержит информацию о длительности фильма в минутах
Год выхода        | Содержит дату выхода в прокат фильма в формате гггг (например, 2004)
Рейтинг           | Необходимо для заполнения рейтинга, присвоенного IMDb* в диапазоне от 0.1 до 10.0
Языки             | Поле содержит информацию о языках (звуковых дорожках их содержащих), доступных для выбора в фильме
Страны            | Поле содержит информацию о стране, в которой был снят фильм (если несколько — вводятся через запятую)
В главных ролях   | Поле содержит список главных актёров, снимавшихся в фильме, через запятую
Описание          | Самое большое поле. Служит для анонса фильма (краткое описание сюжета)

*Internet Movie Database (IMDb, в переводе с англ. — «Интернет-база кинофильмов») — крупнейшая в мире база данных и веб-сайт о кинематографе. 


Изменение информации о фильме:
------------------------------

Для внесение изменений в описание фильма необходимо выбрать необходимый фильм в списке, информация о нем отобразиться в полях справа. Изменения вносятся в эти поля и сразу же автоматически сохраняются в базе. 


Удаление фильма:
----------------
 
Для удаления фильма из каталога необходимо выбрать фильм в списке и нажать кнопку "Удалить диск".


Сохранение в файл:
------------------------------

Чтобы сохранить новую информацию с базой в файл, нужно нажать кнопку "Сохранить в файл". База будет сохранена по тому же пути, откуда она была открыта.


Работа с клиентами:
------------------------------

При нажатии на кнопку "Выдать диск" выбранный диск в списке слева будет выдан выбранному клиенту в списке справа. Информация о клиенте отобразится в соответствующем поле. Чтобы забрать диск у клиента, нужно нажать кнопку "Диск сдан".
Чтобы добавить нового клиента в базу, нужно нажать на кнопку "Добавить клиента" и занести информацию о нем в соответствующие поля. Чтобы удалить клиента из базы, нужно нажать кнопку "Удалить клиента".
