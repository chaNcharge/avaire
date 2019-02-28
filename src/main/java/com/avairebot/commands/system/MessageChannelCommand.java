package com.avairebot.commands.system;

import com.avairebot.AvaIre;
import com.avairebot.commands.CommandMessage;
import com.avairebot.contracts.commands.SystemCommand;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.Collections;
import java.util.List;

public class MessageChannelCommand extends SystemCommand {

    public MessageChannelCommand(AvaIre avaire) {
        super(avaire);
}

    @Override
    public String getName() {
        return "Message Channel Command";
    }

    @Override
    public String getDescription() {
        return "Sends a message to the desired channel that the bot has access to.";
    }

    @Override
    public List<String> getUsageInstructions() {
        return Collections.singletonList(
            "`:command <channel ID> <message>` - Sends a message to the specified channel."
        );
    }

    @Override
    public List<String> getExampleUsage() {
        return Collections.singletonList(
            "`:command 357150537898262529 Hello there` - Sends 'Hello there' to channel 357150537898262529."
        );
    }

    @Override
    public List<String> getTriggers() {
        return Collections.singletonList("message-channel");
    }

    @Override
    public boolean onCommand(CommandMessage context, String[] args) {
        if (args.length == 0) {
            return sendErrorMessage(context, "errors.missingArgument", "channel ID");
        }

        if (args[0].indexOf(' ') > 0) {
            try {
                String channelID = args[0].split(" ")[0];
                String message = args[0].split(" ")[1];
                avaire.getShardManager().getTextChannelById(channelID).sendMessage(message).queue(); // TODO Error catching
            } catch (ArrayIndexOutOfBoundsException e) {
                return sendErrorMessage(context, context.i18n("")); // TODO Add to langs
            }
        }

        return sendErrorMessage(context, context.i18n("errors.missingArgument", "channel ID"));
    }
}
