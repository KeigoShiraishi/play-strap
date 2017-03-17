# --- !Ups

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_identification_name` varchar(255) NOT NULL,
  `account_password` varchar(64) NOT NULL,
  `account_password_salt` varchar(64) NOT NULL,
  `account_timezone` varchar(255) NOT NULL DEFAULT 'Asia/Tokyo',
  `account_language` varchar(255) NOT NULL DEFAULT 'ja',
  `account_email` varchar(255) DEFAULT NULL,
  `account_nickname` varchar(255) DEFAULT NULL,
  `account_authority_id` int(11) NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `account_account_identification_name_uindex` (`account_identification_name`),
  UNIQUE KEY `account_account_email_uindex` (`account_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into account(
  account_identification_name,
  account_password,
  account_password_salt,
  account_timezone,
  account_language,
  account_email,
  account_nickname,
  account_authority_id
) values (
  'hoge.fuga@foo.bar',
  '83627ffa5ab48ecae9d0aafe55940d3d19040319e4b26a94f92d56b6734962e1',
  'f911422a271cf8a986b8a19d1e948c4d8ac4050bf090dd00d0d3e982c57647b8',
  'Asia/Tokyo',
  'ja',
  'hoge.fuga@foo.bar',
  'play-strap',
  3
);

# --- !Downs

drop table "account" if exists;
