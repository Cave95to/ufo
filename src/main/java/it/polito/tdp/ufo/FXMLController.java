package it.polito.tdp.ufo;
import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.ufo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class FXMLController {

	private Model m;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxForma;

    @FXML
    private Label txtMessaggio;

    @FXML
    void handleConta(ActionEvent event) {
    	
    	String forma = boxForma.getValue();
    	int count = m.getCountByForma(forma);
    	txtMessaggio.setText("Gli UFO della forma "+forma+" sono: "+count);

    }
    
    public void setModel (Model model) {
    	this.m = model;
    	boxForma.getItems().addAll(this.m.getFormeUFO()) ;
    }

    @FXML
    void initialize() {
        assert boxForma != null : "fx:id=\"boxForma\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMessaggio != null : "fx:id=\"txtMessaggio\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}