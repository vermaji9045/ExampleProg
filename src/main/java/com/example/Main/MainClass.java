package com.example.Main;

import com.example.Beans.Person;
import com.example.ProjectConfig.Config;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Random;
import java.util.function.Supplier;

public class MainClass {

    public static void main(String[] args) {

        var context=new AnnotationConfigApplicationContext(Config.class);

        Person user1=new Person();
        user1.setName("Sandeep");
        user1.setAge(22);

        Supplier<Person>user1Supplier=()-> user1;
        Supplier<Person>user2Supplier=()->{

            Person user2=new Person();
            user2.setName("Jatin jawla");
            user2.setAge(22);
            return user2;
        };

        Random random=new Random();
        int rnd=random.nextInt(10);
        System.out.println("Random Number is="+rnd);

        if(rnd%2==0)
        {
            context.registerBean("user1",Person.class,user1Supplier);
        }
        else
        {
            context.registerBean("user2",Person.class,user2Supplier);
        }

        Person user1Person=null;
        Person user2Person=null;


        try
        {
            user1Person=context.getBean("user1",Person.class);
        }
        catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException)
        {
            System.out.println("No bean is register for User1");
        }
        try{
            user2Person=context.getBean("user2",Person.class);
        }catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException)
        {
            System.out.println("No bean is register for User2");
        }

        if(null!=user1Person)
        {
            System.out.println("Name and Age for user1: "+user1Person.getName()+" Age: "+user1Person.getAge());
        }
        else

        {
            System.out.println("Name and Age for user2: "+user2Person.getName()+" Age: "+user2Person.getAge());
        }

    }
}
