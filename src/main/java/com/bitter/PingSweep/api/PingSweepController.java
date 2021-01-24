package com.bitter.PingSweep.api;


import com.bitter.PingSweep.model.ActivityProfile;
import com.bitter.PingSweep.model.PingReturn;
import com.bitter.PingSweep.pingSweepCode.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/ping")
@RestController
public class PingSweepController {

    private final PingService pingService;

    @Autowired
    public PingSweepController(PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping
    public List<PingReturn> getAllPings() {
        System.out.println("All ping returns request");
        return pingService.getPingReturnList();
    }

    @GetMapping(path = "{address}")
    public List<PingReturn> getPingReturnByAddress(
            @PathVariable("address") String address) {
        System.out.println("Ping return request at: " + address);
        return pingService.getPingReturnByAddress(address);
    }
}


////
/*
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return  personService.getPeople();
    }

    @PostMapping
    public void  registerNewPerson(@RequestBody Person person) {
        personService.addNewPerson(person);
    }

    @DeleteMapping(path = "{personId}")
    public void deletePersonById(@PathVariable("personId") Long id) {
        personService.deletePersonById(id);
    }

    @PutMapping(path = "{personId}")    //localhost:8080/api/v1/person/1?name=passs
    public void updatePerson(           //localhost:8080/api/v1/person/1?name=passs&email=saa@sda.sda
                                        @PathVariable("personId") Long id,
                                        @RequestParam(required = false) String name) {
        personService.updatePerson(id, name);
    }
}
 */