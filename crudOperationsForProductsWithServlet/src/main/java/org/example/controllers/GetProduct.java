package org.example.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Product;

import java.io.IOException;
@WebServlet("/getProduct")
public class GetProduct extends HelloServlet {
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) {
        String productName = req.getParameter("name");
        resp.setContentType("text/plain");

        if (productName == null || productName.isEmpty()) {
            resp.setContentType("text/html");
            try {
                resp.getWriter().println("<html><body>");
                resp.getWriter().println("<h1>Products</h1>");
                for (Product product : products) {
                    resp.getWriter().println("<p>" + product.getName() + " - " + product.getPrice() + "</p>");
                }
                resp.getWriter().println("</body></html>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        Product foundProduct = null;
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                foundProduct = product;
                break;
            }
        }

        try {
            if (foundProduct != null) {
                resp.getWriter().write("Product found: " + foundProduct.getName() + " - Price: " + foundProduct.getPrice());
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Product \"" + productName + "\" not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
