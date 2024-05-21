package nextstep.courses.domain.session;

import nextstep.courses.domain.course.Course;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class PaidSession extends Session {
	private int maxNumberOfStudent;

	private Long fee;

	public PaidSession(Long id, Course course, SessionState sessionState, SessionImage sessionImage, LocalDateTime startDate, LocalDateTime endDate, int numberOfStudent, int maxNumberOfStudent, Long fee) {
		super(id, course, sessionState, sessionImage, startDate, endDate, numberOfStudent);

		if(maxNumberOfStudent <= 0) {
			throw new IllegalArgumentException("유료 강의의 최대 수강 인원은 1 이상이여야 합니다.");
		}

		this.maxNumberOfStudent = maxNumberOfStudent;
		this.fee = fee;
	}

	public int getMaxNumberOfStudent() {
		return maxNumberOfStudent;
	}

	public Long getFee() {
		return fee;
	}

	@Override
	public void enroll(NsUser nsUser, Payment payment) {
		if(super.getNumberOfStudent() >= maxNumberOfStudent) {
			throw new IllegalArgumentException("수강 신청 인원을 초과했습니다.");
		}

		if(payment.isNotEqualsAmount(fee)) {
			throw new IllegalArgumentException("결제 금액과 수강료가 일치하지 않습니다.");
		}

		super.enroll(nsUser, payment);
	}
}
