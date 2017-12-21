
package backend;

import application.SettingsWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;

class Main {
	
	public static void main(String[] args) {
		
		// Вывожу окно с настройками игры
		SettingsWindow settings = new SettingsWindow();
		settings.showWindow(args);
		
		// Создаю объект игры
		//GoGame game = new GoGame(settings);
		
		// Старт окна с игрой, передача параметров
		/*
		MainWindowController mw = new MainWindowController();
		Group root = new Group();
		Scene scene = new Scene(root,300,300);
		stage.setScene(scene);
		stage.show();
		mw.showWindow(args);
		*/
		System.out.println("PROGRAM FINISHED");
	}
}
