package nextstep.courses.domain.student;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class Student {
	private Long id;

	private NsUser nsUser;

	private Session session;

	public Student(NsUser nsUser, Session session) {
		this.nsUser = nsUser;
		this.session = session;
	}

	public Long getUserId() {
		return nsUser.getId();
	}

	public Long getSessionId() {
		return session.getId();
	}
}
