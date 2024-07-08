package com.sparta.momoPJT.service;

import com.sparta.momoPJT.dto.MemoRequestDto;
import com.sparta.momoPJT.entity.Memo;
import com.sparta.momoPJT.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto){
        Memo memo = new Memo(requestDto);
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
