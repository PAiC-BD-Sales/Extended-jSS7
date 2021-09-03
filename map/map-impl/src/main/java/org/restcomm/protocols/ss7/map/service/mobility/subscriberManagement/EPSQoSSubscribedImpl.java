
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.AllocationRetentionPriority;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.EPSQoSSubscribed;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.QoSClassIdentifier;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class EPSQoSSubscribedImpl extends SequenceBase implements EPSQoSSubscribed {

    private static final int _TAG_qoSClassIdentifier = 0;
    private static final int _TAG_allocationRetentionPriority = 1;
    private static final int _TAG_extensionContainer = 2;

    private QoSClassIdentifier qoSClassIdentifier;
    private AllocationRetentionPriority allocationRetentionPriority;
    private MAPExtensionContainer extensionContainer;

    public EPSQoSSubscribedImpl() {
        super("EPSQoSSubscribed");
    }

    public EPSQoSSubscribedImpl(QoSClassIdentifier qoSClassIdentifier, AllocationRetentionPriority allocationRetentionPriority,
            MAPExtensionContainer extensionContainer) {
        super("EPSQoSSubscribed");
        this.qoSClassIdentifier = qoSClassIdentifier;
        this.allocationRetentionPriority = allocationRetentionPriority;
        this.extensionContainer = extensionContainer;
    }

    @Override
    public QoSClassIdentifier getQoSClassIdentifier() {
        return this.qoSClassIdentifier;
    }

    @Override
    public AllocationRetentionPriority getAllocationRetentionPriority() {
        return this.allocationRetentionPriority;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.qoSClassIdentifier = null;
        this.allocationRetentionPriority = null;
        this.extensionContainer = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (num) {
                case 0:
                    if (tag != _TAG_qoSClassIdentifier || ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC
                            || !ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".qoSClassIdentifier: Parameter bad tag, tag class or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    int qos = (int) ais.readInteger();
                    this.qoSClassIdentifier = QoSClassIdentifier.getInstance(qos);
                    break;
                case 1:
                    if (tag != _TAG_allocationRetentionPriority || ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC
                            || ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".allocationRetentionPriority: Parameter bad tag, tag class or primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.allocationRetentionPriority = new AllocationRetentionPriorityImpl();
                    ((AllocationRetentionPriorityImpl) this.allocationRetentionPriority).decodeAll(ais);
                    break;
                default:
                    switch (ais.getTagClass()) {
                        case Tag.CLASS_CONTEXT_SPECIFIC: {
                            switch (tag) {
                                case _TAG_extensionContainer:
                                    if (ais.isTagPrimitive())
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".extensionContainer: Parameter is primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    this.extensionContainer = new MAPExtensionContainerImpl();
                                    ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                                    break;
                                default:
                                    ais.advanceElement();
                                    break;
                            }
                        }
                            break;
                        default:
                            ais.advanceElement();
                            break;
                    }
            }

            num++;
        }

        if (this.qoSClassIdentifier == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parameter qoSClassIdentifier is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        if (this.allocationRetentionPriority == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parameter allocationRetentionPriority is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.qoSClassIdentifier == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter qoSClassIdentifier is not defined");
        }

        if (this.allocationRetentionPriority == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter allocationRetentionPriority is not defined");
        }

        try {

            if (this.qoSClassIdentifier != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_qoSClassIdentifier, this.qoSClassIdentifier.getCode());

            ((AllocationRetentionPriorityImpl) this.allocationRetentionPriority).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    _TAG_allocationRetentionPriority);

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                        _TAG_extensionContainer);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.qoSClassIdentifier != null) {
            sb.append("qoSClassIdentifier=");
            sb.append(this.qoSClassIdentifier.toString());
            sb.append(", ");
        }

        if (this.allocationRetentionPriority != null) {
            sb.append("allocationRetentionPriority=");
            sb.append(this.allocationRetentionPriority.toString());
            sb.append(", ");
        }

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(this.extensionContainer.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
