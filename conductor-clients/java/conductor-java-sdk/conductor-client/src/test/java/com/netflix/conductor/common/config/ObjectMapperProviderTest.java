/*
 * Copyright 2024 Conductor Authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.netflix.conductor.common.config;

import java.time.*;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMapperProviderTest {
    
    @Test
    public void testRoundtripDatetime() throws Exception {
        ObjectMapper mapperProvider = new ObjectMapperProvider().getObjectMapper();
        Instant epoch = Instant.ofEpochSecond(0);
        ZoneId zone = ZoneId.of("Europe/Berlin");

        testRoundTrip(epoch, mapperProvider);
        testRoundTrip(LocalDate.ofInstant(epoch, zone), mapperProvider);
        testRoundTrip(new Date(epoch.toEpochMilli()), mapperProvider);
        testRoundTrip(OffsetDateTime.ofInstant(epoch, zone), mapperProvider);
        testRoundTrip(ZonedDateTime.ofInstant(epoch, zone), mapperProvider);
    }

    private void testRoundTrip(Object now, ObjectMapper mapperProvider) throws Exception {
        String serialized = mapperProvider.writeValueAsString(now);
        System.out.println(now.getClass() + ": serialized: " + serialized);

        Object deserialized = mapperProvider.readValue(serialized, now.getClass());
        assertEquals(now, deserialized, "Testing round trip for " + now.getClass());
    }

}