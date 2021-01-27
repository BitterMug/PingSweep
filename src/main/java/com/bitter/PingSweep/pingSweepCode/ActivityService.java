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

    //Should not be called on user demand. Preferably on app start or preemptively
    //Takes too long on slow HW...
    //Alternative is to store everything in db. Wanted to avoid this
    public List<ActivityProfile> getAllActivity(){
        List<String> uniqueAddresses = pingRepository.findUniqueAddress();          //gets list of all unique captured addresses to be looked up
        List<ActivityProfile> activityProfileList = new ArrayList<>();
        for (String address : uniqueAddresses) {
            String name;
            name = nameRepository.getNameByAddress(address);                        //Name translate
            ActivityProfile activityProfile = new ActivityProfile(name, address);
            ArrayList<ArrayList<String>> week = new ArrayList<>(7);
            for (int i = 1; i < 8; i++) {                                           //Iterating by day
                ArrayList<String> day = new ArrayList<>(24);            //array cleanup
                for (int j = 0; j < 24; j++) {                                      //Iterating by hour
                    day.add(pingRepository.findCountByHourAndDay(i, j, address));
                }
                week.add(day);
            }
            startWithMonday(week);  //DAYOFWEEK starts with sunday... rotating
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
        startWithMonday(week);  //DAYOFWEEK starts with sunday... rotating
        activityProfile.setWeekActivity(week);
        return activityProfile;
    }

    //Check of nameList file got updated
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

    //Meant to list all Online users in last X minutes
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
            startWithMonday(week);  //DAYOFWEEK starts with sunday... rotating
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
            for (int i = 6; i >= 0; i--) {                                          //Goes from today-6days to today
                ArrayList<String> day = new ArrayList<>(24);
                for (int j = 0; j < 24; j++) {
                    day.add(pingRepository.findCountByHourAndDayLastWeek(i, j, address));
                }
                week.add(day);
            }
            //No need for startWithMonday(X);
            activityProfile.setWeekActivity(week);
            activityProfileList.add(activityProfile);
        }
        return activityProfileList;
    }

    //DAYOFWEEK starts with sunday... rotating
    private void startWithMonday(ArrayList<ArrayList<String>> weekList){
        ArrayList<String> tempShifter;
        tempShifter = weekList.get(0);
        weekList.remove(0);
        weekList.add(tempShifter);
    }

}
