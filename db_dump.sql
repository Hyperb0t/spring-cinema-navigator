INSERT INTO public.film (id, description, duration, imagelink, title) VALUES (2, 'Самый ожидаемый блокбастер года, снятый лично Ником Вуйчичем', extract( epoch from interval '0 years 0 mons 0 days 3 hours 20 mins 0.00 secs'), 'https://www.eknightmedia.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/b/a/ban23894_01.jpg', 'Боец');
INSERT INTO public.film (id, description, duration, imagelink, title) VALUES (6, 'Джонни Синс однажды просто решил включить компьютер, и тут понеслось', extract( epoch from interval '0 years 0 mons 0 days 2 hours 50 mins 0.00 secs'), 'https://i.ytimg.com/vi/KEkrWRHCDQU/maxresdefault.jpg', 'Хакермэн');
INSERT INTO public.film (id, description, duration, imagelink, title) VALUES (5, 'А сможешь ли ты, зритель, почувствовать истинный смысл, который гений хотел донести?', extract( epoch from interval '0 years 0 mons 0 days 2 hours 10 mins 0.00 secs'), 'https://kpfu.ru/portal/docs/F_696938745/IMG_4444.jpg', 'Опять двойка');
INSERT INTO public.film (id, description, duration, imagelink, title) VALUES (4, 'Невелико разнообразие в нашей жизни...', extract( epoch from interval '0 years 0 mons 0 days 0 hours 40 mins 0.00 secs'), 'https://upload.wikimedia.org/wikipedia/ru/b/b5/Andrei_Tarkovsky.jpg', 'Абстрактный фильм Тарковского');
INSERT INTO public.film (id, description, duration, imagelink, title) VALUES (3, 'Шедевр отечественного кинематографа', extract( epoch from interval '0 years 0 mons 0 days 1 hours 26 mins 0.00 secs'), 'https://st.kp.yandex.net/images/film_iphone/iphone360_373314.jpg', 'Зеленый слоник');

INSERT INTO public.seance (id, endtime, price, starttime, film_id) VALUES (6, '2019-12-14 15:00:00.000000', 200, '2019-12-14 13:20:00.000000', 4);
INSERT INTO public.seance (id, endtime, price, starttime, film_id) VALUES (11, '2019-12-14 22:20:00.000000', 170, '2019-12-14 19:20:00.000000', 5);
INSERT INTO public.seance (id, endtime, price, starttime, film_id) VALUES (4, '2019-12-15 13:20:00.000000', 140, '2019-12-15 10:10:00.000000', 6);
INSERT INTO public.seance (id, endtime, price, starttime, film_id) VALUES (1, '2019-12-04 18:20:00.000000', 200, '2019-12-04 15:00:00.000000', 2);
INSERT INTO public.seance (id, endtime, price, starttime, film_id) VALUES (5, '2019-12-13 21:00:00.000000', 190, '2019-12-13 18:00:00.000000', 3);

INSERT INTO public."user" (id, bonuses, email, password, role) VALUES (1, null, 'first@reg.test', '$2a$10$FIQS1JQcC.kD8StHFRIySeFgPV8TGYUz0i6018Ti8C2TULIYYqtSW', 'USER');


