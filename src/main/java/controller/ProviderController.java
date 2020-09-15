package controller;

import model.Category;
import model.Order;
import model.Product;
import model.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProductService;
import service.ProviderService;

import java.util.List;

/**
 * Created by CoT on 7/29/18.
 */
@RestController
@RequestMapping(path = "/")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @RequestMapping(path = "providers/search", method = RequestMethod.GET)
    public List<Provider> getProvidersByName(@RequestParam String s){
        return providerService.findProvidersByName(s);
    }

    @RequestMapping(path = "providers", method = RequestMethod.GET)
    public List<Provider> getAllProviders() {
        return providerService.getAllProviders();
    }

    @RequestMapping(path = "providers", method = RequestMethod.POST)
    public Provider addProviders(@RequestBody Provider provider) {
        return providerService.saveProvider(provider);
    }

    @RequestMapping(path = "providers", method = RequestMethod.PUT)
    public Provider editProvider(@RequestBody Provider provider) {
        return providerService.updateProvider(provider);
    }

    @RequestMapping(path = "providers/{id}", method = RequestMethod.GET)
    public Provider getProviderByID(@PathVariable int id) {
        return providerService.getProvider(id);
    }

    @RequestMapping(path = "providers/{id}/orders", method = RequestMethod.GET)
    public List<Order> getOrdersByID(@PathVariable int id) {
        return providerService.getOrdersByID(id);
    }
}
