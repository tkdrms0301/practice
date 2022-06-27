# -*- coding:utf-8 -*-
import MySQLdb

conn = MySQLdb.connect(user="root", passwd="tkd5957!@#", host="localhost", db="wvat", charset='utf8')
cursor = conn.cursor()
sql = "INSERT INTO payload (vulnerabilityType, payload) VALUES(%s, %s)"
f = open('D://workspace//ai//MySQL//PT.txt', encoding='utf-8')
lines = f.readlines()
for payload in lines:
    payload = payload.rstrip()
    print(payload)
    cursor.execute(sql, ('pathTracking', payload))
conn.commit()
cursor.close()
conn.close()