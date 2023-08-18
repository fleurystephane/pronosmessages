-- public.infoscreatorpicture definition

-- Drop table

-- DROP TABLE public.infoscreatorpicture;

CREATE TABLE public.infoscreatorpicture (
                                            id int8 NOT NULL,
                                            "data" oid NULL,
                                            "extension" varchar(255) NULL,
                                            CONSTRAINT infoscreatorpicture_pkey PRIMARY KEY (id)
);


-- public.sondageresponse definition

-- Drop table

-- DROP TABLE public.sondageresponse;

CREATE TABLE public.sondageresponse (
                                        id bigserial NOT NULL,
                                        count int4 NOT NULL,
                                        responsetext varchar(255) NOT NULL,
                                        CONSTRAINT sondageresponse_pkey PRIMARY KEY (id)
);


-- public.useraccountpicture definition

-- Drop table

-- DROP TABLE public.useraccountpicture;

CREATE TABLE public.useraccountpicture (
                                           id int8 NOT NULL,
                                           "data" oid NULL,
                                           "extension" varchar(255) NULL,
                                           CONSTRAINT useraccountpicture_pkey PRIMARY KEY (id)
);


-- public.infoscreator definition

-- Drop table

-- DROP TABLE public.infoscreator;

CREATE TABLE public.infoscreator (
                                     id int8 NOT NULL,
                                     description varchar(255) NULL,
                                     profilpicture_id int8 NULL,
                                     CONSTRAINT infoscreator_pkey PRIMARY KEY (id),
                                     CONSTRAINT uk_rruilnkklbbrlc9uufcibb27y UNIQUE (profilpicture_id),
                                     CONSTRAINT fk502kl5l9qjvqg2q1sp4bk1a5 FOREIGN KEY (profilpicture_id) REFERENCES public.infoscreatorpicture(id)
);


-- public.useraccount definition

-- Drop table

-- DROP TABLE public.useraccount;

CREATE TABLE public.useraccount (
                                    id int8 NOT NULL,
                                    birthdate timestamp(6) NULL,
                                    accountpicture_id int8 NULL,
                                    CONSTRAINT uk_ip2mu6aj0xfxfl9l4a9ndq5u5 UNIQUE (accountpicture_id),
                                    CONSTRAINT useraccount_pkey PRIMARY KEY (id),
                                    CONSTRAINT fk9a6b5cm3wpxiwjrvle8gayi0y FOREIGN KEY (accountpicture_id) REFERENCES public.useraccountpicture(id)
);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
                              id int8 NOT NULL,
                              displayname varchar(255) NULL,
                              email varchar(255) NOT NULL,
                              username varchar(255) NOT NULL,
                              account_id int8 NOT NULL,
                              creator_id int8 NULL,
                              CONSTRAINT uk_23y4gd49ajvbqgl3psjsvhff6 UNIQUE (username),
                              CONSTRAINT uk_75v6sgy8rl9llb0ejsns7hfee UNIQUE (creator_id),
                              CONSTRAINT uk_hdfr1cp0bc2llwfqtmsa9ubib UNIQUE (account_id),
                              CONSTRAINT uk_ncoa9bfasrql0x4nhmh1plxxy UNIQUE (email),
                              CONSTRAINT users_pkey PRIMARY KEY (id),
                              CONSTRAINT fkm8jl6jniui4dgd9ama7nc9hoo FOREIGN KEY (creator_id) REFERENCES public.infoscreator(id),
                              CONSTRAINT fkqx9p95r3xw9rebns1db63pn1w FOREIGN KEY (account_id) REFERENCES public.useraccount(id)
);


-- public."connection" definition

-- Drop table

-- DROP TABLE public."connection";

