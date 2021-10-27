create table users
(
    email         varchar(64)  not null
        constraint users_pk
            primary key,
    name          varchar(64)  not null,
    password_hash varchar(128) not null,
    avatar_id     bigint
        constraint users_files_id_fk
            references files
);

alter table users
    owner to postgres;

create table if not exists files
(
    id            serial
        constraint files_pk
            primary key,
    storage_name  varchar(128) not null,
    original_name varchar(128) not null,
    type          varchar(64)  not null,
    size          bigint       not null
);

alter table files
    owner to postgres;

INSERT INTO public.files (id, storage_name, original_name, type, size)
VALUES (4, '4cbfdfd0-1155-461d-9118-1c8ff9e8426b', 'CoOdzk9n24M.png', 'image/png', 1562351);

INSERT INTO public.users (email, name, password_hash, avatar_id)
VALUES ('vdm.snx@gmail.com', 'Senex', '5f4dcc3b5aa765d61d8327deb882cf99', 4);