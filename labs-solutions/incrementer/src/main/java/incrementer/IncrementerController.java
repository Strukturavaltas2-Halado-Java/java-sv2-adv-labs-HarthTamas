package incrementer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncrementerController {

    private IncrementerService service;

    public IncrementerController(IncrementerService service) {
        this.service = service;
    }

    @GetMapping("/")
    public int increment() {
        return service.increment();
    }
}
