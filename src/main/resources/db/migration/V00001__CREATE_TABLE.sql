USE `my_address_search`;

CREATE TABLE IF NOT EXISTS `users` (
    `user_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ユーザID',
    `email_address` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'メールアドレス',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'ユーザ名',
    `password` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'メールアドレス',
    `admin_flag` TINYINT(1) NOT NULL DEFAULT false COMMENT '管理者フラグ',
	`deleted` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '削除フラグ',
	`created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
	`updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時',
    PRIMARY KEY (`user_id`)
) COLLATE='utf8mb4_general_ci' ENGINE=InnoDB
;

INSERT INTO `users` 
    (`email_address`, `name`, `password`, `admin_flag`, `deleted`, `created_at`, `updated_at`) VALUES
    ('demo@example.com', 'demo', '$2a$10$xHFQxxpNwdI9w16fMobSs.r5DeANspipqAwMS4IbQWFnoR.4E9Rla', '0', '0', NOW(), NOW())
;

CREATE TABLE IF NOT EXISTS `addresses` (
	`address_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '住所ID',
	`government_code` VARCHAR(5) NOT NULL DEFAULT '' COMMENT '全国地方公共団体コード',
	`old_zip_code` VARCHAR(5) NOT NULL DEFAULT '' COMMENT '旧郵便番号',
	`zip_code` VARCHAR(7) NOT NULL DEFAULT '' COMMENT '郵便番号',
	`kana_prefecture` VARCHAR(60) NOT NULL DEFAULT '' COMMENT '都道府県カナ',
	`kana_city` VARCHAR(60) NOT NULL DEFAULT '' COMMENT '市区町村カナ',
	`kana_district` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '町域カナ',
	`prefecture` VARCHAR(60) NOT NULL DEFAULT '' COMMENT '都道府県',
	`city` VARCHAR(60) NOT NULL DEFAULT '' COMMENT '市区町村',
	`district` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '町域',
	`multiple_zip_code_flag` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '複数郵便番号フラグ',
	`special_koaza_flag` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '特殊小字フラグ',
	`chome_flag` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '丁目フラグ',
	`multiple_district_flag` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '複数町域フラグ',
	`updating_code` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '更新の表示',
	`modified_reason` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '変更理由',
	`deleted` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '削除フラグ',
	`created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
	`updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時',
	PRIMARY KEY (`address_id`)
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;