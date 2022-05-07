#include <dpp/dpp.h>
#include <iostream>

#include "config.hpp"
#include "cmdhandler.hpp"

int main() {
    dpp::cluster bot(TOKEN);
 
    if (DEBUG_LOGGING) {
        bot.on_log(dpp::utility::cout_logger());
    }

    dpp::commandhandler command_handler(&bot);

    command_handler
    .add_prefix(".")
    .add_prefix("/");

    bot.on_ready([&bot, &command_handler](const dpp::ready_t& event) {
        std::cout << "Hello D++!" << std::endl;

        ch::load_commands(&command_handler);
    });
 
    bot.start(false);

    return 0;
}