package gestion;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class ProductService
 */
@Stateless
@LocalBean
public class ProductService implements ProductServiceRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
	@Override
	public int Addproduct(Product P){
	System.out.println("In addProduct : ");
	em.persist(P);
	System.out.println("Out of addProduct" + P.getId());
	return P.getId();
	}
	
	//removebyid
	@Override
	public void Removeproduct (int id) {
	System.out.println("In removeProduct: ");
	em.remove(em.find(Product.class, id));
	System.out.println("Out of removeProduct : ");
	}
	public void Removeproduct2 (Product p) {
		System.out.println("In removeProduct: ");
		em.remove(p);
		System.out.println("Out of removeProduct : ");
		}
    @Override
    public void remove(Product p) {
    	em.getTransaction().begin();
    	em.remove(em.merge(p));
    	em.getTransaction().commit();
    }
	
	//findbyid
	@Override
	public Product findProdById(int id) {
	System.out.println("In findProdById : ");
	Product p = em.find(Product.class, id);
	System.out.println("Out of findProdById : ");
	return p;
	
	}
	
	@Override
	public void updateproduct(Product p) {
		em.merge(p);
	}
	
	
	//findall
	@Override
	public List<Product> findAllProducts() {
	System.out.println("In findAllProducts : ");
	List<Product> Products = em.createQuery("select e from product_tab e", Product.class).getResultList();
	System.out.println("Out of findAllProducts : ");
	return Products;
	}
	public List<Product> findAllProducts2(String s) {
		System.out.println("In findAllProducts : ");
		List<Product> Products = em.createQuery("select e from product_tab e", Product.class).getResultList();
		System.out.println("Out of findAllProducts : ");
		List<Product> Products2=new ArrayList<>();
		for(Product p:Products) {
			if(p.getName().indexOf(s)!=-1) {
				Products2.add(p);
			}
		}
		return Products2;
		}
	@Override
	public List<Product> findSearshedProducts(String s){
		List<Product> products=new ArrayList<>();
		try {
		 products = em.createQuery(
				  "SELECT u from product_tab u WHERE u.name LIKE CONCAT('%',?1,'%')", Product.class).
				   setParameter("1",s).getResultList();
		return products;
		}catch (NoResultException e) {
			return products;
        }
		
		//'%'+@Name+'%'
	}
	@Override
	public Product findProductByName(String name) {
		Product p=new Product();
		p.setName("7");
		          try {
		         p=em.createQuery(
				  "SELECT u from product_tab u WHERE u.name= :name", Product.class).
				   setParameter("name", name).getSingleResult();
		          }catch (NoResultException e) {
		        	  
		          }
		          
	      return p;
	}
	
	//update
	
	/*@Override
	public void updateProd(Product ProductNewValues) {
	System.out.println("In updateProduct : ");
	Product p = em.find(Product.class, ProductNewValues.getId());
	p.setName(ProductNewValues.getName());
	p.setPrice(ProductNewValues.getPrice());
	p.setKioskID(ProductNewValues.getKioskID());
	p.setQuantity(ProductNewValues.getQuantity());
	p.setStatus(ProductNewValues.getStatus());
	p.setTypeProduct(ProductNewValues.getTypeProduct());
	p.setCreationDate(ProductNewValues.getCreationDate());
	p.setExpirationDate(ProductNewValues.getExpirationDate());
	p.setP_picture(ProductNewValues.getP_picture());

	
	System.out.println("Out of updateUser : ");
	}*/
	
	//addrate
	
	
	//bestprod
	
	
	
	/*
	@Override
	public void deleteExpiredproducts(){
		em.getTransaction().begin();
		Query query = em.createQuery("DELETE FROM Product_Tab P WHERE P.Status = :status ");
		query.setParameter("status", status.expired);
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        em.getTransaction().commit();
        em.close();
	}
	*/

}
