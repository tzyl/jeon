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

package io.github.ma1uta.matrix.client.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Groupings.
 */
public class Groupings {

    /**
     * List of groups to request.
     */
    @JsonProperty("group_by")
    private List<Group> groupBy;

    public List<Group> getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(List<Group> groupBy) {
        this.groupBy = groupBy;
    }
}
