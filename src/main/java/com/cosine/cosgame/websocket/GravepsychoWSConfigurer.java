package com.cosine.cosgame.websocket;

import java.util.ArrayList;
import java.util.List;

//import javax.websocket.Session;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class GravepsychoWSConfigurer implements WebSocketConfigurer {
	
	class AllBoardsWSHandler implements WebSocketHandler{
		
		List<WebSocketSession> allSessions;
		
		public AllBoardsWSHandler() {
			allSessions = new ArrayList<>();
		}
		
		public void removeClosedSessions() {
			List<WebSocketSession> newSessions = new ArrayList<>();
			for (int i=0;i<allSessions.size();i++) {
				if (allSessions.get(i).isOpen()) {
					newSessions.add(allSessions.get(i));
				}
			}
			allSessions = newSessions;
		}
		
		public void broadcastAllSessions(String content) {
			TextMessage message = new TextMessage(content);
					
			for (int i=0;i<allSessions.size();i++) {
				try {
					if (allSessions.get(i).isOpen()) {
						allSessions.get(i).sendMessage(message);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			allSessions.add(session);
			removeClosedSessions();
		}

		@Override
		public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
			broadcastAllSessions("refresh");
		}

		@Override
		public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
			
		}

		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
			removeClosedSessions();
		}

		@Override
		public boolean supportsPartialMessages() {
			return false;
		}
		
	}
	
	class boardWSHandler implements WebSocketHandler{
		
		List<WebSocketSession> allSessions;
		
		public boardWSHandler() {
			allSessions = new ArrayList<>();
		}
		
		public void removeClosedSessions() {
			List<WebSocketSession> newSessions = new ArrayList<>();
			for (int i=0;i<allSessions.size();i++) {
				if (allSessions.get(i).isOpen()) {
					newSessions.add(allSessions.get(i));
				}
			}
			allSessions = newSessions;
		}
		
		public void broadcastAllSessionsInBoard(String content) {
			TextMessage message = new TextMessage(content);
					
			for (int i=0;i<allSessions.size();i++) {
				try {
					if (allSessions.get(i).isOpen()) {
						allSessions.get(i).sendMessage(message);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			allSessions.add(session);
			removeClosedSessions();
		}

		@Override
		public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
			System.out.println("Message here:" + message.getPayload());
			String messageStr = (String) message.getPayload();
			/*
			if (messageStr.contentEquals("refresh")) {
				broadcastAllSessionsInBoard("refresh");
			}
			*/
			broadcastAllSessionsInBoard(messageStr);
			
		}

		@Override
		public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
			
		}

		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
			removeClosedSessions();
		}

		@Override
		public boolean supportsPartialMessages() {
			return false;
		}
		
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new AllBoardsWSHandler(), "/gravepsycho/allboardsrefresh").setAllowedOrigins("*");
		registry.addHandler(new boardWSHandler(), "/gravepsycho/boardrefresh").setAllowedOrigins("*");
	}

}
