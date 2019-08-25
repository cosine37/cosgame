package com.cosine.cosgame.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cosine.cosgame.dominion.Board;
import com.cosine.cosgame.dominion.DominionMega;
import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;
import com.cosine.cosgame.dominion.dominion.Dominion;
import com.cosine.cosgame.dominion.intrigue.Intrigue;
import com.cosine.cosgame.dominion.oriental.Oriental;
import com.cosine.cosgame.dominion.seaside.Seaside;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class DominionListController {
	@RequestMapping(value="/dominionlist/allcards", method = RequestMethod.POST)
	public ResponseEntity<List<Expansion>> allcards(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		Board board = new Board();
		board.getBoardFromDB(boardId);
		List<Expansion> expansions = board.getCardList().getExpansions();
		return new ResponseEntity<>(expansions, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominionlist/getused", method = RequestMethod.POST)
	public ResponseEntity<List<Pile>> getused(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		Board board = new Board();
		board.getBoardFromDB(boardId);
		List<Pile> piles = board.getCardList().getIncluded();
		return new ResponseEntity<>(piles, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominionlist/getselected", method = RequestMethod.POST)
	public ResponseEntity<List<List<Integer>>> getselected(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		Board board = new Board();
		board.getBoardFromDB(boardId);
		List<List<Integer>> selected = board.getCardList().getSelected();
		return new ResponseEntity<>(selected, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominionlist/setselected", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setselected(HttpServletRequest request, @RequestParam String selectedJson){
		//System.out.println(selectedJson);
		List<String> tls = Arrays.asList(selectedJson.split("],"));
		List<List<Integer>> selected = new ArrayList<>();
		List<Integer> a;
		for (int i=0;i<tls.size();i++) {
			String s = tls.get(i);
			s = s.replace("[", "");
			s = s.replace("]", "");
			s = s.replace(",", "");
			System.out.println(s);
			a = new ArrayList<>();
			for (int j=0;j<s.length();j++) {
				a.add(s.charAt(j) - '0');
			}
			selected.add(a);
		}
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		Board board = new Board();
		board.getBoardFromDB(boardId);
		board.getCardList().setSelected(selected);
		board.updateDB("cardList", board.getCardList().getSelectedDoc());
		
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
}
