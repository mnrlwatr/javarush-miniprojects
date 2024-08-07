package restaurant.ad;

import java.util.ArrayList;
import java.util.List;


public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public List<Advertisement> getActiveVideoSet(){
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement advertisement: advertisementStorage.list()){
            if (advertisement.getHits() > 0)
                result.add(advertisement);
        }
        return result;
    }

    public List<Advertisement> getArchivedVideoSet(){
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement advertisement: advertisementStorage.list()){
            if (advertisement.getHits() == 0)
                result.add(advertisement);
        }

        return result;
    }
}
