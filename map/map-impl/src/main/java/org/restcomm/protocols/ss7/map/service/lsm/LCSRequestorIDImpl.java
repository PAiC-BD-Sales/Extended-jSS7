package org.restcomm.protocols.ss7.map.service.lsm;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSFormatIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSRequestorID;
import org.restcomm.protocols.ss7.map.datacoding.CBSDataCodingSchemeImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.USSDStringImpl;

/**
 * @author amit bhayani
 *
 */
public class LCSRequestorIDImpl implements LCSRequestorID, MAPAsnPrimitive {

    private static final int _TAG_DATA_CODING_SCHEME = 0;
    private static final int _TAG_NAME_STRING = 1;
    private static final int _TAG_LCS_FORMAT_INDICATOR = 2;

    public static final String _PrimitiveName = "LCSRequestorID";

    private CBSDataCodingScheme dataCodingScheme;
    private USSDString requestorIDString;
    private LCSFormatIndicator lcsFormatIndicator;

    /**
     *
     */
    public LCSRequestorIDImpl() {
        super();
    }

    /**
     * @param dataCodingScheme
     * @param requestorIDString
     * @param lcsFormatIndicator
     */
    public LCSRequestorIDImpl(CBSDataCodingScheme dataCodingScheme, USSDString requestorIDString, LCSFormatIndicator lcsFormatIndicator) {
        super();
        this.dataCodingScheme = dataCodingScheme;
        this.requestorIDString = requestorIDString;
        this.lcsFormatIndicator = lcsFormatIndicator;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.LCSRequestorID#getDataCodingScheme()
     */
    public CBSDataCodingScheme getDataCodingScheme() {
        return this.dataCodingScheme;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.LCSRequestorID#getRequestorIDString()
     */
    public USSDString getRequestorIDString() {
        return this.requestorIDString;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.LCSRequestorID#getLCSFormatIndicator()
     */
    public LCSFormatIndicator getLCSFormatIndicator() {
        return this.lcsFormatIndicator;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getTag()
     */
    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getTagClass ()
     */
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getIsPrimitive ()
     */
    public boolean getIsPrimitive() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#decodeAll
     * (org.mobicents.protocols.asn.AsnInputStream)
     */
    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#decodeData
     * (org.mobicents.protocols.asn.AsnInputStream, int)
     */
    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.dataCodingScheme = null;
        this.requestorIDString = null;
        this.lcsFormatIndicator = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        int tag = ais.readTag();

        // Decode mandatory dataCodingScheme [0] USSD-DataCodingScheme,
        if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive() || tag != _TAG_DATA_CODING_SCHEME) {
            throw new MAPParsingComponentException(
                    "Error while decoding LCSRequestorID: Parameter 0[dataCodingScheme [0] USSD-DataCodingScheme] bad tag class, tag or not primitive",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        int length1 = ais.readLength();
        this.dataCodingScheme = new CBSDataCodingSchemeImpl(ais.readOctetStringData(length1)[0]);

        tag = ais.readTag();

        // Decode mandatory nameString [1] NameString,
        if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive() || tag != _TAG_NAME_STRING) {
            throw new MAPParsingComponentException(
                    "Error while decoding LCSRequestorID: Parameter 1[requestorIDString [1] RequestorIDString] bad tag class, tag or not primitive",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        this.requestorIDString = new USSDStringImpl(this.dataCodingScheme);
        ((USSDStringImpl) this.requestorIDString).decodeAll(ais);

        while (true) {
            if (ais.available() == 0)
                break;

            tag = ais.readTag();
            switch (tag) {
                case _TAG_LCS_FORMAT_INDICATOR:
                    // Decode lcs-FormatIndicator [2] LCS-FormatIndicator OPTIONAL
                    if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive()) {
                        throw new MAPParsingComponentException(
                                "Error while decoding LCSRequestorID: Parameter 2[lcs-FormatIndicator [2] LCS-FormatIndicator OPTIONAL] bad tag class, tag or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    }
                    length1 = ais.readLength();
                    this.lcsFormatIndicator = LCSFormatIndicator.getLCSFormatIndicator((int) ais.readIntegerData(length1));
                    break;

                default:
                    ais.advanceElement();
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeAll
     * (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeAll
     * (org.mobicents.protocols.asn.AsnOutputStream, int, int)
     */
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeData
     * (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.requestorIDString == null)
            throw new MAPException("nameString must not be null");

        try {
            asnOutputStream.writeOctetString(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_DATA_CODING_SCHEME,
                    new byte[] { (byte) this.dataCodingScheme.getCode() });

            ((USSDStringImpl) this.requestorIDString).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_NAME_STRING);

            if (this.lcsFormatIndicator != null) {
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_LCS_FORMAT_INDICATOR,
                        this.lcsFormatIndicator.getIndicator());
            }
        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + "", e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + "", e);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + dataCodingScheme.getCode();
        result = prime * result + ((lcsFormatIndicator == null) ? 0 : lcsFormatIndicator.hashCode());
        result = prime * result + ((requestorIDString == null) ? 0 : requestorIDString.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LCSRequestorIDImpl other = (LCSRequestorIDImpl) obj;
        if (dataCodingScheme.getCode() != other.dataCodingScheme.getCode())
            return false;
        if (lcsFormatIndicator != other.lcsFormatIndicator)
            return false;
        if (requestorIDString == null) {
            if (other.requestorIDString != null)
                return false;
        } else if (!requestorIDString.equals(other.requestorIDString))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        sb.append("dataCodingScheme=");
        sb.append(this.dataCodingScheme);
        if (this.requestorIDString != null) {
            sb.append(", requestorIDString=");
            sb.append(this.requestorIDString.toString());
        }
        if (this.lcsFormatIndicator != null) {
            sb.append(", lcsFormatIndicator=");
            sb.append(this.lcsFormatIndicator.toString());
        }

        sb.append("]");

        return sb.toString();
    }
}
