from django.contrib import admin

# Register your models here.
from .models import Inventory
admin.site.register(Inventory)
from .models import IncomingOrders
admin.site.register(IncomingOrders)