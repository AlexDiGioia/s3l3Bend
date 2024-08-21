package dao;

import entities.Event;
import exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EventsDAO {
    private final EntityManager em;

    public EventsDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Event event) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(event);
        transaction.commit();
        System.out.println("L'evento " + event.getTitolo() + " è stato salvato correttamente!");
    }

    public Event findById(long eventId) {
        Event cerca = em.find(Event.class, eventId);
        if (cerca == null) throw new NotFoundException(eventId);
        return cerca;
    }

    public void findByIdAndDelete(long eventId) {

        Event found = this.findById(eventId);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("L'evento' " + found.getTitolo() + " è stato eliminato correttamente!");
    }
}
