package pl.wsb.fitnesstracker.livecoding.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Spring service bean used to demonstrate the bean lifecycle.
 */
@Profile("BeanCycle")
@Service
public class MyBean {

    /**
     * Constructs the bean instance.
     */
    public MyBean() {
        System.out.println("Instantiation");
    }

    /**
     * Called after dependency injection is completed.
     */
    @PostConstruct
    public void init() {
        System.out.println("Initializing..");
    }

    /**
     * Called before the bean is removed from the application context.
     */
    @PreDestroy
    public void destroy() {
        System.out.println("Destroying..");
    }
}
