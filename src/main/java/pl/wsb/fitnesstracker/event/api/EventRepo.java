package pl.wsb.fitnesstracker.event.api;

import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.event.Event;

import java.util.List;

/**
 * Repository responsible for accessing {@link Event} entities.
 * Provides data access operations related to events.
 */
@Repository
public class EventRepo extends AbstractDao {

    /**
     * Finds events with the given name.
     *
     * @param name the name of the event
     * @return a list of events with the specified name;
     */
    public List<Event> findEventByName(String name) {
        String jpql = "SELECT e FROM Event e WHERE e.name = :name";

        return entityManager.createQuery(jpql, Event.class)
                .setParameter("name", name)
                .getResultList();
    }
}
