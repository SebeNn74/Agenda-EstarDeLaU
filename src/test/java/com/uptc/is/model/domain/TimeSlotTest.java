package com.uptc.is.model.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeSlotTest {

    @Test
    void validHoursShouldReturnTrue() {

        boolean result = TimeSlot.validHours(
                LocalTime.of(8,0),
                LocalTime.of(10,0)
        );

        assertTrue(result);
    }

    @Test
    void validHoursShouldReturnFalse() {

        boolean result = TimeSlot.validHours(
                LocalTime.of(10,0),
                LocalTime.of(8,0)
        );

        assertFalse(result);
    }
}
