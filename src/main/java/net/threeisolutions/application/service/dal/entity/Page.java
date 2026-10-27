package net.threeisolutions.application.service.dal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "page", uniqueConstraints = @UniqueConstraint(
		name = "uk_page_siblings_path", columnNames = {"application_id", "parent_page_id", "path"}))
@Getter
@Setter
@NoArgsConstructor
public class Page extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "application_id", nullable = false)
	private Application application;

	/// Parent page within the same application; null for top-level pages
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_page_id")
	private Page parentPage;

	/// Set for top-level pages only
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "navigation_id")
	private Navigation navigation;

	@Column(nullable = false)
	private String name;

	private String title;

	private String icon;

	@Column(nullable = false)
	private String path;

	@Column(name = "sort_order", nullable = false)
	private int sortOrder;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

}
