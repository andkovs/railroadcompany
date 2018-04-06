package com.railroad.controller.rest;

import com.railroad.core.service.BoardService;
import com.railroad.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardRestController {

    private final
    BoardService boardService;

    @Autowired
    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * Process a request and returns board dto with json format.
     *
     * @param stationId station id.
     * @return board dto with json format.
     */
    @CrossOrigin
    @GetMapping("/rest/board/{id}")
    public BoardDto getBoardByStationId(@PathVariable("id") Long stationId) {
        return boardService.getBoardByStationId(stationId);
    }

    /**
     * Process a request and returns list of  board dto's with json format.
     *
     * @return list of board dto's with json format.
     */
    @CrossOrigin
    @GetMapping("/rest/board")
    public List<BoardDto> getBoards() {
        return boardService.getBoards();
    }

}
