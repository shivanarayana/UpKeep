package UpKeep.Controller;

public enum Status {
    SUCCESS,
    USER_ALREADY_EXISTS,
    LAPTOP_ALREADY_ASSIGNED_CANNOT_REGISTER,
    LAPTOP_ADDED,
    NO_LAPTOP_EXIST,
    LAPTOP_UNASSIGNED_SUCCESSFULLY,
    ALL_LAPTOPS_UNASSIGNED_SUCCESSFULLY,
    NO_USER_EXIST_TO_ASSIGN,
    NO_SUCH_LAPTOP_TO_ASSIGN,
    NO_SUCH_LAPTOP_TO_UNASSIGN,
    LAPTOP_ALREADY_ASSIGNED_TO_SAME_PERSON,
    NO_USER_EXIST,
    NO_SUCH_LAPTOP_EXISTS,
    LAPTOP_REASSIGN_SUCCESSFUL,
    UPDATE_SUCCESS,
    FAILURE,
    USER_REGISTERED_WITH_LAPTOP,
    USER_LOGGED_IN,
    ALL_USERS_DELETED_SUCCESS,
    USER_LOGGED_OUT,
    FAILED_TO_LOGIN,
    FAILED_TO_LOGOUT
}