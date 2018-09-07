/*
 * Copyright sablintolya@gmail.com
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

package io.github.ma1uta.matrix.events.messages;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.ma1uta.matrix.events.RoomMessage;

/**
 * Raw message for unknown messages.
 */
public class RawMessage extends RoomMessage {

    public RawMessage(JsonNode node, String type) {
        this.node = node;
        this.type = type;
    }

    private JsonNode node;

    private String type;

    @Override
    public String getMsgtype() {
        return type;
    }

    public JsonNode getNode() {
        return node;
    }

    @Override
    public String getBody() {
        return node.toString();
    }
}