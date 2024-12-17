package org.example.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/deleteProduct")
public class DeleteProduct extends HelloServlet {
    @Override
    protected void doDelete(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) {
        String productName = req.getParameter("name");
        resp.setContentType("text/plain");

        if (productName == null || productName.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                resp.getWriter().write("Product name is required for deletion.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        boolean removed = products.removeIf(product -> product.getName().equalsIgnoreCase(productName));

        try {
            if (removed) {
                resp.getWriter().write("Product \"" + productName + "\" was deleted successfully.");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Product \"" + productName + "\" not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
