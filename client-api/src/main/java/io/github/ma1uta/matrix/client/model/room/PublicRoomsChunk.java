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

package io.github.ma1uta.matrix.client.model.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Public rooms chunk.
 */
@Schema(
    description = "Public rooms chunk."
)
public class PublicRoomsChunk {

    /**
     * Aliases of the room. May be empty.
     */
    @Schema(
        description = "Aliases of the room. May be empty."
    )
    private List<String> aliases;

    /**
     * The canonical alias of the room, if any.
     */
    @Schema(
        description = "The canonical alias of the room, if any."
    )
    @JsonProperty("canonical_alias")
    private String canonicalAlias;

    /**
     * The name of the room, if any.
     */
    @Schema(
        description = "The name of the room, if any."
    )
    private String name;

    /**
     * Required. The number of members joined to the room.
     */
    @Schema(
        description = "The number of members joined to the room."
    )
    @JsonProperty("num_joined_members")
    private Long numJoinedMembers;

    /**
     * Required. The ID of the room.
     */
    @Schema(
        description = "The ID of the room."
    )
    @JsonProperty("room_id")
    private String roomId;

    /**
     * The topic of the room, if any.
     */
    @Schema(
        description = "The topic of the room, if any."
    )
    private String topic;

    /**
     * Required. Whether the room may be viewed by guest users without joining.
     */
    @Schema(
        description = "Whether the room may be viewed by guest users without joining."
    )
    @JsonProperty("world_readable")
    private Boolean worldReadable;

    /**
     * Required. Whether guest users may join the room and participate in it. If they can, they will be subject to ordinary power
     * level rules like any other user.
     */
    @Schema(
        description = "Whether guest users may join the room and participate in it. If they can, "
            + "they will be subject to ordinary power level rules like any other user."
    )
    @JsonProperty("guest_can_join")
    private Boolean guestCanJoin;

    /**
     * The URL for the room's avatar, if one is set.
     */
    @Schema(
        description = "The URL for the room's avatar, if one is set."
    )
    @JsonProperty("avatar_url")
    private String avatarUrl;

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public String getCanonicalAlias() {
        return canonicalAlias;
    }

    public void setCanonicalAlias(String canonicalAlias) {
        this.canonicalAlias = canonicalAlias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumJoinedMembers() {
        return numJoinedMembers;
    }

    public void setNumJoinedMembers(Long numJoinedMembers) {
        this.numJoinedMembers = numJoinedMembers;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Boolean getWorldReadable() {
        return worldReadable;
    }

    public void setWorldReadable(Boolean worldReadable) {
        this.worldReadable = worldReadable;
    }

    public Boolean getGuestCanJoin() {
        return guestCanJoin;
    }

    public void setGuestCanJoin(Boolean guestCanJoin) {
        this.guestCanJoin = guestCanJoin;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
