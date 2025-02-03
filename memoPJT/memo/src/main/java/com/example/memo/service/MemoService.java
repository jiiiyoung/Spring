
package com.example.memo.service;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemoService {
    private static final Logger log = LoggerFactory.getLogger(MemoService.class);
    private final MemoRepository memoRepository;

    /*
    파일 업로드
    - file.dir: 실제 파일 업로드 경로
    - 파일 등록 경로 생성: uploadDir + fileName
    - 파일 이름의 중복을 방지하기 위해 UUID를 이용하여 storeFileName 생성
    https://velog.io/@hyewon0218/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%8C%8C%EC%9D%BC%EC%97%85%EB%A1%9C%EB%93%9C-%EB%B0%A9%EB%B2%95
    https://thecardeveloper.tistory.com/40
    * */

    // Value 애너테이션은 롬북에서 나온 것이 아닌 beans.factory.annotation이다.
//    @Value("${file.upload-dir}")
//    private String uploadDir;

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto){

        int pos = requestDto.getUploadFileName().lastIndexOf('.');
        String storeFileName = UUID.randomUUID()+ requestDto.getUploadFileName().substring(pos);

        Memo memo = Memo.builder()
                .username(requestDto.getUsername())
                .contents(requestDto.getContents())
                .uploadFileName(requestDto.getUploadFileName())
                .storeFileName(storeFileName)
                .build();
        memoRepository.save(memo);
        return memo;
    }

    @Transactional(readOnly = true)
    public List<Memo> getMemos(){
        return memoRepository.findAllByOrderByModifiedAtDesc();

    }

    @Transactional
    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Memo not found"));
        memo.update(requestDto);
        return memo.getId();
    }

    @Transactional
    public Long deleteMemo(Long id) {
        memoRepository.deleteById(id);
        return id;
    }

}
