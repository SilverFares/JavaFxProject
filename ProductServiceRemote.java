package gestion;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ProductServiceRemote {

	int Addproduct(Product P);

	void Removeproduct(int id);

	Product findProdById(int id);

	List<Product> findAllProducts();

	void updateproduct(Product p);

	Product findProductByName(String name);

	void remove(Product p);
	public void Removeproduct2 (Product p);

	List<Product> findSearshedProducts(String s);
	public List<Product> findAllProducts2(String s);

	

	

}
