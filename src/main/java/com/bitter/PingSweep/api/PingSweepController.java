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