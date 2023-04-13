package jpabook.service;

import jpabook.entity.Client;
import jpabook.entity.Screen;
import jpabook.entity.Seat;
import jpabook.entity.Ticketing;
import jpabook.enums.TicketingType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class TicketingService {
    private EntityManagerFactory emf;

    public TicketingService(EntityManagerFactory emf){
        this.emf = emf;
    }

    public void movieReservation(long personId, long screenId, int row, int column, Long theaterId) {
        EntityManager em = emf.createEntityManager();
        SeatService seatService = new SeatService(emf);
        Long seatId = seatService.findSeatId(row, column, theaterId);
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Seat seat = em.find(Seat.class, seatId);
            Screen screen = em.find(Screen.class, screenId);
            Client client = em.find(Client.class, personId);
            Ticketing ticketing = new Ticketing();
            ticketing.setState(TicketingType.TICKETING);
            ticketing.setSeat(seat);
            ticketing.setScreen(screen);
            ticketing.setClient(client);
            em.persist(ticketing);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    public void cancelTicketing(Long ticketingId){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Ticketing ticketing = em.find(Ticketing.class, ticketingId);
            ticketing.setState(TicketingType.CANCEL);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    public void showTicketingInfo(Long ticketingId){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Ticketing ticketing = em.find(Ticketing.class, ticketingId);

            System.out.println("영화 이름 : " + ticketing.getScreen().getMovie().getName());
            System.out.println("관 이름 : " + ticketing.getScreen().getTheater().getName());
            System.out.println("좌석 : " + ticketing.getSeat().getRow() + "열 " + ticketing.getSeat().getColumn() + "번째 자리");
            System.out.println("시작 시간 : " + ticketing.getScreen().getStart());
            System.out.println("상태 : " + ticketing.getState());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
