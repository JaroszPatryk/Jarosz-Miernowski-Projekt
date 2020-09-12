package io.mbab.sda.groupproject.rest;

import io.mbab.sda.groupproject.dto.LeagueDto;
import io.mbab.sda.groupproject.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/league")
public class LeagueController extends AbstractCrudController<LeagueDto, Integer> {
    public LeagueController(CrudService<LeagueDto, Integer> service) {
        super(service);
    }
}
