from django.urls import path, include
from django.conf.urls import url

from . import views

#THESE URLS ALL GIVE AN ERROR! IT HAS TO DO WITH PASSING ARGUMENTS IN THE URL
#Perhaps the Kwargs argument in the path function needs to be used?

urlpatterns = [
    #index is the main page that loads right away
	path('', views.index, name='index'),

    path('success',views.success,name='success'),
    path('checkout',views.checkout,name='checkout'),
]