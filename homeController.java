package app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Observable;
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
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;


public class homeController implements Initializable {
	ObservableList<Product>filtredData = FXCollections.observableArrayList();
	ObservableList<Product> list = FXCollections.observableArrayList();
	@FXML
    private TableView<Product> listProd;

    @FXML
    private TableColumn<Product, String> productName;

    @FXML
    private TableColumn<Product, String> quantity;
    @FXML
    private Label addMessage;
    @FXML
    private TableColumn<Product, String> price;

    @FXML
    private TableColumn<Product, Date> date;
    @FXML
    private TableColumn<Product, Date> date2;
    @FXML
    private TableColumn<Product, Date> updateDate;

    @FXML
    private TextField search;

    @FXML
    private Button addProduct;

    @FXML
    private Button sortName;

    @FXML
    private Button sortPrice;

    @FXML
    private Button searshButton;
    @FXML
    private Label labelTest;
    @FXML
    private AnchorPane homePane;
    @FXML
    private TextField updateName;

    @FXML
    private TextField updatePrice;

    @FXML
    private TextField updateQuantity;
    private int id;
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		
		intitList();
		loadList();
 
	}
	@FXML
   public void addProduct() {
		Stage primaryStage=new Stage();
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("updateProduct.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
			
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
	public void updateProduct() {
		Stage primaryStage=new Stage();
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("updateProduct.fxml"));
			
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
	public void inspectProduct() {
		Stage primaryStage=new Stage();
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("inspectProduct.fxml"));
			
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
	
	
		private void closeScene(){
		    // get a handle to the stage
		    Stage stage = (Stage)search.getScene().getWindow();
		    // do what you have to do
		    stage.close();
    }
	
		
		public void intitList() {
		productName.setCellValueFactory(
			    new PropertyValueFactory<Product,String>("name")
			);
		quantity.setCellValueFactory(
			    new PropertyValueFactory<Product,String>("Quantity")
			);
		price.setCellValueFactory(
			    new PropertyValueFactory<Product,String>("price")
			);
		date.setCellValueFactory(
			    new PropertyValueFactory<Product,Date>("creationDate")
			);
		date2.setCellValueFactory(
			    new PropertyValueFactory<Product,Date>("updateDate")
			);
		
		
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
		
		list.addAll(dao.findAllProducts());
		listProd.setItems(list);
	}
	ProductServiceRemote dao2;
	
	public void loadList() {
		
		
		
		listProd.setRowFactory(new Callback<TableView<Product>, TableRow<Product>>() {  
            @Override  
            public TableRow<Product> call(TableView<Product> tableView) {  
                final TableRow<Product> row = new TableRow<>();  
                final ContextMenu contextMenu = new ContextMenu(); 
                final MenuItem inspectMenuItem = new MenuItem("Inspect");
                final MenuItem removeMenuItem = new MenuItem("Remove"); 
                final MenuItem updateMenuItem = new MenuItem("Update"); 
                
                loader Loader=new loader();
                updateMenuItem.setOnAction(event -> {
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
                	Loader.setId(1);
                	Loader.setUpdateId(row.getItem().getId());
                	dao.updateLoder(Loader);
                	updateProduct();
                	
                });
                inspectMenuItem.setOnAction(event -> {
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
                	Loader.setId(1);
                	Loader.setUpdateId(row.getItem().getId());
                	dao.updateLoder(Loader);
                	inspectProduct();
                	
                });
                removeMenuItem.setOnAction(event ->{
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
                	Product p=new Product();
                	p=row.getItem();
                	dao.Removeproduct(p.getId());
                	//dao.remove(p);
                	listProd.getItems().remove(row.getItem());
                	
                });
                contextMenu.getItems().add(inspectMenuItem);  
                 contextMenu.getItems().add(updateMenuItem);   
                 contextMenu.getItems().add(removeMenuItem);
               // Set context menu on row, but use a binding to make it only show for non-empty rows:  
                row.contextMenuProperty().bind(  
                        Bindings.when(row.emptyProperty())  
                        .then((ContextMenu)null)  
                        .otherwise(contextMenu)  
                );  
                return row ;  
            }  
        }); 
	}
	
  		
  		
  		
	void updateProduct(Product p2) {
		}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	  
	   Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
	   
	      
	      Matcher matcher = pattern.matcher(str);
	 
	      if (!matcher.matches()) {
	           return false ;
	      } else {
	          return true;
	      }
   }
   @FXML
   void searshAction(ActionEvent event) {
	   Context context;
	   ProductServiceRemote dao = null;
	  // listProd.getItems().clear();
		try {
			context = new InitialContext();
			
			String jndiname="PiDEV-ear/PiDEV-ejb/ProductService!gestion.ProductServiceRemote";
			dao = (ProductServiceRemote)context.lookup(jndiname);
			 
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(search.getText().isEmpty()||search.getText()==null||search.getText().equals("")) {
			listProd.getItems().clear();
			listProd.setItems(list);
			return;
		}
		else {
			filtredData.clear();
		    filtredData.addAll(dao.findSearshedProducts(search.getText()));
		
		listProd.setItems(filtredData);return;
		}
   }
   /*@FXML
   void searchRecord(KeyEvent ke) {
	   
	   
	   FilteredList<Product>filterData=new FilteredList<>(list,p->true);
	   search.textProperty().addListener((observable, oldvalue, newvalue) -> {
		   filterData.setPredicate(pers ->{
			   if(newvalue ==null ||newvalue.isEmpty()) {
				   return true;
			   }
			   String typedText=newvalue.toLowerCase();
			   if(pers.getName().toLowerCase().indexOf(typedText) !=- 1) {
				   filterData.add(pers);
				   return true;
			   }
			   
			   return false;
		   });
		   SortedList<Product> sortedList=new SortedList<>(filterData);
		   sortedList.comparatorProperty().bind(listProd.comparatorProperty());
		   listProd.setItems(sortedList);
	   });

   }*/
}
