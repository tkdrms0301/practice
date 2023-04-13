package jpabook.start;

import jpabook.entity.*;
import jpabook.enums.ActorType;
import jpabook.enums.GenreType;
import jpabook.enums.TicketingType;
import jpabook.service.MovieService;
import jpabook.service.PersonService;
import jpabook.service.ScreenService;
import jpabook.service.TicketingService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JpaMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();

//            Director director1 = new Director();
//            director1.setName("감독1");
//            director1.setDayOfBirth(LocalDate.parse("1999-09-15"));
//            director1.setPlaceOfBirth("대구");
//            em.persist(director1);
//
//            Director director2 = new Director();
//            director2.setName("감독2");
//            director2.setDayOfBirth(LocalDate.parse("1989-10-11"));
//            director2.setPlaceOfBirth("서울");
//            em.persist(director2);
//
//            Movie movie1 = new Movie();
//            movie1.setName("기생충");
//            movie1.setRuntime(120);
//            movie1.setGenreType(GenreType.SF);
//            movie1.setRunningDate(LocalDate.parse("2019-10-11"));
//            movie1.setModifiedDate(new ModifiedDate(LocalDate.parse("2015-10-11"), LocalDate.parse("2018-10-11")));
//            movie1.setDirector(director1);
//            em.persist(movie1);
//
//            Movie movie2 = new Movie();
//            movie2.setName("명량");
//            movie2.setRuntime(130);
//            movie2.setGenreType(GenreType.SF);
//            movie2.setRunningDate(LocalDate.parse("2019-10-11"));
//            movie2.setModifiedDate(new ModifiedDate(LocalDate.parse("2015-10-11"), LocalDate.parse("2018-10-11")));
//            movie2.setDirector(director2);
//            em.persist(movie2);
//
//            Movie movie3 = new Movie();
//            movie3.setName("신과함께2");
//            movie3.setRuntime(130);
//            movie3.setGenreType(GenreType.SF);
//            movie3.setRunningDate(LocalDate.parse("2019-10-11"));
//            movie3.setModifiedDate(new ModifiedDate(LocalDate.parse("2015-10-11"), LocalDate.parse("2018-10-11")));
//            movie3.setDirector(director1);
//            em.persist(movie3);
//
//            Actor actor1 = new Actor();
//            actor1.setName("배우1");
//            actor1.setDayOfBirth(LocalDate.parse("1989-10-11"));
//            actor1.setAgency("큐브 엔터테인먼트");
//            actor1.setHeight(180);
//            em.persist(actor1);
//
//            Actor actor2 = new Actor();
//            actor2.setName("배우2");
//            actor2.setDayOfBirth(LocalDate.parse("1989-10-11"));
//            actor2.setAgency("JYP 엔터테인먼트");
//            actor2.setHeight(180);
//            em.persist(actor2);
//
//            Actor actor3 = new Actor();
//            actor3.setName("배우3");
//            actor3.setDayOfBirth(LocalDate.parse("1989-12-11"));
//            actor3.setAgency("SM 엔터테인먼트");
//            actor3.setHeight(180);
//            em.persist(actor3);
//
//            Starring movie1Starring1 = new Starring();
//            movie1Starring1.setActor(actor3);
//            movie1Starring1.setActorType(ActorType.LEAD);
//            movie1Starring1.setMovie(movie1);
//            em.persist(movie1Starring1);
//
//            Starring movie1Starring2 = new Starring();
//            movie1Starring2.setActor(actor2);
//            movie1Starring2.setActorType(ActorType.SUPPORTING);
//            movie1Starring2.setMovie(movie1);
//            em.persist(movie1Starring2);
//
//            Starring movie1Starring3 = new Starring();
//            movie1Starring3.setActor(actor1);
//            movie1Starring3.setActorType(ActorType.SUPPORTING);
//            movie1Starring3.setMovie(movie1);
//            em.persist(movie1Starring3);
//
//            Starring movie2Starring1 = new Starring();
//            movie2Starring1.setActor(actor2);
//            movie2Starring1.setActorType(ActorType.LEAD);
//            movie2Starring1.setMovie(movie2);
//            em.persist(movie2Starring1);
//
//            Starring movie2Starring2 = new Starring();
//            movie2Starring2.setActor(actor1);
//            movie2Starring2.setActorType(ActorType.SUPPORTING);
//            movie2Starring2.setMovie(movie2);
//            em.persist(movie2Starring2);
//
//            Starring movie2Starring3 = new Starring();
//            movie2Starring3.setActor(actor3);
//            movie2Starring3.setActorType(ActorType.SUPPORTING);
//            movie2Starring3.setMovie(movie2);
//            em.persist(movie2Starring3);
//
//            Starring movie3Starring1 = new Starring();
//            movie3Starring1.setActor(actor1);
//            movie3Starring1.setActorType(ActorType.LEAD);
//            movie3Starring1.setMovie(movie3);
//            em.persist(movie3Starring1);
//
//            Starring movie3Starring2 = new Starring();
//            movie3Starring2.setActor(actor3);
//            movie3Starring2.setActorType(ActorType.SUPPORTING);
//            movie3Starring2.setMovie(movie3);
//            em.persist(movie3Starring2);
//
//            Theater theater1 = new Theater();
//            theater1.setName("상영관 1층");
//            theater1.setFloor(1);
//            Seat seat1 = new Seat(1, 1, true);
//            Seat seat2 = new Seat(1, 2, true);
//            Seat seat3 = new Seat(1, 3, true);
//            Seat seat4 = new Seat(1, 4);
//            Seat seat5 = new Seat(1, 5);
//            Seat seat6 = new Seat(2, 1);
//            Seat seat7 = new Seat(2, 2);
//            Seat seat8 = new Seat(2, 3, true);
//            Seat seat9 = new Seat(2, 4, true);
//            Seat seat10 = new Seat(2, 5, true);
//            theater1.addSeat(seat1);
//            theater1.addSeat(seat2);
//            theater1.addSeat(seat3);
//            theater1.addSeat(seat4);
//            theater1.addSeat(seat5);
//            theater1.addSeat(seat6);
//            theater1.addSeat(seat7);
//            theater1.addSeat(seat8);
//            theater1.addSeat(seat9);
//            theater1.addSeat(seat10);
//            em.persist(theater1);
//
//            Theater theater2 = new Theater();
//            theater2.setName("상영관 2층");
//            theater2.setFloor(2);
//            Seat seat11 = new Seat(1, 1);
//            Seat seat12 = new Seat(1, 2);
//            Seat seat13 = new Seat(1, 3);
//            Seat seat14 = new Seat(1, 4);
//            Seat seat15 = new Seat(1, 5);
//            Seat seat16 = new Seat(2, 1);
//            Seat seat17 = new Seat(2, 2);
//            Seat seat18 = new Seat(2, 3);
//            Seat seat19 = new Seat(2, 4);
//            Seat seat20 = new Seat(2, 5);
//            theater2.addSeat(seat11);
//            theater2.addSeat(seat12);
//            theater2.addSeat(seat13);
//            theater2.addSeat(seat14);
//            theater2.addSeat(seat15);
//            theater2.addSeat(seat16);
//            theater2.addSeat(seat17);
//            theater2.addSeat(seat18);
//            theater2.addSeat(seat19);
//            theater2.addSeat(seat20);
//            em.persist(theater2);
//
//            Screen theather1Screen1 = new Screen();
//            theather1Screen1.setMovie(movie1);
//            theather1Screen1.setTheater(theater1);
//            theather1Screen1.setStart(LocalDateTime.parse("2022-11-15T14:00:00"));
//            theather1Screen1.setEnd(LocalDateTime.parse("2022-11-15T16:00:00"));
//            em.persist(theather1Screen1);
//
//            Screen theather1Screen2 = new Screen();
//            theather1Screen2.setMovie(movie1);
//            theather1Screen2.setTheater(theater1);
//            theather1Screen2.setStart(LocalDateTime.parse("2022-11-15T20:00:00"));
//            theather1Screen2.setEnd(LocalDateTime.parse("2022-11-15T22:00:00"));
//            em.persist(theather1Screen2);
//
//            Screen theather2Screen1 = new Screen();
//            theather2Screen1.setMovie(movie1);
//            theather2Screen1.setTheater(theater2);
//            theather2Screen1.setStart(LocalDateTime.parse("2022-11-15T17:00:00"));
//            theather2Screen1.setEnd(LocalDateTime.parse("2022-11-15T19:00:00"));
//            em.persist(theather2Screen1);
//
//            Screen theather2Screen2 = new Screen();
//            theather2Screen2.setMovie(movie1);
//            theather2Screen2.setTheater(theater2);
//            theather2Screen2.setStart(LocalDateTime.parse("2022-11-15T09:00:00"));
//            theather2Screen2.setEnd(LocalDateTime.parse("2022-11-15T11:00:00"));
//            em.persist(theather2Screen2);
//
//            Client person1 = new Client();
//            person1.setName("고객1");
//            person1.setDayOfBirth(LocalDate.parse("2000-11-15"));
//            person1.setMileage(100);
//            person1.setModifiedDate(new ModifiedDate(LocalDate.parse("2020-11-15"), LocalDate.parse("2021-11-15")));
//            person1.setAddress(new Address("구미", "금오공대로", "130"));
//            Ticketing ticketing1 = new Ticketing();
//            ticketing1.setClient(person1);
//            ticketing1.setState(TicketingType.TICKETING);
//            ticketing1.setSeat(seat1);
//            ticketing1.setScreen(theather1Screen1);
//            em.persist(person1);
//
//            Manager person2 = new Manager();
//            person2.setName("관리자1");
//            person2.setDayOfBirth(LocalDate.parse("2022-11-15"));
//            person2.setWorkingStartDate(LocalDate.parse("2021-11-15"));
//            person2.setAddress(new Address("대구", "동북로", "130"));
//            em.persist(person2);
//
//            Client person3 = new Client();
//            person3.setName("고객3");
//            person3.setDayOfBirth(LocalDate.parse("2001-11-15"));
//            person3.setMileage(110);
//            person3.setModifiedDate(new ModifiedDate(LocalDate.parse("2020-11-15"), LocalDate.parse("2021-11-15")));
//            person3.setAddress(new Address("구미", "금오공대로", "130"));
//            Ticketing ticketing2 = new Ticketing();
//            ticketing2.setClient(person3);
//            ticketing2.setState(TicketingType.TICKETING);
//            ticketing2.setSeat(seat2);
//            ticketing2.setScreen(theather1Screen1);
//            em.persist(person3);
//
//
//            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//            System.out.println("문제 1 고객과 직원 생성");
//            PersonService personService = new PersonService(emf);
//            Address address = new Address("새로", "생성한", "주소");
//            ModifiedDate modifiedDate = new ModifiedDate(LocalDate.parse("2020-11-15"), LocalDate.parse("2021-11-15"));
//            personService.createClient(address, LocalDate.parse("2020-10-11"), "새고객", modifiedDate, 111);
//            personService.createManager(address, LocalDate.parse("2020-10-12"), "새관리자", LocalDate.parse("2020-10-12"));

