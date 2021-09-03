package org.restcomm.protocols.ss7.isup.impl.message;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.restcomm.protocols.ss7.isup.impl.message.AbstractISUPMessage;
import org.restcomm.protocols.ss7.isup.message.CircuitGroupResetAckMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.CallReference;
import org.restcomm.protocols.ss7.isup.message.parameter.RangeAndStatus;
import org.testng.annotations.Test;

/**
 * Start time:09:26:46 2009-04-22<br>
 * Project: mobicents-isup-stack<br>
 * Test for GRA
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class GRATest extends MessageHarness {

    @Test(groups = { "functional.encode", "functional.decode", "message" })
    public void testTwo_Params() throws Exception {
        byte[] message = getDefaultBody();

        // CircuitGroupResetAckMessage grs=new CircuitGroupResetAckMessageImpl(this,message);
        CircuitGroupResetAckMessage grs = super.messageFactory.createGRA();
        ((AbstractISUPMessage) grs).decode(message, messageFactory,parameterFactory);

        try {
            RangeAndStatus RS = (RangeAndStatus) grs.getParameter(RangeAndStatus._PARAMETER_CODE);
            assertNotNull(RS, "Range And Status retrun is null, it shoul not be");
            if (RS == null)
                return;
            byte range = RS.getRange();
            assertEquals(range, 0x01, "Range is wrong,");
            byte[] b = RS.getStatus();
            assertNotNull(b, "RangeAndStatus.getRange() is null");
            if (b == null) {
                return;
            }
            assertEquals(b.length, 1, "Length of param is wrong");
            if (b.length != 1)
                return;
            assertTrue(super.makeCompare(b, new byte[] { 0x02 }), "RangeAndStatus.getRange() is wrong");

        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed on get parameter[" + CallReference._PARAMETER_CODE + "]:" + e);
        }

    }

    protected byte[] getDefaultBody() {
        // FIXME: for now we strip MTP part
        byte[] message = {

        0x0C, (byte) 0x0B, CircuitGroupResetAckMessage.MESSAGE_CODE

        , 0x01 // ptr to variable part
                // no optional, so no pointer
                // RangeAndStatus._PARAMETER_CODE
                , 0x02, 0x01, 0x02

        };

        return message;
    }

    protected ISUPMessage getDefaultMessage() {
        return super.messageFactory.createGRA();
    }

}
