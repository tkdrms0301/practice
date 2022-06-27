from rest_framework import serializers
from appServer.models import Diagnose

class DiagnoseSerializer(serializers.ModelSerializer) :
    class Meta:
        model = Diagnose
        fields = '__all__' 