package io.mbab.sda.groupproject.rest;

import io.mbab.sda.groupproject.dto.PlayerDto;
import io.mbab.sda.groupproject.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController extends AbstractCrudController<PlayerDto, Integer> {
    public PlayerController(CrudService<PlayerDto, Integer> service) {
        super(service);
    }
}
