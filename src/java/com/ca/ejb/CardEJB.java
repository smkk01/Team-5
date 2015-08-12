
package com.ca.ejb;



import com.ca.model.Card;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;

@Stateless
public class CardEJB {
    
   @PersistenceContext private EntityManager em;
  
    

  
    protected EntityManager getEntityManager() {
        return em;
    }

       
    @GET
    public List<Card> RetrieveCard(){
        return em.createNamedQuery("Card.findAll", Card.class).getResultList();
    }
    
    public Card getCardById(String cardId) {
     
        Card cc = em.find(Card.class, cardId);
        //System.out.println(">>> c = " + cc);
       // System.out.println(">>>> cardId = " + cardId);
//        TypedQuery<Card> query;
//        query = em.createQuery("select c from Card c", Card.class);
//        for (Card c: query.getResultList())
//            if (c.getCardId().equals(cardId))
//                return (c);
//       // System.out.println(">>>> in here: " + cardId);
//        return (null);
//        
//        
//           /*
        Query createNamedQuery = getEntityManager().createNamedQuery("Card.findByCardId");

        createNamedQuery.setParameter("cardId", cardId);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Card) createNamedQuery.getSingleResult();
        }
        else {
            return null;
        }
              
        
        
//        for (Card c: query.getResultList())
//            System.out.println(">>> " + c.getCardId());
        
//        query = em.createQuery("select c from Card c where c.cardId like :cid", Card.class);
//        
//        query.setParameter("cid", cardId);
//        List<Card> c = query.getResultList();
//        System.out.println(">>> size = " + c.size());
//        if (c.size() > 0)
//            return (c.get(0));
//        return (null);
    }
    
    
    public List<Card> RetrieveCardById(Card c){
        Query createNamedQuery = getEntityManager().createNamedQuery("Card.findByCardId");

        createNamedQuery.setParameter("cardId", c.getCardId());
        
        if (createNamedQuery.getResultList().size() > 0) {
            List<Card> lst = createNamedQuery.getResultList();
            return lst;
        }
        else {
            return null;
        }
    }
    
    public void updateStatus(Card a,String strStatus)
    {
        em.flush();
        a.setStatus(strStatus);
        em.merge(a);
        
    }
    public List<Card> RetrieveCardByStatus(String strStatus){
        Query createNamedQuery = getEntityManager().createNamedQuery("Card.findByStatus");

        createNamedQuery.setParameter("status", strStatus);
        
        if (createNamedQuery.getResultList().size() > 0) {
            List<Card> lst = createNamedQuery.getResultList();
            return lst;
        }
        else {
            return null;
        }
    }
    
    
    public void insertStatus(Card a, Card b, Card c){
        System.out.println("insert status===============================");
        a.setStatus("selected");
        b.setStatus("selected");
        c.setStatus("selected");
        em.merge(a);
        em.merge(b);
        em.merge(c);
        
    }
  
}


//When I set the bean as @Stateless I always get the same object ID and counter always increments.
//When I set the bean as @Stateful I get a new instance every time I refresh the page.
//When I set it to @Singleton I get the same results as when I set it to @Stateless: same object ID, counter incrementing.