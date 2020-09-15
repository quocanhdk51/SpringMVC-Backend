package test;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static test.TestConfig.*;
import static test.TestConfig.requestGet;

//***** Must Pay Attention *****//
//***** Run In Order: POST, GET, PUT *****//
//***** Run Second in Order *****//
public class ProjectTestGet {
    @Test
    public void testGetProductsByName() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("s", "a");

        URL url = new URL(TestConfig.URL + "products/search?" + getParamsString(params));
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<Product> products = gson.fromJson(json,
                new TypeToken<List<Product>>(){}.getType());

        assertEquals(products.get(0).getName(), "Ipad");
    }

    @Test
    public void testGetAllProducts() throws IOException {
        URL url = new URL(TestConfig.URL + "products");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<Product> products = gson.fromJson(json,
                new TypeToken<List<Product>>(){}.getType());

        assertEquals(products.get(0).getName(), "Ipad");
    }

    @Test
    public void testGetProductByID() throws IOException{
        URL url = new URL(TestConfig.URL + "products/1");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        Product product = gson.fromJson(json, new TypeToken<Product>(){}.getType());
        assertEquals(product.getName(), "Ipad");
    }

    @Test
    public void testGetCategoriesByProductID() throws IOException{
        URL url = new URL(TestConfig.URL + "products/1/categories");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<Category> categories = gson.fromJson(json, new TypeToken<List<Category>>(){}.getType());
        assertEquals(categories.get(0).getName(), "Apple");
    }

    @Test
    public void testGetOrderDetailsByProductID() throws IOException{
        URL url = new URL(TestConfig.URL + "products/1/order-details");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<OrderDetail> categories = gson.fromJson(json, new TypeToken<List<OrderDetail>>(){}.getType());
        assertEquals(categories.get(0).getOrder().getStaff().getName(), "Hannah");
    }

    @Test
    public void testGetDeliveryNotesByProductID() throws IOException{
        URL url = new URL(TestConfig.URL + "products/1/delivery-notes");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<DeliveryNote> deliveryNotes = gson.fromJson(json, new TypeToken<List<DeliveryNote>>(){}.getType());
        assertEquals(deliveryNotes.get(0).getProduct().getName(), "Ipad");
    }

    @Test
    public void testGetSaleInvoicesByProductID() throws IOException{
        URL url = new URL(TestConfig.URL + "products/1/sale-invoices");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<SaleInvoice> saleInvoices = gson.fromJson(json, new TypeToken<List<SaleInvoice>>(){}.getType());
        assertEquals(saleInvoices.get(0).getProduct().getName(), "Ipad");
    }

    @Test
    public void testGetCategoriesByName() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("s", "A");

        URL url = new URL(TestConfig.URL + "categories/search?" + getParamsString(params));
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<Category> categories = gson.fromJson(json,
                new TypeToken<List<Category>>(){}.getType());

        assertEquals(categories.get(0).getName(), "Apple");
    }

    @Test
    public void testGetAllCategories() throws IOException {
        URL url = new URL(TestConfig.URL + "categories");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<Category> categories = gson.fromJson(json,
                new TypeToken<List<Category>>(){}.getType());

        assertEquals(categories.get(0).getName(), "Apple");
    }

    @Test
    public void testGetCategoryByID() throws IOException{
        URL url = new URL(TestConfig.URL + "categories/1");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        Category category = gson.fromJson(json, new TypeToken<Category>(){}.getType());
        assertEquals(category.getName(), "Apple");
    }

    @Test
    public void testGetProductsByCategoryID() throws IOException{
        URL url = new URL(TestConfig.URL + "categories/1/products");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<Product> products = gson.fromJson(json, new TypeToken<List<Product>>(){}.getType());
        assertEquals(products.get(0).getName(), "Ipad");
    }

    @Test
    public void testGetCustomersByName() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("s", "a");

        URL url = new URL(TestConfig.URL + "customers/search?" + getParamsString(params));
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<Customer> customers = gson.fromJson(json,
                new TypeToken<List<Customer>>(){}.getType());

        assertEquals(customers.get(0).getName(), "Mark");
    }

    @Test
    public void testGetAllCustomers() throws IOException {
        URL url = new URL(TestConfig.URL + "customers");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<Customer> customers = gson.fromJson(json,
                new TypeToken<List<Customer>>(){}.getType());

        assertEquals(customers.get(0).getName(), "Mark");
    }

    @Test
    public void testGetCustomerByID() throws IOException{
        URL url = new URL(TestConfig.URL + "customers/1");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        Customer customer = gson.fromJson(json, new TypeToken<Customer>(){}.getType());
        assertEquals(customer.getName(), "Mark");
    }

    @Test
    public void testGetSaleInvoicesByCustomerID() throws IOException{
        URL url = new URL(TestConfig.URL + "customers/1/sale-invoices");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<SaleInvoice> saleInvoices = gson.fromJson(json, new TypeToken<List<SaleInvoice>>(){}.getType());
        assertEquals(saleInvoices.get(0).getTotal(), 2000);
    }

    @Test
    public void testGetRevenueByCustomer() throws IOException{
        Map<String, String> params = new HashMap<>();
        params.put("start", "01-01-2019");
        params.put("end", "01-01-2021");

        URL url = new URL(URL + "customers/1/revenue?" + getParamsString(params));
        String json = requestGet(url);
        assertEquals(json, "2000.0");
    }

    @Test
    public void testGetProvidersByName() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("s", "A");

        URL url = new URL(TestConfig.URL + "providers/search?" + getParamsString(params));
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<Provider> providers = gson.fromJson(json,
                new TypeToken<List<Provider>>(){}.getType());

        assertEquals(providers.get(0).getName(), "Apple Inc");
    }

    @Test
    public void testGetAllProviders() throws IOException {
        URL url = new URL(TestConfig.URL + "providers");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<Provider> providers = gson.fromJson(json,
                new TypeToken<List<Provider>>(){}.getType());

        assertEquals(providers.get(0).getName(), "Apple Inc");
    }

    @Test
    public void testGetProviderByID() throws IOException{
        URL url = new URL(TestConfig.URL + "providers/1");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        Provider provider = gson.fromJson(json, new TypeToken<Provider>(){}.getType());
        assertEquals(provider.getName(), "Apple Inc");
    }

    @Test
    public void testGetOrdersByProviderID() throws IOException{
        URL url = new URL(TestConfig.URL + "providers/1/orders");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<Order> orders = gson.fromJson(json, new TypeToken<List<Order>>(){}.getType());
        assertEquals(orders.get(0).getProvider().getName(), "Apple Inc");
    }

    @Test
    public void testGetAllOrders() throws IOException {
        URL url = new URL(TestConfig.URL + "orders");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<Order> orders = gson.fromJson(json,
                new TypeToken<List<Order>>(){}.getType());

        assertEquals(orders.get(0).getProvider().getName(), "Apple Inc");
        assertEquals(orders.get(0).getStaff().getName(), "Hannah");
    }

    @Test
    public void testGetOrderByID() throws IOException{
        URL url = new URL(TestConfig.URL + "orders/1");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        Order order = gson.fromJson(json, new TypeToken<Order>(){}.getType());
        assertEquals(order.getProvider().getName(), "Apple Inc");
        assertEquals(order.getStaff().getName(), "Hannah");
    }

    @Test
    public void testGetOrderDetailsByOrderID() throws IOException{
        URL url = new URL(TestConfig.URL + "orders/1/order-details");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<OrderDetail> orderDetails = gson.fromJson(json, new TypeToken<List<OrderDetail>>(){}.getType());
        assertEquals(orderDetails.get(0).getProduct().getName(), "Ipad");
    }

    @Test
    public void testGetReceivingNotesByOrderID() throws IOException{
        URL url = new URL(TestConfig.URL + "orders/1/receiving-notes");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        ReceivingNote receivingNote = gson.fromJson(json, new TypeToken<ReceivingNote>(){}.getType());
        assertEquals(receivingNote.getOrder().getStaff().getName(), "Hannah");
    }

    @Test
    public void testGetAllOrderDetails() throws IOException {
        URL url = new URL(TestConfig.URL + "order-details");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<OrderDetail> orderDetails = gson.fromJson(json,
                new TypeToken<List<OrderDetail>>(){}.getType());

        assertEquals(orderDetails.get(0).getProduct().getName(), "Ipad");
    }

    @Test
    public void testGetOrderDetailByID() throws IOException{
        URL url = new URL(TestConfig.URL + "order-details/1");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        OrderDetail orderDetail = gson.fromJson(json, new TypeToken<OrderDetail>(){}.getType());
        assertEquals(orderDetail.getProduct().getName(), "Ipad");
    }

    @Test
    public void testGetAllReceivingNotes() throws IOException {
        URL url = new URL(TestConfig.URL + "receiving-notes");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<ReceivingNote> receivingNotes = gson.fromJson(json,
                new TypeToken<List<ReceivingNote>>(){}.getType());

        assertEquals(receivingNotes.get(0).getStaff().getName(), "Hannah");
    }

    @Test
    public void testGetReceivingNoteByID() throws IOException{
        URL url = new URL(TestConfig.URL + "receiving-notes/1");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        ReceivingNote receivingNote = gson.fromJson(json, new TypeToken<ReceivingNote>(){}.getType());
        assertEquals(receivingNote.getStaff().getName(), "Hannah");
    }

    @Test
    public void testGetReceivingNoteBetween() throws IOException{
        Map<String, String> params = new HashMap<>();
        params.put("start", "01-01-2018");
        params.put("end", "01-01-2020");

        URL url = new URL(URL + "receiving-notes/between?" + getParamsString(params));
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<ReceivingNote> receivingNotes = gson.fromJson(json, new TypeToken<List<ReceivingNote>>(){}.getType());
        assertEquals(receivingNotes.get(0).getStaff().getName(), "Hannah");
    }

    @Test
    public void testGetAllDeliveryNotes() throws IOException {
        URL url = new URL(TestConfig.URL + "delivery-notes");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<DeliveryNote> deliveryNotes = gson.fromJson(json,
                new TypeToken<List<DeliveryNote>>(){}.getType());

        assertEquals(deliveryNotes.get(0).getProduct().getName(), "Ipad");
    }

    @Test
    public void testGetDeliveryNoteByID() throws IOException{
        URL url = new URL(TestConfig.URL + "delivery-notes/1");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        DeliveryNote deliveryNote = gson.fromJson(json, new TypeToken<DeliveryNote>(){}.getType());
        assertEquals(deliveryNote.getProduct().getName(), "Ipad");
    }

    @Test
    public void testGetDeliveryNoteBetween() throws IOException{
        Map<String, String> params = new HashMap<>();
        params.put("start", "01-01-2018");
        params.put("end", "30-12-2020");

        URL url = new URL(URL + "delivery-notes/between?" + getParamsString(params));
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<DeliveryNote> deliveryNotes = gson.fromJson(json, new TypeToken<List<DeliveryNote>>(){}.getType());
        assertEquals(deliveryNotes.get(0).getProduct().getName(), "Ipad");
    }

    @Test
    public void testGetAllSaleInvoices() throws IOException {
        URL url = new URL(TestConfig.URL + "sale-invoices");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        List<SaleInvoice> saleInvoices = gson.fromJson(json,
                new TypeToken<List<SaleInvoice>>(){}.getType());

        assertEquals(saleInvoices.get(0).getProduct().getName(), "Ipad");
    }

    @Test
    public void testGetSaleInvoiceByID() throws IOException{
        URL url = new URL(TestConfig.URL + "sale-invoices/1");
        String json = requestGet(url);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = gsonBuilder.create();
        SaleInvoice saleInvoice = gson.fromJson(json, new TypeToken<SaleInvoice>(){}.getType());
        assertEquals(saleInvoice.getProduct().getName(), "Ipad");
    }

    @Test
    public void testGetSaleInvoiceRevenueBetween() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("start", "01-01-2018");
        params.put("end", "30-12-2020");

        URL url = new URL(URL + "sale-invoices/revenue?" + getParamsString(params));
        String json = requestGet(url);
        assertEquals(json, "2000.0");
    }



}
