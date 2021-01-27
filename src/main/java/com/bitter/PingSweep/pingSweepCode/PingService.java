package com.bitter.PingSweep.pingSweepCode;

import com.bitter.PingSweep.model.PingReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PingService implements Runnable
{
    private static PingRepository pingRepository;

    @Autowired
    public PingService(PingRepository pingRepository) {
        PingService.pingRepository = pingRepository;
    }
    private List<PingReturn> pingReturnList = new ArrayList<>();
    private String ipAddress;
    private boolean test;

    public PingService(List<PingReturn> pingReturnsList, String ipAddress, boolean test) {
        this.ipAddress = ipAddress;
        this.test = test;
        this.pingReturnList = pingReturnsList;
    }

    //for some reason needed, looks like Spring needs variant without any parameters
    public PingService(){
    }

    public List<PingReturn> getPingReturnList() {
        return pingRepository.findAll();
    }

    public List<PingReturn> getPingReturnByAddress(String address) {
        return pingRepository.getPingReturnByAddress(address);
    }

    public void run() {
        try {
            InetAddress geek = InetAddress.getByName(ipAddress);
            if (!test && geek.isReachable(3000)) { //If ping is successful

                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                PingReturn ping = new PingReturn(ipAddress, df.format(now), tf.format(now));
                    //Date and time probably don't need their own column (MYSQL can format it itself)
                pingReturnList.add(ping);   //Add ping entry to db
                System.out.println(ping.getAddress() + " " + ping.getTime()); //Debug
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
