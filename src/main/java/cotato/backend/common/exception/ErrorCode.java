package cotato.backend.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	//400
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", "COMMON-001"),
	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "요청 파라미터가 잘못되었습니다.", "COMMON-002"),
    INVALID_FILTER(HttpStatus.BAD_REQUEST, "잘못된 필터 조건입니다.", "COMMON-003"),

    //404
    NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없습니다.", "COMMON-004"),

    // 409
    DUPLICATE_APPLICATION(HttpStatus.CONFLICT, "이미 해당 기수에 지원한 서류가 존재합니다.", "COMMON-005"),  // 추가
    ALREADY_LIKED(HttpStatus.CONFLICT, "이미 좋아요를 눌렀습니다.", "COMMON-006"),  // 추가

    //500
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다.", "COMMON-007"),
	;

	private final HttpStatus httpStatus;
	private final String message;
	private final String code;
}