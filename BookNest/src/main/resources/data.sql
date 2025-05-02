INSERT INTO public.roles(id, value, description)VALUES ('ADMIN', 'administrador', 'administrador del sistema')
    ON CONFLICT (id) DO UPDATE SET value = excluded.value, description = excluded.description;

INSERT INTO public.roles(id, value, description)VALUES ('USER', 'usuario', 'usuario del sistema')
    ON CONFLICT (id) DO UPDATE SET value = excluded.value, description = excluded.description;


INSERT INTO public.genres(id, genre)VALUES('NH', 'Novela historica')
      ON CONFLICT(id) DO UPDATE SET genre = excluded.genre;

INSERT INTO public.genres(id, genre)VALUES('ROM', 'Romantica')
    ON CONFLICT(id) DO UPDATE SET genre = excluded.genre;