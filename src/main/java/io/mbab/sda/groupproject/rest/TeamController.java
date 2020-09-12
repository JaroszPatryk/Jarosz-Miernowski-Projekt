package io.mbab.sda.groupproject.rest;

import io.mbab.sda.groupproject.dto.TeamDto;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController extends AbstractCrudController<TeamDto, Integer> {

    public TeamController(CrudService<TeamDto, Integer> service) {
        super(service);
    }
}
