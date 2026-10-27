package net.threeisolutions.application.service.dal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "template")
@Getter
@Setter
@NoArgsConstructor
public class Template extends BaseEntity {

	public static final int DEFAULT_MAX_VERSIONS = 20;

	@Column(nullable = false)
	private String name;

	/// Cap on the number of versions kept for this template
	@Column(name = "max_versions", nullable = false)
	private int maxVersions = DEFAULT_MAX_VERSIONS;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

}
