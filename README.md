# tweettelegrambot
The Bot is implemented using SparkJava framework, Retrofit, Twitter4j and MYSQL.

WORKING :

When a user opens the bot in telegram and presses get started, the user information is recieved by the server (Written in SparkJava) and the user chat_id is added to the database. Parallely code written using Twitter4j is used to track tweets from the given twitter users (Twitter Streaming API) and when the users tweet, the tweet is sent to all the telegram bot users using Retrofit.
