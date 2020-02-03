/*
 * Copyright Anatoliy Sablin tolya@sablin.xyz
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

package io.github.ma1uta.matrix.identity.model.key;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The validity of the public key.
 */
@Schema(
    description = "The validity of the public key."
)
public class KeyValidationResponse {

    /**
     * Required. Whether the public key is recognised and is currently valid.
     */
    @Schema(
        description = "Whether the public key is recognised and is currently valid.",
        required = true
    )
    private Boolean valid;

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
