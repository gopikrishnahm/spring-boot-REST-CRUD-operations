package carefirst.cardvalue.pbm.emp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusCode {

    ERROR_CREATING_DATA("Error occured while create employee","PBM-1000","Error occured while create employee"),
    ERROR_FETCHING_DATA("Error fetching employees, Please try again","PBM-1001","Error Occured while fetching employees."),
    GENERIC_ERROR("Generic Error Occurred","PBM-1002","Generic Error Occurred"),
    NO_DATA_FOUND_ERROR("No record found","PBM-1003","No Records found in DB"),
    NO_EMPLOYEE_EMPID_FOUND_ERROR("No record found for the given Employee Id","PBM-1004","\"No Records found in DB"),
    DELETE_EMPLOYEE_MSG ("Employee deleted successfully.","PBM-1004","\"No Records found in DB");
    private final String description;
    private final String code;
    private final String detailDescription;
}