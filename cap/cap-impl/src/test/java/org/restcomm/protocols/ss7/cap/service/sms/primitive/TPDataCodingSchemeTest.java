
package org.restcomm.protocols.ss7.cap.service.sms.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.service.sms.primitive.TPDataCodingSchemeImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class TPDataCodingSchemeTest {
	
	public byte[] getData() {
		return new byte[] { 4, 1, 5 };
	};
	
	@Test(groups = { "functional.decode", "primitives" })
	public void testDecode() throws Exception {
		byte[] data = this.getData();
		AsnInputStream asn = new AsnInputStream(data);
		int tag = asn.readTag();
		TPDataCodingSchemeImpl prim = new TPDataCodingSchemeImpl();
		prim.decodeAll(asn);
		
		assertEquals(tag, Tag.STRING_OCTET);
		assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
		
		assertEquals(prim.getData(), 5);
		
	}
	
	@Test(groups = { "functional.encode", "primitives" })
	public void testEncode() throws Exception {
		TPDataCodingSchemeImpl prim = new TPDataCodingSchemeImpl(5);
		AsnOutputStream asn = new AsnOutputStream();
		prim.encodeAll(asn);
		
		assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
	}
	
}
