package fr.arcesi.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="PRODUCTS")
public class Product  implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CODE")
	private Long code;
	@Column(name="NAME")
	private String name;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="CURRENT_PRICE")
	private double currentPrice;
	@Column(name="PROMOTION")
	private boolean isPromotion;
	@Column(name="SELECTED")
	private boolean isSelected;
	@Column(name="AVAILABLE")
	private boolean isAvailable;
	@Column(name="PHOTO_NAME")
	private String photoName;
	@ManyToOne
	private  Category category;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String name, String description, double currentPrice, boolean isPromition, boolean isSelected,
			boolean isAvailable, String photoName, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.currentPrice = currentPrice;
		this.isPromotion = isPromition;
		this.isSelected = isSelected;
		this.isAvailable = isAvailable;
		this.photoName = photoName;
		this.category = category;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public boolean isPromotion() {
		return isPromotion;
	}
	public void setPromotion(boolean isPromition) {
		this.isPromotion = isPromition;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name + ", description=" + description + ", currentPrice="
				+ currentPrice + ", isPromition=" + isPromotion + ", isSelected=" + isSelected + ", isAvailable="
				+ isAvailable + ", photoName=" + photoName + "]";
	} 

	
}
