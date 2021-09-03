
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.*;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.QoSSubscribed_DelayClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.QoSSubscribed_MeanThroughput;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.QoSSubscribed_PeakThroughput;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.QoSSubscribed_PrecedenceClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.QoSSubscribed_ReliabilityClass;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.QoSSubscribedImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class QoSSubscribedTest {

    public byte[] getData1() {
        return new byte[] { 4, 3, 27, (byte) 147, 31 };
    };

    public byte[] getData2() {
        return new byte[] { 4, 3, 35, 98, 31 };
    };

    public byte[] getData3() {
        return new byte[] { 4, 3, 21, 33, 5 };
    };

    @Test(groups = { "functional.decode", "mobility.subscriberManagement" })
    public void testDecode() throws Exception {
        byte[] data = this.getData1();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        QoSSubscribedImpl prim = new QoSSubscribedImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(prim.getReliabilityClass(), QoSSubscribed_ReliabilityClass.unacknowledgedGtpAndLlc_AcknowledgedRlc_ProtectedData);
        assertEquals(prim.getDelayClass(), QoSSubscribed_DelayClass.delay_Class_3);
        assertEquals(prim.getPrecedenceClass(), QoSSubscribed_PrecedenceClass.lowPriority);
        assertEquals(prim.getPeakThroughput(), QoSSubscribed_PeakThroughput.upTo_256000_octetS);
        assertEquals(prim.getMeanThroughput(), QoSSubscribed_MeanThroughput.bestEffort);


        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new QoSSubscribedImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(prim.getReliabilityClass(), QoSSubscribed_ReliabilityClass.unacknowledgedGtpAndLlc_AcknowledgedRlc_ProtectedData);
        assertEquals(prim.getDelayClass(), QoSSubscribed_DelayClass.delay_Class_4_bestEffort);
        assertEquals(prim.getPrecedenceClass(), QoSSubscribed_PrecedenceClass.normalPriority);
        assertEquals(prim.getPeakThroughput(), QoSSubscribed_PeakThroughput.upTo_32000_octetS);
        assertEquals(prim.getMeanThroughput(), QoSSubscribed_MeanThroughput.bestEffort);


        data = this.getData3();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new QoSSubscribedImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(prim.getReliabilityClass(), QoSSubscribed_ReliabilityClass.unacknowledgedGtpLlcAndRlc_UnprotectedData);
        assertEquals(prim.getDelayClass(), QoSSubscribed_DelayClass.delay_Class_2);
        assertEquals(prim.getPrecedenceClass(), QoSSubscribed_PrecedenceClass.highPriority);
        assertEquals(prim.getPeakThroughput(), QoSSubscribed_PeakThroughput.upTo_2000_octetS);
        assertEquals(prim.getMeanThroughput(), QoSSubscribed_MeanThroughput._2000_octetH);
    }

    @Test(groups = { "functional.encode", "mobility.subscriberManagement" })
    public void testEncode() throws Exception {
        QoSSubscribedImpl prim = new QoSSubscribedImpl(QoSSubscribed_ReliabilityClass.unacknowledgedGtpAndLlc_AcknowledgedRlc_ProtectedData,
                QoSSubscribed_DelayClass.delay_Class_3, QoSSubscribed_PrecedenceClass.lowPriority, QoSSubscribed_PeakThroughput.upTo_256000_octetS,
                QoSSubscribed_MeanThroughput.bestEffort);

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertEquals(asn.toByteArray(), this.getData1());


        prim = new QoSSubscribedImpl(QoSSubscribed_ReliabilityClass.unacknowledgedGtpAndLlc_AcknowledgedRlc_ProtectedData,
                QoSSubscribed_DelayClass.delay_Class_4_bestEffort, QoSSubscribed_PrecedenceClass.normalPriority, QoSSubscribed_PeakThroughput.upTo_32000_octetS,
                QoSSubscribed_MeanThroughput.bestEffort);

        asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertEquals(asn.toByteArray(), this.getData2());


        prim = new QoSSubscribedImpl(QoSSubscribed_ReliabilityClass.unacknowledgedGtpLlcAndRlc_UnprotectedData,
                QoSSubscribed_DelayClass.delay_Class_2, QoSSubscribed_PrecedenceClass.highPriority, QoSSubscribed_PeakThroughput.upTo_2000_octetS,
                QoSSubscribed_MeanThroughput._2000_octetH);

        asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertEquals(asn.toByteArray(), this.getData3());
    }

}
