# -*- coding:utf-8 -*-
import MySQLdb

conn = MySQLdb.connect(user="root", passwd="tkd5957!@#", host="localhost", db="wvat", charset='utf8')
cursor = conn.cursor()
sql = "INSERT INTO sqlinjection (payload, vulnerabilityType) VALUES(%s, %s)"
f = open('D://workspace//ai//MySQL//SI.txt', encoding='utf-8')
lines = f.readlines()
print(lines)
for payload in lines:
    payload = payload.rstrip()
    cursor.execute(sql, (payload, 'sqlinjection'))
conn.commit()
f.close()