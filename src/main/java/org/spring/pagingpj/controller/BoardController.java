package org.spring.pagingpj.controller;

import lombok.RequiredArgsConstructor;
import org.spring.pagingpj.dto.BoardDto;
import org.spring.pagingpj.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
  public final BoardService boardService;

  @GetMapping("/boardList")
  public String boardList(Model model) {
    List<BoardDto> boardList = boardService.boardListDo();
    model.addAttribute("boardList", boardList);

    return "board/boardList";
  }

  @GetMapping("/pagingList")
  public String pagingList() {

    return "board/pagingList";
  }

  @PostMapping("/pagingList")
  public String pagingList(@PageableDefault(page = 0, size = 6, sort = "id",
          direction = Sort.Direction.DESC) Pageable pageable, Model model) {

    Page<BoardDto> boardList= boardService.boardPagingList(pageable);
    model.addAttribute("boardList",boardList);
return "board/pagingList";
  }
}
