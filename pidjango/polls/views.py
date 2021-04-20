from django.http import HttpResponse
from django.template import loader
from django.shortcuts import get_object_or_404, render
from .models import Inventory, IncomingOrders

#This is where you put all the code that handles HTTP Requests and serves the responses
#Do the REAL CODE HERE -Ryan

def index(request):
    if 'searchQuery' in request.GET:
        searchQuery = request.GET['searchQuery']
        dropdown = request.GET['dropdown'] #Search by product id, seller id or price
        latest_inventory_list = Inventory.objects.filter(product_id__icontains=searchQuery)
       
        #if dropdown == 'productID':
        ##    latest_inventory_list = Inventory.objects.filter(product_id__icontains=searchQuery)
        #elif dropdown == 'sellerID':
        #    latest_inventory_list = Inventory.objects.filter(seller_id__icontains=searchQuery)
        #else:
        #    latest_inventory_list = Inventory.objects.filter(retail_cost__range=(0,int(searchQuery))

        context = {'latest_inventory_list': latest_inventory_list}
        return render(request, 'polls/index.html', context)
    else:
        return render(request, 'polls/index.html')


def success(request):

    return render(request, 'polls/success.html')

#This currently adds only an instance of "newproduct" to the database
#change later to loop through all items in cart and add each to database
def checkout(request):
    if request.method == "POST":
        newOrder = IncomingOrders()
        newOrder.date = request.POST['fname']
        newOrder.cust_email = request.POST['email']
        newOrder.cust_location = request.POST['city']
        newOrder.product_id = 'newProduct'
        newOrder.product_quantity = 1
        newOrder.save()
    return render(request, 'polls/checkout.html')