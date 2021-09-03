
package org.restcomm.protocols.ss7.tcap.api.tc.dialog.events;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.UserInformation;

public interface TCContinueRequest extends DialogRequest {

    void setReturnMessageOnError(boolean val);

    boolean getReturnMessageOnError();

    /**
     * Sets origin address. This parameter is used only in first TCContinue, sent as response to TCBegin. This parameter, if
     * set, changes local peer address(remote end will send request to value set by this method).
     *
     * @return
     */
    SccpAddress getOriginatingAddress();

    void setOriginatingAddress(SccpAddress sccpCallingPartyAddress);

    /**
     * Application context name for this dialog.
     *
     * @return
     */
    ApplicationContextName getApplicationContextName();

    void setApplicationContextName(ApplicationContextName applicationContextName);

    /**
     * User information for this dialog.
     *
     * @return
     */
    UserInformation getUserInformation();

    void setUserInformation(UserInformation acn);

}
