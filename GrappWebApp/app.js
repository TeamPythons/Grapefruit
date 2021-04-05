
var x= [];
var ids = [];
const buyButton = document.querySelector('.btn');
const searchButton = document.querySelector('.searchButton');
const NAME=0;
const COST=1;
const PRODUCT_ID=2;
var costTotal = 0;

//All the products are stored here

const itemGrid = [["Grapefruit",10,00000000],["Grape",5,00000001],["Eggplant",15,00000002],["Durian",25,00000003]];

//Populate product list
searchButton.addEventListener('click', function() {
    for(var i = 0; i < itemGrid.length; i++){
        var list = document.getElementById('prodList');
        var itemName = itemGrid[i][NAME]+" $"+itemGrid[i][COST].toString();
        var entry = document.createElement('li');
        entry.appendChild(document.createTextNode(itemName));
        list.appendChild(entry);
    }

    console.log("Loaded");
    var lis = document.getElementById("prodList").getElementsByTagName('li');

    for (var i=0; i<lis.length; i++) {
        lis[i].addEventListener('click', orderItem, false);
    }
})


//Click Buy Button
buyButton.addEventListener('click', function() {
    //JSON.stringify(x)
    localStorage.setItem('shoppingCart', x);
    localStorage.setItem('cost', costTotal);
    localStorage.setItem('productId',ids);
    window.location.href = "checkout.html";
});


function getElementIndex(node) {
    //Returns the number of an element in a list
    var index = 0;
    while ( (node = node.previousElementSibling) ) {
        index++;
    }
    return index;
}

function orderItem() {
    //Add the item to the Shopping Cart and the lists X and ID'S which get stored in memory for checkout
    var selectedItem = this.textContent;
    var list = document.getElementById('cartList');
    var itemName = this.textContent;
    var itemIndex = getElementIndex(this);
    var itemPrice = itemGrid[itemIndex][COST];
    var itemID = itemGrid[itemIndex][PRODUCT_ID];
    costTotal += itemPrice;
    var p = document.getElementById("Price");
    p.textContent = "$"+costTotal.toString();

    var entry = document.createElement('li');
    entry.appendChild(document.createTextNode(itemName));
    list.appendChild(entry);
    x.push(itemName);
    ids.push(itemID);
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