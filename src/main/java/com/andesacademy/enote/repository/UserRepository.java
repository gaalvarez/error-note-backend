/**
 * 
 */
package com.andesacademy.enote.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.andesacademy.enote.model.User;
/**
 * @author gaalvarez
 *
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

}
