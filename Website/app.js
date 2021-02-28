'use strict'
var x= [];
const buyButton = document.querySelector('.btn');


//Click Buy Button
buyButton.addEventListener('click', function() {
    //JSON.stringify(x)
    localStorage.setItem('shoppingCart', x);
    window.location.href = "checkout.html";
});

var lis = document.getElementById("prodList").getElementsByTagName('li');

for (var i=0; i<lis.length; i++) {
    lis[i].addEventListener('click', orderItem, false);
}

function orderItem() {

    var selectedItem = this.textContent;
    var list = document.getElementById('cartList');
    var itemName = this.textContent;
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