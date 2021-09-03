package org.restcomm.protocols.ss7.inap.api.primitives;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.isup.BearerInap;
import org.restcomm.protocols.ss7.inap.api.isup.TmrInap;

/**
*
<code>
BearerCapability {PARAMETERS-BOUND : bound} ::= CHOICE {
    bearerCap  [0] OCTET STRING (SIZE(2..bound.&maxBearerCapabilityLength)),
    tmr        [1] OCTET STRING (SIZE(1))
}
-- Indicates the type of bearer capability connection to the user. For bearerCapability, either
-- DSS1 (EN 300 403-1) or the ISUP User Service Information (ITU-T Recommendation Q.763)
-- encoding can be used. Refer
-- to the ITU-T Recommendation Q.763 Transmission Medium Requirement parameter for tmr encoding.

bearerCapability:
This parameter indicates the type of the bearer capability connection or the transmission medium requirements to
the user. See IN CS2 Signalling Interworking Requirements [48].
It is a network option to select one of the two parameters to be used:

- bearerCap:
This parameter contains the value of the DSS1 Bearer Capability parameter (EN 300 403-1 [10]) in case the
SSF is at local exchange level or the value of the ISUP User Service Information parameter
(ITU-T Recommendation Q.763 [20]) in case the SSF is at transit exchange level.
ETSI EN 301 140-1 V1.3.4 (1999-06)

- tmr:
The tmr is encoded as the Transmission Medium Requirement parameter of the ISUP according to
ITU-T Recommendation Q.763 [20].
</code>

*
* @author sergey vetyutnev
*
*/
public interface BearerCapability extends Serializable {

    BearerInap getBearerCap();

    TmrInap getTmr();

}
