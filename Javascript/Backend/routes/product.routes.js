const express = require("express");
const {
  addProduct,
  updateProduct,
  filterProduct,
  deleteproduct,
  allProducts,
  searchById,
  getBycategory
} = require("../controllers/product.controllers");
const jwtHandler = require("../utils/jwthandler");

// Creating a custom route for product-related endpoints
const productRoute = express.Router();

// Route to add a new product
productRoute.post("/addProduct", addProduct);

// Route to update an existing product
productRoute.post("/updateProduct", jwtHandler, updateProduct);

// Route to filter and retrieve products
productRoute.post("/filterProduct", jwtHandler, filterProduct);

// Route to delete a specific product by its productId
productRoute.post("/deleteproduct/:productId", jwtHandler, deleteproduct);

// Route to getting the list of products from server
productRoute.get("/allProducts", allProducts);

//searching for product by id
productRoute.get("/searchById", searchById);

//searching for product by category
productRoute.get("/getBycategory", getBycategory);

// Exporting the productRoute for use in the main app
module.exports = productRoute;
