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

package io.github.ma1uta.matrix.server.model.federation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.content.RoomMemberContent;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * JSON request of the send_join/send_leave requests.
 */
@Schema(
    description = "JSON request of the send_join/send_leave requests."
)
public class SendRequest {

    /**
     * Required. The user ID of the joining member.
     */
    @Schema(
        description = "The user ID of the joining member.",
        required = true
    )
    private String sender;

    /**
     * Required. The name of the joining homeserver.
     */
    @Schema(
        description = "The name of the joining homeserver.",
        required = true
    )
    private String origin;

    /**
     * Required. A timestamp added by the joining homeserver.
     */
    @Schema(
        name = "origin_server_ts",
        description = "A timestamp added by the joining homeserver.",
        required = true
    )
    @JsonbProperty("origin_server_ts")
    private Long originServerTs;

    /**
     * Required. The value m.room.member.
     */
    @Schema(
        description = "The value m.room.member.",
        required = true,
        defaultValue = "m.room.member"
    )
    private String type;

    /**
     * Required. The user ID of the joining member.
     */
    @Schema(
        name = "state_key",
        description = "The user ID of the joining member.",
        required = true
    )
    @JsonbProperty("state_key")
    private String stateKey;

    /**
     * Required. The content of the event.
     */
    @Schema(
        description = "The content of the event.",
        required = true
    )
    private RoomMemberContent content;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @JsonProperty("origin_server_ts")
    public Long getOriginServerTs() {
        return originServerTs;
    }

    public void setOriginServerTs(Long originServerTs) {
        this.originServerTs = originServerTs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("state_key")
    public String getStateKey() {
        return stateKey;
    }

    public void setStateKey(String stateKey) {
        this.stateKey = stateKey;
    }

    public RoomMemberContent getContent() {
        return content;
    }

    public void setContent(RoomMemberContent content) {
        this.content = content;
    }
}
