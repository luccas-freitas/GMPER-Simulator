package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class SampleController extends AnchorPane implements Initializable {
	@FXML	private GridPane gridPane;
	@FXML	private MenuBar menuBar;
	@FXML	private Menu menuMenu;
	@FXML	private MenuItem menuItemNovo;
	@FXML	private Menu menuAjuda;
	@FXML	private MenuItem menuItemSobre;
	@FXML	private ListView<String> listView;
	@FXML	private Button button;
	@FXML	private Label label00, label01, label02, label03, label04, label05,label06, label07, label08, label09,
						  label10, label11, label12, label13, label14, label15,label16, label17, label18, label19,
						  label20, label21, label22, label23, label24, label25,label26, label27, label28, label29,
						  label30, label31, label32, label33, label34, label35,label36, label37, label38, label39,
						  label40, label41, label42, label43, label44, label45,label46, label47, label48, label49,
						  label50, label51, label52, label53, label54, label55,label56, label57, label58, label59,
						  label60, label61, label62, label63, label64, label65,label66, label67, label68, label69,
						  label70, label71, label72, label73, label74, label75,label76, label77, label78, label79,
						  label80, label81, label82, label83, label84, label85,label86, label87, label88, label89,
						  label90, label91, label92, label93, label94, label95,label96, label97, label98, label99;				
	@FXML	private TextField textField;
	@FXML	private ScrollPane scrollPane;
			private Label[] labelGrid = new Label[100];
			final private ObservableList<Processo> listProcesso = FXCollections.observableArrayList(
				new Processo(1, "Processo 1", 10, "rgba(51, 51, 255, .5)"),
				new Processo(2, "Processo 2", 50, "rgba(153, 153, 102, .5)"),
				new Processo(3, "Processo 3", 15, "rgba(255, 255, 102, .5)"),
				new Processo(4, "Processo 4", 30, "rgba(255, 0, 0, .5)"),
				new Processo(5, "Processo 5", 25, "rgba(255, 102, 0, .5)"),
				new Processo(6, "Processo 6", 60, "rgba(78, 102, 80, .5)"),
				new Processo(7, "Processo 7", 45, "rgba(21, 18, 96, .5)"),
				new Processo(8, "Processo 8", 10, "rgba(51, 204, 51, .5)"),
				new Processo(9, "Processo 9", 20, "rgba(153, 45, 0, .5)"),
				new Processo(10, "Processo 10", 80, "rgba(102, 0, 102, .5)"),
				new Processo(11, "Processo 11", 45, "rgba(53, 248, 255, .5)"),
				new Processo(12, "Processo 12", 25, "rgba(244, 166, 188, .5)"),
				new Processo(13, "Processo 13", 15, "rgba(229, 34, 229, .5)"),
				new Processo(14, "Processo 14", 20, "rgba(66, 134, 244, .5)"),
				new Processo(15, "Processo 15", 50, "rgba(97, 122, 24, .5)")
			);
			
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initBindings();
		initListView();
		colorGridView();
	}

	private void initListView() {
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < listProcesso.size(); i++)
			list.add(listProcesso.get(i).getNome() + " - " + Integer.toString(listProcesso.get(i).getSize()) + "Kb");
		listView.setItems(FXCollections.observableArrayList(list));
		scrollPane.setContent(textField);
	}
	
	public void initButton() {
		button.setOnAction(e -> {
			if(!listView.getItems().isEmpty()) {
				String selected = listView.getSelectionModel().getSelectedItem();
				String index[] = selected.split(" ");
				alocatteProcess(index[1]);
				listView.getItems().remove(listView.getSelectionModel().getSelectedIndex());
			}
		});
	}
	
	private void colorGridView() {
		ArrayList<Processo> shuffledList = new ArrayList<Processo>();
		shuffledList.addAll(listProcesso);
		Collections.shuffle(shuffledList);
		int status = 0;
		for(int i = 0; i < shuffledList.size(); i++)
			for(int aux = 0; aux < shuffledList.get(i).getSize() / 5; aux++) {
				labelGrid[status].setStyle("-fx-background-color:" + shuffledList.get(i).getColor() + ";");
				labelGrid[status].setText(Integer.toString(shuffledList.get(i).getSize() / 5));
				status++;
			}
	}
	
	private void alocatteProcess(String index) {
		int size = listProcesso.get(Integer.parseInt(index)-1).getSize() / 5;
		for(int i = 0; i < labelGrid.length; i++) {
			int aux = Integer.parseInt(labelGrid[i].getText());
			if(aux >= size) {
				for(int j = 0; j < size; j++)
					labelGrid[i+j].setStyle("-fx-background-color: black;");
				for(int j = 0; j < aux; j++)
					labelGrid[i+j].setText("0");
				break;
			}
			if(i == 99 && aux < size) {
				System.out.println("Fila de Espera");
				textField.appendText(listProcesso.get(Integer.parseInt(index)-1).getNome() + " ; ");
				break;
			}
		}
	}
	
	public void clear() {
		initListView();
		colorGridView();
	}
	
	private void initBindings() {
		for(int i = 0; i < 100; i++)
			labelGrid[i] = new Label();

		Bindings.bindBidirectional(labelGrid[0].styleProperty(), label00.styleProperty());
		Bindings.bindBidirectional(labelGrid[1].styleProperty(), label01.styleProperty());
		Bindings.bindBidirectional(labelGrid[2].styleProperty(), label02.styleProperty());
		Bindings.bindBidirectional(labelGrid[3].styleProperty(), label03.styleProperty());
		Bindings.bindBidirectional(labelGrid[4].styleProperty(), label04.styleProperty());
		Bindings.bindBidirectional(labelGrid[5].styleProperty(), label05.styleProperty());
		Bindings.bindBidirectional(labelGrid[6].styleProperty(), label06.styleProperty());
		Bindings.bindBidirectional(labelGrid[7].styleProperty(), label07.styleProperty());
		Bindings.bindBidirectional(labelGrid[8].styleProperty(), label08.styleProperty());
		Bindings.bindBidirectional(labelGrid[9].styleProperty(), label09.styleProperty());
		
		Bindings.bindBidirectional(labelGrid[10].styleProperty(), label10.styleProperty());
		Bindings.bindBidirectional(labelGrid[11].styleProperty(), label11.styleProperty());
		Bindings.bindBidirectional(labelGrid[12].styleProperty(), label12.styleProperty());
		Bindings.bindBidirectional(labelGrid[13].styleProperty(), label13.styleProperty());
		Bindings.bindBidirectional(labelGrid[14].styleProperty(), label14.styleProperty());
		Bindings.bindBidirectional(labelGrid[15].styleProperty(), label15.styleProperty());
		Bindings.bindBidirectional(labelGrid[16].styleProperty(), label16.styleProperty());
		Bindings.bindBidirectional(labelGrid[17].styleProperty(), label17.styleProperty());
		Bindings.bindBidirectional(labelGrid[18].styleProperty(), label18.styleProperty());
		Bindings.bindBidirectional(labelGrid[19].styleProperty(), label19.styleProperty());
		
		Bindings.bindBidirectional(labelGrid[20].styleProperty(), label20.styleProperty());
		Bindings.bindBidirectional(labelGrid[21].styleProperty(), label21.styleProperty());
		Bindings.bindBidirectional(labelGrid[22].styleProperty(), label22.styleProperty());
		Bindings.bindBidirectional(labelGrid[23].styleProperty(), label23.styleProperty());
		Bindings.bindBidirectional(labelGrid[24].styleProperty(), label24.styleProperty());
		Bindings.bindBidirectional(labelGrid[25].styleProperty(), label25.styleProperty());
		Bindings.bindBidirectional(labelGrid[26].styleProperty(), label26.styleProperty());
		Bindings.bindBidirectional(labelGrid[27].styleProperty(), label27.styleProperty());
		Bindings.bindBidirectional(labelGrid[28].styleProperty(), label28.styleProperty());
		Bindings.bindBidirectional(labelGrid[29].styleProperty(), label29.styleProperty());
		
		Bindings.bindBidirectional(labelGrid[30].styleProperty(), label30.styleProperty());
		Bindings.bindBidirectional(labelGrid[31].styleProperty(), label31.styleProperty());
		Bindings.bindBidirectional(labelGrid[32].styleProperty(), label32.styleProperty());
		Bindings.bindBidirectional(labelGrid[33].styleProperty(), label33.styleProperty());
		Bindings.bindBidirectional(labelGrid[34].styleProperty(), label34.styleProperty());
		Bindings.bindBidirectional(labelGrid[35].styleProperty(), label35.styleProperty());
		Bindings.bindBidirectional(labelGrid[36].styleProperty(), label36.styleProperty());
		Bindings.bindBidirectional(labelGrid[37].styleProperty(), label37.styleProperty());
		Bindings.bindBidirectional(labelGrid[38].styleProperty(), label38.styleProperty());
		Bindings.bindBidirectional(labelGrid[39].styleProperty(), label39.styleProperty());

		Bindings.bindBidirectional(labelGrid[40].styleProperty(), label40.styleProperty());
		Bindings.bindBidirectional(labelGrid[41].styleProperty(), label41.styleProperty());
		Bindings.bindBidirectional(labelGrid[42].styleProperty(), label42.styleProperty());
		Bindings.bindBidirectional(labelGrid[43].styleProperty(), label43.styleProperty());
		Bindings.bindBidirectional(labelGrid[44].styleProperty(), label44.styleProperty());
		Bindings.bindBidirectional(labelGrid[45].styleProperty(), label45.styleProperty());
		Bindings.bindBidirectional(labelGrid[46].styleProperty(), label46.styleProperty());
		Bindings.bindBidirectional(labelGrid[47].styleProperty(), label47.styleProperty());
		Bindings.bindBidirectional(labelGrid[48].styleProperty(), label48.styleProperty());
		Bindings.bindBidirectional(labelGrid[49].styleProperty(), label49.styleProperty());
		
		Bindings.bindBidirectional(labelGrid[50].styleProperty(), label50.styleProperty());
		Bindings.bindBidirectional(labelGrid[51].styleProperty(), label51.styleProperty());
		Bindings.bindBidirectional(labelGrid[52].styleProperty(), label52.styleProperty());
		Bindings.bindBidirectional(labelGrid[53].styleProperty(), label53.styleProperty());
		Bindings.bindBidirectional(labelGrid[54].styleProperty(), label54.styleProperty());
		Bindings.bindBidirectional(labelGrid[55].styleProperty(), label55.styleProperty());
		Bindings.bindBidirectional(labelGrid[56].styleProperty(), label56.styleProperty());
		Bindings.bindBidirectional(labelGrid[57].styleProperty(), label57.styleProperty());
		Bindings.bindBidirectional(labelGrid[58].styleProperty(), label58.styleProperty());
		Bindings.bindBidirectional(labelGrid[59].styleProperty(), label59.styleProperty());
		
		Bindings.bindBidirectional(labelGrid[60].styleProperty(), label60.styleProperty());
		Bindings.bindBidirectional(labelGrid[61].styleProperty(), label61.styleProperty());
		Bindings.bindBidirectional(labelGrid[62].styleProperty(), label62.styleProperty());
		Bindings.bindBidirectional(labelGrid[63].styleProperty(), label63.styleProperty());
		Bindings.bindBidirectional(labelGrid[64].styleProperty(), label64.styleProperty());
		Bindings.bindBidirectional(labelGrid[65].styleProperty(), label65.styleProperty());
		Bindings.bindBidirectional(labelGrid[66].styleProperty(), label66.styleProperty());
		Bindings.bindBidirectional(labelGrid[67].styleProperty(), label67.styleProperty());
		Bindings.bindBidirectional(labelGrid[68].styleProperty(), label68.styleProperty());
		Bindings.bindBidirectional(labelGrid[69].styleProperty(), label69.styleProperty());
		
		Bindings.bindBidirectional(labelGrid[70].styleProperty(), label70.styleProperty());
		Bindings.bindBidirectional(labelGrid[71].styleProperty(), label71.styleProperty());
		Bindings.bindBidirectional(labelGrid[72].styleProperty(), label72.styleProperty());
		Bindings.bindBidirectional(labelGrid[73].styleProperty(), label73.styleProperty());
		Bindings.bindBidirectional(labelGrid[74].styleProperty(), label74.styleProperty());
		Bindings.bindBidirectional(labelGrid[75].styleProperty(), label75.styleProperty());
		Bindings.bindBidirectional(labelGrid[76].styleProperty(), label76.styleProperty());
		Bindings.bindBidirectional(labelGrid[77].styleProperty(), label77.styleProperty());
		Bindings.bindBidirectional(labelGrid[78].styleProperty(), label78.styleProperty());
		Bindings.bindBidirectional(labelGrid[79].styleProperty(), label79.styleProperty());
		
		Bindings.bindBidirectional(labelGrid[80].styleProperty(), label80.styleProperty());
		Bindings.bindBidirectional(labelGrid[81].styleProperty(), label81.styleProperty());
		Bindings.bindBidirectional(labelGrid[82].styleProperty(), label82.styleProperty());
		Bindings.bindBidirectional(labelGrid[83].styleProperty(), label83.styleProperty());
		Bindings.bindBidirectional(labelGrid[84].styleProperty(), label84.styleProperty());
		Bindings.bindBidirectional(labelGrid[85].styleProperty(), label85.styleProperty());
		Bindings.bindBidirectional(labelGrid[86].styleProperty(), label86.styleProperty());
		Bindings.bindBidirectional(labelGrid[87].styleProperty(), label87.styleProperty());
		Bindings.bindBidirectional(labelGrid[88].styleProperty(), label88.styleProperty());
		Bindings.bindBidirectional(labelGrid[89].styleProperty(), label89.styleProperty());
		
		Bindings.bindBidirectional(labelGrid[90].styleProperty(), label90.styleProperty());
		Bindings.bindBidirectional(labelGrid[91].styleProperty(), label91.styleProperty());
		Bindings.bindBidirectional(labelGrid[92].styleProperty(), label92.styleProperty());
		Bindings.bindBidirectional(labelGrid[93].styleProperty(), label93.styleProperty());
		Bindings.bindBidirectional(labelGrid[94].styleProperty(), label94.styleProperty());
		Bindings.bindBidirectional(labelGrid[95].styleProperty(), label95.styleProperty());
		Bindings.bindBidirectional(labelGrid[96].styleProperty(), label96.styleProperty());
		Bindings.bindBidirectional(labelGrid[97].styleProperty(), label97.styleProperty());
		Bindings.bindBidirectional(labelGrid[98].styleProperty(), label98.styleProperty());
		Bindings.bindBidirectional(labelGrid[99].styleProperty(), label99.styleProperty());

		Bindings.bindBidirectional(labelGrid[0].textProperty(), label00.textProperty());
		Bindings.bindBidirectional(labelGrid[1].textProperty(), label01.textProperty());
		Bindings.bindBidirectional(labelGrid[2].textProperty(), label02.textProperty());
		Bindings.bindBidirectional(labelGrid[3].textProperty(), label03.textProperty());
		Bindings.bindBidirectional(labelGrid[4].textProperty(), label04.textProperty());
		Bindings.bindBidirectional(labelGrid[5].textProperty(), label05.textProperty());
		Bindings.bindBidirectional(labelGrid[6].textProperty(), label06.textProperty());
		Bindings.bindBidirectional(labelGrid[7].textProperty(), label07.textProperty());
		Bindings.bindBidirectional(labelGrid[8].textProperty(), label08.textProperty());
		Bindings.bindBidirectional(labelGrid[9].textProperty(), label09.textProperty());
		
		Bindings.bindBidirectional(labelGrid[10].textProperty(), label10.textProperty());
		Bindings.bindBidirectional(labelGrid[11].textProperty(), label11.textProperty());
		Bindings.bindBidirectional(labelGrid[12].textProperty(), label12.textProperty());
		Bindings.bindBidirectional(labelGrid[13].textProperty(), label13.textProperty());
		Bindings.bindBidirectional(labelGrid[14].textProperty(), label14.textProperty());
		Bindings.bindBidirectional(labelGrid[15].textProperty(), label15.textProperty());
		Bindings.bindBidirectional(labelGrid[16].textProperty(), label16.textProperty());
		Bindings.bindBidirectional(labelGrid[17].textProperty(), label17.textProperty());
		Bindings.bindBidirectional(labelGrid[18].textProperty(), label18.textProperty());
		Bindings.bindBidirectional(labelGrid[19].textProperty(), label19.textProperty());
		
		Bindings.bindBidirectional(labelGrid[20].textProperty(), label20.textProperty());
		Bindings.bindBidirectional(labelGrid[21].textProperty(), label21.textProperty());
		Bindings.bindBidirectional(labelGrid[22].textProperty(), label22.textProperty());
		Bindings.bindBidirectional(labelGrid[23].textProperty(), label23.textProperty());
		Bindings.bindBidirectional(labelGrid[24].textProperty(), label24.textProperty());
		Bindings.bindBidirectional(labelGrid[25].textProperty(), label25.textProperty());
		Bindings.bindBidirectional(labelGrid[26].textProperty(), label26.textProperty());
		Bindings.bindBidirectional(labelGrid[27].textProperty(), label27.textProperty());
		Bindings.bindBidirectional(labelGrid[28].textProperty(), label28.textProperty());
		Bindings.bindBidirectional(labelGrid[29].textProperty(), label29.textProperty());
		
		Bindings.bindBidirectional(labelGrid[30].textProperty(), label30.textProperty());
		Bindings.bindBidirectional(labelGrid[31].textProperty(), label31.textProperty());
		Bindings.bindBidirectional(labelGrid[32].textProperty(), label32.textProperty());
		Bindings.bindBidirectional(labelGrid[33].textProperty(), label33.textProperty());
		Bindings.bindBidirectional(labelGrid[34].textProperty(), label34.textProperty());
		Bindings.bindBidirectional(labelGrid[35].textProperty(), label35.textProperty());
		Bindings.bindBidirectional(labelGrid[36].textProperty(), label36.textProperty());
		Bindings.bindBidirectional(labelGrid[37].textProperty(), label37.textProperty());
		Bindings.bindBidirectional(labelGrid[38].textProperty(), label38.textProperty());
		Bindings.bindBidirectional(labelGrid[39].textProperty(), label39.textProperty());

		Bindings.bindBidirectional(labelGrid[40].textProperty(), label40.textProperty());
		Bindings.bindBidirectional(labelGrid[41].textProperty(), label41.textProperty());
		Bindings.bindBidirectional(labelGrid[42].textProperty(), label42.textProperty());
		Bindings.bindBidirectional(labelGrid[43].textProperty(), label43.textProperty());
		Bindings.bindBidirectional(labelGrid[44].textProperty(), label44.textProperty());
		Bindings.bindBidirectional(labelGrid[45].textProperty(), label45.textProperty());
		Bindings.bindBidirectional(labelGrid[46].textProperty(), label46.textProperty());
		Bindings.bindBidirectional(labelGrid[47].textProperty(), label47.textProperty());
		Bindings.bindBidirectional(labelGrid[48].textProperty(), label48.textProperty());
		Bindings.bindBidirectional(labelGrid[49].textProperty(), label49.textProperty());
		
		Bindings.bindBidirectional(labelGrid[50].textProperty(), label50.textProperty());
		Bindings.bindBidirectional(labelGrid[51].textProperty(), label51.textProperty());
		Bindings.bindBidirectional(labelGrid[52].textProperty(), label52.textProperty());
		Bindings.bindBidirectional(labelGrid[53].textProperty(), label53.textProperty());
		Bindings.bindBidirectional(labelGrid[54].textProperty(), label54.textProperty());
		Bindings.bindBidirectional(labelGrid[55].textProperty(), label55.textProperty());
		Bindings.bindBidirectional(labelGrid[56].textProperty(), label56.textProperty());
		Bindings.bindBidirectional(labelGrid[57].textProperty(), label57.textProperty());
		Bindings.bindBidirectional(labelGrid[58].textProperty(), label58.textProperty());
		Bindings.bindBidirectional(labelGrid[59].textProperty(), label59.textProperty());
		
		Bindings.bindBidirectional(labelGrid[60].textProperty(), label60.textProperty());
		Bindings.bindBidirectional(labelGrid[61].textProperty(), label61.textProperty());
		Bindings.bindBidirectional(labelGrid[62].textProperty(), label62.textProperty());
		Bindings.bindBidirectional(labelGrid[63].textProperty(), label63.textProperty());
		Bindings.bindBidirectional(labelGrid[64].textProperty(), label64.textProperty());
		Bindings.bindBidirectional(labelGrid[65].textProperty(), label65.textProperty());
		Bindings.bindBidirectional(labelGrid[66].textProperty(), label66.textProperty());
		Bindings.bindBidirectional(labelGrid[67].textProperty(), label67.textProperty());
		Bindings.bindBidirectional(labelGrid[68].textProperty(), label68.textProperty());
		Bindings.bindBidirectional(labelGrid[69].textProperty(), label69.textProperty());
		
		Bindings.bindBidirectional(labelGrid[70].textProperty(), label70.textProperty());
		Bindings.bindBidirectional(labelGrid[71].textProperty(), label71.textProperty());
		Bindings.bindBidirectional(labelGrid[72].textProperty(), label72.textProperty());
		Bindings.bindBidirectional(labelGrid[73].textProperty(), label73.textProperty());
		Bindings.bindBidirectional(labelGrid[74].textProperty(), label74.textProperty());
		Bindings.bindBidirectional(labelGrid[75].textProperty(), label75.textProperty());
		Bindings.bindBidirectional(labelGrid[76].textProperty(), label76.textProperty());
		Bindings.bindBidirectional(labelGrid[77].textProperty(), label77.textProperty());
		Bindings.bindBidirectional(labelGrid[78].textProperty(), label78.textProperty());
		Bindings.bindBidirectional(labelGrid[79].textProperty(), label79.textProperty());
		
		Bindings.bindBidirectional(labelGrid[80].textProperty(), label80.textProperty());
		Bindings.bindBidirectional(labelGrid[81].textProperty(), label81.textProperty());
		Bindings.bindBidirectional(labelGrid[82].textProperty(), label82.textProperty());
		Bindings.bindBidirectional(labelGrid[83].textProperty(), label83.textProperty());
		Bindings.bindBidirectional(labelGrid[84].textProperty(), label84.textProperty());
		Bindings.bindBidirectional(labelGrid[85].textProperty(), label85.textProperty());
		Bindings.bindBidirectional(labelGrid[86].textProperty(), label86.textProperty());
		Bindings.bindBidirectional(labelGrid[87].textProperty(), label87.textProperty());
		Bindings.bindBidirectional(labelGrid[88].textProperty(), label88.textProperty());
		Bindings.bindBidirectional(labelGrid[89].textProperty(), label89.textProperty());
		
		Bindings.bindBidirectional(labelGrid[90].textProperty(), label90.textProperty());
		Bindings.bindBidirectional(labelGrid[91].textProperty(), label91.textProperty());
		Bindings.bindBidirectional(labelGrid[92].textProperty(), label92.textProperty());
		Bindings.bindBidirectional(labelGrid[93].textProperty(), label93.textProperty());
		Bindings.bindBidirectional(labelGrid[94].textProperty(), label94.textProperty());
		Bindings.bindBidirectional(labelGrid[95].textProperty(), label95.textProperty());
		Bindings.bindBidirectional(labelGrid[96].textProperty(), label96.textProperty());
		Bindings.bindBidirectional(labelGrid[97].textProperty(), label97.textProperty());
		Bindings.bindBidirectional(labelGrid[98].textProperty(), label98.textProperty());
		Bindings.bindBidirectional(labelGrid[99].textProperty(), label99.textProperty());
	}

	public void showHelp() {
		Alert alert = new Alert(AlertType.INFORMATION, 
		"Simulador de Gerenciamento de Memória Particionada Estática Relocável.\n"
		+ "Cada célula da memória representa 5Kb, somando o total de 500Kb.", ButtonType.OK);
		alert.setHeaderText("Simulador GMPER");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.show();
	}
	
	static boolean showMessage(String title, String message){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.headerTextProperty().set(null);
		alert.titleProperty().set(title);
		alert.contentTextProperty().set(message);
		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.YES);
		
		Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
		noButton.setDefaultButton(true);

		Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.YES);
		yesButton.setDefaultButton(false);
		
	    Optional<ButtonType> result = alert.showAndWait();
	    if(result.get() == ButtonType.CANCEL){
	    	return false;
	    }else{
	    	return true;
	    }
	}
}