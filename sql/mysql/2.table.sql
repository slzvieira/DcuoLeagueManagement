CREATE TABLE tab_role (
    role_code INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(30)
);

CREATE TABLE tab_power (
    power_code INT PRIMARY KEY AUTO_INCREMENT,
    power_name VARCHAR(30),
    role_code  INT
);

ALTER TABLE tab_power
ADD CONSTRAINT fk_role_power
    FOREIGN KEY (role_code)
        REFERENCES tab_role (role_code);

CREATE TABLE tab_rank (
    rank_code INT PRIMARY KEY,
    rank_name VARCHAR(30)
);

CREATE TABLE tab_movement (
    mvmnt_code INT PRIMARY KEY AUTO_INCREMENT,
    mvmnt_name VARCHAR(20)
);

CREATE TABLE tab_origin (
    origin_code INT PRIMARY KEY AUTO_INCREMENT,
    origin_name VARCHAR(10)
);

CREATE TABLE tab_region (
    region_code INT PRIMARY KEY AUTO_INCREMENT,
    region_name VARCHAR(60)
);

CREATE TABLE tab_person (
    person_code INT PRIMARY KEY AUTO_INCREMENT,
    person_name VARCHAR(60)
);

CREATE TABLE tab_league (
    league_code      INT PRIMARY KEY AUTO_INCREMENT,
    league_census_id BIGINT,
    league_name      VARCHAR(30),
    world_code       INT NOT NULL,
    alignment_code   INT NOT NULL
);

CREATE TABLE tab_entry (
    entry_code  INT PRIMARY KEY AUTO_INCREMENT,
    entry_date  DATETIME,
    league_code INT
);

ALTER TABLE tab_entry
ADD CONSTRAINT fk_entry_league
    FOREIGN KEY (league_code)
        REFERENCES tab_league (league_code);

CREATE TABLE tab_character (
    char_code      INT PRIMARY KEY AUTO_INCREMENT,
    char_census_id BIGINT,
    char_name      VARCHAR(30),
    league_code    INT,
    power_code     INT,
    pve_cr_val     INT,
    pvp_cr_val     INT,
    skill_val      INT,
    rank_code      INT,
    level_val      INT,
    mvmnt_code     INT,
    origin_code    INT,
    gender_code    INT,
    region_code    INT,
    person_code    INT,
    deleted_ind	   INT(1)
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

ALTER TABLE tab_character
ADD CONSTRAINT fk_character_league
    FOREIGN KEY (league_code)
        REFERENCES tab_league (league_code);

CREATE TABLE tab_character_history (
    char_hist_code INT PRIMARY KEY AUTO_INCREMENT,
    char_code      INT,
    entry_code     INT,
    league_code    INT,
    power_code     INT,
    pve_cr_val     INT,
    pvp_cr_val     INT,
    skill_val      INT,
    rank_code      INT,
    mvmnt_code     INT
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

CREATE TABLE tab_feat_category (
    feat_cat_code INT PRIMARY KEY AUTO_INCREMENT,
    feat_cat_name VARCHAR(20)
);

CREATE TABLE tab_feat_subcategory (
    feat_scat_code INT PRIMARY KEY AUTO_INCREMENT,
    feat_scat_name VARCHAR(60),
    feat_cat_code  INT
);

ALTER TABLE tab_feat_subcategory
ADD CONSTRAINT fk_feat_subcategory_category
    FOREIGN KEY (feat_cat_code)
        REFERENCES tab_feat_category (feat_cat_code);

CREATE TABLE tab_feat (
    feat_code      INT PRIMARY KEY AUTO_INCREMENT,
    feat_dcuo_id   BIGINT,
    feat_name
    feat_value
    feat_desc_en
    feat_desc_pt
    feat_scat_code
    fact_code
    origin_code
    mvmnt_code
    role_code
);
