package com.evolveum.day_off_planner_rest.data.enums

enum class EmailTemplate(val templateName: String) {
    ACCOUNT_CREATED("account_created"),
    PASSWORD_RESET("password_reset"),
    REQUEST_APPROVAL("request_approval"),
    REQUEST_APPROVAL_CONFLICT("request_approval_conflict"),
    REQUEST_NO_APPROVAL("request_no_approval"),
    REQUEST_NEW_MESSAGE("request_new_message"),
    REQUEST_APPROVED("request_approved"),
    REQUEST_REJECTED("request_rejected"),
    REQUEST_SUBMITTED("request_submitted")
}