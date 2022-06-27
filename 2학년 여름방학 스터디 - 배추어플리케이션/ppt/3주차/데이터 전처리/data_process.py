import json
from os import spawnl
from konlpy.tag import Okt
from konlpy.corpus import kolaw
from collections import Counter

read_file_path = 'C:\\Users\\ATIV\\Desktop\\app\\foods.json'
write_file_path = 'C:\\Users\\ATIV\\Desktop\\app\\foods_adj.json'
korean_stopwords_path = "C:\\Users\\ATIV\\Desktop\\app\\korean_stopwords.txt"

okt = Okt()
with open(read_file_path, 'r', encoding='utf-8') as file: #foods.json파일 경로 입력
    food_data = json.load(file)

with open(korean_stopwords_path, encoding='utf-8') as f:
   stopwords = f.readlines()
stopwords = [x.strip() for x in stopwords]

word_data = dict()
keys = food_data.keys()

for key in keys:
    str = ''.join(food_data.get(key))
    str_result = okt.pos(str, norm=True, stem=True)
    tag = []
    for str_line in str_result:
        if str_line[1] in ['Adjective'] and len(str_line[0]) > 1:
            if str_line[0] not in stopwords:
                tag.append(str_line[0])
    counts = Counter(tag)

    word_data[key] = counts.most_common(30)

    with open(write_file_path, 'w+', encoding='UTF-8') as f:
        json.dump(word_data, f, ensure_ascii=False)
    print(key + "저장완료!")
