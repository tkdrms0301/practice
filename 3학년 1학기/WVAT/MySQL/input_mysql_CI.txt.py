# -*- coding:utf-8 -*-
import MySQLdb

conn = MySQLdb.connect(user="root", passwd="tkd5957!@#", host="localhost", db="wvat", charset='utf8')
cursor = conn.cursor()
sql = "INSERT INTO oscommand (payload, vulnerabilityType) VALUES(%s, %s)"
f = open('D://workspace//ai//MySQL//CI.txt', encoding='utf-8')
lines = f.readlines()
print(lines)
for payload in lines:
    payload = payload.rstrip()
    cursor.execute(sql, (payload, 'oscommand'))
conn.commit()
f.close()