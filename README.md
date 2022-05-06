# HomeworkBot 2

HomeworkBot 2: A Discord bot that keeps track of your homework. HomeworkBot is a Discord bot that links your assigned homework to your Discord server.

# Building

## 1. Prerequisites
### Discord
- You need a Discord bot token to run the bot

### Dependencies
- D++

## 2. Configuration
- Copy `src/config.example.hpp` to `src/config.hpp`
- Adjust the values in the config accordingly.
    - Set `TOKEN` to the token of your Discord bot
    - `DEBUG_LOGGING` controls if there should be debug messages printed to the standard output

## 3. Compiling
- In the root of this repository, run `make` to build
- Ensure that your environment variable `LD_LIBRARY_PATH` is set to `/usr/local/lib/` or the correct library directory on your system
- Run `bot.out`

