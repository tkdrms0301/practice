from rest_framework import routers, views
from django.contrib import admin
from django.urls import path
from appServer.views import DiagnoseListAPI

urlpatterns = [
    path('admin/', admin.site.urls),
	path('appServer/get', DiagnoseListAPI.as_view()),
    path('appServer/post', DiagnoseListAPI.as_view()),
]