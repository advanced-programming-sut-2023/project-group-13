package view.menus;

import controller.ControllerControllersG;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Enums.DataEnumFile;
import model.Map;
import model.Player;
import model.SaveAndLoadData;
import java.util.ArrayList;
import javafx.scene.control.Label;

public class NewGameMenu extends Application {
    private Label errorLabel;

    private ComboBox mapsCombo = new ComboBox<>();
    private TextField username = new TextField();
    private static ArrayList<Player> selectedPlayer = new ArrayList<>();
    private ArrayList<Player> allPlayers =  Player.players;
    private ArrayList<Map> maps = Map.getMaps();
    private ArrayList<String> mapNames = new ArrayList<>();


    @Override
    public void start(Stage stage) throws Exception {
        for (Map map :maps = SaveAndLoadData.LoadData(DataEnumFile.MAPS.getFileName(), DataEnumFile.MAPS.getDataType())) {
            mapNames.add(map.getMapName());
        }

        Pane pane = new FXMLLoader(LoginMenuG.class.getResource("/fxml/newGameMenu.fxml")).load();
        Image background = new Image(LoginMenuG.class.getResource("/png/mainmenu.jpg").toExternalForm());
        errorLabel =  new Label("error");
        username.setLayoutY(90);
        username.setLayoutX(150);
        mapsCombo.setLayoutY(150);
        mapsCombo.setLayoutX(500);
        errorLabel.setLayoutX(150);
        errorLabel.setLayoutY(180);
        mapsCombo.getItems().addAll(mapNames);
        stage.setTitle("LoginMenu!");
        BackgroundImage x = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(x));
        pane.getChildren().addAll(username , mapsCombo );
        pane.getChildren().add(errorLabel);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(pane, screenBounds.getWidth(), screenBounds.getHeight());

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(true);
        stage.show();

    }
    public void initialize() {

    }
    public void select(MouseEvent mouseEvent) {
        if (username.getText() == null) {
            errorLabel.setText("choose 2 player atleast");
        } else  {
            Player player = Player.getPlayerByUsername(username.getText());
            if (player == null) errorLabel.setText("player not found");
            else selectedPlayer.add(player);
        }
    }

    public void runGame(MouseEvent mouseEvent) {

    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenuG().start(ControllerControllersG.stage);
    }



}
