/*
 * Copyright (c) 2024 Oracle and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.helidon.examples.microprofile.coherence;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;

import io.helidon.microprofile.testing.junit5.HelidonTest;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@HelidonTest
class MainTest {

    @Inject
    private WebTarget target;

    @Test
    void testCreditScore() {

        final String JSON_PAYLOAD = """
        {
            "ssn" : "123-45-6789",
            "firstName" : "Frank",
            "lastName" : "Helidon",
            "dateOfBirth" : "02/19/2019"
        }
        """;

        try (Response r = target
                .path("creditscore")
                .request()
                .post(Entity.entity(JSON_PAYLOAD, MediaType.APPLICATION_JSON))){
            assertThat(r.getStatus(), is(200));
        }
    }
}
