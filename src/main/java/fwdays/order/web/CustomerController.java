package fwdays.order.web;

import fwdays.order.domain.Customer;
import fwdays.order.persistence.CustomerRepository;
import fwdays.order.web.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;

    @PostMapping
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customer) {
        return modelMapper.map(customerRepository.save(
                modelMapper.map(customer, Customer.class)), CustomerDTO.class);
    }

    @GetMapping
    public List<CustomerDTO> findCustomers() {
        return customerRepository.findAll()
                .stream().map(customer -> modelMapper.map(customer, CustomerDTO.class)).toList();
    }
}
