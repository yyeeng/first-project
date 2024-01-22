package com.hompage.login.service;


import com.hompage.login.domain.Posting;
import com.hompage.login.repository.PostingRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class PostingService {
    private final PostingRepository postingRepository;
    @Autowired
    public PostingService(PostingRepository postingRepository){
        this.postingRepository = postingRepository;
    }
    @Transactional
    public Long postsave(Posting posting) {
        postingRepository.save(posting);
        return posting.getId();
    }
    public Optional<Posting> postid(Long id){
        return postingRepository.findById(id);
    }
    public String saveImage(MultipartFile image) {
        // 이미지를 저장하는 로직
        // 예시: 서버의 특정 경로에 이미지를 저장하고 해당 파일의 URL을 반환
        // 이때 파일명은 중복을 피하기 위해 유니크한 이름으로 저장하는 것이 좋습니다.
        // 예시에서는 현재 시간을 기반으로 한 이름을 사용
        String fileName = "image_" + System.currentTimeMillis() + ".png";
        String filePath = "/path/to/image/directory/" + fileName;

        // 실제로는 File I/O 또는 이미지 업로드 라이브러리 등을 사용하여 저장하는 로직을 추가해야 합니다.

        return "/image/" + fileName; // 이미지 파일의 URL 반환
    }

    public String saveVideo(MultipartFile video) {
        String fileName = "video_" + System.currentTimeMillis() + ".avi";
        String filePath = "/path/to/file/directory/" + fileName;

        return "/file/" + fileName;
    }

    public String saveFile(MultipartFile file) {
        String fileName = "file_" + System.currentTimeMillis() + ".pdf";
        String filePath = "/path/to/file/directory/" + fileName;

        return "/file/" + fileName;
    }
    public List<Posting> findAll() {
        return postingRepository.findAll();
    }

}
