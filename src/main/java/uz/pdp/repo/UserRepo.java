package uz.pdp.repo;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

//    //jpa query
//    //select * from users where group_name = :groupName order by birth_date desc
//    List<User> findAllByGroupNameAndNameOrderByBirthDateDesc(String groupName, @NotBlank(message = "Name field should not be empty or null") String name);
//
//    //jpql
//    //select * from users where group_name = :groupName order by birth_date desc
//    @Query(value = "SELECT u FROM User u WHERE u.groupName = :groupName ORDER BY u.birthDate DESC")
//    List<User> ketmtonnitop(String groupName);
//
//    //native -> sql
//    //select * from users where group_name = :groupName order by birth_date desc
//    @Query(nativeQuery = true, value = "select * from users where group_name = :groupName AND name = : name order by birth_date desc")
//    List<User> qidirGroupNameVaName(String groupName,String name);
//
//    @Query(nativeQuery = true,value = "SELECT * FROM myfunc(:ketmon)")
//    List<User> myfunc(String ketmon);


    @Query(nativeQuery = true,value = "CALL myProc(:ketmon)")
    void myProc(String ketmon);

    Optional<User> findByEmail(String email);
}
