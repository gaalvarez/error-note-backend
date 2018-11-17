/**
 * 
 */
package com.andesacademy.enote.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.andesacademy.enote.model.File;
/**
 * @author gaalvarez
 *
 */
@Repository
public interface FileRepository extends PagingAndSortingRepository<File, Integer> {

}
