from django.db import models

class Diagnose(models.Model):
    diseaseName = models.TextField(blank=True, null=True)
    description = models.TextField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'diagnose'

#python manage.py inspectdb
#python manage.py makemigrations
#python manage.py migrate