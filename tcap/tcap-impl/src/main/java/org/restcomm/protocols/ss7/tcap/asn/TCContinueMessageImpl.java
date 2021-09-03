
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCContinueMessage;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class TCContinueMessageImpl implements TCContinueMessage {

    private static final String _OCTET_STRING_ENCODE = "US-ASCII";

    // mandatory
    private byte[] originatingTransactionId;
    private byte[] destinationTransactionId;
    // opt
    private DialogPortion dp;
    // opt
    private Component[] component;

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCContinueMessage#getComponent ()
     */
    public Component[] getComponent() {
        return this.component;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCContinueMessage#getDestinationTransactionId()
     */
    public byte[] getDestinationTransactionId() {
        return this.destinationTransactionId;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCContinueMessage#getDialogPortion ()
     */
    public DialogPortion getDialogPortion() {
        return this.dp;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCContinueMessage# getOriginatingTransactionId()
     */
    public byte[] getOriginatingTransactionId() {
        return this.originatingTransactionId;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCContinueMessage#setComponent
     * (org.restcomm.protocols.ss7.tcap.asn.comp.Component[])
     */
    public void setComponent(Component[] component) {

        this.component = component;
    }

    /*
     * (non-Javadoc)
     *
     * @seeorg.restcomm.protocols.ss7.tcap.asn.comp.TCContinueMessage# setDestinationTransactionId(java.lang.String)
     */
    public void setDestinationTransactionId(byte[] t) {
        this.destinationTransactionId = t;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCContinueMessage#setDialogPortion
     * (org.restcomm.protocols.ss7.tcap.asn.DialogPortion)
     */
    public void setDialogPortion(DialogPortion dialogPortion) {
        this.dp = dialogPortion;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCContinueMessage# setOriginatingTransactionId(java.lang.String)
     */
    public void setOriginatingTransactionId(byte[] originatingTransactionId) {
        this.originatingTransactionId = originatingTransactionId;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#decode(org.mobicents.protocols .asn.AsnInputStream)
     */
    public void decode(AsnInputStream asnInputStream) throws ParseException {
        try {
            AsnInputStream localAis = asnInputStream.readSequenceStream();

            int tag = localAis.readTag();
            if (tag != _TAG_OTX || localAis.getTagClass() != Tag.CLASS_APPLICATION)
                throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                        "Error decoding TC-Continue: Expected OriginatingTransactionId, found tag: " + tag);
            this.originatingTransactionId = localAis.readOctetString();

            tag = localAis.readTag();
            if (tag != _TAG_DTX || localAis.getTagClass() != Tag.CLASS_APPLICATION)
                throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                        "Error decoding TC-Continue: Expected DestinationTransactionId, found tag: " + tag);
            this.destinationTransactionId = localAis.readOctetString();

            if (localAis.available() == 0)
                return;

            while (true) {
                if (localAis.available() == 0)
                    return;

                tag = localAis.readTag();
                if (localAis.isTagPrimitive() || localAis.getTagClass() != Tag.CLASS_APPLICATION)
                    throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                            "Error decoding TC-Continue: DialogPortion and Component portion must be constructive and has tag class CLASS_APPLICATION");

                switch (tag) {
                    case DialogPortion._TAG:
                        this.dp = TcapFactory.createDialogPortion(localAis);
                        break;

                    case Component._COMPONENT_TAG:
                        AsnInputStream compAis = localAis.readSequenceStream();
                        List<Component> cps = new ArrayList<Component>();
                        // its iterator :)
                        while (compAis.available() > 0) {
                            Component c = TcapFactory.createComponent(compAis);
                            if (c == null) {
                                break;
                            }
                            cps.add(c);
                        }

                        this.component = new Component[cps.size()];
                        this.component = cps.toArray(this.component);
                        break;

                    default:
                        throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                                "Error decoding TC-Continue: DialogPortion and Componebt parsing: bad tag - " + tag);
                }
            }
        } catch (IOException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null, "IOException while decoding TC-Continue: "
                    + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null, "AsnException while decoding TC-Continue: "
                    + e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols .asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {

        try {
            asnOutputStream.writeTag(Tag.CLASS_APPLICATION, false, _TAG);
            int pos = asnOutputStream.StartContentDefiniteLength();

            asnOutputStream.writeOctetString(Tag.CLASS_APPLICATION, _TAG_OTX, this.originatingTransactionId);
            asnOutputStream.writeOctetString(Tag.CLASS_APPLICATION, _TAG_DTX, this.destinationTransactionId);

            if (this.dp != null)
                this.dp.encode(asnOutputStream);

            if (component != null) {
                asnOutputStream.writeTag(Tag.CLASS_APPLICATION, false, Component._COMPONENT_TAG);
                int pos2 = asnOutputStream.StartContentDefiniteLength();
                for (Component c : this.component) {
                    c.encode(asnOutputStream);
                }
                asnOutputStream.FinalizeContent(pos2);
            }

            asnOutputStream.FinalizeContent(pos);

        } catch (IOException e) {
            throw new EncodeException("IOException while encoding TC-Continue: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new EncodeException("AsnException while encoding TC-Continue: " + e.getMessage(), e);
        }

    }

}
