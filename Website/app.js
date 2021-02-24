'use strict'
var x= [];
const switcher = document.querySelector('.btn');

switcher.addEventListener('click', function() {
    document.body.classList.toggle('dark-theme')

    var className = document.body.className;
    if(className == "light-theme") {
        this.textContent = "Dark";
    }
    else {
        this.textContent = "Light";
    }

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