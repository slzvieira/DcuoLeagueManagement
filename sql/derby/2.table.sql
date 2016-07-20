CREATE TABLE tab_role (
    role_code INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    role_name VARCHAR(30),
    CONSTRAINT pk_role PRIMARY KEY (role_code)
);

CREATE TABLE tab_power (
    power_code INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    power_name VARCHAR(30),
    role_code  INTEGER,
    CONSTRAINT pk_power PRIMARY KEY (power_code)
);

ALTER TABLE tab_power
ADD CONSTRAINT fk_role_power
    FOREIGN KEY (role_code)
        REFERENCES tab_role (role_code);

CREATE TABLE tab_rank (
    rank_code INTEGER NOT NULL,
    rank_name VARCHAR(30),
    CONSTRAINT pk_rank PRIMARY KEY (rank_code)
);

CREATE TABLE tab_movement (
    mvmnt_code INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    mvmnt_name VARCHAR(20),
    CONSTRAINT pk_movement PRIMARY KEY (mvmnt_code)
);

CREATE TABLE tab_origin (
    origin_code INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    origin_name VARCHAR(10),
    CONSTRAINT pk_origin PRIMARY KEY (origin_code)
);

CREATE TABLE tab_region (
    region_code INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    region_name VARCHAR(60),
    CONSTRAINT pk_region PRIMARY KEY (region_code)
);

CREATE TABLE tab_person (
    person_code INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    person_name VARCHAR(60),
    CONSTRAINT pk_person PRIMARY KEY (person_code)
);

CREATE TABLE tab_entry (
    entry_code INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    entry_date TIMESTAMP,
    CONSTRAINT pk_entry PRIMARY KEY (entry_code)
);

CREATE TABLE tab_character (
    char_code    INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    char_dcuo_id BIGINT,
    char_name    VARCHAR(30),
    power_code   INTEGER,
    pve_cr_val   INTEGER,
    pvp_cr_val   INTEGER,
    skill_val    INTEGER,
    rank_code    INTEGER,
    level_val    INTEGER,
    mvmnt_code   INTEGER,
    origin_code  INTEGER,
    gender_code  INTEGER,
    region_code  INTEGER,
    person_code  INTEGER,
    active_ind   SMALLINT,
    deleted_ind	 SMALLINT,
    CONSTRAINT pk_character PRIMARY KEY (char_code)
);

ALTER TABLE tab_character
ADD CONSTRAINT fk_character_role
    FOREIGN KEY (power_code)
        REFERENCES tab_power (power_code);

ALTER TABLE tab_character
ADD CONSTRAINT fk_character_rank
    FOREIGN KEY (rank_code)
        REFERENCES tab_rank (rank_code);

ALTER TABLE tab_character
ADD CONSTRAINT fk_character_movement
    FOREIGN KEY (mvmnt_code)
        REFERENCES tab_movement (mvmnt_code);

ALTER TABLE tab_character
ADD CONSTRAINT fk_character_origin
    FOREIGN KEY (origin_code)
        REFERENCES tab_origin (origin_code);

ALTER TABLE tab_character
ADD CONSTRAINT fk_character_region
    FOREIGN KEY (region_code)
        REFERENCES tab_region (region_code);

ALTER TABLE tab_character
ADD CONSTRAINT fk_character_person
    FOREIGN KEY (person_code)
        REFERENCES tab_person (person_code);

CREATE TABLE tab_character_history (
    char_hist_code INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    char_code      INTEGER,
    entry_code     INTEGER,
    power_code     INTEGER,
    pve_cr_val     INTEGER,
    pvp_cr_val     INTEGER,
    skill_val      INTEGER,
    rank_code      INTEGER,
    mvmnt_code     INTEGER,
    CONSTRAINT pk_history PRIMARY KEY (char_hist_code)
);

ALTER TABLE tab_character_history
ADD CONSTRAINT fk_character_hist_char
    FOREIGN KEY (char_code)
        REFERENCES tab_character (char_code);

ALTER TABLE tab_character_history
ADD CONSTRAINT fk_character_hist_entry
    FOREIGN KEY (entry_code)
        REFERENCES tab_entry (entry_code);

ALTER TABLE tab_character_history
ADD CONSTRAINT fk_character_hist_power
    FOREIGN KEY (power_code)
        REFERENCES tab_power (power_code);

ALTER TABLE tab_character_history
ADD CONSTRAINT fk_character_hist_rank
    FOREIGN KEY (rank_code)
        REFERENCES tab_rank (rank_code);

ALTER TABLE tab_character_history
ADD CONSTRAINT fk_character_hist_movement
    FOREIGN KEY (mvmnt_code)
        REFERENCES tab_movement (mvmnt_code);
