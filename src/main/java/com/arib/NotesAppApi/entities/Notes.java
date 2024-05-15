package com.arib.NotesAppApi.entities;

import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

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
		public Notes(@NotNull String title, String content, User user, LocalDateTime dateUpdated) {
			super();
			this.title = title;
			this.content = content;
			this.user = user;
			this.dateUpdated =dateUpdated;
		}
	
	
	//for update dto
	public Notes(int id, @NotNull String title, String content, @NotNull User user, LocalDateTime dateUpdated, LocalDateTime dateDeleted, boolean deleted, boolean archived, boolean pinned) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.user = user;
		this.dateUpdated =dateUpdated;
		this.dateDeleted =dateDeleted;
		this.deleted = deleted;
		this.archived = archived;
		this.pinned = pinned;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@NotNull
	@ManyToOne
	@Column(name = "title", nullable = false, length = 100)
	private String title;
	
	@Column(name = "content", length = 2000)
	private String content;
	
	@Column(name = "color")
	private String color;
	
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
    private LocalDateTime dateCreated = LocalDateTime.now();
	
	@Column(name = "date_updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateUpdated;
	
	@Column(name = "date_deleted")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateDeleted;

}