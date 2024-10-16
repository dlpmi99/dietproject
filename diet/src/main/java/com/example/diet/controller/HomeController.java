package com.example.diet.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.diet.entity.DietEntity;
import com.example.diet.entity.InfoEntity;
import com.example.diet.entity.MenuEntity;
import com.example.diet.entity.SupportEntity;
import com.example.diet.repository.DietRepository;
import com.example.diet.repository.InfoRepository;
import com.example.diet.repository.SupportBoardRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.sql.Blob;
import javax.sql.rowset.serial.SerialBlob;


@Controller
@RequestMapping("member")
public class HomeController {
	
	/* dependency injection */
	private final DietRepository dietRepository;
	private final InfoRepository infoRepository;
	private final SupportBoardRepository supportBoardRepository;
	

	public HomeController(DietRepository dietRepository, InfoRepository infoRepository,
			SupportBoardRepository supportBoardRepository) {
		super();
		this.dietRepository = dietRepository;
		this.infoRepository = infoRepository;
		this.supportBoardRepository = supportBoardRepository;
	}
	

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/user")
	public String userForm(Model model, @RequestParam(value="msg",required=false) String msg) {
		model.addAttribute("msg", msg);
		return "userForm";
	}
	
	@PostMapping("/user")
	public String userInput(DietEntity entity, Model model, HttpSession session) {
		System.out.println(entity);
		List<MenuEntity> list1 = dietRepository.findByBloodAndCholesterol(entity.getBlood(), entity.getCholesterol(),
				"조식");
		for (MenuEntity e : list1) {
			System.out.println(e);
		}

		List<MenuEntity> list2 = dietRepository.findByBloodAndCholesterol(entity.getBlood(), entity.getCholesterol(),
				"중식");
		for (MenuEntity e : list2) {
			System.out.println(e);
		}

		List<MenuEntity> list3 = dietRepository.findByBloodAndCholesterol(entity.getBlood(), entity.getCholesterol(),
				"석식");
		for (MenuEntity e : list3) {
			System.out.println(e);
		}
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		
		session.setAttribute("blood",entity.getBlood());
		
		session.setAttribute("cholesterol",entity.getCholesterol());
		 return "result";
	}
	
	
	@GetMapping("/health")
	public String health() {
		return "health";
	}
	
	
	@GetMapping("/result")
	public String result() {
		return "result";
	}
	
	@GetMapping("/support")
	public String support() {
		return "support";
	}
	
	@GetMapping("/supportboard")
	public String supportboard(Model model) {
		List<SupportEntity> list=  supportBoardRepository.findAll();
		model.addAttribute("list" ,list);
		
		return "supportboard";
	}
	
	@PostMapping("/createsupportboard")
	public String createSupporBoard( @RequestParam("spName") String name,
	        @RequestParam("spTitle") String title,
	        @RequestParam("spContents") String content,
	        @RequestParam("spFile") MultipartFile file, // 파일 파라미터 추가
	        Model model) {
	    try {
	    	System.out.println("spName:"+name);
	    	System.out.println("spTitle:"+title);
	    	System.out.println("spContents:"+content);
	    	System.out.println("spFile:"+file.getOriginalFilename());
	    	
	    	
            // SupportEntity 객체 생성 후 데이터 설정
            SupportEntity support = new SupportEntity();
            support.setSpName(name);
            support.setSpTitle(title);
            support.setSpContents(content);
            support.setCreatedAt(new Date()); // 현재 시간 설정

            // 파일 저장 처리
            if (!file.isEmpty()) {
                Blob blob = new javax.sql.rowset.serial.SerialBlob(file.getBytes());
                support.setSpFile(blob);
            }

            // 데이터베이스에 저장
            supportBoardRepository.save(support);

            model.addAttribute("message", "글이 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "글 저장 중 오류가 발생했습니다.");
        }

        return "redirect:/supportboard"; // 저장 후 게시판 페이지로 이동
    }

	@GetMapping("/supportboard/detail/{supportNo}")
    public String getSupportDetail(@PathVariable("supportNo") Long supportNo, Model model) {
        // supportNo를 사용해 데이터베이스에서 게시물 찾기
        SupportEntity support = supportBoardRepository.findById(supportNo).orElseThrow(() -> 
            new IllegalArgumentException("해당 게시물을 찾을 수 없습니다: " + supportNo));
        
        // 모델에 해당 게시물 정보 추가
        model.addAttribute("support", support);
        
        return "supportDetail"; // supportDetail.html 페이지로 이동
    }
	
	@PostMapping("/supportboard/update/{supportNo}")
	public String updateBoard(@PathVariable("supportNo") Long supportNo,
			@RequestParam("spName") String name,
	        @RequestParam("spTitle") String title,
	        @RequestParam("spContents") String content) {
		SupportEntity support = supportBoardRepository.findById(supportNo).orElseThrow(() -> 
        new IllegalArgumentException("해당 게시물을 찾을 수 없습니다: " + supportNo));
		
		support.setSpTitle(title);
		support.setSpName(name);
		support.setSpContents(content);

		supportBoardRepository.save(support);
		
		return "redirect:/supportboard"; // 저장 후 게시판 페이지로 이동
	}
	
	
	    @GetMapping("/printhealth")
	    public String printHealth(DietEntity entity, HttpSession session, Model model, RedirectAttributes rttr) throws IOException, java.io.IOException {
	        // 세션에서 속성 가져오기
	        Double blood = (Double) session.getAttribute("blood");
	        Double cholesterol = (Double) session.getAttribute("cholesterol");
	        System.out.println("세션정보: 혈당:"+ blood+", 콜레스테롤:"+cholesterol);
			/*
			 * if (blood == null) { rttr.addAttribute("msg","건강정보를 먼저 입력등록하세요."); return
			 * "redirect:/user"; }
			 */

	        // 저장소에서 데이터 조회
	        InfoEntity infoEntity = infoRepository
	                .findHealthInfoByBloodAndCholesterol(blood, cholesterol)
	                .orElseThrow(() -> new RuntimeException("정보를 찾을 수 없습니다."));
	        System.out.println(infoEntity);
	        model.addAttribute("title", infoEntity.getTitle());
	        String formattedInfo = infoEntity.getInfo().replace("\n", "</br>");
	        model.addAttribute("info", formattedInfo);
	        
	        System.out.println(entity);
			List<MenuEntity> list1 = dietRepository.findByBloodAndCholesterol(entity.getBlood(), entity.getCholesterol(),
					"조식");
			for (MenuEntity e : list1) {
				System.out.println(e);
			}

			List<MenuEntity> list2 = dietRepository.findByBloodAndCholesterol(entity.getBlood(), entity.getCholesterol(),
					"중식");
			for (MenuEntity e : list2) {
				System.out.println(e);
			}

			List<MenuEntity> list3 = dietRepository.findByBloodAndCholesterol(entity.getBlood(), entity.getCholesterol(),
					"석식");
			for (MenuEntity e : list3) {
				System.out.println(e);
			}
			model.addAttribute("list1", list1);
			model.addAttribute("list2", list2);
			model.addAttribute("list3", list3);
			
			session.setAttribute("blood",entity.getBlood());
			
			session.setAttribute("cholesterol",entity.getCholesterol());
	        
			
			
	        return "printhealth";
	      
	    }
	
	
	

}
