package gestion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import enumerations.type;

/**
 * Entity implementation class for Entity: product
 *
 */
import javax.persistence.*;




/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity(name="product_tab")
@Table(name="product_tab")
public class Product implements Serializable {

	
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue( strategy = GenerationType.IDENTITY )
@Column(name="ID_product")
int id;

@Column(name="name")
String name;

@Column(name="CreationDate_product")
Date creationDate;

@Column(name="ExpirationDate_product")
Date ExpirationDate;
Date updateDate;

public Date getUpdateDate() {
	return updateDate;
}

public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
}

@Column(name="Kiosk_ID")
int kioskID;

@Column(name="Quantity")
String Quantity;	

@Column(name="Price")
String price;



@Enumerated(EnumType.STRING)
private status Status;

@Enumerated(EnumType.STRING)
private type TypeProduct;



@Column(name="rate")
int rate;

//getter&setter

	public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
public Date getCreationDate() {
	return creationDate;
}


public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
}

public Date getExpirationDate() {
	return ExpirationDate;
}

public void setExpirationDate(Date expirationDate) {
	ExpirationDate = expirationDate;
}

public int getKioskID() {
	return kioskID;
}

public void setKioskID(int kioskID) {
	this.kioskID = kioskID;
}

public String getQuantity() {
	return Quantity;
}

public void setQuantity(String quantity) {
	Quantity = quantity;
}

	public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}




public status getStatus() {
	return Status;
}

public void setStatus(status status) {
	Status = status;
}

	public type getTypeProduct() {
	return TypeProduct;
}
public void setTypeProduct(type typeProduct) {
	TypeProduct = typeProduct;
}


	





	public int getRate() {
	return rate;
}

public void setRate(int rate) {
	this.rate = rate;
}

	//constructeur 
	public Product() {
		super();
	}
	
	public Product(String name, Date creationDate, Date expirationDate, int kioskID, String quantity, String price,
			String p_picture, status status, type typeProduct) {
		super();
		this.name = name;
		this.creationDate = creationDate;
		ExpirationDate = expirationDate;
		this.kioskID = kioskID;
		Quantity = quantity;
		this.price = price;
		
		Status = status;
		TypeProduct = typeProduct;
	}
	
	/*public Product(String name, Date creationDate, Date expirationDate, int kioskID, int quantity, float price,
			String p_picture, status status, type typeProduct,int id) {
		super();
		this.name = name;
		this.creationDate = creationDate;
		ExpirationDate = expirationDate;
		this.kioskID = kioskID;
		Quantity = quantity;
		this.price = price;
		this.p_picture = p_picture;
		Status = status;
		TypeProduct = typeProduct;
		this.id = id;
	}
	public Product(String name) {
		super();
		this.name = name;
	}
	*/
	public Product(String name, String price, status status) {
		super();
		this.name = name;
		this.price = price;
		
		Status = status;
	}
	public Product(String name, String price, status status, type typeProduct) {
		super();
		this.name = name;
		this.price = price;
		
		Status = status;
		TypeProduct = typeProduct;
	}
	
	public Product(String name, String price, status status, type typeProduct,int id) {
		super();
		this.name = name;
		this.price = price;
		
		Status = status;
		TypeProduct = typeProduct;
		this.id = id;
		
	}
	
	
	
	public Product(int id, String name, Date creationDate, Date expirationDate, int kioskID, String quantity, String price,
		 status status, type typeProduct) {
		super();
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
		ExpirationDate = expirationDate;
		this.kioskID = kioskID;
		Quantity = quantity;
		this.price = price;
		
		Status = status;
		TypeProduct = typeProduct;
	}

	public Product(int id, String name, Date creationDate, Date expirationDate, int kioskID, String quantity, String price,
			 status status, type typeProduct , int rate) {
		super();
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
		ExpirationDate = expirationDate;
		this.kioskID = kioskID;
		Quantity = quantity;
		this.price = price;
		
		Status = status;
		TypeProduct = typeProduct;
		this.rate = rate;
		
	}

	

	
	

	
	
	
	
	
	
   
}
