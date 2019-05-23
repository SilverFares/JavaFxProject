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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class addProductController implements Initializable {

	
	 @FXML
	    private TextField product;

	    @FXML
	    private TextField price;

	    @FXML
	    private TextField qua;

	    @FXML
	    private Button addP;
	    @FXML
	    private Button cancelB;
	    @FXML
	    private Label addMessage;
	public void initialize(URL url,ResourceBundle rb) {
		
 
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
				primaryStage.show();
				closeScene();
			} catch(Exception e) {
				e.printStackTrace();
			}
	    }
	@FXML
    void addProduct(ActionEvent event) {
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
		
		if(product.getText().isEmpty()||qua.getText().isEmpty()||price.getText().isEmpty()) {
			addMessage.setText("Complete missing information");
			return ;
		}
		Product p=new Product();
		p=dao.findProductByName(product.getText());
		
		if(!isValideProduct(product.getText())) {
			addMessage.setText("not a valide product name ");
			return;
		}
		else if(!isInteger(qua.getText())) {
			addMessage.setText("none valide quantity ");
			return;
		}
		else if(!isFloat(price.getText())) {
			addMessage.setText("none valide price tag ");
			return;
		}
	       else if (p.getName().equals(product.getText())) {
			addMessage.setText("Product existe already ");
			return;
		}
		int t=Integer.parseInt(qua.getText());
		float f=Float.parseFloat(price.getText());
		 if (t<0||f<0) {
			 addMessage.setText("The price and the quantity should be positive ");
			 return;
		 }
		else {
			Product p2=new Product();
			
			p2.setName(product.getText());
			p2.setQuantity(qua.getText());
			p2.setPrice(price.getText());
			Date in = new Date();
   		    LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
   		    Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
   		    p2.setCreationDate(out);
   		    dao.Addproduct(p2);
   		    Alert alert = new Alert(AlertType.INFORMATION);
   		    alert.setTitle("Information Dialog");
   		    alert.setHeaderText("Success");
   		    alert.setContentText("Product added successfully");

   		    
   		    Optional<ButtonType> result = alert.showAndWait();
   		     if (result.get() == ButtonType.OK){
   		    	Stage primaryStage=new Stage();
   				try {
   					Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
   					
   					Scene scene= new Scene(root);
   					primaryStage.setScene(scene);
   					//primaryStage.initStyle(StageStyle.UNDECORATED);
   					//primaryStage.setResizable(false);
   					primaryStage.show();
   					closeScene();
   				} catch(Exception e) {
   					e.printStackTrace();
   				}
   		       } 
   		     return;
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