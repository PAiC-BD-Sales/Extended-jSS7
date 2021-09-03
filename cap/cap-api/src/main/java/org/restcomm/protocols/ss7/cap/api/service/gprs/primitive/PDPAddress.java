package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 PDPAddress ::= OCTET STRING (SIZE(1 .. 63)) -- refer to 3GPP TS 29.060 [12] for the encoding.
 *
 * @author Lasith Waruna Perera
 *
 */
public interface PDPAddress extends Serializable {

    byte[] getData();

}