from django.db import models

# Create your models here.
#Inventory database is initialized here - Ryan
#This is a LOCAL database not the remote one
#question_text and pub_date are the names of each column in the SQL_Lite database
class Inventory(models.Model):
    date = models.DateTimeField('date added')
    cust_email = models.CharField(max_length = 200)
    cust_location = models.CharField(max_length = 200)
    product_id = models.CharField(max_length = 200)
    product_quantity = models.IntegerField(default=0)
    wholesale_cost = models.IntegerField(default=0)
    retail_cost = models.IntegerField(default=0)
    seller_id = models.IntegerField(default=0)
    
class IncomingOrders(models.Model):
    date = models.DateTimeField('date added')
    cust_email = models.CharField(max_length = 200)
    cust_location = models.CharField(max_length = 200)
    product_id = models.CharField(max_length = 200)
    product_quantity = models.IntegerField(default=0)