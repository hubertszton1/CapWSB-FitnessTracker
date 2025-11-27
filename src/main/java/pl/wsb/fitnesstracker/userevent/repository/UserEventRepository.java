package pl.wsb.fitnesstracker.userevent.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.userevent.UserEvent;

import java.util.List;

@Repository
public class UserEventRepository {

    @PersistenceContext
    private EntityManager em;

    // Zapisz nowe powiązanie User-Event
    public UserEvent save(UserEvent userEvent) {
        em.persist(userEvent);
        return userEvent;
    }

    // Znajdź wszystkie eventy dla danego użytkownika
    public List<UserEvent> findByUserId(Long userId) {
        TypedQuery<UserEvent> query = em.createQuery(
                "SELECT ue FROM UserEvent ue WHERE ue.user.id = :userId", UserEvent.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    // Znajdź wszystkich użytkowników zapisanych na dany event
    public List<UserEvent> findByEventId(Long eventId) {
        TypedQuery<UserEvent> query = em.createQuery(
                "SELECT ue FROM UserEvent ue WHERE ue.event.id = :eventId", UserEvent.class);
        query.setParameter("eventId", eventId);
        return query.getResultList();
    }

    // Przykład natywnego SQL – znajdź wszystkie aktywne eventy dla użytkownika
    public List findActiveEventsForUser(Long userId) {
        return em.createNativeQuery(
                        "SELECT * FROM user_event WHERE user_id = ? AND status = 'ACTIVE'", UserEvent.class)
                .setParameter(1, userId)
                .getResultList();
    }

    // Usuń powiązanie User-Event
    public void delete(UserEvent userEvent) {
        em.remove(em.contains(userEvent) ? userEvent : em.merge(userEvent));
    }
}
