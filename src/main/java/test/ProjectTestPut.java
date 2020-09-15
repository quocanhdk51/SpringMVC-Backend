package test;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static test.TestConfig.requestPost;
import static test.TestConfig.requestPut;

//***** Must Pay Attention *****//
//***** Run In Order: POST, GET, PUT *****//
//***** Run Last In Order *****//
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectTestPut {

    private static StringBuilder output = new StringBuilder();


    @Test
    @Order(1)
    public void testEditProduct() throws IOException {
        output.append("Product");
        URL url = new URL(TestConfig.URL + "products");
        String jsonInputString = "{\"name\":\"Imac\",\"model\":\"2019\",\"brand\":\"Apple\",\"company\":\"Apple Inc.\",\"description\":\"A Good Computer\",\"price\":2000.0}";
        requestPost(url, jsonInputString);
        jsonInputString = "{\"id\":2,\"name\":\"Imac\",\"model\":\"2019\",\"brand\":\"Apple\",\"company\":\"Apple Inc.\",\"description\":\"A Good Computer\",\"price\":2500.0}";
        assertEquals(jsonInputString, TestConfig.requestPut(url, jsonInputString));
    }

    @Test
    @Order(2)
    public void testEditCategory() throws IOException {
        output.append("Category");
        URL url = new URL(TestConfig.URL + "categories");
        String jsonInputString = "{\"name\":\"Luxury\"}";
        requestPost(url, jsonInputString);
        jsonInputString = "{\"id\":2,\"name\":\"Electronics\"}";
        assertEquals(jsonInputString, requestPut(url, jsonInputString));
    }

    @Test
    @Order(3)
    public void testEditProductCategory() throws IOException {
        output.append("ProductCategory");
        URL url = new URL(TestConfig.URL + "product_categories");
        String jsonInputString = "{\"product\":{\"id\":2},\"category\":{\"id\":1}}";
        requestPost(url, jsonInputString);
        jsonInputString = "{\"id\":2,\"product\":{\"id\":2},\"category\":{\"id\":2}}";
        String json = requestPut(url, jsonInputString);
        Gson gson = new Gson();
        ProductCategory productCategories = gson.fromJson(json, new TypeToken<ProductCategory>(){}.getType());
        assertEquals(productCategories.getProduct().getId(), 2);
        assertEquals(productCategories.getCategory().getId(), 2);
    }

    @Test
    @Order(4)
    public void testEditCustomer() throws IOException{
        output.append("Customer");
        URL url = new URL(TestConfig.URL + "customers");
        String jsonInputString = "{\"name\":\"Daniel\",\"address\":\"America\",\"phone\":\"0123456789\",\"fax\":\"0123456789\",\"email\":\"daniel@gmail.com\",\"contactPerson\":\"Daniel\"}";
        requestPost(url, jsonInputString);
        jsonInputString = "{\"id\":2,\"name\":\"Daniel\",\"address\":\"America\",\"phone\":\"0123456789\",\"fax\":\"0123456789\",\"email\":\"daniel@gmail.com\",\"contactPerson\":\"Daniel\"}";
        assertEquals(jsonInputString, requestPut(url, jsonInputString));
    }

    @Test
    @Order(5)
    public void testEditProvider() throws IOException{
        output.append("Provider");
        URL url = new URL(TestConfig.URL + "providers");
        String jsonInputString = "{\"name\":\"Jb Hifi Inc\",\"address\":\"Australia\",\"phone\":\"0123456789\",\"fax\":\"0123456789\",\"email\":\"jbhifi@gmail.com\",\"contactPerson\":\"Zack\"}";
        requestPost(url, jsonInputString);
        jsonInputString = "{\"id\":2,\"name\":\"Jb Hifi Inc\",\"address\":\"Australia\",\"phone\":\"0123456789\",\"fax\":\"0123456789\",\"email\":\"jbhifi@gmail.com\",\"contactPerson\":\"Tom\"}";
        assertEquals(jsonInputString, requestPut(url, jsonInputString));
    }

    @Test
    @Order(6)
    public void testEditStaff() throws IOException{
        output.append("Staff");
        URL url = new URL(TestConfig.URL + "staffs");
        String jsonInputString = "{\"name\":\"Teo\",\"address\":\"American\",\"phone\":\"0123456789\",\"email\":\"teo@gmail.com\"}";
        requestPost(url, jsonInputString);
        jsonInputString = "{\"id\":2,\"name\":\"Teo\",\"address\":\"American\",\"phone\":\"9876543210\",\"email\":\"teo@gmail.com\"}";
        assertEquals(jsonInputString, requestPut(url, jsonInputString));
    }

    @Test
    @Order(7)
    public void testEditOrder() throws IOException{
        output.append("Order");
        URL url = new URL(TestConfig.URL + "orders");
        String jsonInputString = "{\"date\":\"2019-02-15T00:00:00Z\",\"staff\":{\"id\":2},\"provider\":{\"id\":1}}";
        requestPost(url, jsonInputString);
        jsonInputString = "{\"id\":2,\"date\":\"2019-02-15T00:00:00Z\",\"staff\":{\"id\":2},\"provider\":{\"id\":2}}";
        String json = requestPut(url, jsonInputString);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        model.Order order = gson.fromJson(json,
                new TypeToken<model.Order>(){}.getType());

        assertEquals(order.getProvider().getId(), 2);
        assertEquals(order.getStaff().getId(), 2);
    }

    @Test
    @Order(8)
    public void testEditOrderDetail() throws IOException{
        output.append("OrderDetail");
        URL url = new URL(TestConfig.URL + "order-details");
        String jsonInputString = "{\"order\":{\"id\":1},\"product\":{\"id\":2},\"quantity\":1,\"price\":2500}";
        requestPost(url, jsonInputString);
        jsonInputString = "{\"id\":2,\"order\":{\"id\":2},\"product\":{\"id\":2},\"quantity\":1,\"price\":2500}";
        String json = requestPut(url, jsonInputString);
        Gson gson = new Gson();
        OrderDetail orderDetail = gson.fromJson(json,
                new TypeToken<OrderDetail>(){}.getType());

        assertEquals(orderDetail.getProduct().getId(), 2);
        assertEquals(orderDetail.getOrder().getId(), 2);
    }

    @Test
    @Order(9)
    public void testEditReceivingNote() throws IOException{
        output.append("Receiving");
        URL url = new URL(TestConfig.URL + "receiving-notes");
        String jsonInputString = "{\"date\":\"2019-02-15T00:00:00Z\",\"staff\":{\"id\":1},\"order\":{\"id\":2}}";
        requestPost(url, jsonInputString);
        jsonInputString = "{\"id\":2,\"date\":\"2019-02-15T00:00:00Z\",\"staff\":{\"id\":2},\"order\":{\"id\":2}}";
        String json = requestPut(url, jsonInputString);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        ReceivingNote receivingNote = gson.fromJson(json,
                new TypeToken<ReceivingNote>(){}.getType());

        assertEquals(receivingNote.getOrder().getId(), 2);
        assertEquals(receivingNote.getStaff().getId(), 2);

    }

    @Test
    @Order(10)
    public void testEditDeliveryNote() throws IOException{
        output.append("Delivery");
        URL url = new URL(TestConfig.URL + "delivery-notes");
        String jsonInputString = "{\"date\":\"2020-02-15T00:00:00Z\",\"product\":{\"id\":1},\"quantity\":1}";
        requestPost(url, jsonInputString);
        jsonInputString = "{\"id\":2,\"date\":\"2020-02-15T00:00:00Z\",\"product\":{\"id\":2},\"quantity\":1}";
        String json = requestPut(url, jsonInputString);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        DeliveryNote deliveryNote = gson.fromJson(json,
                new TypeToken<DeliveryNote>(){}.getType());

        assertEquals(deliveryNote.getProduct().getId(), 2);
    }

    @Test
    @Order(11)
    public void testEditSaleInvoice() throws IOException{
        output.append("Sale");
        URL url = new URL(TestConfig.URL +"sale-invoices");
        String jsonInputString = "{\"date\":\"2020-02-15T00:00:00Z\",\"product\":{\"id\":1},\"quantity\":1,\"staff\":{\"id\":2},\"customer\":{\"id\":2}}";
        requestPost(url, jsonInputString);
        jsonInputString = "{\"id\":2,\"date\":\"2020-02-15T00:00:00Z\",\"product\":{\"id\":2},\"quantity\":1,\"staff\":{\"id\":2},\"customer\":{\"id\":2}}";
        String json = requestPut(url, jsonInputString);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        SaleInvoice saleInvoice = gson.fromJson(json,
                new TypeToken<SaleInvoice>(){}.getType());

        assertEquals(saleInvoice.getProduct().getId(), 2);
        assertEquals(saleInvoice.getCustomer().getId(), 2);
    }

    @Test
    public void test() {
        System.out.println(output.toString());
    }
}
