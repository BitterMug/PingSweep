package com.bitter.PingSweep.pingSweepCode;

import com.bitter.PingSweep.model.PingReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class PingSweep {
    static Timer myTimer = new Timer ();
    static TimerTask myTask = new TimerTask () {
        @Override
        public void run () {
            try {
                                            //Called two times. Second time are address tables filed.
                pingMultiThread(true); //One ping scan is without any output. Going around loosing first ping.
                TimeUnit.SECONDS.sleep(10);
                pingMultiThread(false);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    };

    private static PingRepository pingRepository;

    @Autowired
    public PingSweep(PingRepository pingRepository) {
        PingSweep.pingRepository = pingRepository;
    }

    public void startPingSweep(int delayMinutes) {
        myTimer.scheduleAtFixedRate(myTask , 0L, delayMinutes * (60*1000)); // Runs every 5 minutes
    }

    private static void pingMultiThread(boolean test) throws InterruptedException, IOException {
        String ipAddress = "192.168.0.";        //Network definition
        List<PingReturn> pingReturnsList = new ArrayList<>();    //Clearing list
        for (int i = 1; i < 255; i++) {         //Address pool
            Runnable r = new PingService(pingReturnsList, ipAddress + i, test); //Defining new thread w/ assign ip
            new Thread(r).start();
        }
        if (!test) {
            TimeUnit.SECONDS.sleep(3);          //Waiting for ping to finish
            System.out.println(pingReturnsList);        //Debug
            pingRepository.saveAll(pingReturnsList);    //Saving to database
        }
    }
}