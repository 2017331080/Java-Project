import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.text.Text;

import java.util.Map;

public class Game extends GameApplication {
    private Entity background,basket;
    private final int BASKET_SPEED = 2;
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Egg & Basket");
        settings.setWidth(1005);
        settings.setHeight(550);
    }
    @Override
    protected void initGame() {
        background = FXGL.entityBuilder()
                .at(0,0)
                .view("background.png")
                .buildAndAttach();
        basket = FXGL.entityBuilder()
                .at(500,90)
                .view("basket.png")
                .with("velocity", new Point2D(BASKET_SPEED, 0))
                .buildAndAttach();
//        basket.translateX(BASKET_SPEED);
    }
    @Override
    protected void onUpdate(double tpf) {
        Point2D velocity = basket.getObject("velocity");
        basket.translate(velocity);
        if (basket.getRightX() == background.getRightX() || basket.getX() == background.getX()) {
            basket.setProperty("velocity",new Point2D(-BASKET_SPEED,0));
        }

    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("Points", 0);
    }

    //for displaying additional elements like score etc.
    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setTranslateX(905);
        textPixels.setTranslateY(60);
        textPixels.setStyle("-fx-font: 45 arial;");

        textPixels.textProperty().bind(FXGL.getGameState().intProperty("Points").asString());

        FXGL.getGameScene().addUINode(textPixels);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
