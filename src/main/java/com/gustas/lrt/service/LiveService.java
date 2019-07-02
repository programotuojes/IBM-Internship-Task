package com.gustas.lrt.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gustas.lrt.CurrentlyLive;

import org.springframework.stereotype.Service;

@Service
public class LiveService implements ILiveService {

	@Override
	public List<CurrentlyLive> getCurrentlyLive() {
        List<CurrentlyLive> liveList = new ArrayList<CurrentlyLive>();

        try {
            URL url = new URL("https://www.lrt.lt/mediateka/tiesiogiai/lrt-televizija");

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            Pattern channelPattern = Pattern.compile("(?<=data-tvprogname=\")(.*?)(?=\")");
            Pattern titlePattern = Pattern.compile("(?<=channel-item__title\"><span>)(.*?)(?=</span>)");
            Pattern timePattern = Pattern.compile("(?<=<span class=\"data-block__text\">)(.*?)(?=</span>)");

            CurrentlyLive live = new CurrentlyLive();
            boolean foundChannel = false;
            boolean foundTitle = false;
            boolean foundTime = false;

            while ((line = br.readLine()) != null) {
                Matcher channelMatcher = channelPattern.matcher(line);
                Matcher titleMatcher = titlePattern.matcher(line);
                Matcher timeMatcher = timePattern.matcher(line);

                if (channelMatcher.find()) {
                    live.setChannel(parseChannel(channelMatcher.group(1)));
                    foundChannel = true;
                }

                if (titleMatcher.find()) {
                    live.setTitle(titleMatcher.group(1));
                    System.out.println(titleMatcher.group(1));
                    foundTitle = true;
                }
                    
                if (timeMatcher.find()) {
                    live.setTime(timeMatcher.group(1));
                    foundTime = true;
                }

                if (foundChannel && foundTitle && foundTime) {
                    liveList.add(live);
                    live = new CurrentlyLive();
                    foundChannel = false;
                    foundTitle = false;
                    foundTime = false;
                }
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to download content.");
            liveList.add(new CurrentlyLive("", "Failed to download content.", ""));
        }
        
        return liveList;
    }

    private String parseChannel(String channel) {
        switch(channel) {
            case "LTV1":
                return "LRT";
            case "LTV2":
                return "LRT Plius";
            case "WORLD":
                return "LRT Lituanica";
            case "LR":
                return "LRT Radijas";
            case "Klasika":
                return "LRT Klasika";
            case "Opus":
                return "LRT Opus";
            default:
                return "Unknown channel";
        }
    }
}
