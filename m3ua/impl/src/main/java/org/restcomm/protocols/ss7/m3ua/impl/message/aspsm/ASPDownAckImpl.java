package org.restcomm.protocols.ss7.m3ua.impl.message.aspsm;

import io.netty.buffer.ByteBuf;

import org.restcomm.protocols.ss7.m3ua.impl.message.M3UAMessageImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterImpl;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.aspsm.ASPDownAck;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;

/**
 *
 * @author amit bhayani
 *
 */
public class ASPDownAckImpl extends M3UAMessageImpl implements ASPDownAck {

    public ASPDownAckImpl() {
        super(MessageClass.ASP_STATE_MAINTENANCE, MessageType.ASP_DOWN_ACK, MessageType.S_ASP_DOWN_ACK);
    }

    public InfoString getInfoString() {
        return (InfoString) parameters.get(Parameter.INFO_String);
    }

    public void setInfoString(InfoString infoString) {
        if (infoString != null) {
            parameters.put(Parameter.INFO_String, infoString);
        }
    }

    @Override
    protected void encodeParams(ByteBuf byteBuf) {

        if (parameters.containsKey(Parameter.INFO_String)) {
            ((ParameterImpl) parameters.get(Parameter.INFO_String)).write(byteBuf);
        }
    }

}
