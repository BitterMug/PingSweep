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
                pingMultiThread(true);
                TimeUnit.SECONDS.sleep(10);
                pingMultiThread(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private static PingRepository pingRepository;
    private static List<PingReturn> pingReturnsList;

    @Autowired
    public PingSweep(PingRepository pingRepository) {
        this.pingRepository = pingRepository;
    }

    public void startPingSweep(int delayMinutes) throws InterruptedException, IOException {
        myTimer.scheduleAtFixedRate(myTask , 0l, delayMinutes * (60*1000)); // Runs every 5 minutes
    }

    private static void pingMultiThread(boolean test) throws InterruptedException, IOException {
        String ipAddress = "192.168.0.";
        for (int i = 1; i < 255; i++) {
            Runnable r = new PingService(pingReturnsList, ipAddress + i, test);
            new Thread(r).start();
        }

    }
}