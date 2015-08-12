package com.ca.ejb;


import com.ca.model.Card;
import com.ca.model.Tempcard;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;


public class TempCardEJB {
      @PersistenceContext private EntityManager em;
      
       private List<Card> lstCard;
       
    protected EntityManager getEntityManager() {
        return em;
    }

       
    @GET
    public List<Tempcard> getTempCard(Card c){
         Query createNamedQuery = getEntityManager().createNamedQuery("Tempcard.findByCardId");
          createNamedQuery.setParameter("cardId", c.getCardId());
          
          if (createNamedQuery.getResultList().size() > 0) {
            List<Tempcard> lst = createNamedQuery.getResultList();
            
            return lst;
          }
           else {
            return null;
        }      
    }
    
      public List<Card> getCards() {
        return lstCard;
    }
    
    
}
