package jpabook.service;

import jpabook.dto.MovieDto;
import jpabook.entity.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovieService {
    private EntityManagerFactory emf;

    public MovieService(EntityManagerFactory emf){
        this.emf = emf;
    }

    public void showMovieInfo(Long movieId){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();

            Movie movie = em.find(Movie.class, movieId);
            System.out.println("영화이름 : " + movie.getName());
            System.out.println("개봉일 : " + movie.getRunningDate());
            System.out.println("감독 : " + movie.getDirector().getName());
            for(int i = 0; i < movie.getStarrings().size(); i++){
                System.out.println(i + "번째 배우 이름 : "  + movie.getStarrings().get(i).getActor().getName());
            }
            System.out.println("장르 : " + movie.getGenreType());
            System.out.println("러닝 타임 : " + movie.getRuntime());

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }

    public void showMovieWithActors(int pageNum){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();

            List<Movie> result = em.createQuery("select m from Movie as m", Movie.class).setFirstResult(pageNum)
                    .setMaxResults(2)
                    .getResultList();
            for(Movie movie : result){
                System.out.println("영화이름 : " + movie.getName());
                System.out.println("개봉일 : " + movie.getRunningDate());
                System.out.println("감독 : " + movie.getDirector().getName());
                for(int i = 0; i < movie.getStarrings().size(); i++){
                    System.out.println(i + "번째 배우 이름 : "  + movie.getStarrings().get(i).getActor().getName());
                }
                System.out.println("장르 : " + movie.getGenreType());
                System.out.println("러닝 타임 : " + movie.getRuntime());
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            }
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
