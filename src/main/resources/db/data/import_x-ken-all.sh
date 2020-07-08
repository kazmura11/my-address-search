#!/bin/bash

mysql -uroot -proot -e "
set character_set_database=sjis;
LOAD DATA LOCAL INFILE 'x-ken-all.csv'
INTO TABLE my_address_search.addresses
FIELDS TERMINATED BY ',' ENCLOSED BY '\"' 
LINES TERMINATED BY '\r\n' IGNORE 0 LINES 
(government_code, old_zip_code, zip_code, kana_prefecture, kana_city, kana_district,
prefecture, city, district, multiple_zip_code_flag, special_koaza_flag, chome_flag,
multiple_district_flag, updating_code, modified_reason);
"