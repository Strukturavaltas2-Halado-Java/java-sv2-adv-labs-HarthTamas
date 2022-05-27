package incrementer;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class IncrementerService {

    private IncrementerRepository repository;

    public IncrementerService(IncrementerRepository repository) {
        this.repository = repository;
    }

    public int increment() {
        int value = repository.getCounter();
        value++;
        repository.setCounter(value);
        return repository.getCounter();
    }
}
