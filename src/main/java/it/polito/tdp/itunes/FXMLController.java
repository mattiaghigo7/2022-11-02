/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.itunes.model.Genre;
import it.polito.tdp.itunes.model.Model;
import it.polito.tdp.itunes.model.Track;
import it.polito.tdp.itunes.model.ValoriAmmessi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPlaylist"
    private Button btnPlaylist; // Value injected by FXMLLoader

    @FXML // fx:id="cmbGenere"
    private ComboBox<Genre> cmbGenere; // Value injected by FXMLLoader

    @FXML // fx:id="txtDTOT"
    private TextField txtDTOT; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="txtMin"
    private TextField txtMin; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaPlaylist(ActionEvent event) {

    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	Genre g = this.cmbGenere.getValue();
    	String input1 = this.txtMin.getText();
    	String input2 = this.txtMax.getText();
    	if(g==null) {
    		this.txtResult.setText("Selezionare un genere.");
    		return;
    	}
    	if(input1=="" || input2=="") {
    		this.txtResult.setText("Inserire i valori richiesti.");
    		return;
    	}
    	try {
    		Integer min = Integer.parseInt(input1)*1000;
    		Integer max = Integer.parseInt(input2)*1000;
    		ValoriAmmessi v = model.getValori(g);
    		if(min>=v.getMin() && max<=v.getMax()) {
    			model.creaGrafo(g, min, max);
    			this.txtResult.setText("Grafo creato!\n");
    			this.txtResult.appendText("# Vertici: "+model.getVSize()+"\n");   			
    			this.txtResult.appendText("# Archi: "+model.getASize()+"\n\n");
    			List<List<Track>> connesse = model.calcolaConnessa();
    			for(List<Track> l : connesse) {
    				this.txtResult.appendText("Componente con "+l.size()+" vertici, inseriti in "+model.getP(l.get(0))+" playlist\n");
    			}
    		}
    		else {
    			this.txtResult.setText("Restringere il campo.");
    			return;
    		}
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("I valori inseriti non sono validi.");
    		return;
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPlaylist != null : "fx:id=\"btnPlaylist\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbGenere != null : "fx:id=\"cmbGenere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDTOT != null : "fx:id=\"txtDTOT\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMin != null : "fx:id=\"txtMin\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbGenere.getItems().addAll(model.getGenres());
    }

}
