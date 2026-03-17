package com.uptc.is.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IdGeneratorTest {

    @Test
    void shouldGenerateSequentialIds() {

        String id1 = IdGenerator.generateId("H");
        String id2 = IdGenerator.generateId("H");

        assertNotEquals(id1, id2);
    }

    @Test
    void shouldUpdateCounter() {

        IdGenerator.updateCounter("X",10);

        int counter = IdGenerator.getCounter("X");

        assertEquals(10,counter);
    }

    @Test
    void shouldReturnDefaultCounter() {

        int counter = IdGenerator.getCounter("NEW");

        assertEquals(1,counter);
    }
}
