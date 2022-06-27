# -*- coding:utf-8 -*-
import MySQLdb

conn = MySQLdb.connect(user="root", passwd="tkd5957!@#", host="localhost", db="wvat", charset='utf8')
cursor = conn.cursor()
sql = "INSERT INTO adminexpose (payload, vulnerabilityType) VALUES(%s, %s)"
f = open('D://workspace//ai//MySQL//AE.txt', encoding='utf-8')
lines = f.readlines()
for payload in lines:
    payload = payload.split('.')[0].rstrip()
set(lines)

for payload in lines:
    payload = payload.rstrip()
    print(payload)
    cursor.execute(sql, (payload, 'adminexpose'))
conn.commit()
f.close()