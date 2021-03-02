window.addEventListener('load', () => {
    //On window load, populate shopping cart
    const params = (new URL(document.location)).searchParams;
    const name = params.get('name');
    const x = localStorage.getItem('shoppingCart');
    const ids = localStorage.getItem('productId');
    const cost = localStorage.getItem('cost');
    const shoppingCartList = x.split(",");

    for(var i = 0; i < shoppingCartList.length; i++){
        var para = document.createElement("p");
        var node = document.createTextNode(shoppingCartList[i]);
        para.appendChild(node);
        var element = document.getElementById("shoppingCart");
        element.appendChild(para);
        //element.insertAdjacentHTML('<span class="price" id="Price" style="color:black"><b>$0</b></span></p>');
    }


    //document.getElementById('shopping-cart').innerHTML = x;
    document.getElementById('Price').textContent = "$"+cost.toString();

})
