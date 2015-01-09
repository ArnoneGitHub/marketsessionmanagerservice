package eu.geyser.marketplace.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import eu.geyser.marketplace.db.model.Bid;
import eu.geyser.marketplace.db.model.Invoice;
import eu.geyser.marketplace.db.model.Marketsession;
import eu.geyser.marketplace.db.model.Offer;
import eu.geyser.marketplace.db.model.Transaction;

import javax.persistence.*;

/**
*
* @author Diego Arnone <diego.arnone@eng.it>
*/


public class MarketSessionManagerREST {
	
	private static final String PERSISTENCE_UNIT_NAME = "marketplacedatabase";
	EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	protected EntityManager getEntityManager() {
	      return factory.createEntityManager();
	    } 
	protected EntityManager em = getEntityManager();
	
	@Path("/marketplace/{marketServiceType}/{timeframe}/{marketId}/marketSessions/{sessionId}/offers/unchecked")
	@POST
	@Consumes ({"application/xml", "application/json"})
	public void uncheckedOffers(
			@PathParam("marketServiceType") String marketServiceType, 
			@PathParam("timeframe") String timeframe, 
			@PathParam("marketId") String marketId, 
			@PathParam("sessionId") String sessionId,
			List <Offer> offers) {
	 
	}
	 
	
	@Path("/marketplace/{marketServiceType}/{timeframe}/{marketId}/marketSessions/{sessionId}/bids/unchecked")
	@POST
	@Consumes ({"application/xml", "application/json"})
	public void uncheckedBids(
			@PathParam("marketServiceType") String marketServiceType, 
			@PathParam("timeframe") String timeframe, 
			@PathParam("marketId") String marketId, 
			@PathParam("sessionId") String sessionId,
			List <Bid> bids) {
	}
	
	
	@Path("/marketplace/{marketServiceType}/{timeframe}/{marketId}/marketSessions/{sessionId}/transactions/")
	@GET
	@Produces({"application/xml", "application/json"})
	public List <Transaction> transactions(
			@PathParam("marketServiceType") String marketServiceType, 
			@PathParam("timeframe") String timeframe, 
			@PathParam("marketId") String marketId, 
			@PathParam("sessionId") String sessionId) {
	
	Class<Marketsession> entityClass = Marketsession.class;
	Marketsession mark_ses = em.find(entityClass, sessionId);
	return mark_ses.getTransactions();
	}
	
	
	@Path("/marketplace/{marketServiceType}/{timeframe}/{marketId}/marketSessions/{sessionId}/transactions/invoices")
	@GET
	@Produces({"application/xml", "application/json"})
	public  List <Invoice> invoices(
			@PathParam("marketServiceType") String marketServiceType, 
			@PathParam("timeframe") String timeframe, 
			@PathParam("marketId") String marketId, 
			@PathParam("sessionId") String sessionId) {

	 
	Class<Marketsession> entityClass = Marketsession.class;
	Marketsession mark_ses = em.find(entityClass, sessionId);
	List <Transaction> transactions = mark_ses.getTransactions();
	ListIterator<Transaction> li = transactions.listIterator();
	List<Invoice> invoice_list = new ArrayList<Invoice>();
	while (li.hasNext()){
		invoice_list.addAll(((Transaction)li.next()).getInvoices());	
	}
	return invoice_list;
	}
	
}



 
