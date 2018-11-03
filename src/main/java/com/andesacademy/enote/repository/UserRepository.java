/**
 * 
 */
package com.andesacademy.enote.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.andesacademy.enote.model.User;
/**
 * @author gaalvarez
 *
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	@Query("SELECT count(u) FROM User u where u.username = ?1")
    public Integer countByUsername(String username);
}
