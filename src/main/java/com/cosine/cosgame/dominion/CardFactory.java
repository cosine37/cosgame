package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.base.*;
import com.cosine.cosgame.dominion.dominion.*;
import com.cosine.cosgame.dominion.intrigue.*;
import com.cosine.cosgame.dominion.seaside.*;
import com.cosine.cosgame.dominion.prosperity.*;
import com.cosine.cosgame.dominion.oriental.*;
import com.cosine.cosgame.dominion.entertainment.*;

public class CardFactory {
	public CardFactory() {
		
	}
	
	public Card createCard(String cardname) {
		if (cardname.equals("Empty")) return new Empty();
		
		if (cardname.equals("Copper")) return new Copper();
		if (cardname.equals("Silver")) return new Silver();
		if (cardname.equals("Gold")) return new Gold();
		if (cardname.equals("Estate")) return new Estate();
		if (cardname.equals("Duchy")) return new Duchy();
		if (cardname.equals("Province")) return new Province();
		if (cardname.equals("Curse")) return new Curse();
		
		if (cardname.equals("Smithy")) return new Smithy();
		if (cardname.equals("Village")) return new Village();
		if (cardname.equals("Market")) return new Market();
		if (cardname.equals("Laboratory")) return new Laboratory();
		if (cardname.equals("Festival")) return new Festival();
		if (cardname.equals("Woodcutter")) return new Woodcutter();
		if (cardname.equals("Gardens")) return new Gardens();
		if (cardname.equals("Moneylender")) return new Moneylender();
		if (cardname.equals("Vassal")) return new Vassal();
		if (cardname.equals("Cellar")) return new Cellar();
		if (cardname.equals("Chapel")) return new Chapel();
		if (cardname.equals("Poacher")) return new Poacher();
		if (cardname.equals("Throne Room")) return new ThroneRoom();
		if (cardname.equals("Workshop")) return new Workshop();
		if (cardname.equals("Artisan")) return new Artisan();
		if (cardname.equals("Remodel")) return new Remodel();
		if (cardname.equals("Mine")) return new Mine();
		if (cardname.equals("Library")) return new Library();
		if (cardname.equals("Merchant")) return new Merchant();
		if (cardname.equals("Council Room")) return new CouncilRoom();
		if (cardname.equals("Sentry")) return new Sentry();
		if (cardname.equals("Harbinger")) return new Harbinger();
		if (cardname.equals("Witch")) return new Witch();
		if (cardname.equals("Moat")) return new Moat();
		if (cardname.equals("Bureaucrat")) return new Bureaucrat();
		if (cardname.equals("Militia")) return new Militia();
		
		if (cardname.equals("Shanty Town")) return new ShantyTown();
		if (cardname.equals("Duke")) return new Duke();
		if (cardname.equals("Harem")) return new Harem();
		if (cardname.equals("Steward")) return new Steward();
		if (cardname.equals("Nobles")) return new Nobles();
		if (cardname.equals("Pawn")) return new Pawn();
		if (cardname.equals("Trading Post")) return new TradingPost();
		if (cardname.equals("Upgrade")) return new Upgrade();
		if (cardname.equals("Baron")) return new Baron();
		if (cardname.equals("Ironworks")) return new Ironworks();
		if (cardname.equals("Courtyard")) return new Courtyard();
		if (cardname.equals("Patrol")) return new Patrol();
		if (cardname.equals("Conspirator")) return new Conspirator();
		if (cardname.equals("Courtier")) return new Courtier();
		if (cardname.equals("Lurker")) return new Lurker();
		if (cardname.equals("Mining Village")) return new MiningVillage();
		if (cardname.equals("Mill")) return new Mill();
		if (cardname.equals("Wishing Well")) return new WishingWell();
		if (cardname.equals("Bridge")) return new Bridge();
		if (cardname.equals("Secret Passage")) return new SecretPassage();
		
		if (cardname.equals("Caravan")) return new Caravan();
		if (cardname.equals("Fishing Village")) return new FishingVillage();
		if (cardname.equals("Merchant Ship")) return new MerchantShip();
		if (cardname.equals("Wharf")) return new Wharf();
		if (cardname.equals("Tactician")) return new Tactician();
		if (cardname.equals("Island")) return new Island();
		if (cardname.equals("Warehouse")) return new Warehouse();
		if (cardname.equals("Treasure Map")) return new TreasureMap();
		if (cardname.equals("Explorer")) return new Explorer();
		if (cardname.equals("Native Village")) return new NativeVillage();
		if (cardname.equals("Pearl Diver")) return new PearlDiver();
		if (cardname.equals("Bazaar")) return new Bazaar();
		if (cardname.equals("Lighthouse")) return new Lighthouse();
		if (cardname.equals("Haven")) return new Haven();
		if (cardname.equals("Smugglers")) return new Smugglers();
		if (cardname.equals("Treasury")) return new Treasury();
		if (cardname.equals("Salvager")) return new Salvager();
		if (cardname.equals("Embargo")) return new Embargo();
		
		if (cardname.equals("Monument")) return new Monument();
		if (cardname.equals("Forge")) return new Forge();
		if (cardname.equals("Worker's Village")) return new WorkersVillage();
		if (cardname.equals("City")) return new City();
		if (cardname.equals("Expand")) return new Expand();
		if (cardname.equals("Bank")) return new Bank();
		
		if (cardname.equals("Pendant for Wine")) return new PendantForWine();
		if (cardname.equals("Army Drummer")) return new ArmyDrummer();
		if (cardname.equals("Exile")) return new Exile();
		if (cardname.equals("Temple Fair")) return new TempleFair();
		if (cardname.equals("Lantern Exhibition")) return new LanternExhibition();
		if (cardname.equals("Corrupted Official")) return new CorruptedOfficial();
		if (cardname.equals("Dragon Boat")) return new DragonBoat();
		if (cardname.equals("Tomb Sweeping")) return new TombSweeping();
		if (cardname.equals("Executioner")) return new Executioner();
		if (cardname.equals("Paper Maker")) return new PaperMaker();
		if (cardname.equals("Quadrangle")) return new Quadrangle();
		if (cardname.equals("Field Reaper")) return new FieldReaper();
		if (cardname.equals("Firework Show")) return new FireworkShow();
		if (cardname.equals("Imperial Exam")) return new ImperialExam();
		if (cardname.equals("Swallow Fleet")) return new SwallowFleet();
		if (cardname.equals("Pisces Jade")) return new PiscesJade();
		if (cardname.equals("Compass")) return new Compass();
		if (cardname.equals("Frontier State")) return new FrontierState();
		
		if (cardname.equals("Catan Island")) return new CatanIsland();
		
		return null;
	}
	
	public Card createCard(String cardname, Player p) {
		Card card = createCard(cardname);
		if (card != null) {
			card.setPlayer(p);
			return card;
		} else {
			return null;
		}
		
	}
	
	public List<Card> createCards(String cardname, int n) {
		List<Card> cardlist = new ArrayList<Card>();
		for (int i=0;i<n;i++) {
			cardlist.add(createCard(cardname));
		}
		return cardlist;
	}
	
}
