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

public class inspectProductController implements Initializable {@FXML
    private TextField product1;

    @FXML
    private TextField price1;

    @FXML
    private TextField qua1;

    @FXML
    private Button cancelB;

   

    @FXML
    private Label addMessage;
    @FXML
    private TextField creationDate;
  
    

    
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
	creationDate.setText(pro.getCreationDate().toString());
	
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

