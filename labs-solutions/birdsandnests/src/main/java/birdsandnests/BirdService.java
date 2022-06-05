package birdsandnests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BirdService {

    private BirdDao birdDao;

    public BirdService(BirdDao birdDao) {
        this.birdDao = birdDao;
    }

//    public Map<BirdSpecies, Integer> getBirdStatistics() {
//        Map<BirdSpecies, Integer> result = new HashMap<>();
//        for (BirdSpecies actual: BirdSpecies.values()) {
//            result.put(actual,birdDao.listBirdsSpeciesGiven(actual).size());
//        }
//        return result;
//    }

    public Map<BirdSpecies, Integer> getBirdStatistics() {
        List<Bird> birds = birdDao.listBirds();

        Map<BirdSpecies, Integer> result = new HashMap<>();
        for (Bird b: birds) {
            if (!result.containsKey(b.getSpecies())) {
                result.put(b.getSpecies(), 0);
            }
            result.put(b.getSpecies(), result.get(b.getSpecies()) + 1);
        }
        return result;
    }
}
