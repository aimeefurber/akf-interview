package zipcode;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;

@RestController
public class ZipCodeController {

    @RequestMapping("/zip")
    public ZipCode zipCode(@RequestParam(value = "zip") int zipCode) {

        return null;
    }
}
