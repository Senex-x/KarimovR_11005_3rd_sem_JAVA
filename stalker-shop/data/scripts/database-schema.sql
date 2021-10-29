create table if not exists users
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

create table items
(
    name        varchar(128) not null
        constraint items_pk
            primary key,
    cost        integer      not null,
    description varchar(2048),
    image_name  varchar(128)
);

alter table items
    owner to postgres;

INSERT INTO public.files (id, storage_name, original_name, type, size)
VALUES (4, '4cbfdfd0-1155-461d-9118-1c8ff9e8426b', 'CoOdzk9n24M.png', 'image/png', 1562351);

INSERT INTO public.users (email, name, password_hash, avatar_id)
VALUES ('vdm.snx@gmail.com', 'Senex', '5f4dcc3b5aa765d61d8327deb882cf99', 4);

INSERT INTO public.items (name, cost, description, image_name) VALUES ('Cossacks vodka', 100, 'Distilled by the GSC company, the Cossacks-brand vodka is a clear distilled liquor composed of water and ethyl alcohol. Vodka is made from a fermented substance of either grain, rye, wheat, potatoes, or sugar beet molasses. Vodka’s alcoholic concentration usually ranges between 35 to 70 per cent by volume.

It is an extremely popular drink in the Zone, arguably even more than energy drinks, as it removes radiation and "smooths the rough edges" of a stalker''s life.', 'cossacks_vodka.png');