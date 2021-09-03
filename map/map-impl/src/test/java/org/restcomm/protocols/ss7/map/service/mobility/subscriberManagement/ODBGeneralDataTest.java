
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ODBGeneralDataImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ODBGeneralDataTest {

    private byte[] getEncodedData() {
        return new byte[] { 3, 5, 3, 74, -43, 85, 80 };
    }

    private byte[] getEncodedData1() {
        return new byte[] { 3, 5, 3, -75, 42, -86, -88 };
    }

    @Test(groups = { "functional.decode", "service.lsm" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        ODBGeneralDataImpl imp = new ODBGeneralDataImpl();
        imp.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(!imp.getAllOGCallsBarred());
        assertTrue(imp.getInternationalOGCallsBarred());
        assertTrue(!imp.getInternationalOGCallsNotToHPLMNCountryBarred());
        assertTrue(imp.getInterzonalOGCallsBarred());
        assertTrue(!imp.getInterzonalOGCallsNotToHPLMNCountryBarred());
        assertTrue(imp.getInterzonalOGCallsAndInternationalOGCallsNotToHPLMNCountryBarred());
        assertTrue(!imp.getPremiumRateInformationOGCallsBarred());
        assertTrue(imp.getPremiumRateEntertainementOGCallsBarred());
        assertTrue(!imp.getSsAccessBarred());
        assertTrue(imp.getAllECTBarred());
        assertTrue(!imp.getChargeableECTBarred());
        assertTrue(imp.getInternationalECTBarred());
        assertTrue(!imp.getInterzonalECTBarred());
        assertTrue(imp.getDoublyChargeableECTBarred());
        assertTrue(!imp.getMultipleECTBarred());
        assertTrue(imp.getAllPacketOrientedServicesBarred());
        assertTrue(!imp.getRoamerAccessToHPLMNAPBarred());
        assertTrue(imp.getRoamerAccessToVPLMNAPBarred());
        assertTrue(!imp.getRoamingOutsidePLMNOGCallsBarred());
        assertTrue(imp.getAllICCallsBarred());
        assertTrue(!imp.getRoamingOutsidePLMNICCallsBarred());
        assertTrue(imp.getRoamingOutsidePLMNICountryICCallsBarred());
        assertTrue(!imp.getRoamingOutsidePLMNBarred());
        assertTrue(imp.getRoamingOutsidePLMNCountryBarred());
        assertTrue(!imp.getRegistrationAllCFBarred());
        assertTrue(imp.getRegistrationCFNotToHPLMNBarred());
        assertTrue(!imp.getRegistrationInterzonalCFBarred());
        assertTrue(imp.getRegistrationInterzonalCFNotToHPLMNBarred());
        assertTrue(!imp.getRegistrationInternationalCFBarred());

        rawData = getEncodedData1();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        imp = new ODBGeneralDataImpl();
        imp.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(imp.getAllOGCallsBarred());
        assertTrue(!imp.getInternationalOGCallsBarred());
        assertTrue(imp.getInternationalOGCallsNotToHPLMNCountryBarred());
        assertTrue(!imp.getInterzonalOGCallsBarred());
        assertTrue(imp.getInterzonalOGCallsNotToHPLMNCountryBarred());
        assertTrue(!imp.getInterzonalOGCallsAndInternationalOGCallsNotToHPLMNCountryBarred());
        assertTrue(imp.getPremiumRateInformationOGCallsBarred());
        assertTrue(!imp.getPremiumRateEntertainementOGCallsBarred());
        assertTrue(imp.getSsAccessBarred());
        assertTrue(!imp.getAllECTBarred());
        assertTrue(imp.getChargeableECTBarred());
        assertTrue(!imp.getInternationalECTBarred());
        assertTrue(imp.getInterzonalECTBarred());
        assertTrue(!imp.getDoublyChargeableECTBarred());
        assertTrue(imp.getMultipleECTBarred());
        assertTrue(!imp.getAllPacketOrientedServicesBarred());
        assertTrue(imp.getRoamerAccessToHPLMNAPBarred());
        assertTrue(!imp.getRoamerAccessToVPLMNAPBarred());
        assertTrue(imp.getRoamingOutsidePLMNOGCallsBarred());
        assertTrue(!imp.getAllICCallsBarred());
        assertTrue(imp.getRoamingOutsidePLMNICCallsBarred());
        assertTrue(!imp.getRoamingOutsidePLMNICountryICCallsBarred());
        assertTrue(imp.getRoamingOutsidePLMNBarred());
        assertTrue(!imp.getRoamingOutsidePLMNCountryBarred());
        assertTrue(imp.getRegistrationAllCFBarred());
        assertTrue(!imp.getRegistrationCFNotToHPLMNBarred());
        assertTrue(imp.getRegistrationInterzonalCFBarred());
        assertTrue(!imp.getRegistrationInterzonalCFNotToHPLMNBarred());
        assertTrue(imp.getRegistrationInternationalCFBarred());
    }

    @Test(groups = { "functional.encode", "service.lsm" })
    public void testEncode() throws Exception {

        ODBGeneralDataImpl imp = new ODBGeneralDataImpl(false, true, false, true, false, true, false, true, false, true, false,
                true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true,
                false);

        AsnOutputStream asnOS = new AsnOutputStream();
        imp.encodeAll(asnOS);

        assertTrue(Arrays.equals(getEncodedData(), asnOS.toByteArray()));

        imp = new ODBGeneralDataImpl(true, false, true, false, true, false, true, false, true, false, true, false, true, false,
                true, false, true, false, true, false, true, false, true, false, true, false, true, false, true);

        asnOS = new AsnOutputStream();
        imp.encodeAll(asnOS);

        assertTrue(Arrays.equals(getEncodedData1(), asnOS.toByteArray()));
    }
}
