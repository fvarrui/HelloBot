package fvarrui.telegram.bot;

import java.time.LocalDateTime;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelloHandler extends TelegramLongPollingBot {
	
	private String token; 
	private String username;
	
	public HelloHandler() {
		super();
		
		try {
			// read properties from bot.properties file if it exists (development)
			ResourceBundle bundle = ResourceBundle.getBundle("bot");
			token = bundle.getString("token");
			username = bundle.getString("username");
		} catch (MissingResourceException e) {
			// if properties file not found, get properties from environment (production)
			token = System.getenv("token");
			username = System.getenv("username");
		}
		
		System.out.println("HelloBot running ... ");
		System.out.println("* username : " + username);
		System.out.println("* token    : " + token);
	}

	@Override
	public String getBotUsername() {
		return username;
	}

	@Override
	public String getBotToken() {
		return token;
	}

	@Override
	public void onUpdateReceived(Update update) {		
		
		Long chatId = update.getMessage().getChatId();
		String command = update.getMessage().getText();
		
		System.out.println(update.getMessage());
		
		if (command == null) return;
		
		switch(command) {
		case "/hello":
			sendMessage(chatId, "Hi! It's " + LocalDateTime.now());
			break;
		case "/hello@Hello20190828Bot":
			sendMessage(chatId, "Hi everybody people in da house! It's " + LocalDateTime.now());
			break;
		case "/byebye":
		case "/byebye@Hello20190828Bot":
			sendMessage(chatId, "Ciao bambinos!");
			System.exit(0);
		}
		
	}

	private void sendMessage(Long chatId, String text) {
	    try {
	        SendMessage echoMessage = new SendMessage();
	        echoMessage.setChatId(chatId);
	        echoMessage.setText(text);
	        execute(echoMessage);
	    } catch (TelegramApiException e) {
	    	e.printStackTrace();
	    }
	}

}
