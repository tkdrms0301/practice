from gensim.models import fasttext
import json
from konlpy.tag import Okt

model = fasttext.load_facebook_model('D:\\django\\delivery_service\\delivery_main\\wiki\\cc.ko.300.bin')
okt = Okt()
with open('./sense.json', 'r', encoding='utf-8') as atr_file:
    atr_list = json.load(atr_file)

def preprocessing(input):
    tokens = okt.pos(input, norm=True, stem=True)
    result = []
    for t in tokens:
        if (t[1] in ['Noun', 'Adjective']): result.append(t)
    return result

def feature_match(tokens, atr_list):
    sim_list = []
    result = ''

    if (len(tokens) >= 1):
        for atr in atr_list:
            sim_list.append((atr, model.wv.similarity(tokens[0][0], atr)))
        result = max(sim_list, key=lambda x: x[1])[0]

    return result

print(type(feature_match(preprocessing('고소한'), atr_list)))
