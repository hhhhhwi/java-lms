package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.StudentRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sessionService")
public class SessionService {
	@Resource(name = "sessionRepository")
	SessionRepository sessionRepository;

	@Resource(name = "studentRepository")
	StudentRepository studentRepository;

	public void enroll(Long sessionId, NsUser nsUser, Payment payment) {
		Session session = sessionRepository.findById(sessionId).convert();
		session.enroll(nsUser, payment);

		sessionRepository.updateNumberOfStudent(sessionId, session.getNumberOfStudent() + 1);

		studentRepository.save(new Student(nsUser, session));
	}
}
