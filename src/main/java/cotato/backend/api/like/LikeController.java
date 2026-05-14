package cotato.backend.api.like;

import cotato.backend.api.like.dto.LikeRequest;
import cotato.backend.api.like.dto.LikeResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.like.application.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<DataResponse<LikeResponse>> like(@RequestBody @Valid LikeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(DataResponse.created(likeService.like(request)));
    }
}
