package jpabook.service;

import jpabook.entity.Screen;
import jpabook.entity.Seat;
import jpabook.entity.Ticketing;
import jpabook.enums.TicketingType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class ScreenService {
    private EntityManagerFactory emf;

    public ScreenService(EntityManagerFactory emf){
        this.emf = emf;
    }

    public void showScreenInfo(long screenId){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();

            Screen screen = em.find(Screen.class, screenId);
            System.out.println("영화이름 : " + screen.getMovie().getName());
            System.out.println("시작시간 : " + screen.getStart());
            System.out.println("종료시간 : " + screen.getEnd());
            System.out.println("총좌석 : " + screen.getTheater().getSeats().size());
            int count = 0;

            List<Seat> seatList = screen.getTheater().getSeats();
            List<Long> seatId = new ArrayList<>();
            for(int i = 0; i < screen.getTicketings().size(); i++){
                if(screen.getTicketings().get(i).getState().equals(TicketingType.TICKETING))
                    seatId.add(screen.getTicketings().get(i).getSeat().getId());
            }
            List<Seat> newSeatList = new ArrayList<>();
            for(int i = 0; i < seatList.size(); i++){
                if(seatList.get(i).getAvailable() && !seatId.contains(seatList.get(i).getId())){
                    newSeatList.add(seatList.get(i));
                    count++;
                }
            }
            System.out.println("가능 좌석 : " + count);
            showAvailableSeats(newSeatList);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }

    public void showAvailableSeats(List<Seat> seats){
        String[][] matrix = {
                {"X", "X", "X", "X", "X"},
                {"X", "X", "X", "X", "X"}
        };
        for(Seat seat : seats){
            matrix[seat.getRow() - 1][seat.getColumn() - 1] = "O";
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
