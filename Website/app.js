'use strict'
var x= [];
const buyButton = document.querySelector('.btn');
var cost = 0;
var prices = [10,5,15,25];
var items = ["Grapefruit","Grape","Eggplant","Durian"];

//Click Buy Button
buyButton.addEventListener('click', function() {
    //JSON.stringify(x)
    localStorage.setItem('shoppingCart', x);
    localStorage.setItem('cost', cost);
    window.location.href = "checkout.html";
});

var lis = document.getElementById("prodList").getElementsByTagName('li');

for (var i=0; i<lis.length; i++) {
    lis[i].addEventListener('click', orderItem, false);
}

function getElementIndex(node) {
    var index = 0;
    while ( (node = node.previousElementSibling) ) {
        index++;
    }
    return index;
}

function orderItem() {

    var selectedItem = this.textContent;
    var list = document.getElementById('cartList');
    var itemName = this.textContent;
    var itemIndex = getElementIndex(this);
    var itemPrice = prices[itemIndex];
    cost += itemPrice;
    var p = document.getElementById("Price");
    p.textContent = "$"+cost.toString();

    var entry = document.createElement('li');
    entry.appendChild(document.createTextNode(itemName));
    list.appendChild(entry);
    x.push(itemName);
    //entry.addEventListener('click', deleteItem, false);
}



function getInputValues(){
    let nameValue = document.getElementById("name").value;  
    let emailAddressValue = document.getElementById("emailAddress").value;
    let shippingAddressValue = document.getElementById("shippingAddress").value;
    alert(nameValue);
    alert(emailAddressValue);
    alert(shippingAddressValue);
}


//function deleteItem() {
//    var itemToRemove = this.id;
//    itemToRemove.parentNode.removeChild(itemToRemove);
//}