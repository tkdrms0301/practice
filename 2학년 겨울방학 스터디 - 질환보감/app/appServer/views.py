from random import random
from django.shortcuts import render
from django.template import RequestContext
from rest_framework import serializers, viewsets
from rest_framework.response import Response
from .models import Diagnose
from rest_framework.views import APIView
from .serializers import DiagnoseSerializer
import numpy

from diagnostic.disease_similarity import Model

st = Model()
class DiagnoseListAPI(APIView):
    def get(self, request):

        sentence = request.GET.get('symptom', False)
        print(sentence)
        indices = st.get_indices(sentence)
        print(indices) #id list

        queryset = Diagnose.objects.all()
        queryset = list(Diagnose.objects.values())
        queryset_result = list()
        for diagnose in queryset:
            for id in indices:
                if diagnose['id'] == id:
                    queryset_result.append(diagnose)
        serializer = DiagnoseSerializer(queryset_result, many=True)
        return Response(serializer.data)
    
    def post(self, request):

        sentence = request.POST.get('symptom', False)
        print(sentence)
        indices = st.get_indices(sentence)
        print(indices) #id list

        queryset = Diagnose.objects.all()
        queryset = list(Diagnose.objects.values())
        queryset_result = list()
        for diagnose in queryset:
            for id in indices:
                if diagnose['id'] == id:
                    queryset_result.append(diagnose)
        serializer = DiagnoseSerializer(queryset_result, many=True)
        return Response(serializer.data)