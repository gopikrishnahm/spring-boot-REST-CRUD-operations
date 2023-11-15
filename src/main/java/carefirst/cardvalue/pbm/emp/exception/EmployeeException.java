package carefirst.cardvalue.pbm.emp.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class EmployeeException extends  RuntimeException {
    private  String errorCode;
    private  String errorDescription;
    private  String detailedDescription;

}
