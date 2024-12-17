package org.example.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.example.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products")
public class HelloServlet extends HttpServlet {
    public static List<Product>products;
    public HelloServlet(){
        products=new ArrayList<>();
        products.add(new Product("product1", 100));
        products.add(new Product("product2", 200));
    }
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) {
        resp.setContentType("text/html");
        try {
            resp.getWriter().println("<html><body>");
            resp.getWriter().println("<h1>Products</h1>");
            for(Product product:products){
                resp.getWriter().println("<p>"+product.getName()+" - "+product.getPrice()+"</p>");
            }
            resp.getWriter().println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String priceParam = req.getParameter("price");
        resp.setContentType("text/html");
        try {
            int price = Integer.parseInt(priceParam);
            Product product = new Product(name,price);
            products.add(product);
            System.out.println("Added Product: " + product);
            resp.getWriter().println("<h1>Added Product</h1>");
            resp.getWriter().println(product + "<br>");
        } catch (NumberFormatException | NullPointerException e) {
            resp.getWriter().println("<h1>Error: Invalid input</h1>");
            resp.getWriter().println("<p>Please provide valid 'name' and 'price' parameters.</p>");
        } catch (RuntimeException e) {
            resp.getWriter().println("<h1>Error</h1>");
            resp.getWriter().println("<p>" + e.getMessage() + "</p>");
        }

        resp.getWriter().println("<br><a href='addProduct.html'>Back to Add</a>");
    }

}
