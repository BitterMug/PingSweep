package com.bitter.PingSweep.pingSweepCode;

import com.bitter.PingSweep.model.PingReturn;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
class PingService implements Runnable
{
    private List<PingReturn> pingReturnList = new ArrayList<>();
    private String ipAddress;
    private boolean test;

    public PingService(List<PingReturn> pingReturnsList, String ipAddress, boolean test) {
        this.ipAddress = ipAddress;
        this.test = test;
        this.pingReturnList = pingReturnsList;
    }

    public PingService(){ //for some reason needed, looks like Spring needs variant without any parameters
    }

    public void run() {
        try {
            InetAddress geek = InetAddress.getByName(ipAddress);
            if (!test && geek.isReachable(2000)) { //If ping is successful

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                PingReturn ping = new PingReturn(ipAddress, dtf.format(now));
                pingReturnList.add(ping);
                //System.out.println(ping.getAddress() + " " + ping.getDateTime());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
