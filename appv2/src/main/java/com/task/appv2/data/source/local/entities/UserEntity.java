package com.task.appv2.data.source.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "UserData")

public class UserEntity {

    @NonNull
    @PrimaryKey
    @SerializedName("U_ID")
    private String userId;

    @SerializedName("USR_NM")
    private String userName;
    @SerializedName("U_MNG")
    private String userManager;
    @SerializedName("PASSWORD")
    private String password;
    @SerializedName("TRMNL_NO")
    private String terminalNo; // SEND IN REQUEST ORDER
    @SerializedName("TRMNL_NAME")
    private String terminalName;
    @SerializedName("TRMNL_LGN")
    private String terminalLogin;
    @SerializedName("LOGIN")
    private String loginDate;
    @SerializedName("LOGOUT")
    private String logoutDate;


    @SerializedName("GROUP_NO")
    private String groupNo;
    @SerializedName("PASSWORD2")
    private String password2;
    @SerializedName("S_SDATE")
    private String startDate;
    @SerializedName("S_EDATE")
    private String endDate;
    @SerializedName("S_STIME")
    private String startTime;
    @SerializedName("S_ETIME")
    private String endTime;
    @SerializedName("LGN_METHOD")
    private String loginMethod;
    @SerializedName("DAYACTIVE")
    private String dayActive;
    @SerializedName("INACTIVE")
    private String inactive;

    @SerializedName("INACTIVE_DATE")
    private String inactiveDate;

    @SerializedName("INACTIVE_U_ID")
    private String inactiveUserId;

    @SerializedName("INACTIVE_RES")
    private String inactiveReason;

    @SerializedName("A_CODE")
    private String code;

    @SerializedName("USER_TYPE")
    private String userType;

    @SerializedName("ADMIN_USER")
    private String adminUser;

    @SerializedName("CHNG_PASSWD_AFTR_LGN")
    private String changePasswordAfterLogin;

    @SerializedName("CONN_WEB_SYS")
    private String connectWebSystem;

    @SerializedName("UPD_PWD_CNT")
    private String updatePasswordCount;

    @SerializedName("SCR_THEME_NO")
    private String screenThemeNo;

    @SerializedName("LOGGED_ON")
    private String loggedOn;

    @SerializedName("LOGIN_CNT")
    private String loginCount;

    @SerializedName("CONN_BRN_NO")
    private String connectionBranchNo;

    @SerializedName("CASH_NO")
    private String cashNo;

    @SerializedName("EMP_NO")
    private String employeeNo;

    @SerializedName("USER_ONLINE")
    private String userOnline;

    //======================================


    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public String getCode() {
        return code;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId( @NonNull String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserManager() {
        return userManager;
    }

    public void setUserManager(String userManager) {
        this.userManager = userManager;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLoginMethod() {
        return loginMethod;
    }

    public void setLoginMethod(String loginMethod) {
        this.loginMethod = loginMethod;
    }

    public String getDayActive() {
        return dayActive;
    }

    public void setDayActive(String dayActive) {
        this.dayActive = dayActive;
    }

    public String getInactive() {
        return inactive;
    }

    public void setInactive(String inactive) {
        this.inactive = inactive;
    }

    public String getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(String inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    public String getInactiveUserId() {
        return inactiveUserId;
    }

    public void setInactiveUserId(String inactiveUserId) {
        this.inactiveUserId = inactiveUserId;
    }

    public String getInactiveReason() {
        return inactiveReason;
    }

    public void setInactiveReason(String inactiveReason) {
        this.inactiveReason = inactiveReason;
    }


    public void setCode(String aCode) {
        this.code = aCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
    }

    public String getChangePasswordAfterLogin() {
        return changePasswordAfterLogin;
    }

    public void setChangePasswordAfterLogin(String changePasswordAfterLogin) {
        this.changePasswordAfterLogin = changePasswordAfterLogin;
    }

    public String getConnectWebSystem() {
        return connectWebSystem;
    }

    public void setConnectWebSystem(String connectWebSystem) {
        this.connectWebSystem = connectWebSystem;
    }

    public String getUpdatePasswordCount() {
        return updatePasswordCount;
    }

    public void setUpdatePasswordCount(String updatePasswordCount) {
        this.updatePasswordCount = updatePasswordCount;
    }

    public String getScreenThemeNo() {
        return screenThemeNo;
    }

    public void setScreenThemeNo(String screenThemeNo) {
        this.screenThemeNo = screenThemeNo;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public String getTerminalLogin() {
        return terminalLogin;
    }

    public void setTerminalLogin(String terminalLogin) {
        this.terminalLogin = terminalLogin;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(String logoutDate) {
        this.logoutDate = logoutDate;
    }

    public String getLoggedOn() {
        return loggedOn;
    }

    public void setLoggedOn(String loggedOn) {
        this.loggedOn = loggedOn;
    }

    public String getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(String loginCount) {
        this.loginCount = loginCount;
    }

    public String getConnectionBranchNo() {
        return connectionBranchNo;
    }

    public void setConnectionBranchNo(String connectionBranchNo) {
        this.connectionBranchNo = connectionBranchNo;
    }

    public String getCashNo() {
        return cashNo;
    }

    public void setCashNo(String cashNo) {
        this.cashNo = cashNo;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getUserOnline() {
        return userOnline;
    }

    public void setUserOnline(String userOnline) {
        this.userOnline = userOnline;
    }

    @NotNull
    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userManager='" + userManager + '\'' +
                ", password='" + password + '\'' +
                ", terminalNo='" + terminalNo + '\'' +
                ", terminalName='" + terminalName + '\'' +
                ", terminalLogin='" + terminalLogin + '\'' +
                ", loginDate='" + loginDate + '\'' +
                ", logoutDate='" + logoutDate + '\'' +
                ", groupNo='" + groupNo + '\'' +
                ", password2='" + password2 + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", loginMethod='" + loginMethod + '\'' +
                ", dayActive='" + dayActive + '\'' +
                ", inactive='" + inactive + '\'' +
                ", inactiveDate='" + inactiveDate + '\'' +
                ", inactiveUserId='" + inactiveUserId + '\'' +
                ", inactiveReason='" + inactiveReason + '\'' +
                ", code='" + code + '\'' +
                ", userType='" + userType + '\'' +
                ", adminUser='" + adminUser + '\'' +
                ", changePasswordAfterLogin='" + changePasswordAfterLogin + '\'' +
                ", connectWebSystem='" + connectWebSystem + '\'' +
                ", updatePasswordCount='" + updatePasswordCount + '\'' +
                ", screenThemeNo='" + screenThemeNo + '\'' +
                ", loggedOn='" + loggedOn + '\'' +
                ", loginCount='" + loginCount + '\'' +
                ", connectionBranchNo='" + connectionBranchNo + '\'' +
                ", cashNo='" + cashNo + '\'' +
                ", employeeNo='" + employeeNo + '\'' +
                ", userOnline='" + userOnline + '\'' +
                '}';
    }
}