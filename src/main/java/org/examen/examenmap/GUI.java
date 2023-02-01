package org.examen.examenmap;

import org.examen.examenmap.controller.GuiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.examen.examenmap.service.Service;

import java.io.IOException;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        GuiController.setSrv(createNetwork());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);

//        GuiController.setCurrentStage(stage);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private Service createNetwork() {
        return new Service(
//                new UserService(new UserDatabaseRepo(
//                        DatabaseTables.users.toString(),
//                        ApplicationContext.DATABASE_URL,
//                        ApplicationContext.DB_USERNAME,
//                        ApplicationContext.DB_PASSWORD,
//                        new UserValidator()
//                )),
//                new FriendshipService(new FriendshipDatabaseRepo(
//                        DatabaseTables.friendships.toString(),
//                        ApplicationContext.DATABASE_URL,
//                        ApplicationContext.DB_USERNAME,
//                        ApplicationContext.DB_PASSWORD,
//                        new FriendshipValidator()
//                )),
//                new TextMessageService(new TextMessageDatabaseRepo(
//                        DatabaseTables.messages.toString(),
//                        ApplicationContext.DATABASE_URL,
//                        ApplicationContext.DB_USERNAME,
//                        ApplicationContext.DB_PASSWORD
//                ))
        );
    }
}