package pl.wsb.fitnesstracker.grade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final Random random = new Random();

    @GetMapping("/mygrade")
    public int getRandomGrade() {
        return 3 + random.nextInt(3);
    }
}
