package jpabook.service;

import jpabook.entity.Seat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class SeatService {
    private EntityManagerFactory emf;

    public SeatService(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Long findSeatId(int row, int column, Long theaterId){
        Long seatId = -1L;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            TypedQuery<Seat> query = em.createQuery("select s from Seat as s join fetch s.theater t where s.row =:row and s.column =:column and t.id =:theaterId", Seat.class);
            query.setParameter("row", row);
            query.setParameter("column", column);
            query.setParameter("theaterId", theaterId);
            Seat seat = query.getSingleResult();
            seatId = seat.getId();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        return seatId;
    }
}
