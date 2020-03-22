package com.demente.ideas.learn.models.repository;

import com.demente.ideas.learn.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    /**
     * Query generated dynamically using Spring and the reserved name findBy
     *
     * @param name
     * @param lastname
     * @return
     */
    Optional<User> findByNameAndLastname(String name, String lastname);

    /**
     * Query generated dynamically using Spring Data Query Methods
     *
     * @return
     */
    Optional<User> findByUsernameOrEmail(String username, String email);

    /**
     * Query generated dynamically using Spring Data Query Methods
     *
     * @param username
     * @return
     */
    Boolean existsByUsername(String username);

    /**
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

    /**
     * Spring Data @Query
     *
     * @param from
     * @param to
     * @return
     */
	@Query("SELECT u FROM User u WHERE u.createdAt >=:from AND u.createdAt <=:to")
    List<User> findByFilter(@Param("from") Date from, @Param("to") Date to);
}
