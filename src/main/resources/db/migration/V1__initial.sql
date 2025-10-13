create table public.courses
(
    duration integer
        constraint courses_duration_check
            check (duration <= 10),
    acronym  varchar(10)
        unique,
    id       uuid not null
        primary key,
    name     varchar(100)
);

alter table public.courses
    owner to user_api;

create table public.disciplines
(
    workload  integer
        constraint disciplines_workload_check
            check (workload <= 200),
    course_id uuid
        constraint fkp3yixfciykv0jedndb893nupl
            references public.courses,
    id        uuid not null
        primary key,
    name      varchar(100)
);

alter table public.disciplines
    owner to user_api;

create table public.users
(
    id           uuid not null
        primary key,
    registration varchar(20)
        unique,
    email        varchar(150)
        unique,
    name         varchar(150),
    password     varchar(255),
    role         varchar(255)
        constraint users_role_check
            check ((role)::text = ANY
                   ((ARRAY ['ADMIN'::character varying, 'PROFESSOR'::character varying, 'STUDENT'::character varying, 'SECRETARY'::character varying])::text[]))
);

alter table public.users
    owner to user_api;

INSERT INTO public.users (id, registration, email, name, password, role) VALUES ('fc77e828-a6e5-40c0-8cd9-cbb155be7c9c', 'admin_ifpe', 'admin@ifpe.edu.com.br', 'ADMIN', '$2a$12$HJtPn6neO5uVG9qbVnKiwegYdUjPHyWfYYvdWal17exSDMHQA/aWy', 'ADMIN');