CREATE TABLE public."connection" (
                                     id int8 NOT NULL,
                                     info varchar(255) NULL,
                                     abonne_id int8 NOT NULL,
                                     abonnea_id int8 NOT NULL,
                                     CONSTRAINT connection_pkey PRIMARY KEY (id),
                                     CONSTRAINT ukc6cmw0yqgvamgkjl03udpsjme UNIQUE (abonne_id, abonnea_id),
                                     CONSTRAINT fknhefbjujnwkyo5j45etikllvd FOREIGN KEY (abonne_id) REFERENCES public.users(id),
                                     CONSTRAINT fkokfd93qqm70w07nwt00jvtmrr FOREIGN KEY (abonnea_id) REFERENCES public.users(id)
);


-- public.favoritecreator definition

-- Drop table

-- DROP TABLE public.favoritecreator;

CREATE TABLE public.favoritecreator (
                                        id int8 NOT NULL,
                                        creator_id int8 NOT NULL,
                                        user_id int8 NOT NULL,
                                        CONSTRAINT favoritecreator_pkey PRIMARY KEY (id),
                                        CONSTRAINT uk_kp0w7qfm2njs82hy84rkqjjg5 UNIQUE (creator_id),
                                        CONSTRAINT fkaqti37bav4q4hmgkulhbnexeb FOREIGN KEY (user_id) REFERENCES public.users(id),
                                        CONSTRAINT fkf0m07myqp5bp3u747c8kmxr26 FOREIGN KEY (creator_id) REFERENCES public.users(id)
);


-- public.publication definition

-- Drop table

-- DROP TABLE public.publication;

