package carefirst.cardvalue.pbm.emp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String errorDescription;
    private String detailedErrorDescription;
    private String timeStamp;
    private int errorStatus;

}