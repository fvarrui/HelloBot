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
	private String botname;
	
	public HelloHandler() {
		super();
		
		try {
			
			// read properties from bot.properties file if it exists (development)
			ResourceBundle bundle = ResourceBundle.getBundle("bot");
			token = bundle.getString("token");
			botname = bundle.getString("botname");
			
		} catch (MissingResourceException e) {
			
			// if properties file not found, get properties from environment (production)
			token = System.getProperty("token");
			botname = System.getProperty("botname");
			
		}
		
		System.out.println("HelloBot running ... ");
		System.out.println("* botname : " + botname);
		System.out.println("* token   : " + token);
	}

	@Override
	public String getBotUsername() {
		return botname;
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
		
		if (command.equals("/help") || command.equals("/start"))
			sendMessage(chatId, "HelloBot commmands:\n- /hello = say hi!\n- /byebye = shutdown hellobot (disabled)");
		else if (command.startsWith("/hello"))
			sendMessage(chatId, "Hi @" + update.getMessage().getFrom().getUserName() + "! It's " + LocalDateTime.now());
		else if (command.startsWith("/bye"))
			sendMessage(chatId, "Ciao @" + update.getMessage().getFrom().getUserName() + "!");
		else
			sendMessage(chatId, "What do you mean with '" + update.getMessage().getText() + "'?");
		
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
