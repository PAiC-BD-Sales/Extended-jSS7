package org.restcomm.protocols.ss7.inap.api.primitives;

import java.io.Serializable;

/**
*
<code>
ExtensionField {PARAMETERS-BOUND : bound} ::= SEQUENCE{
    type EXTENSION.&id ({SupportedExtensions {bound}}),
    -- shall identify the value of an EXTENSION type
    criticality CriticalityType DEFAULT ignore,
    value [1] EXTENSION.&ExtensionType ({SupportedExtensions {bound}}{@type}),
...
}
--This parameter indicates an extension of an argument data type.
-- Its content is network operator specific

CriticalityType ::= ENUMERATED {ignore (0),abort (1)}
</code>

*
* @author sergey vetyutnev
*
*/
public interface ExtensionField extends Serializable {

    Integer getLocalCode();

    long[] getGlobalCode();

    CriticalityType getCriticalityType();

    /**
     *
     * @return Encoded field parameter without tag and length fields
     */
    byte[] getData();

    void setLocalCode(Integer localCode);

    void setGlobalCode(long[] globalCode);

    void setCriticalityType(CriticalityType criticalityType);

    /**
     * @param data Encoded field parameter without tag and length fields
     */
    void setData(byte[] data);

}
