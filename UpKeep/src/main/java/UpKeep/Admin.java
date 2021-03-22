package UpKeep;

import UpKeep.DAO.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public
class Admin {
    public static void main(String args[]){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpa-tutorial");
        EntityManager em = emf.createEntityManager();

        User user = em.find(User.class,51L);
//        Transaction steps


//        em.getTransaction().begin();
//
//        em.persist(a); em.persist(p); em.persist(c);
//
//        em.getTransaction().commit();

        System.out.println(user);

    }
}
