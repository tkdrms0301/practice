CREATE TABLE `Users` (
	`user_id`	INT	NOT NULL,
	`name`	varchar(20)	NOT NULL,
	`account`	varchar(20)	NOT NULL,
	`password`	varchar(20)	NOT NULL,
	`SNS`	INT	NULL,
	`Email`	varchar(50)	NULL,
	`Alarm`	BOOLEAN	NOT NULL	DEFAULT 0,
	`Image`	text	NULL,
	`Nickname`	varchar(20)	NULL,
	`Introduction`	varchar(100)	NULL,
	`Link`	varchar(100)	NULL
);

CREATE TABLE `Posts` (
	`Post_id`	INT	NOT NULL,
	`Category_id`	INT	NOT NULL,
	`User_id`	INT	NOT NULL,
	`mold_id`	INT	NOT NULL,
	`Title`	varchar(50)	NOT NULL,
	`Time`	Datetime	NOT NULL
);

CREATE TABLE `Molds` (
	`mold_id`	INT	NOT NULL,
	`user_id`	INT	NOT NULL,
	`name`	Char(20)	NOT NULL	DEFAULT ''
);

CREATE TABLE `Layouts` (
	`layout_id`	INT	NOT NULL,
	`Type`	INT	NOT NULL,
	`Coordinate_X`	float	NULL,
	`Coordinate_Y`	float	NULL,
	`Width`	float	NULL,
	`Height`	float	NULL,
	`Explanation`	text	NULL,
	`mold_id`	INT	NOT NULL,
	`main`	BOOLEAN	NOT NULL	DEFAULT 0
);

CREATE TABLE `Category` (
	`Category_id`	INT	NOT NULL,
	`Name`	varchar(50)	NOT NULL,
	`user_id`	INT	NOT NULL
);

CREATE TABLE `Attachments` (
	`Attachment_id`	INT	NOT NULL,
	`Name`	varchar(20)	NOT NULL,
	`url`	text	NOT NULL,
	`Extension`	varchar(20)	NOT NULL,
	`Post_id`	INT	NOT NULL
);

CREATE TABLE `Likes` (
	`Like_id`	INT	NOT NULL,
	`user_id`	INT	NOT NULL,
	`Post_id`	INT	NOT NULL
);

CREATE TABLE `Tags` (
	`Tag_id`	INT	NOT NULL,
	`Name`	varchar(20)	NOT NULL
);

CREATE TABLE `Posts_and_Tags` (
	`post_and_tag_id`	INT	NOT NULL,
	`Post_id`	INT	NOT NULL,
	`Tag_id`	INT	NOT NULL
);

CREATE TABLE `Hits` (
	`Hits_id`	INT	NOT NULL,
	`Time`	DATE	NOT NULL,
	`Post_id`	INT	NOT NULL
);

CREATE TABLE `Comments` (
	`Comment_id`	INT	NOT NULL,
	`Context`	varchar(100)	NOT NULL,
	`Time`	Datetime	NOT NULL,
	`block`	BOOLEAN	NOT NULL	DEFAULT 0,
	`upper_id`	INT	NULL,
	`Post_id`	INT	NOT NULL,
	`user_id`	INT	NOT NULL
);

CREATE TABLE `Images` (
	`image_id`	INT	NOT NULL,
	`layout_id`	INT	NOT NULL,
	`url`	text	NOT NULL
);

CREATE TABLE `Codes` (
	`code_id`	INT	NOT NULL,
	`layout_id`	INT	NOT NULL,
	`Code`	text	NULL,
	`Type`	INT	NULL,
	`code_explanation`	text	NULL
);

CREATE TABLE `Contexts` (
	`layout_id`	INT	NOT NULL,
	`text`	text	NULL
);

CREATE TABLE `Hyperlinks` (
	`layout_id`	INT	NOT NULL,
	`Link`	text	NULL
);

CREATE TABLE `Mathematics` (
	`layout_id`	INT	NOT NULL,
	`Context`	text	NULL
);

CREATE TABLE `Videos` (
	`layout_id`	INT	NOT NULL,
	`url`	text	NULL
);

CREATE TABLE `Documents` (
	`layout_id`	INT	NOT NULL,
	`url`	text	NULL
);

