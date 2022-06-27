# -*- coding:utf-8 -*-
import MySQLdb

conn = MySQLdb.connect(user="root", passwd="tkd5957!@#", host="localhost", db="wvat", charset='utf8')
cursor = conn.cursor()
sql = "INSERT INTO payload (vulnerabilityType, payload) VALUES(%s, %s)"
f = open('D://workspace//ai//MySQL//AE.txt', encoding='utf-8')
lines = f.readlines()
for payload in lines:
    payload = payload.rstrip()
    cursor.execute(sql, ('adminExpose', payload))
conn.commit()
f.close()
f = open('D://workspace//ai//MySQL//CI.txt', encoding='utf-8')
lines = f.readlines()
for payload in lines:
    payload = payload.rstrip()
    cursor.execute(sql, ('osCommand', payload))
conn.commit()
f.close()
f = open('D://workspace//ai//MySQL//LD.txt', encoding='utf-8')
lines = f.readlines()
for payload in lines:
    payload = payload.rstrip()
    cursor.execute(sql, ('locationDisclosure', payload))
conn.commit()
f.close()
f = open('D://workspace//ai//MySQL//SI.txt', encoding='utf-8')
lines = f.readlines()
for payload in lines:
    payload = payload.rstrip()
    cursor.execute(sql, ('sqlInjection', payload))
conn.commit()
f.close()
f = open('D://workspace//ai//MySQL//XSS.txt', encoding='utf-8')
lines = f.readlines()
for payload in lines:
    payload = payload.rstrip()
    cursor.execute(sql, ('xss', payload))

conn.commit()
f.close()
f = open('D://workspace//ai//MySQL//PT.txt', encoding='utf-8')
lines = f.readlines()
for payload in lines:
    payload = payload.rstrip()
    cursor.execute(sql, ('pathTracking', payload))
conn.commit()
f.close()
cursor.close()
conn.close()
print("end");