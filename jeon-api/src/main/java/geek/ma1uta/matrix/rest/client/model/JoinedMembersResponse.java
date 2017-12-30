package geek.ma1uta.matrix.rest.client.model;

import geek.ma1uta.matrix.RoomId;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class JoinedMembersResponse {

    private Map<RoomId, RoomMember> joined;
}
