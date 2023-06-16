package fwdays.delivery.web;

import fwdays.delivery.domain.Manager;
import fwdays.delivery.persistence.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerRepository managerRepository;

    @GetMapping
    public List<Manager> findManagers() {
        return managerRepository.findAll();
    }

    @PostMapping
    public void saveManager(@RequestBody Manager manager) {
        managerRepository.save(manager);
    }
}