CREATE TABLE public.publication (
                                    id int8 NOT NULL,
                                    "content" varchar(255) NULL,
                                    creationdate timestamp(6) NULL,
                                    nbcomments int8 NOT NULL,
                                    nblikes int8 NOT NULL,
                                    visible bool NOT NULL,
                                    user_id int8 NOT NULL,
                                    CONSTRAINT publication_pkey PRIMARY KEY (id),
                                    CONSTRAINT fkmy5u2vbprp81qvcs322s4hyfj FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.publicationpicturemanagement definition

-- Drop table

-- DROP TABLE public.publicationpicturemanagement;

CREATE TABLE public.publicationpicturemanagement (
                                                     id int8 NOT NULL,
                                                     countpictures int4 NOT NULL,
                                                     publication_id int8 NULL,
                                                     CONSTRAINT publicationpicturemanagement_pkey PRIMARY KEY (id),
                                                     CONSTRAINT uk_5oh8ee62eoc1n06rdaucrqu5u UNIQUE (publication_id),
                                                     CONSTRAINT fk315qcqn6oli5cebph8777ul1j FOREIGN KEY (publication_id) REFERENCES public.publication(id)
);


-- public.publicationsaved definition

-- Drop table

-- DROP TABLE public.publicationsaved;

CREATE TABLE public.publicationsaved (
                                         id int8 NOT NULL,
                                         publication_id int8 NOT NULL,
                                         user_id int8 NOT NULL,
                                         CONSTRAINT publicationsaved_pkey PRIMARY KEY (id),
                                         CONSTRAINT ukjtlwy269oic18mthspob3fyo1 UNIQUE (user_id, publication_id),
                                         CONSTRAINT fk8l37tfhjwqiuqywh60nnap7fe FOREIGN KEY (publication_id) REFERENCES public.publication(id),
                                         CONSTRAINT fk9rtielssa0ucclot1o00d8gqw FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.sondage definition

-- Drop table

-- DROP TABLE public.sondage;

CREATE TABLE public.sondage (
                                actif bool NOT NULL,
                                multiple bool NOT NULL,
                                id int8 NOT NULL,
                                CONSTRAINT sondage_pkey PRIMARY KEY (id),
                                CONSTRAINT fkocbbc04i5ltc1chaf2ik7ta3j FOREIGN KEY (id) REFERENCES public.publication(id)
);


-- public.sondage_sondageresponse definition

-- Drop table

-- DROP TABLE public.sondage_sondageresponse;

CREATE TABLE public.sondage_sondageresponse (
                                                sondage_id int8 NOT NULL,
                                                responses_id int8 NOT NULL,
                                                response_order int4 NOT NULL,
                                                CONSTRAINT sondage_sondageresponse_pkey PRIMARY KEY (sondage_id, response_order),
                                                CONSTRAINT uk_5sycdviksfiktakwwo5j127in UNIQUE (responses_id),
                                                CONSTRAINT fkap44cchrn5btnb6ss1wu4n4qk FOREIGN KEY (sondage_id) REFERENCES public.sondage(id),
                                                CONSTRAINT fkeb4e9gj7lrbpt59i7uyyuxoor FOREIGN KEY (responses_id) REFERENCES public.sondageresponse(id)
);


-- public."comment" definition

-- Drop table

-- DROP TABLE public."comment";

CREATE TABLE public."comment" (
                                  id int8 NOT NULL,
                                  "content" varchar(255) NULL,
                                  creationdate timestamp(6) NULL,
                                  author_id int8 NULL,
                                  publication_id int8 NULL,
                                  CONSTRAINT comment_pkey PRIMARY KEY (id),
                                  CONSTRAINT fk6ktxartevecn7cil2nv6jbwbl FOREIGN KEY (publication_id) REFERENCES public.publication(id),
                                  CONSTRAINT fkiwvt54kgdieli7eaaxfmonn22 FOREIGN KEY (author_id) REFERENCES public.users(id)
);


-- public.participantlike definition

-- Drop table

-- DROP TABLE public.participantlike;

CREATE TABLE public.participantlike (
                                        id int8 NOT NULL,
                                        publication_id int8 NOT NULL,
                                        user_id int8 NOT NULL,
                                        CONSTRAINT participantlike_pkey PRIMARY KEY (id),
                                        CONSTRAINT ukmo718imtv2ddwgh8iir7bfyga UNIQUE (user_id, publication_id),
                                        CONSTRAINT fk134k4m8d61l8wco0w3h6lj0jd FOREIGN KEY (user_id) REFERENCES public.users(id),
                                        CONSTRAINT fkociu48p7cmufjubhmj58uk1he FOREIGN KEY (publication_id) REFERENCES public.publication(id)
);


-- public.participantsaved definition

-- Drop table

-- DROP TABLE public.participantsaved;

CREATE TABLE public.participantsaved (
                                         id int8 NOT NULL,
                                         publication_id int8 NOT NULL,
                                         user_id int8 NOT NULL,
                                         CONSTRAINT participantsaved_pkey PRIMARY KEY (id),
                                         CONSTRAINT uk4d3dq5qiqcq28j4jhci6c1xo1 UNIQUE (user_id, publication_id),
                                         CONSTRAINT fkoklwiffvppefkp5dgd16gkqtx FOREIGN KEY (publication_id) REFERENCES public.publication(id),
                                         CONSTRAINT fkrv0uklwyfsns0yyn8td6cj2qa FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.participantsondage definition

-- Drop table

-- DROP TABLE public.participantsondage;

CREATE TABLE public.participantsondage (
                                           id int8 NOT NULL,
                                           response varchar(255) NULL,
                                           sondage_id int8 NOT NULL,
                                           user_id int8 NOT NULL,
                                           CONSTRAINT participantsondage_pkey PRIMARY KEY (id),
                                           CONSTRAINT fk9n8dx88lnneje53m0jvkiqhkm FOREIGN KEY (user_id) REFERENCES public.users(id),
                                           CONSTRAINT fkj8xdmq6ywl1rmq8vj4c5wpmb4 FOREIGN KEY (sondage_id) REFERENCES public.sondage(id)
);


-- public.publicationpicture definition

-- Drop table

-- DROP TABLE public.publicationpicture;

CREATE TABLE public.publicationpicture (
                                           id int8 NOT NULL,
                                           "data" oid NULL,
                                           "extension" varchar(255) NULL,
                                           "size" int8 NOT NULL,
                                           publication_picture_management_id int8 NOT NULL,
                                           picture_order int4 NULL,
                                           CONSTRAINT publicationpicture_pkey PRIMARY KEY (id),
                                           CONSTRAINT fkd6524rjuvjofiqnfm6xtcjkwi FOREIGN KEY (publication_picture_management_id) REFERENCES public.publicationpicturemanagement(id)
);