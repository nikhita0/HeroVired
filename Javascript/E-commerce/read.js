document.addEventListener("DOMContentLoaded", function () {
    fetchProductData();
    const searchButton = document.getElementById("searchButton");
    searchButton.addEventListener("click", searchProduct);
 
    //const cartButton = document.getElementById('cart-button');
    //cartButton.addEventListener("click", updateCartDisplay);

   // const cartButton2 = document.querySelector(".cart-button");
    //cartButton2.addEventListener("click", toggleCartAndProducts);

    //cartButton2.addEventListener('click', () => toggleProductVisibility(productDiv));
});

var allProducts = [];
var cart = [];
var total = 0;


function toggleCartAndProducts() {
    const productsContainer = document.querySelector(".products");
    const cartItems = document.getElementById("cartItems");
   // productsContainer.classList.toggle("hidden");
    //cartItems.classList.toggle("hidden");

    // Toggle the visibility of products and cart items
    productsContainer.style.display = "none";
    cartItems.style.display = "block";
}

function fetchProductData() {
    fetch('products.json')
        .then((response) => {
            if (!response.ok) {
                throw new Error("Failed to fetch product data.");
            }
           
            return response.json();
        })
        .then((data) => {
            if (Array.isArray(data.products)) {
                allProducts = data.products;
                displayProducts(data.products);
            } else {
                throw new Error("The 'products' property is not an array.");
            }
        })
        .catch((error) => {
            console.error("Error fetching product data: " + error.message);
        });
}

function displayProducts(products) {
    const productsContainer = document.querySelector(".products");
    productsContainer.innerHTML = "";

    products.forEach(product => {
        const productDiv = createProductDiv(product);
        productsContainer.appendChild(productDiv);

        productDiv.addEventListener('click', () => toggleProductVisibility(productDiv));
    });
}

function toggleProductVisibility(clickedProduct) {
    const productDivs = document.querySelectorAll(".product");
    productDivs.forEach(productDiv => {
        if (productDiv === clickedProduct) {
            productDiv.style.display = "block";
        } else {
            productDiv.style.display = "none";
        }
    });
}

function searchProduct() {
    const searchInput = document.getElementById("searchInput");
    console.log(searchInput.value)
    const searchTerm = searchInput.value.toLowerCase();
    const matchingProducts = allProducts.filter(product => {
        const productName = product.Name.toLowerCase();
        return productName.includes(searchTerm);
    });
    displayProducts(matchingProducts);
}

// Initialize cart as an empty array and store it in localStorage
if (!localStorage.getItem('cart')) {
    localStorage.setItem('cart', JSON.stringify([]));
}

if (!localStorage.getItem('total')) {
    localStorage.setItem('total', '0');
}
/*
if (!localStorage.getItem('existingProduct')) {
    localStorage.setItem('existingProduct', JSON.stringify([]));
}*/
function addToCart(product) {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
  /*  let existingProductsRaw = localStorage.getItem('existingProduct');
    
    // Log the raw data to the console
    console.log('Existing Products (Raw):', existingProductsRaw);

    // Parse the JSON if it's a string, or initialize as an empty array
    let existingProducts = Array.isArray(existingProductsRaw) ? existingProductsRaw : [];
*/
let existingProductsRaw = localStorage.getItem('existingProduct');
console.log('Existing Products (Raw):', existingProductsRaw);

// Parse the JSON if it's a string, or initialize as an empty array
let existingProducts = Array.isArray(existingProductsRaw) ? JSON.parse(existingProductsRaw) : [];

    
    //let existingProducts = JSON.parse(localStorage.getItem('existingProduct')) || [];

    console.log('Product:', product);

    // Check if the product is already in the cart
    const existingProduct = cart.find(item => item.Name === product.Name);

    console.log('Existing Product in Cart:', existingProduct);

    if (existingProduct) {
        // Product already exists in the cart, update the quantity
        existingProduct.Quantity += 1;
    } else {
        // Product is not in the cart, add it
        product.Quantity = 1;
        cart.push(product);
    }

    // Check if the product is already in the existing products list
    const existingProductIndex = existingProducts.findIndex(item => item.Name === product.Name);

    console.log('Existing Product Index:', existingProductIndex);

    if (existingProductIndex !== -1) {
        // Product is already in the existing products list, update it
        existingProducts[existingProductIndex].Quantity += 1;
    } else {
        // Product is not in the existing products list, add it
        product.Quantity = 1;
        existingProducts.push(product);
    }

    // Log the content of existingProducts before storing in localStorage
    console.log('Existing Products (Before):', existingProducts);

    try {
        // Update the cart and existing products in localStorage
        localStorage.setItem('cart', JSON.stringify(cart));
        localStorage.setItem('existingProduct', JSON.stringify(existingProducts));
    } catch (error) {
        console.error('Error storing in localStorage:', error);
    }

    // Update the total in localStorage
    const total = parseFloat(localStorage.getItem('total')) || 0;
    localStorage.setItem('total', (total + product.Price).toString());

    console.log('Cart:', cart);
    console.log('Existing Products (After):', existingProducts);

    // Log a message to indicate that the function has been executed
    console.log('addToCart function executed successfully');
}


// Rest of your code remains the same


 export function updateCartDisplay() {
    const cartItems = document.getElementById("cartItems");
    cartItems.innerHTML = "";

    cart.forEach(product => {
        const cartItem = document.createElement("div");
        cartItem.className = "cart-product";

        const productName = document.createElement("h2");
        productName.textContent = product.Name;

        const productDescription = document.createElement("p");
        productDescription.textContent = product.Description;

        const productPrice = document.createElement("p");
        if (typeof product.Price === 'number') {
            productPrice.textContent = "Price: $" + product.Price.toFixed(2);
        } else {
            productPrice.textContent = "Price: N/A";
        }

        const removeButton = document.createElement("button");
        removeButton.textContent = "Remove";
        removeButton.addEventListener("click", () => removeFromCart(product));

        cartItem.appendChild(productName);
        cartItem.appendChild(productDescription);
        cartItem.appendChild(productPrice);
        cartItem.appendChild(removeButton);
        cartItems.appendChild(cartItem);
    });

    const totalPrice = document.createElement("p");
    totalPrice.textContent = "Total: $" + total.toFixed(2);
    cartItems.appendChild(totalPrice);
}

function createProductDiv(product) {
    const productDiv = document.createElement("div");
    productDiv.className = "product";
    productDiv.style.display = "block";

    const productImage = document.createElement("img");
    productImage.src = product.Image;
    productImage.alt = product.Name;

    const productName = document.createElement("h2");
    productName.textContent = product.Name;

    const productDescription = document.createElement("p");
    productDescription.textContent = product.Description;

    const productPrice = document.createElement("p");
    if (typeof product.Price === 'number') {
        productPrice.className = "price";
        productPrice.textContent = product.Price ? "Price: $" + product.Price.toFixed(2) : "Price: N/A";
    } else {
        productPrice.textContent = "Price: N/A";
    }

    const addToCartButton = document.createElement("button");
    addToCartButton.textContent = "Add to Cart";
    addToCartButton.addEventListener("click", () => addToCart(product));

    productDiv.appendChild(productImage);
    productDiv.appendChild(productName);
    productDiv.appendChild(productDescription);
    productDiv.appendChild(productPrice);
    productDiv.appendChild(addToCartButton);

    return productDiv;
}


