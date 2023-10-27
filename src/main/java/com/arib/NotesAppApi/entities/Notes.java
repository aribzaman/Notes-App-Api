package com.arib.NotesAppApi.entities;

import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "Notes")
@DynamicUpdate
public class Notes {
	
	//for saving dto (applyReverse) dateCreated is automatic and dateUpdated is new()
		public Notes(@NotNull String title, String content, User user, Date dateUpdated) {
			super();
			this.title = title;
			this.content = content;
			this.user = user;
			this.dateUpdated =dateUpdated;
		}
	
	
	//for update dto
	public Notes(int id, @NotNull String title, String content, @NotNull User user, Date dateUpdated, boolean deleted, boolean archived, boolean pinned) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.user = user;
		this.dateUpdated =dateUpdated;
		this.deleted = deleted;
		this.archived = archived;
		this.pinned = pinned;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@NotNull 
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "content", length = 500)
	private String content;
	
	@ManyToOne(targetEntity = User.class)
	private User user;
	
	@Column(columnDefinition = "TINYINT(1) default 0")
    private boolean deleted;

    @Column(columnDefinition = "TINYINT(1) default 0")
    private boolean archived;
    
    @Column(columnDefinition = "TINYINT(1) default 0")
    private boolean pinned;
	
	@Column(name = "date_created", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();
	
	@Column(name = "date_updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

}