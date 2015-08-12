
package com.ca.controller;
import com.ca.ejb.CardEJB;
import com.ca.model.Card;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getCardList")
public class CardController extends HttpServlet{

     @EJB
    private CardEJB cardejb;
     
    // @Inject @ServerSentEventContext("/cardUpdateList")
    //ServerSentEventHandlerContext<CardConnection> connections;
    Card table []= new Card[12];
    
     
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        Card c = new Card();
        
        long startTime = System.currentTimeMillis();
         //List<Card> Card81 = cardejb.RetrieveCard();
        List<Card> Card81 = cardejb.RetrieveCardByStatus("Unselected");
        
//        for(int i=0; i <lstCard.size() ; i++){
//        Collections.shuffle(lstCard);
//        }  
        //Card table []= new Card[12];
        
        List<Card> lstCard = new ArrayList<>();
        lstCard = Card81;
       
        //Remove Blank Card
        lstCard.remove(lstCard.size()-2);
         
        //Collections.shuffle(lstCard);
        
        for (int i=0; i<12; i++)
        {
           Card tempCard = lstCard.get(i);
           cardejb.updateStatus(tempCard,"InUse");    
           table[i]=lstCard.get(i);
           lstCard.remove(i);                 
           
           System.out.println("this is from servlet : "+table[i].getImageName());
            System.out.println("this is from servlet : "+table[i].getCardId());
        }
        JsonArrayBuilder items = Json.createArrayBuilder();
           for (Card inv : table) 
           {
                JsonObjectBuilder item = Json.createObjectBuilder();
                item.add("cardId", inv.getCardId());
                item.add("pImg", inv.getImageName());        
                items.add(item);
           }                   
           
           
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setHeader("CardList", "Success");               
            try (PrintWriter pw = resp.getWriter()) 
            {
                pw.println(items.build().toString());
            }             
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String first = req.getParameter("cardId1");
        String second= req.getParameter("cardId2");
        String third = req.getParameter("cardId3");
        List<Card> lstCard = new ArrayList<Card>();      
        
        Card a = cardejb.getCardById(first);
        lstCard.add(a);
        Card b = cardejb.getCardById(second);
        lstCard.add(b);
        Card c = cardejb.getCardById(third);
        lstCard.add(c);
        System.out.println("Card a========"+a);
        
//        String positionId = "";
//        HashMap<String, List<Card>> map = new HashMap<String, List<Card>>();
//        map.put(positionId,lstCard);
     int pass1 = 0 ;
       if (((a.getNumber().equals(b.getNumber())) && (b.getNumber().equals(c.getNumber()))) ||
             (!(a.getNumber().equals(b.getNumber())) && !(a.getNumber().equals(c.getNumber())) && !(b.getNumber().equals(c.getNumber())))) {
             pass1 = 1;
            System.out.println("Pass1 No1: "+ pass1);
       }else{
            pass1=0;
            System.out.println("Else Pass1 No1:" + pass1);
        }
        if (((a.getShape() == b.getShape()) && (b.getShape() == c.getShape())) ||
               ((a.getShape() != b.getShape()) && (a.getShape() != c.getShape()) && (b.getShape() != c.getShape()))) {
                pass1 ++;    
                  System.out.println("Pass1 No2: "+ pass1);
        }else{
            pass1=0;
            System.out.println(" Else  Pass1 No2:" + pass1);
        }
        if (((a.getShade() == b.getShade()) && (b.getShade() == c.getShade())) ||
                ((a.getShade() != b.getShade()) && (a.getShade() != c.getShade()) && (b.getShade() != c.getShade()))) {
              pass1 ++; 
                System.out.println("Pass1 No3: "+ pass1);
        }else{
            pass1=0;
            System.out.println("Else  Pass1 No3:" + pass1);
        }
        if (((a.getColor() == b.getColor()) && (b.getColor() == c.getColor())) ||
                ((a.getColor() != b.getColor()) && (a.getColor() != c.getColor()) && (b.getColor() != c.getColor()))) {
            pass1 ++;  
              System.out.println("Pass1 No4: "+ pass1);
        }else{
            pass1=0;
            System.out.println("Else  Pass1 No4:" + pass1);
        }
      System.out.println("Pass 1 : "+pass1);
        
        //String status = "selected";
        if(pass1 == 4){
            
            System.out.println("Set Found"); 
            cardejb.updateStatus(a, "Selected");
            cardejb.updateStatus(b, "Selected");
            cardejb.updateStatus(c, "Selected");
            
            //a.table[].getStatus("selected");
           //cardejb.insertStatus(a,b,c);
          //System.out.println("CardEJB InsertStatus"+a);
          //  System.out.println("CardEJB InsertStatus"+b);
          //  System.out.println("CardEJB InsertStatus"+c);
           
              JsonArrayBuilder items = Json.createArrayBuilder();
                for (Card inv : lstCard) 
                {
                    JsonObjectBuilder item = Json.createObjectBuilder();
                    item.add("cardId", inv.getCardId());
                    item.add("pImg", inv.getImageName()); 
                     item.add("isNew","N");
                    items.add(item);
                }
                
                List<Card> Card3 = cardejb.RetrieveCardByStatus("Unselected");
           
                for (int i=0; i<3; i++)
                 {
                    Card tempCard = Card3.get(i);
                    cardejb.updateStatus(tempCard,"InUse"); 

                    JsonObjectBuilder item = Json.createObjectBuilder();
                     item.add("cardId", tempCard.getCardId());
                     item.add("pImg", tempCard.getImageName());        
                     item.add("isNew","Y");
                     items.add(item);

                    System.out.println("this is from servlet : "+table[i].getImageName());
                     System.out.println("this is from servlet : "+table[i].getCardId());
                     System.out.println("**********************************************");
                 }

                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                resp.setHeader("CardList", "Success");               
                try (PrintWriter pw = resp.getWriter()) 
                {
                    pw.println(items.build().toString());
                                  
                }   
        }
        else{
            System.out.println("Not Found");
        }
       // int score;
      //  score++;
         
      //return true;
    //}
    // System.out.println("CardId = "+  first+second+third);  //+ " " + second + " "  + third
    //    resp.setContentType("text/plain");
      // resp.setStatus((HttpServletResponse.SC_OK));
               
              
               
        
        
//        int tempSetList = Integer.parseInt(req.getParameter("tempSetList"));
//        
//         ServerSentEventData data = new ServerSentEventData();
//         JsonObjectBuilder b = Json.createObjectBuilder();
//         b.add("cardId", cardId)
//          .add("tempSetList",tempSetList);
//               
//         data.data(b.build().toString());
//         data.event("current");
//         for(CardConnection c: connections.getHandlers())
//             c.send(data);
        
    }
  
}
    
//       System.out.println("Card1 :  "+a.getNumber());
//       System.out.println("Card1 :  "+a.getColor());
//       System.out.println("Card1 :  "+a.getShade());
//       System.out.println("Card1 :  "+a.getShape());
//       
//       
//       System.out.println("Card2 :  "+b.getNumber());
//       System.out.println("Card2 :  "+b.getColor());
//       System.out.println("Card2 :  "+b.getShade());
//       System.out.println("Card2 :  "+b.getShape());
//       
//       System.out.println("Card3 :  "+c.getNumber());
//       System.out.println("Card3 :  "+c.getColor());
//       System.out.println("Card3 :  "+c.getShade());
//       System.out.println("Card3 :  "+c.getShape());
