#ifndef CMDHANDLER_HPP
#define CMDHANDLER_HPP

#include <dpp/dpp.h>
#include <iostream>

namespace ch
{

void load_commands(dpp::commandhandler* command_handler)
{

    command_handler->add_command(

        // name
        "test",
        
        // params
        { },

        // body
        [&](const std::string& command, const dpp::parameter_list_t& parameters, dpp::command_source src)
        {
            std::cout << "Hello from test command" << std::endl;
        },

        // description
        "test command"

        // guild could also go here but it is omitted for this
    );

    // follow the above format to add any new commands

    command_handler->register_commands();
}

} // namespace ch

#endif // CMDHANDLER_HPP