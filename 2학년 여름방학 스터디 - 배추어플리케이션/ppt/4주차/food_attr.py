#-*- coding:utf-8 -*-
import MySQLdb
import json

conn = MySQLdb.connect( user="delivery_user", passwd="user", host="localhost", db="delivery", charset = 'utf8')
cursor = conn.cursor()

cursor.execute("SELECT fno, name from food")
row = cursor.fetchone()
food_list = dict()
while row:
    food_list[row[1]] = row[0]
    row = cursor.fetchone()
    
cursor.execute("CREATE TABLE if not exists attribute(`fno` int, `sense` varchar(20), `frequency` int, `good` int default 0, `bad` int default 0, PRIMARY KEY(fno, sense), foreign key (fno) references food(fno))")

with open('C:\\Users\\ATIV\\Desktop\\foods_attr.json', 'r', encoding='utf-8') as f:
    data = json.load(f)

sql = "INSERT INTO attribute (fno, sense, frequency) VALUES (%s, %s, %s)"

for sense, food_data in data.items():
    for food_name, frequency in food_data.items():
        food_num = food_list[food_name]
        cursor.execute(sql, (food_num, sense, frequency))

conn.commit()