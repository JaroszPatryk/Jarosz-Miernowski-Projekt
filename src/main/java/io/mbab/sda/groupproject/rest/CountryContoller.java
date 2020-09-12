package io.mbab.sda.groupproject.rest;

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CountryContoller extends AbstractCrudController<CountryDto, Integer> {
    public CountryContoller(CrudService<CountryDto, Integer> service) {
        super(service);
    }
}
