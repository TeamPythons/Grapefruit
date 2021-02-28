window.addEventListener('load', () => {
    const params = (new URL(document.location)).searchParams;
    const name = params.get('name');
    const x = localStorage.getItem('shoppingCart');

    document.getElementById('shopping-cart').innerHTML = x;

})
