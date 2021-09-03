
package org.restcomm.ss7.management.console.impl;

import org.restcomm.ss7.management.console.CommandContext;
import org.restcomm.ss7.management.console.CommandHandlerWithHelp;
import org.restcomm.ss7.management.console.Tree;
import org.restcomm.ss7.management.console.Tree.Node;

/**
 * @author amit bhayani
 *
 */
public class TcapAnsiCommandHandler extends CommandHandlerWithHelp {

    static final Tree commandTree = new Tree("tcapansi");
    static {
        Node parent = commandTree.getTopNode();

        Node set = parent.addChild("set");
        set.addChild("dialogidletimeout");
        set.addChild("invoketimeout");
        set.addChild("maxdialogs");
        set.addChild("dialogidrangestart");
        set.addChild("dialogidrangeend");
//        set.addChild("previewmode");
        set.addChild("statisticsenabled");
        set.addChild("swaptcapidbytes");
        set.addChild("slsrange");

        Node get = parent.addChild("get");
        get.addChild("dialogidletimeout");
        get.addChild("invoketimeout");
        get.addChild("maxdialogs");
        get.addChild("dialogidrangestart");
        get.addChild("dialogidrangeend");
        get.addChild("previewmode");
        get.addChild("statisticsenabled");
        get.addChild("swaptcapidbytes");
        get.addChild("slsrange");

    };

    public TcapAnsiCommandHandler() {
        super(commandTree, CONNECT_MANDATORY_FLAG);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.ss7.management.console.CommandHandler#isValid(java.lang .String)
     */
    @Override
    public void handle(CommandContext ctx, String commandLine) {
        // TODO Validate command
        if (commandLine.contains("--help")) {
            this.printHelp(commandLine, ctx);
            return;
        }
        ctx.sendMessage(commandLine);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.ss7.management.console.CommandHandler#isAvailable(org.mobicents
     * .ss7.management.console.CommandContext)
     */
    @Override
    public boolean isAvailable(CommandContext ctx) {
        if (!ctx.isControllerConnected()) {
            ctx.printLine("The command is not available in the current context. Please connnect first");
            return false;
        }
        return true;
    }

}
