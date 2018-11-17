package com.andesacademy.enote.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the tbl_note database table.
 * 
 */
@Entity
@Table(name = "tbl_note")
@NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n")
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_note")
	private Integer id;

	private String descripcion;

	private String solution;

	private String tags;

	private String title;

	@ManyToOne
	@JoinColumn(name = "id_user_tbl_user")
	private User user;

	@OneToOne
	@JoinColumn(name = "id_file_tbl_file")
	private File file;

	public Note() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSolution() {
		return this.solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}