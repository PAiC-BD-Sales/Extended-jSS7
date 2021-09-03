package org.restcomm.protocols.ss7.isup.message.parameter;

import org.restcomm.protocols.ss7.isup.ParameterException;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public enum ReturnErrorProblemType {

    /**
     * No operation with the specified invoke ID is in progress. This code is generated by the TCAP layer.
     */
    UnrecognizedInvokeID(0),

    /**
     * The invoked operation does not report failure. This code is generated by the TCAP layer.
     */
    ReturnErrorUnexpected(1),

    /**
     * The error code is not one of those agreed by the two TC-User. This code is generated by the TC-User (not by TCAP layer).
     */
    UnrecognizedError(2),

    /**
     * The received error is not one of those that the invoked operation may report. This code is generated by the TC-User (not
     * by TCAP layer).
     */
    UnexpectedError(3),

    /**
     * Signifies that the type parameter in a Return Error component is not that agreed by the two TC-Users. This code is
     * generated by the TC-User (not by TCAP layer).
     */
    MistypedParameter(4);

    ReturnErrorProblemType(long l) {
        this.type = l;
    }

    private long type;

    /**
     * @return the type
     */
    public long getType() {
        return type;
    }

    public static ReturnErrorProblemType getFromInt(long t) throws ParameterException {
        if (t == 0) {
            return UnrecognizedInvokeID;
        } else if (t == 1) {
            return ReturnErrorUnexpected;
        } else if (t == 2) {
            return UnrecognizedError;
        } else if (t == 3) {
            return UnexpectedError;
        } else if (t == 4) {
            return MistypedParameter;
        }

        throw new ParameterException("Wrong value of type: " + t);
    }
}
