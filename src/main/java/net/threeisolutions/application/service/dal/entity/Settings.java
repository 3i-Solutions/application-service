package net.threeisolutions.application.service.dal.entity;

import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.threeisolutions.application.service.enums.SettingsType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

//// Settings attached to exactly one owner; {@link #type} decides which foreign key is set
@Entity
@Table(name = "settings", check = @CheckConstraint(name = "ck_settings_owner", constraint = """
		(type = 'APPLICATION' AND application_id IS NOT NULL AND navigation_id IS NULL AND page_id IS NULL) \
		OR (type = 'NAVIGATION' AND navigation_id IS NOT NULL AND application_id IS NULL AND page_id IS NULL) \
		OR (type = 'PAGE' AND page_id IS NOT NULL AND application_id IS NULL AND navigation_id IS NULL)"""))
@Getter
@Setter
@NoArgsConstructor
public class Settings extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SettingsType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "application_id")
	private Application application;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "navigation_id")
	private Navigation navigation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id")
	private Page page;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "jsonb")
	private Map<String, Object> config;

}
