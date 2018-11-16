package entity;

import entity.Role;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2018-11-16T11:15:12")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> userPass;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile ListAttribute<User, Role> roleList;

}