from rest_framework import viewsets
from .FoodSerializer import food_dataSerializer, attributeSerializer
from .models import food_data, attribute
from .input_match import preprocessing #feature_match
import numpy
import json

class food_dataView(viewsets.ModelViewSet):
    serializer_class = food_dataSerializer
    queryset = food_data.objects.all()

    def get_queryset(self, *args):
        qs = super().get_queryset()
        keyword = self.request.GET.get("keyword")
        preprocessing_keyword = preprocessing(keyword)[0][0]
        #with open('C:\Users\김주호\Desktop\delivery_service\food_atr.json', 'r', encoding='utf-8') as atr_file:
        #    atr_list = json.load(atr_file)
        #if preprocessing_keyword not in atr_list:
        #   preprocessing_keyword = feature_match(preprocessing_keyword)
        #   if preprocessing_keyword not in atr_list:
        #        random_choice = numpy.random.choice(atr_list, 1)
        #        qs = qs.filter(sense=random_choice)
        #        random_choice_data = numpy.random.choice(qs, 3, replace=False)
        #        return random_choice_data
        qs = qs.filter(sense = preprocessing_keyword)
        sum_frequency = 0
        probality = []
        data_result_choice = []
        data_list = qs.values()
        for food in data_list:
            sum_frequency = sum_frequency + food['frequency'] + food['good'] - food['bad']
        for food in data_list:
            probality.append((food['frequency'] + food['good'] - food['bad']) / sum_frequency)
        if len(data_list) > 3:
            data_result_choice = numpy.random.choice(data_list, 3, replace=False, p=probality)
        else:
            data_result_choice = numpy.random.choice(data_list, len(data_list), replace=False, p=probality)
        return data_result_choice

class food_goodView(viewsets.ModelViewSet):
    serializer_class = attributeSerializer
    queryset = attribute.objects.all()

    def get_queryset(self, *args):
        get = self.request.GET
        fno = get['fno']
        sense = get['sense']
        qs = super().get_queryset()
        qs = qs.filter(fno=fno, sense=sense)
        qs.update(good = qs.values()[0]['good'] + 1)
        return qs

class food_badView(viewsets.ModelViewSet):
    serializer_class = attributeSerializer
    queryset = attribute.objects.all()

    def get_queryset(self, *args):
        get = self.request.GET
        fno = get['fno']
        sense = get['sense']
        qs = super().get_queryset()
        qs = qs.filter(fno=fno, sense=sense)
        qs.update(bad = qs.values()[0]['bad'] + 1)
        return qs