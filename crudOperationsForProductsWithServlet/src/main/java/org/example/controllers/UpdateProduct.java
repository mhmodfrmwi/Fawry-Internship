package org.example.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Product;

import java.io.IOException;
@WebServlet("/updateProduct")
public class UpdateProduct extends HelloServlet {
    @Override
    protected void doPut(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) {
        String productName = req.getParameter("name");
        String newPrice = req.getParameter("price");

        resp.setContentType("text/plain");

        if (productName == null || productName.isEmpty() || newPrice == null || newPrice.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                resp.getWriter().write("Product name and new price are required for updating.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try {
            int price = Integer.parseInt(newPrice);
            boolean updated = false;

            for (Product product : products) {
                if (product.getName().equalsIgnoreCase(productName)) {
                    product.setPrice(price);
                    updated = true;
                    break;
                }
            }

            if (updated) {
                resp.getWriter().write("Product \"" + productName + "\" was updated successfully.");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Product \"" + productName + "\" not found.");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                resp.getWriter().write("Invalid value for price. It must be a valid integer.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
