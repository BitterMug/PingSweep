package com.bitter.PingSweep.pingSweepCode;

import com.bitter.PingSweep.model.PingReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
class PingService implements Runnable
{
    private List<PingReturn> pingReturnList;
    private String ipAddress;
    private boolean test;

    public PingService(List<PingReturn> pingReturnsList, String ipAddress, boolean test) {
        this.ipAddress = ipAddress;
        this.test = test;
    }

    public PingService(){ //for some reason needed, looks like it needs variant without any parameters
    }


    public void addPing(PingReturn ping){
        //////////////////////////
    }

    public void run() {
        try {
            InetAddress geek = InetAddress.getByName(ipAddress);
            if (!test && geek.isReachable(2000)) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                PingReturn ping = new PingReturn(ipAddress, dtf.format(now));
                System.out.println(ping.getAddress());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