//            System.out.println("문제 2 영화정보 조회1");
//            MovieService movieService = new MovieService(emf);
//            movieService.showMovieInfo(3L);
//            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//            movieService.showMovieInfo(4L);
//            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//            movieService.showMovieInfo(5L);
//            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//
//            MovieService movieService = new MovieService(emf);
//            System.out.println("문제 3 영화정보 조회2");
//            //movieService.showMovieWithActors(1);
//            movieService.showMovieWithActors(2);
//
//            System.out.println("문제 4 상영정보 조회1");
//            ScreenService screenService = new ScreenService(emf);
//            screenService.showScreenInfo(39L);

//            System.out.println("문제 5 예매");
//            ScreenService screenService = new ScreenService(emf);
//            screenService.showScreenInfo(40L);
//            System.out.println("예매전 ....");
//            TicketingService ticketingService = new TicketingService(emf);
//            ticketingService.movieReservation(46L, 40L, 1, 3, 17L);
//            screenService.showScreenInfo(40L);
//            System.out.println("예매후 ....");

//            System.out.println("문제 6 예매");
//            TicketingService ticketingService = new TicketingService(emf);
//            ticketingService.showTicketingInfo(50L);

//            System.out.println("문제 7 예매");
//            ScreenService screenService = new ScreenService(emf);
//            screenService.showScreenInfo(40L);
//            TicketingService ticketingService = new TicketingService(emf);
//            ticketingService.cancelTicketing(50L);
//            screenService.showScreenInfo(40L);

            System.out.println("문제 8 고객 삭제");
            PersonService personService = new PersonService(emf);
            personService.removePerson(46L);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

    }
}