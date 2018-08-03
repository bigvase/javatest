package com.test.demo;


import com.test.entity.ActivityEntity;
import com.test.entity.StudentEntity;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class HibernateDemo {

    public static void main1(String[] args){
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory=cfg.buildSessionFactory();

        //creating session object
        Session session=factory.openSession();

        //creating transaction object
        Transaction t=session.beginTransaction();

        ActivityEntity ae = new ActivityEntity();
        ae.setTitle("1test");
        ae.setCover_image("1test---est--re--ee");
        ae.setContent("1就不高数你");
        ae.setIntro("1abcdefg");
        ae.setAdd_time(1533052800);
        ae.setStatus(1);
        session.persist(ae);//persisting the object
        t.commit();//transaction is committed
        session.close();

        System.out.println("successfully saved");

    }

    public static void main(String[] args){
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Session session = factory.openSession();

        Transaction t = session.beginTransaction();
        StudentEntity se = new StudentEntity();
        se.setName("lmx");
        se.setAge(20);
        session.persist(se);
        t.commit();
        session.close();
        System.out.println("successfully saved");
    }

}
