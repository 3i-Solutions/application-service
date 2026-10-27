package net.threeisolutions.application.service.dal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.threeisolutions.application.service.enums.PageContentStatus;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/// A version of a page's content. A page has at most one DRAFT and one PUBLISHED version at a time
@Entity
@Table(name = "page_content", uniqueConstraints = @UniqueConstraint(
		name = "uk_page_content_page_version", columnNames = {"page_id", "version_no"}))
@Getter
@Setter
@NoArgsConstructor
public class PageContent extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "page_id", nullable = false)
	private Page page;

	@Column(name = "version_no", nullable = false)
	private int versionNo;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PageContentStatus status = PageContentStatus.DRAFT;

	/// Nodes with config, style and event_config
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "jsonb")
	private List<Map<String, Object>> items;

	@Version
	@Column(name = "lock_version", nullable = false)
	private int lockVersion;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	/// Null while DRAFT
	@Column(name = "published_at")
	private Instant publishedAt;

}
