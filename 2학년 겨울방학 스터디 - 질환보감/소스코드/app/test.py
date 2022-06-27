#-*- coding:utf-8 -*-
from diagnostic.disease_similarity import Model
st = Model()
sentence = "발목이 부었어요"
indices = st.get_indices(sentence)
print(indices)