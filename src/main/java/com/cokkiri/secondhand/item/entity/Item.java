package com.cokkiri.secondhand.item.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cokkiri.secondhand.user.entity.UserEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "ITEM")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", length = 100, nullable = false)
	private String title;

	@Column(name = "price", length = 100, nullable = false)
	private Long price;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;

	@OneToOne
	@JoinColumn(name = "seller_id")
	private UserEntity seller;

	@OneToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@OneToOne
	@JoinColumn(name = "status_id")
	private ItemStatus status;

	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToOne
	@JoinColumn(name = "metadata_id")
	private ItemMetadata itemMetadata;

	public Long findSellerId() {
		return seller.getId();
	}
}