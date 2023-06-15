package fwdays.web;

import fwdays.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ShopController {

    private String libraryName = "FwDays Shop";

    @Autowired
    private OrderService orderService;

    @GetMapping("library")
    public String getLibraryName() {
        return libraryName;
    }

    //FIXME remove
    public void addBook(int orderId, int bookId, int number) {
        orderService.addBook(orderId, bookId, number);
    }


}
