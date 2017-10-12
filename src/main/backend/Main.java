
package backend;

import application.SettingsController;
import javafx.application.Application;

class Main {
	
	public static void main(String[] args) {
		System.out.println("PROGRAM STARTED");
		
		// Вывожу окно с настройками игры
		SettingsController settings = new SettingsController();
		settings.showWindow(args);
		
		// Старт окна с игрой, передача параметров
		//MainWindowController mw = new MainWindowController(settings);
		//mw.showWindow(args);
		
		System.out.println("PROGRAM FINISHED");
	}
}
