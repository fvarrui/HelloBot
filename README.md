# HelloBot

Telegram Bot example in Java language that says `Hi!` if you said `/hello`.

![HelloBot in action](docs/image01.png)

## Requirements

- Java 1.8
- Maven

## Compile 

```bash
mvn compile
```

## Run

```bash
mvn exec:java -Dexec.mainClass="fvarrui.telegram.bot.Main" -Dtoken=<token> -Dbotname=<botname>
```

## Create your bot 

Follow this [link](https://telegram.me/botfather) to register your own bot, and use the given `token` and `botname` in the previous command.
