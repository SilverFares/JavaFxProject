package app;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import gestion.Product;
import gestion.ProductServiceRemote;
import gestion.loader;
import gestion.loaderServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class updateProductController implements Initializable {

	@FXML
    private TextField product1;

    @FXML
    private TextField price1;

    @FXML
    private TextField qua1;

    @FXML
    private Button cancelB;

    @FXML
    private Button addP;

    @FXML
    private Label addMessage;

    @FXML
    void updateProduct() {
    	Context context;
		ProductServiceRemote dao = null;
		try {
			context = new InitialContext();
			
			String jndiname="PiDEV-ear/PiDEV-ejb/ProductService!gestion.ProductServiceRemote";
			dao = (ProductServiceRemote)context.lookup(jndiname);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(product1.getText().isEmpty()||qua1.getText().isEmpty()||price1.getText().isEmpty()) {
			addMessage.setText("Complete missing information");
			return ;
		}
		Product p=new Product();
		p=dao.findProductByName(product1.getText());
		
		if(!isValideProduct(product1.getText())) {
			addMessage.setText("not a valide product name ");
			return;
		}
		else if(!isInteger(qua1.getText())) {
			addMessage.setText("none valide quantity ");
			return;
		}
		else if(!isFloat(price1.getText())) {
			addMessage.setText("none valide price tag ");
			return;
		}
		else if(!pro.getName().equals(product1.getText())) {
	     if (p.getName().equals(product1.getText())) {
			addMessage.setText("Product existe already ");
			return;
		}
		}
		int t=Integer.parseInt(qua1.getText());
		float f=Float.parseFloat(price1.getText());
		 if (t<0||f<0) {
			 addMessage.setText("The price and the quantity should be positive ");
			 return;
		 }
		else {
			
			
			pro.setName(product1.getText());
			pro.setQuantity(qua1.getText());
			pro.setPrice(price1.getText());
			Date in = new Date();
   		    LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
   		    Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
   		    pro.setUpdateDate(out);
   		    dao.updateproduct(pro);
   		    Alert alert = new Alert(AlertType.INFORMATION);
   		    alert.setTitle("Information Dialog");
   		    alert.setHeaderText("Success");
   		    alert.setContentText("Product update successfully");

   		    
   		    Optional<ButtonType> result = alert.showAndWait();
   		     if (result.get() == ButtonType.OK){
   		    	Stage primaryStage=new Stage();
   				try {
   					Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
   					
   					Scene scene= new Scene(root);
   					primaryStage.setScene(scene);
   					//primaryStage.initStyle(StageStyle.UNDECORATED);
   					//primaryStage.setResizable(false);
   					primaryStage.setTitle("Product List");
   					primaryStage.show();
   					closeScene();
   				} catch(Exception e) {
   					e.printStackTrace();
   				}
   		       } 
   		     return;
		}
    }

    
    private int id;
   public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
private Product pro=new Product();


	   

	

public void initialize(URL url,ResourceBundle rb) {
	//initItems();
	loadProduct();
	product1.setText(pro.getName());
	price1.setText(pro.getPrice());
	qua1.setText(pro.getQuantity());
	}


public void loadProduct() {
	Context context;
		 loaderServiceRemote dao = null;
		try {
			context = new InitialContext();
			String jndiname="PiDEV-ear/PiDEV-ejb/loaderService!gestion.loaderServiceRemote";
			
			dao = (loaderServiceRemote)context.lookup(jndiname);
			 
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loader l=new loader();
		l=dao.findLoder(1);
		Context context2;
		ProductServiceRemote dao2 = null;
		try {
			context2 = new InitialContext();
			String jndiname="PiDEV-ear/PiDEV-ejb/ProductService!gestion.ProductServiceRemote";
			
			dao2 = (ProductServiceRemote)context2.lookup(jndiname);
			 
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pro=dao2.findProdById(l.getUpdateId());
}
public void initItems() {
	product1.setText(pro.getName());
	price1.setPromptText(pro.getPrice());
	qua1.setPromptText(pro.getQuantity());
}

@FXML
void cancelButton(ActionEvent event) {
 Stage primaryStage=new Stage();
	try {
		Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
		
		Scene scene= new Scene(root);
		primaryStage.setScene(scene);
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		//primaryStage.setResizable(false);
		primaryStage.setTitle("Product List");
		primaryStage.show();
		closeScene();
	} catch(Exception e) {
		e.printStackTrace();
	}
}
private void closeScene(){
    // get a handle to the stage
    Stage stage = (Stage) addMessage.getScene().getWindow();
    // do what you have to do
    stage.close();
}
public static boolean isInteger(String str) {
    try {
    	int i=Integer.parseInt(str);
	} catch (Exception e) {
		return false;
	}
    return true;
}

public static boolean isFloat(String str) {
	try {
		float f=Float.parseFloat(str);
	}
	catch (Exception e) {
		return false;
	}
	return true;
}

public static boolean isValideProduct(String str) {
   /*String specialChar= "/w";Character.
   if (str.matches(specialChar)){
	   return false;
   }
   else {
	   return true;
   }*/
   Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
   
      
      Matcher matcher = pattern.matcher(str);
 
      if (!matcher.matches()) {
           return false ;
      } else {
          return true;
      }
}
}
