package com.komnenos.studentassistant.Repository;

import com.komnenos.studentassistant.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select s from User s where s.userId=?1")
    public User findByUserId(int userId);

    //@Query("select s from User s where s.userName=?1")
    //public User findByUserName(String userName);

    public User findByUserName(String username);

    @Query("select s from User s where s.userName=?1 and s.passWord=?2")
    public User findByNameAndCode(String userName,String passWord);
}
