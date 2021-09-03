
package org.restcomm.protocols.ss7.tcap.asn.comp;

import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.DialogPortion;
import org.restcomm.protocols.ss7.tcap.asn.Encodable;

/**
 * @author baranowb
 *
 */
public interface TCEndMessage extends Encodable {
    int _TAG = 0x04;
    boolean _TAG_PC_PRIMITIVE = false;
    int _TAG_CLASS = Tag.CLASS_APPLICATION;

    int _TAG_DTX = 0x09;
    boolean _TAG_DTX_PC_PRIMITIVE = true;
    int _TAG_CLASS_DTX = Tag.CLASS_APPLICATION;

    // mandatory
    byte[] getDestinationTransactionId();

    void setDestinationTransactionId(byte[] destinationTransactionId);

    // opt FIXME: make this External?
    DialogPortion getDialogPortion();

    void setDialogPortion(DialogPortion dialogPortion);

    // opt
    Component[] getComponent();

    void setComponent(Component[] component);

}
