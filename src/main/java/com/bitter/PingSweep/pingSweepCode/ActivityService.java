package com.bitter.PingSweep.pingSweepCode;

import com.bitter.PingSweep.config.GetConfigValues;
import com.bitter.PingSweep.model.ActivityProfile;
import com.bitter.PingSweep.model.NameList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ActivityService {

    private static NameListRepository nameRepository;
    private static PingRepository pingRepository;
    private static GetConfigValues getConfigValues;

    @Autowired
    public ActivityService(NameListRepository nameRepository,
                           PingRepository pingRepository,
                           GetConfigValues getConfigValues) {
        ActivityService.nameRepository = nameRepository;
        ActivityService.pingRepository = pingRepository;
        ActivityService.getConfigValues = getConfigValues;
    }
    public ActivityService(){
    }

    public List<ActivityProfile> getAllActivity(){
        List<String> uniqueAddresses = pingRepository.findUniqueAddress();
        List<ActivityProfile> activityProfileList = new ArrayList<>();
        for (String address : uniqueAddresses) {
            String name;
            name = nameRepository.getNameByAddress(address);
            ActivityProfile activityProfile = new ActivityProfile(name, address);
            ArrayList<ArrayList<String>> week = new ArrayList<>(7);
            for (int i = 1; i < 8; i++) {
                ArrayList<String> day = new ArrayList<>(24);
                for (int j = 0; j < 24; j++) {
                    day.add(pingRepository.findCountByHourAndDay(i, j, address));
                }
                week.add(day);
            }
            activityProfile.setWeekActivity(week);
            activityProfileList.add(activityProfile);
        }
        return activityProfileList;
    }

    public ActivityProfile getActivityByAddress(String address) {
        String name;
        name = nameRepository.getNameByAddress(address);
        ActivityProfile activityProfile = new ActivityProfile(name, address);
        ArrayList<ArrayList<String>> week = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            ArrayList<String> day = new ArrayList<>(24);
            for (int j = 0; j < 24; j++) {
                day.add(pingRepository.findCountByHourAndDay(i, j, address));
            }
            week.add(day);
        }
        activityProfile.setWeekActivity(week);
        return activityProfile;
    }

    public void updateNameList() throws IOException {
        List<NameList> nameSet = getConfigValues.getNameValues();
        if (nameSet == null) {
            return;
        }
        for (NameList nameList : nameSet) {
            Optional<NameList> nameByAddress = nameRepository
                    .findNameListByAddress(nameList.getAddress());
            if (nameByAddress.isPresent()) {
                continue;
            }
            nameRepository.save(nameList);
            System.out.println(nameList.getName());
        }
    }

    public List<String> getActive(int timeMinutes){
        List<String> activeAddresses =  pingRepository.getActive(Integer.toString(timeMinutes));
        List<String> activeNames = new ArrayList<>();
        for (String address : activeAddresses) {
            activeNames.add(nameRepository.getNameByAddress(address));
        }
        return activeNames;
    }

    public List<ActivityProfile> getActivityByWeek(int weekNum){
        List<String> uniqueAddresses = pingRepository.findUniqueAddress();
        List<ActivityProfile> activityProfileList = new ArrayList<>();
        for (String address : uniqueAddresses) {
            String name;
            name = nameRepository.getNameByAddress(address);
            ActivityProfile activityProfile = new ActivityProfile(name, address);
            ArrayList<ArrayList<String>> week = new ArrayList<>(7);
            for (int i = 1; i < 8; i++) {
                ArrayList<String> day = new ArrayList<>(24);
                for (int j = 0; j < 24; j++) {
                    day.add(pingRepository.findCountByHourAndDayInWeek(i, j, address, weekNum));
                }
                week.add(day);
            }
            //DAYOFWEEK starts with sunday...
            ArrayList<String> tempShifter;
            tempShifter = week.get(0);
            week.remove(0);
            week.add(tempShifter);
            activityProfile.setWeekActivity(week);
            activityProfileList.add(activityProfile);
        }
        return activityProfileList;
    }

    public List<ActivityProfile> getLastWeekActivity(){
        List<String> uniqueAddresses = pingRepository.findUniqueAddress();
        List<ActivityProfile> activityProfileList = new ArrayList<>();
        for (String address : uniqueAddresses) {
            String name;
            name = nameRepository.getNameByAddress(address);
            ActivityProfile activityProfile = new ActivityProfile(name, address);
            ArrayList<ArrayList<String>> week = new ArrayList<>(7);
            for (int i = 7; i > 0; i--) {
                ArrayList<String> day = new ArrayList<>(24);
                for (int j = 0; j < 24; j++) {
                    day.add(pingRepository.findCountByHourAndDayLastWeek(i, j, address));
                }
                week.add(day);
            }
            activityProfile.setWeekActivity(week);
            activityProfileList.add(activityProfile);
        }
        return activityProfileList;
    }

}
