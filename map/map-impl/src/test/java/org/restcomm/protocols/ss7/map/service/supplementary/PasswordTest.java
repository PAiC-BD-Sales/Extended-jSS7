
package org.restcomm.protocols.ss7.map.service.supplementary;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.supplementary.PasswordImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class PasswordTest {

    private byte[] getEncodedData() {
        return new byte[] { 18, 4, 48, 49, 50, 51 };
    }

    @Test(groups = { "functional.decode", "service.supplementary" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, Tag.STRING_NUMERIC);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        PasswordImpl impl = new PasswordImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getData(), "0123");


    }

    @Test(groups = { "functional.encode", "service.supplementary" })
    public void testEncode() throws Exception {

        PasswordImpl impl = new PasswordImpl("0123");
        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


    }

}
