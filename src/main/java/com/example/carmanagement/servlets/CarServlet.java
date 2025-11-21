package com.example.carmanagement.servlets;

import com.example.carmanagement.models.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CarServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, Car> carData = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");

        if (id == null || id.isEmpty()) {
            out.print("{\"message\":\"Car ID is required\"}");
            return;
        }

        Car car = carData.get(id);

        if (car == null) {
            out.print("{\"message\":\"Car not found\"}");
        } else {
            String json = objectMapper.writeValueAsString(car);
            out.print(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        Car car = objectMapper.readValue(request.getInputStream(), Car.class);

        if (car.getId() == null) {
            out.print("{\"message\":\"Car ID is required\"}");
            return;
        }

        carData.put(car.getId(), car);

        Map<String, Object> result = new HashMap<>();
        result.put("message", "Car added successfully");
        result.put("car", car);

        out.print(objectMapper.writeValueAsString(result));
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");

        if (id == null) {
            out.print("{\"message\":\"Car ID is required\"}");
            return;
        }

        Car deletedCar = carData.remove(id);

        if (deletedCar == null) {
            out.print("{\"message\":\"Car not found\"}");
        } else {
            out.print("{\"message\":\"Car deleted successfully\"}");
        }
    }
}
