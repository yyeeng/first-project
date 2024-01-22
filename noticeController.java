package com.hompage.login.controller;

import com.hompage.login.domain.Posting;
import com.hompage.login.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class noticeController {

    private PostingService postingService;

    @Autowired
    public noticeController(PostingService postingService){
        this.postingService=postingService;
    }

    @GetMapping("/notice")
    public String notice(Model model){
        List<Posting> notice = postingService.findAll(); // 해당 메서드는 Notice 목록을 반환하는 메서드로 가정

        model.addAttribute("notice", notice);

        return "notice";
    }

    @GetMapping("/notice/{id}")
    public String getNoticeById(@PathVariable Long id, Model model) {
        // 서버에서 특정 ID의 공지사항 데이터를 가져오는 로직 (실제 데이터베이스 등에서 가져온다고 가정)
        Optional<Posting> postingOptional = postingService.postid(id);
        Posting posting = postingOptional.orElse(null);
        model.addAttribute("posting", posting);



        return "noticepage"; // notice.html 템플릿으로 이동
    }

    @GetMapping("/notice-write")
    public String noticewrite(){return "notice-write";}

    @PostMapping("/submit_notice")
    String write(@ModelAttribute PostingForm postingForm){
        Posting posting=new Posting();
        posting.setAuthor(postingForm.getAuthor());
        posting.setContent(postingForm.getContent());
        posting.setTitle(postingForm.getTitle());
        posting.setPassword(postingForm.getPassword());
        posting.setCreated_At(LocalDateTime.now());

        if (postingForm.getImage() != null) {
            // 이미지를 저장하고 그에 대한 URL을 데이터베이스에 저장
            String image = postingService.saveImage(postingForm.getImage());
            posting.setImage(image);
        }
        if (postingForm.getVideo() != null) {
            // 이미지를 저장하고 그에 대한 URL을 데이터베이스에 저장
            String video = postingService.saveVideo(postingForm.getVideo());
            posting.setVideo(video);
        }

        if (postingForm.getFile() != null) {
            // 파일을 저장하고 그에 대한 URL을 데이터베이스에 저장
            String file = postingService.saveFile(postingForm.getFile());
            posting.setFile(file);
        }

        posting.setLink(postingForm.getLink());
        postingService.postsave(posting);


        return "redirect:/notice";
    }
    @GetMapping("/notice/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Posting> postingOptional = postingService.postid(id);
        Posting posting = postingOptional.orElse(null);
        model.addAttribute("posting", posting);

        return "editnotice"; // editnotice.html 템플릿으로 이동
    }





}


