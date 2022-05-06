#include <dpp/dpp.h>
#include <iostream>

#include "config.hpp"

int main() {
    dpp::cluster bot(TOKEN);
 
    if (DEBUG_LOGGING) {
        bot.on_log(dpp::utility::cout_logger());
    }
 
    bot.on_ready([&bot](const dpp::ready_t& event) {
        std::cout << "Hello D++!" << std::endl;
    });
 
    bot.start(false);

    return 0;
}