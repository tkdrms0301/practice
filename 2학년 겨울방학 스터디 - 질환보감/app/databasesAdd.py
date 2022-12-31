#-*- coding:utf-8 -*-
from genericpath import exists
import MySQLdb
import json

conn = MySQLdb.connect( user="app_user", passwd="user", host="localhost", db="app", charset = 'utf8')
cursor = conn.cursor()
    
cursor.execute("CREATE TABLE if not exists diagnose(`id` int AUTO_INCREMENT, `diseaseName` text, `description` text, PRIMARY KEY(id))")

with open('D:\\workspace\\sig\\app\\data_set_all_edit.json', 'r', encoding='utf-8') as f:
    data = json.load(f)
       
sql = "INSERT INTO diagnose (diseaseName, description) VALUES (%s, %s)"

for diagnose in data:
    cursor.execute(sql, (diagnose['diseaseName'], diagnose['description']))
#select @@global.sql_mode; mysql 모드 확인 필요 (오류 Data too long for column)
#set @@global.sql_mode="NO_ENGINE_SUBSTITUTION"; STRICT_TRANS_TABLES 설정 변경
conn.commit()