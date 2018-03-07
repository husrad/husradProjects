package privacyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author DEVPC
 */
public class Main extends Application {

    static Connection conn;
    static PreparedStatement ps = null;
    static ResultSet res = null;
    static Statement query;
    final ObservableList items = FXCollections.observableArrayList();
    ComboBox comboBox = new ComboBox();
        
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException {
        
        conn = DBConnection.getConnection();             
        fillComboBox();
        
        primaryStage.setTitle("Privacy Manage");
        Group root = new Group();
        Scene scene = new Scene(root, 1300, 900, Color.ALICEBLUE);

        TabPane tabPane = new TabPane();
        BorderPane borderPane = new BorderPane();

        //Create Tabs
        Tab tabPwd = new Tab();
        tabPwd.setText("Passwort Keeper");
        VBox tabPwd_vbox = new VBox();
        
        //Add something in Tab tabPwd
        MenuBar menuBar = new MenuBar();
        // File menu - new, save, and exit
        Menu fileMenu = new Menu("File");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        //add the new menuitems to the fileMenu
        fileMenu.getItems().addAll(saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

        Menu languageMenu = new Menu("Language");
        CheckMenuItem englishMenuItem = new CheckMenuItem("English");
        CheckMenuItem germanMenuItem = new CheckMenuItem("German");
        CheckMenuItem arabicMenuItem = new CheckMenuItem("Arabic");
        languageMenu.getItems().addAll(englishMenuItem, germanMenuItem, arabicMenuItem);

        menuBar.getMenus().addAll(fileMenu, languageMenu);

    // wir legen unser GridPane an
    GridPane grid = new GridPane();
    // weisen das Padding (interner Abstand) zu
    grid.setPadding(new Insets(50, 100, 10, 10));
    // und fügen einen kleinen Außenabstand hinzu
    grid.setVgap(10);
    grid.setHgap(10);

    // Labels und Textfields and Buttons
    Label lblItem = new Label("Item Name");
    TextField txtItem = new TextField();
    txtItem.setPromptText ("Item Name");
    Label lblWebLink = new Label("Web Link");
    TextField txtWebLink = new TextField();
    comboBox.setEditable(true);
    comboBox.setValue("Select");
    comboBox.getValue();
    comboBox.setItems(items);
    txtWebLink.setPromptText ("Web Link");
    Button btnWebLinkGo = new Button();
    btnWebLinkGo.setText ("Go!");
    Label lblUserName = new Label("User Name");
    TextField txtUserName = new TextField();
    txtUserName.setPromptText ("User Name");
    Button btnUserCopy = new Button();
    btnUserCopy.setText ("Copy!");
    Label lblPassword = new Label("Password");
    PasswordField txtPassword = new PasswordField();
    txtPassword.setPromptText ("Password");
    Button btnPasswdCopy = new Button();
    btnPasswdCopy.setText ("Copy!");
    Label lblNotes = new Label("Notes");
    TextArea txtNotes = new TextArea();
    txtNotes.setPromptText ("Notes to the Item");

// wir weißen die Spalten und Zeilen zu unseren Elementen zu
    GridPane.setConstraints (lblItem, 0, 0);
    GridPane.setConstraints (txtItem, 1, 0);
    GridPane.setConstraints (comboBox, 20, 0);
    GridPane.setConstraints (lblWebLink, 0, 1);
    GridPane.setConstraints (txtWebLink, 1, 1);
    GridPane.setConstraints (btnWebLinkGo, 2, 1);
    GridPane.setConstraints (lblUserName, 0, 2);
    GridPane.setConstraints (txtUserName, 1, 2);
    GridPane.setConstraints (btnUserCopy, 2, 2);
    GridPane.setConstraints (lblPassword, 0, 3);
    GridPane.setConstraints (txtPassword, 1, 3);
    GridPane.setConstraints (btnPasswdCopy, 2, 3);
    GridPane.setConstraints (lblNotes, 0, 4);
    GridPane.setConstraints (txtNotes, 1, 4);

// wir fügen unsere Elemente zum grid hinzu
    
    grid.getChildren().add(lblItem);
    grid.getChildren().add(txtItem);
    grid.getChildren().add(comboBox);
    grid.getChildren().add(lblWebLink);
    grid.getChildren().add(txtWebLink);
    grid.getChildren().add(btnWebLinkGo);
    grid.getChildren().add(lblUserName);
    grid.getChildren().add(txtUserName);
    grid.getChildren().add(btnUserCopy);
    grid.getChildren().add(lblPassword);
    grid.getChildren().add(txtPassword);
    grid.getChildren().add(btnPasswdCopy);
    grid.getChildren().add(lblNotes);
    grid.getChildren().add(txtNotes);
    
//    tabPwd.setContent(menuBar);   
//    tabPwd.setContent(grid);
    
    tabPwd_vbox.getChildren().addAll(menuBar, grid);
    tabPwd.setContent(tabPwd_vbox);
    
    tabPane.getTabs().add(tabPwd);
     
    Tab tabAddressBox = new Tab();
    tabAddressBox.setText ("Address Book");
    Button tab2_button = new Button("Button@Tab 2");
    tabAddressBox.setContent (tab2_button);
    tabPane.getTabs().add(tabAddressBox);

    
    Tab tabHtmlBuilder = new Tab();
    tabHtmlBuilder.setText ("Html Builder");
    Button tab3_button = new Button("Button@Tab 3");
    tabHtmlBuilder.setContent (tab3_button);
    tabPane.getTabs().add(tabHtmlBuilder);
    

    borderPane.setCenter(tabPane);
    borderPane.prefHeightProperty().bind(scene.heightProperty());
    borderPane.prefWidthProperty().bind(scene.widthProperty());

    root.getStylesheets().add(Main.class.getResource("Format.css").toExternalForm());
    root.getChildren().add(tabPwd_vbox);
    root.getChildren().add(tabPane);

    primaryStage.setScene (scene);
    primaryStage.show ();

}

    public void fillComboBox(){
         
        try {     
            String query = "SELECT Item FROM Items order by Item;";
            ps = conn.prepareStatement(query);
            res = ps.executeQuery();

            while (res.next()) {
                items.add(res.getString("Item"));
            }
            ps.close();
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
     

    
//     private void comboBoxSubjectItemStateChanged() {
//        try {
//            bindFieldsToComboBox();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
     
//     public void bindFieldsToComboBox() throws ClassNotFoundException {
//        try {
//            String s = comboBox.getSelectedItem().toString();
//            String query = "select * from Items where Item ='" + s + "'";
//            ps = conn.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                txtItem.setText(rs.getString("Item"));
//                txtLink.setText(rs.getString("WebLink"));
//                txtUserName.setText(rs.getString("UserName"));
//                txtPassword.setText(rs.getString("Passwd"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
     
     
/**
 * @param args the command line arguments
 */
public static void main(String[] args) {
        launch(args);  
        
        
    }

}
