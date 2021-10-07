package io.github.thebusybiscuit.slimefun4.core.commands.subcommands;

import io.github.thebusybiscuit.slimefun4.core.commands.SlimefunCommand;
import io.github.thebusybiscuit.slimefun4.core.commands.SubCommand;
import io.github.thebusybiscuit.slimefun4.core.debug.Debug;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

/**
 * The debug command will allow server owners to get information for us developers.
 * We can put debug messages in the code and they can trigger it for us to see what exactly is going on.
 */
public class DebugCommand extends SubCommand {

    protected DebugCommand(Slimefun plugin, SlimefunCommand cmd) {
        super(plugin, cmd, "debug", true);
    }

    @Override
    protected @Nonnull String getDescription() {
        return "commands.debug.description";
    }

    @Override
    public void onExecute(@Nonnull CommandSender sender, @Nonnull String[] args) {
        if (!sender.hasPermission("slimefun.command.debug")) {
            Slimefun.getLocalization().sendMessage(sender, "messages.no-permission", true);
            return;
        }

        if (args.length < 2) {
            Slimefun.getLocalization().sendMessage(sender, "messages.usage", true,
                msg -> msg.replace("%usage%", "/sf debug <test>"));
            return;
        }

        String test = args[1];

        if (test.equalsIgnoreCase("disable") || test.equalsIgnoreCase("off")) {
            Debug.setTestMode(null);
            Slimefun.getLocalization().sendMessage(sender, "commands.debug.disabled");
        } else {
            Debug.setTestMode(test);
            Slimefun.getLocalization().sendMessage(sender, "commands.debug.running",
                msg -> msg.replace("%test%", test));
        }
    }
}
