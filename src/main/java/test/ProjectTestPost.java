package test;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static test.TestConfig.*;

//***** Must Pay Attention *****//
//***** Run In Order: POST, GET, PUT *****//
//***** Run First in Order *****//
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectTestPost {
    private static StringBuilder output = new StringBuilder();


    @Test
    @Order(1)
    public void testAddProduct() throws IOException {
        output.append("Product");
        URL url = new URL(TestConfig.URL + "products");
        String jsonInputString = "{\"name\":\"Ipad\",\"model\":\"2018\",\"brand\":\"Apple\",\"company\":\"Apple Inc.\",\"description\":\"A Good Tablet\",\"price\":1000.0}";
        String expected = "{\"id\":1,\"name\":\"Ipad\",\"model\":\"2018\",\"brand\":\"Apple\",\"company\":\"Apple Inc.\",\"description\":\"A Good Tablet\",\"price\":1000.0}";
        assertEquals(expected, TestConfig.requestPost(url, jsonInputString));
    }

    @Test
    @Order(2)
    public void testAddCategory() throws IOException {
        output.append("Category");
        URL url = new URL(URL + "categories");
        String jsonInputString = "{\"name\":\"Apple\"}";
        String expected = "{\"id\":1,\"name\":\"Apple\"}";
        assertEquals(expected, requestPost(url, jsonInputString));
    }

    @Test
    @Order(3)
    public void testAddProductCategory() throws IOException {
        output.append("ProductCategory");
        URL url = new URL(URL + "product_categories");
        String jsonInputString = "{\"product\":{\"id\":1},\"category\":{\"id\":1}}";
        String json = requestPost(url, jsonInputString);
        Gson gson = new Gson();
        ProductCategory productCategories = gson.fromJson(json, new TypeToken<ProductCategory>(){}.getType());
        assertEquals(productCategories.getProduct().getId(), 1);
        assertEquals(productCategories.getCategory().getId(), 1);
    }

    @Test
    @Order(4)
    public void testAddCustomer() throws IOException{
        output.append("Customer");
        URL url = new URL(URL + "customers");
        String jsonInputString = "{\"name\":\"Mark\",\"address\":\"America\",\"phone\":\"0123456789\",\"fax\":\"0123456789\",\"email\":\"mark@gmail.com\",\"contactPerson\":\"Mark\"}";
        String expected = "{\"id\":1,\"name\":\"Mark\",\"address\":\"America\",\"phone\":\"0123456789\",\"fax\":\"0123456789\",\"email\":\"mark@gmail.com\",\"contactPerson\":\"Mark\"}";
        assertEquals(expected, requestPost(url, jsonInputString));
    }

    @Test
    @Order(5)
    public void testAddProvider() throws IOException{
        output.append("Provider");
        URL url = new URL(URL + "providers");
        String jsonInputString = "{\"name\":\"Apple Inc\",\"address\":\"America\",\"phone\":\"0123456789\",\"fax\":\"0123456789\",\"email\":\"daniel@gmail.com\",\"contactPerson\":\"Mark\"}";
        String expected = "{\"id\":1,\"name\":\"Apple Inc\",\"address\":\"America\",\"phone\":\"0123456789\",\"fax\":\"0123456789\",\"email\":\"daniel@gmail.com\",\"contactPerson\":\"Mark\"}";
        assertEquals(expected, requestPost(url, jsonInputString));
    }

    @Test
    @Order(6)
    public void testAddStaff() throws IOException{
        output.append("Staff");
        URL url = new URL(URL + "staffs");
        String jsonInputString = "{\"name\":\"Hannah\",\"address\":\"American\",\"phone\":\"0123456789\",\"email\":\"hannah@gmail.com\"}";
        String expected = "{\"id\":1,\"name\":\"Hannah\",\"address\":\"American\",\"phone\":\"0123456789\",\"email\":\"hannah@gmail.com\"}";
        assertEquals(expected, requestPost(url, jsonInputString));
    }

    @Test
    @Order(7)
    public void testAddOrder() throws IOException{
        output.append("Order");
        URL url = new URL(URL + "orders");
        String jsonInputString = "{\"date\":\"2019-02-15T00:00:00Z\",\"staff\":{\"id\":1},\"provider\":{\"id\":1}}";
        String json = requestPost(url, jsonInputString);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        model.Order order = gson.fromJson(json,
                new TypeToken<model.Order>(){}.getType());

        assertEquals(order.getProvider().getId(), 1);
        assertEquals(order.getStaff().getId(), 1);
    }

    @Test
    @Order(8)
    public void testAddOrderDetail() throws IOException{
        output.append("OrderDetail");
        URL url = new URL(URL + "order-details");
        String jsonInputString = "{\"order\":{\"id\":1},\"product\":{\"id\":1},\"quantity\":2,\"price\":2000}";
        String json = requestPost(url, jsonInputString);
        Gson gson = new Gson();
        OrderDetail orderDetail = gson.fromJson(json,
                new TypeToken<OrderDetail>(){}.getType());

        assertEquals(orderDetail.getProduct().getId(), 1);
        assertEquals(orderDetail.getOrder().getId(), 1);
    }

    @Test
    @Order(9)
    public void testAddReceivingNote() throws IOException{
        output.append("Receiving");
        URL url = new URL(URL + "receiving-notes");
        String jsonInputString = "{\"date\":\"2019-02-15T00:00:00Z\",\"staff\":{\"id\":1},\"order\":{\"id\":1}}";
        String json = requestPost(url, jsonInputString);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        ReceivingNote receivingNote = gson.fromJson(json,
                new TypeToken<ReceivingNote>(){}.getType());

        assertEquals(receivingNote.getOrder().getId(), 1);
        assertEquals(receivingNote.getStaff().getId(), 1);

    }

    @Test
    @Order(10)
    public void testAddDeliveryNote() throws IOException{
        output.append("Delivery");
        URL url = new URL(URL + "delivery-notes");
        String jsonInputString = "{\"date\":\"2020-02-15T00:00:00Z\",\"product\":{\"id\":1},\"quantity\":2}";
        String json = requestPost(url, jsonInputString);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        DeliveryNote deliveryNote = gson.fromJson(json,
                new TypeToken<DeliveryNote>(){}.getType());

        assertEquals(deliveryNote.getProduct().getId(), 1);
    }

    @Test
    @Order(11)
    public void testAddSaleInvoice() throws IOException{
        output.append("Sale");
        URL url = new URL(URL +"sale-invoices");
        String jsonInputString = "{\"date\":\"2020-02-15T00:00:00Z\",\"product\":{\"id\":1},\"quantity\":2,\"staff\":{\"id\":1},\"customer\":{\"id\":1}}";
        String json = requestPost(url, jsonInputString);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        SaleInvoice saleInvoice = gson.fromJson(json,
                new TypeToken<SaleInvoice>(){}.getType());

        assertEquals(saleInvoice.getProduct().getId(), 1);
        assertEquals(saleInvoice.getCustomer().getId(), 1);
    }

    @Test
    public void test() {
        System.out.println(output.toString());
    }



}
