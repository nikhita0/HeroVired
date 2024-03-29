function removeFromCart(product) {
    console.log("Product to be removed:", product);

    // Remove one unit of the product from the cart
    const cart = JSON.parse(localStorage.getItem('cart'));
    console.log("Cart before removal:", cart);

    const updatedCart = cart.map(item => {
        if (item.id === product.id && item.Quantity >= 1) {
            // Decrease the quantity by 1 if it's greater than 1
            item.Quantity -= 1;
        }
        return item;
    }).filter(item => item.Quantity > 0); // Remove items with quantity 0

    localStorage.setItem('cart', JSON.stringify(updatedCart));

   
     const total = updatedCart.reduce((acc, item) => acc + (item.Price * item.Quantity), 0);
     localStorage.setItem('total', total.toString());

    console.log("Cart after removal:", updatedCart);

    // Redisplay the cart items
    displayCartItems();
}


function displayCartItems() {
    // Fetch the cart items from localStorage
   try{
     const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const total = parseFloat(localStorage.getItem('total')) || 0;

    const cartItems = document.getElementById("cartItems");
    cartItems.innerHTML = "";


    // Display existing products
    const existingProductsRaw = localStorage.getItem('existingProduct');
    console.log('Existing Products (Raw):', existingProductsRaw);

    const existingProducts = JSON.parse(existingProductsRaw) || [];
    console.log('Existing Products (Parsed):', existingProducts);

    // Display cart items
    cart.forEach(product => {
        console.log(product)
        const cartItem = document.createElement("div");
        cartItem.className = "cart-product";

        const productName = document.createElement("h2");
        productName.textContent = product.Name;

        const productDescription = document.createElement("p");
        productDescription.textContent = product.Description;

        const productPrice = document.createElement("p");
        productPrice.textContent = "Price: $" + product.Price.toFixed(2);

        const productQuantity = document.createElement("p");
        productQuantity.textContent = "Quantity: "+product.Quantity;

        const removeButton = document.createElement("button");
        removeButton.textContent = "Remove";
        removeButton.addEventListener("click", () => removeFromCart(product));

        cartItem.appendChild(productName);
        cartItem.appendChild(productDescription);
        cartItem.appendChild(productPrice);
        cartItem.appendChild(productQuantity);
        cartItem.appendChild(removeButton);
        cartItems.appendChild(cartItem);
    });

    const totalPrice = document.createElement("p");
    totalPrice.textContent = "Total: $" + total.toFixed(2);
    cartItems.appendChild(totalPrice);
}catch (error) {
        console.error('Error in displayCartItems:', error);
    }
}

// Call the displayCartItems function when the cart page loads
document.addEventListener("DOMContentLoaded", function () {
    displayCartItems();
});
