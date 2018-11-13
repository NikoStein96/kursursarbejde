package utils;

import entity.Role;
import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManager em = Persistence.createEntityManagerFactory("pu").createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    
    
        
    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    User user = new User("user", "lolleren");
    user.addRole(userRole);
    User admin = new User("admin", "sebastian123");
    admin.addRole(adminRole);
    User both = new User("user_admin", "skr98");
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    //User user = em.find(User.class, "user");
    //User test = new User("test", "lol");
    //em.persist(test);
    //em.getTransaction().commit();
    System.out.println("Testing user with password: " + user.verifyPassword("lolleren"));
    System.out.println("Created TEST Users");
   
  }

}
