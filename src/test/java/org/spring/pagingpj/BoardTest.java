package org.spring.pagingpj;

import org.junit.jupiter.api.Test;
import org.spring.pagingpj.dto.BoardDto;
import org.spring.pagingpj.entity.BoardEntity;
import org.spring.pagingpj.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BoardTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void insert(){

        BoardDto boardDto=new BoardDto();
        boardDto.setBoardWriter("작성자10");
        boardDto.setBoardTitle("제목10");
        boardDto.setBoardContent("내용10");

        BoardEntity boardEntity= BoardEntity.toBoardEntity(boardDto);
       Long boardId= boardRepository.save(boardEntity).getId();

      Optional<BoardEntity> optionalBoardEntity
              = Optional.ofNullable(boardRepository.findById(boardId).orElseThrow(() -> {
          return new IllegalArgumentException("아이디가 존재하지않습니다");
        }));
      if(optionalBoardEntity.isPresent()){
          BoardEntity boardEntity1= optionalBoardEntity.get();
          System.out.println(boardEntity1.getBoardWriter());
          System.out.println(boardEntity1.getBoardTitle());
          System.out.println(boardEntity1.getBoardContent());
      }

    }

    @Test
    void  boardList(){
        List<BoardDto> boardDtoList=new ArrayList<>();
        List<BoardEntity> boardEntityList = boardRepository.findAll();

        for(BoardEntity boardEntity : boardEntityList){
            BoardDto boardDto=BoardDto.toBoardDto(boardEntity);
            boardDtoList.add(boardDto);
        }
        for(BoardDto boardDto: boardDtoList){
            System.out.println("ID: "+boardDto.getId());
            System.out.println("작성자: "+boardDto.getBoardWriter());
            System.out.println("제목: "+boardDto.getBoardTitle());
            System.out.println("내용: "+boardDto.getBoardContent());
            System.out.println("작성일:" +boardDto.getCreateTime());
            System.out.println("수정일:" +boardDto.getUpdateTime());
        }
    }
    @Test
    void paging(){

        int page =0 ;    //현재 페이지 -> 실제페이지는0부터  ,DB테이블의 기본 1부터
        int pageView =6; // 한 페이지에 보여줄 글 갯수
        Page<BoardEntity> boardEntities =            // 페이지수, 한페이지 보이는View수 , 정렬
                boardRepository.findAll( PageRequest.of(page, pageView, Sort.by(Sort.Direction.DESC, "id"))  );
        // Entity -> Dto  변환
        // View return
//    Page<BoardDto> boardDtos =  boardEntities.map(board ->
//                        new BoardDto(board.getId(), board.getBoardWriter(),
//                        board.getBoardTitle(),  board.getBoardContent()));

        Page<BoardDto> boardDtos1 =  boardEntities.map(BoardDto::toBoardDto);

        for(BoardDto boardDto:boardDtos1){
            System.out.print("id: "+boardDto.getId());
            System.out.print(", 글제목: "+boardDto.getBoardTitle());
            System.out.print(", 글내용: "+boardDto.getBoardContent());
            System.out.println(", 작성자: "+boardDto.getBoardWriter());
        }


    }
}