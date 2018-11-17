/**
 * 
 */
package com.andesacademy.enote.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.andesacademy.enote.model.Note;
/**
 * @author gaalvarez
 *
 */
@Repository
public interface NoteRepository extends PagingAndSortingRepository<Note, Integer> {

}
