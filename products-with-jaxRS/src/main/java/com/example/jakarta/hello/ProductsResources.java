package com.example.jakarta.hello;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;

import java.util.List;

@Path( "/products")
public class ProductsResources {
    private static final String JSON_MEDIA_TYPE = MediaType.APPLICATION_JSON;

    private final List<Product> productList = new ArrayList<>();

    public ProductsResources() {
        productList.add(new Product("product1", 100));
    }

    @GET
    @Produces(JSON_MEDIA_TYPE)
    public List<Product> getAllProducts() {
        return productList;
    }

    @POST
    @Produces(JSON_MEDIA_TYPE)
    public Object addProduct(Product product) {
        if (productList.stream().anyMatch(p -> p.getName().equals(product.getName()))) {
            return "this product exists";
        }
        addProductToList(product);
        return productList;
    }
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProduct(@PathParam("name") String name) {
        if (!productList.stream().anyMatch(product -> product.getName().equals(name))) {
            return "Product not found";
        }
        return productList.stream().filter(product -> product.getName().equals(name)).findFirst().get();
    }
    @PATCH
    @Path("/{name}")
    @Produces(JSON_MEDIA_TYPE)
    public Object updateProduct(@PathParam("name") String name, Product updatedProduct) {
        if (!updatedProduct.getName().equals(name)) {
            return "product not found";
        }
        if (productList.stream().anyMatch(product -> product.getName().equals(updatedProduct.getName()))) {
            return "product already exists";
        }
        deleteProductFromList(name);
        addProductToList(updatedProduct);
        return updatedProduct;
    }

    @DELETE
    @Path("/{name}")
    @Produces(JSON_MEDIA_TYPE)
    public String deleteProduct(@PathParam("name") String name) {
        if (!productList.stream().anyMatch(product -> product.getName().equals(name))) {
            return "product not found";
        }
        deleteProductFromList(name);
        return "Deleted product: " + name;
    }

    private void addProductToList(Product product) {
        productList.add(product);
    }

    private void deleteProductFromList(String name) {
        productList.removeIf(product -> product.getName().equals(name));
    }
}