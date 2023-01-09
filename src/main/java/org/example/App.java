package org.example;

import org.example.entities.Piloto;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Piloto p1 = new Piloto("Fernando Alonso", LocalDate.of(1981, 7, 29));
        Piloto p2 = new Piloto("Lance Stroll", LocalDate.of(1998, 10, 29));
        Piloto p3 = new Piloto("Felipe Drugovich", LocalDate.of(1000, 5, 23));

        // Insertar (save --> persist)
        // La gestión del id le corresponde a SGBD
        System.out.println("\n------Antes de insertar------ ");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        Transaction tx = session.beginTransaction();
        session.persist(p1);
        session.persist(p2);
        session.persist(p3);
        tx.commit();

        System.out.println("\n------Tras insertar------ ");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        System.out.println();

        tx = session.beginTransaction();
        Piloto p1copia = session.get(Piloto.class, p1.getId());
        System.out.println("------Leer id 1------");
        System.out.println(p1copia);
        tx.commit();

        System.out.println();

        tx = session.beginTransaction();
        System.out.println("------Ver todos------");
        session.createSelectionQuery("from Piloto ").getResultList().forEach(System.out::println);

        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}
