package com.sirajchaudhary.healthcare.appointment.enums;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public enum AppointmentStatus {

    SCHEDULED,
    CONFIRMED,
    COMPLETED,
    CANCELLED,
    NO_SHOW
}