ALTER TABLE `Users` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
	`user_id`
);

ALTER TABLE `Posts` ADD CONSTRAINT `PK_POSTS` PRIMARY KEY (
	`Post_id`
);

ALTER TABLE `Molds` ADD CONSTRAINT `PK_MOLDS` PRIMARY KEY (
	`mold_id`
);

ALTER TABLE `Layouts` ADD CONSTRAINT `PK_LAYOUTS` PRIMARY KEY (
	`layout_id`
);

ALTER TABLE `Category` ADD CONSTRAINT `PK_CATEGORY` PRIMARY KEY (
	`Category_id`
);

ALTER TABLE `Attachments` ADD CONSTRAINT `PK_ATTACHMENTS` PRIMARY KEY (
	`Attachment_id`
);

ALTER TABLE `Likes` ADD CONSTRAINT `PK_LIKES` PRIMARY KEY (
	`Like_id`
);

ALTER TABLE `Tags` ADD CONSTRAINT `PK_TAGS` PRIMARY KEY (
	`Tag_id`
);

ALTER TABLE `Posts_and_Tags` ADD CONSTRAINT `PK_POSTS_AND_TAGS` PRIMARY KEY (
	`post_and_tag_id`
);

ALTER TABLE `Hits` ADD CONSTRAINT `PK_HITS` PRIMARY KEY (
	`Hits_id`
);

ALTER TABLE `Comments` ADD CONSTRAINT `PK_COMMENTS` PRIMARY KEY (
	`Comment_id`
);

ALTER TABLE `Images` ADD CONSTRAINT `PK_IMAGES` PRIMARY KEY (
	`image_id`,
	`layout_id`
);

ALTER TABLE `Codes` ADD CONSTRAINT `PK_CODES` PRIMARY KEY (
	`code_id`,
	`layout_id`
);

ALTER TABLE `Contexts` ADD CONSTRAINT `PK_CONTEXTS` PRIMARY KEY (
	`layout_id`
);

ALTER TABLE `Hyperlinks` ADD CONSTRAINT `PK_HYPERLINKS` PRIMARY KEY (
	`layout_id`
);

ALTER TABLE `Mathematics` ADD CONSTRAINT `PK_MATHEMATICS` PRIMARY KEY (
	`layout_id`
);

ALTER TABLE `Videos` ADD CONSTRAINT `PK_VIDEOS` PRIMARY KEY (
	`layout_id`
);

ALTER TABLE `Documents` ADD CONSTRAINT `PK_DOCUMENTS` PRIMARY KEY (
	`layout_id`
);

ALTER TABLE `Images` ADD CONSTRAINT `FK_Layouts_TO_Images_1` FOREIGN KEY (
	`layout_id`
)
REFERENCES `Layouts` (
	`layout_id`
);

ALTER TABLE `Codes` ADD CONSTRAINT `FK_Layouts_TO_Codes_1` FOREIGN KEY (
	`layout_id`
)
REFERENCES `Layouts` (
	`layout_id`
);

ALTER TABLE `Contexts` ADD CONSTRAINT `FK_Layouts_TO_Contexts_1` FOREIGN KEY (
	`layout_id`
)
REFERENCES `Layouts` (
	`layout_id`
);

ALTER TABLE `Hyperlinks` ADD CONSTRAINT `FK_Layouts_TO_Hyperlinks_1` FOREIGN KEY (
	`layout_id`
)
REFERENCES `Layouts` (
	`layout_id`
);

ALTER TABLE `Mathematics` ADD CONSTRAINT `FK_Layouts_TO_Mathematics_1` FOREIGN KEY (
	`layout_id`
)
REFERENCES `Layouts` (
	`layout_id`
);

ALTER TABLE `Videos` ADD CONSTRAINT `FK_Layouts_TO_Videos_1` FOREIGN KEY (
	`layout_id`
)
REFERENCES `Layouts` (
	`layout_id`
);

ALTER TABLE `Documents` ADD CONSTRAINT `FK_Layouts_TO_Documents_1` FOREIGN KEY (
	`layout_id`
)
REFERENCES `Layouts` (
	`layout_id`
);

