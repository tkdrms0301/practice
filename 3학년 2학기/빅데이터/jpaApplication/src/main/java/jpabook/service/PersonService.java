package jpabook.service;

import jpabook.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

public class PersonService {
    private EntityManagerFactory emf;

    public PersonService(EntityManagerFactory emf){
        this.emf = emf;
    }

    public void createClient(Address address, LocalDate dob, String name, ModifiedDate modifiedDate, Integer mileage){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();

            Client client = new Client();
            client.setAddress(address);
            client.setDayOfBirth(dob);
            client.setName(name);
            client.setModifiedDate(modifiedDate);
            client.setMileage(mileage);
            em.persist(client);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }

    public void createManager(Address address, LocalDate dob, String name, LocalDate wsd){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();

            Manager manager = new Manager();
            manager.setAddress(address);
            manager.setDayOfBirth(dob);
            manager.setName(name);
            manager.setWorkingStartDate(wsd);
            em.persist(manager);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }

    public void removePerson(Long personId){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();

            Person person = em.find(Person.class, personId);
            em.remove(person);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
