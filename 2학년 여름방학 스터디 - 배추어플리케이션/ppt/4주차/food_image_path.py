#-*- coding:utf-8 -*-
import MySQLdb
import openpyxl

conn = MySQLdb.connect( user="delivery_user", passwd="user", host="localhost", db="delivery", charset = 'utf8')
cursor = conn.cursor()
cursor.execute("CREATE TABLE if not exists food(`fno` int AUTO_INCREMENT primary key, `name` varchar(20), `path` text)")

path = 'C:\\Users\\ATIV\\Desktop\\food_image_list.xlsx' #path
food_image_list = openpyxl.load_workbook(path, data_only=True)
sheet = food_image_list['Sheet1']

sql = "INSERT INTO food (name, path) VALUES(%s, %s)"

for food in sheet.rows:
    name = food[0].value
    src = food[1].value
    cursor.execute(sql, (name, src))
    
conn.commit()