from rest_framework import serializers
from .models import food_data, attribute, food

class food_dataSerializer(serializers.ModelSerializer):
    class Meta:
        model = food_data
        fields = '__all__'

class attributeSerializer(serializers.ModelSerializer):
    class Meta:
        model = attribute
        fields = '__all__'