package com.bitter.PingSweep.api;

import com.bitter.PingSweep.model.ActivityProfile;
import com.bitter.PingSweep.model.PingReturn;
import com.bitter.PingSweep.pingSweepCode.ActivityService;
import com.bitter.PingSweep.pingSweepCode.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/activity")
@RestController
public class ActivityController {
    private final PingService pingService;
    private final ActivityService activityService;

    @Autowired
    public ActivityController(PingService pingService, ActivityService activityService) {
        this.pingService = pingService;
        this.activityService = activityService;
    }

    @GetMapping
    public List<ActivityProfile> getAllActivity() {
        System.out.println("All activity request");
        return activityService.getAllActivity();
    }

    @GetMapping(path = "{address}")
    public ActivityProfile getActivityByAddress(
            @PathVariable("address") String address) {
        System.out.println("Activity request at: " + address);
        return activityService.getActivityByAddress(address);
    }

}
