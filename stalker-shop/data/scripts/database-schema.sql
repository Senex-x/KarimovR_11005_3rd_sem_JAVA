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

create table if not exists items
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

create table if not exists carts
(
    user_email           varchar(64)   not null
        constraint cart_pk
            primary key
        constraint cart_users_email_fk
            references users,
    item_names_list_json varchar(2048) not null
);

alter table carts
    owner to postgres;

INSERT INTO public.users (email, name, password_hash, avatar_id)
VALUES ('vdm.snx@gmail.com', 'Senex', '5f4dcc3b5aa765d61d8327deb882cf99', null);

INSERT INTO public.items (name, cost, description, image_name) VALUES ('Cossacks vodka', 100, 'Distilled by the GSC company, the Cossacks-brand vodka is a clear distilled liquor composed of water and ethyl alcohol. Vodka is made from a fermented substance of either grain, rye, wheat, potatoes, or sugar beet molasses. Vodka’s alcoholic concentration usually ranges between 35 to 70 per cent by volume.
It is an extremely popular drink in the Zone, arguably even more than energy drinks, as it removes radiation and "smooths the rough edges" of a stalker''s life.', 'cossacks_vodka.png');
INSERT INTO public.items (name, cost, description, image_name) VALUES ('Tourist''s Delight', 100, 'A can of military rations taken from an army depot raided by Stalkers, one wonders if it already gone past its expiration date. It is capable of satisfying hunger and partially restoring some of the player''s health. ', 'tourists_delight.png');
INSERT INTO public.items (name, cost, description, image_name) VALUES ('Diet sausage', 50, 'Diet sausage is a food item made of chicken and soybeans. It is commonly eaten in the Zone as a staple food, due to the lack of other food options available. ', 'diet_sausage.png');
INSERT INTO public.items (name, cost, description, image_name) VALUES ('Bread', 20, 'The 0.3kg loaf of bread is the lightest and cheapest form of food available to a stalker in Shadow of Chernobyl and Clear Sky. In Call of Pripyat the weight was increased to 0.4kg, making it less economical. The bread has no known baked origin, but is a food staple for stalkers in the Zone.
Hard to say who manages to bake these loaves in the Zone, but the bread isn''t contaminated and is perfectly edible. Well, at least none of the stalkers have complained about it. ', 'bread.png');
INSERT INTO public.items (name, cost, description, image_name) VALUES ('Anti-rad', 300, 'Anti-rad drugs are stalkers'' main method of clearing radiation poisoning after exposure to irradiated areas, mutants, or artifacts in The Zone.
Anti-rad drugs are common amongst stalkers, regardless of their experience or affiliation. Anti-rad''s way of application differs throughout the series, its either a packet consisting of two blue and red pills that are supposed to be ingested immediately or a syringe with an anti-radiation drug solution for injection. The effects are the same however, to remove radioactive particles from the system as soon as possible.
Anti-rad does not prevent or protect from radiation but removes accumulated radiation poisoning after exposure.', 'antirad.png');
INSERT INTO public.items (name, cost, description, image_name) VALUES ('Medkit', 150, 'An all-purpose medkit. Used to treat injuries of various types and severities - wounds, burns, poisonings, etc.', 'medkit.png');
INSERT INTO public.items (name, cost, description, image_name) VALUES ('Bandage', 20, 'A medicinal gauze that instantly stops light bleeding, but will require more than one to stop severe hemorrhages. It''s a good idea to take a number of bandages for excursions into territories where you will face a lot of opposition.', 'bandage.png');
INSERT INTO public.items (name, cost, description, image_name) VALUES ('Jellyfish', 1000, 'Is formed in the Springboard. Forms a weak protective field whose side effect is a slight radiation. The artifact is widespread and not very valuable.
The Jellyfish increases bullet protection by 2%, but has a 5% radiation increase. The Jellyfish isn''t particularly useful and considering how common it is, it would work better as an monetary item.', 'jellyfish.png');