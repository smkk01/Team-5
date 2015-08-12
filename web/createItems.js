function createItems()
{
       var promise = $.getJSON("getCardList");
       promise.done(function(data) {

           //to get how many item do we have
           //console.log(data.length)
           var c = 0;

           var numOfRow =parseInt(12/3);
           var numOfElement = 12;
           
//           //If data.lenght is not empty add one row
//           if(numOfElement%3 != 0)
//               numOfRow++;
           console.log("number of elements ---------> " + numOfElement);
           console.log("number of rows--------> " + numOfRow);

           for(var i=0;i<numOfRow;i++)
           {
               jQuery('<div/>',{
                        id:'row'+i,
                        class:'row'}).appendTo($("#leftContent"));
           }

           for(var j=0;j<numOfRow;j++)
           {
               for(var z=0;z<3;z++)
               {
                   jQuery('<div/>',{
                        id:'Card'+c,
                        class:'col-lg-4 portfolio-item'}).appendTo($("#row"+j));
                    c++;
               }
           }
           for(var iThumb=0; iThumb < numOfElement; iThumb++)
           {
               jQuery('<div/>',{
                        id:'thumb'+iThumb,
                        class:'thumbnail'}).appendTo($("#Card"+iThumb));
           }

           for(var i=0; i < numOfElement; i++)
           {
               jQuery('<div/>',{
                        id:'divImg'+ data[i].cardId
                    }).appendTo($("#thumb"+i));
                    
                    
               jQuery('<img/>',{
                        class:'img-responsive',
                        src: 'image/' + data[i].pImg
                    }).appendTo($("#divImg"+data[i].cardId));

       
              
                       $("#thumb"+i).append(
                       $('<button/>',{
                           id:'btnAdd'+data[i].cardId,
                           class:'btn btn-primary',
                           onclick:'addToSet("' + data[i].cardId+'")'
                          }).text("Add To Set"));                        
                      
                }
         
       }).fail(function(data) {
           console.log("fail ------");
       }).always(function(data) {
           console.log("-----> always");
       });

}

var lstcardId = new Array();
var count = -1;

function addToSet(cardId)
{
    count++;
    lstcardId[count] = cardId;
    
    if(count == 2)   
    {
       
        var isConfirm;
        var isConfirm = confirm("You already chose 3 cards!");
        if (isConfirm == true) {
            // Pass Value Json To Servlet
            
            var param='{'; 
            for(var i = 0; i<=count; i++)
            {
                if(i == 0)
                    param += 'cardId' + i + ":" + lstcardId[i];
                else 
                    param +=  "," + 'cardId'+ i + ":" + lstcardId[i]                
            }
            param += '}';
            
        $.ajax
        ({
            type: 'post', // it's easier to read GET request parameters
            url: 'getCardList',
            dataType: 'JSON',
            data: 
                { 
                cardId1 : lstcardId[0],
                cardId2 : lstcardId[1],
                cardId3 : lstcardId[2]
                },
            success: function(data) 
            {
                alert("Hello Data=========="+data.length);
                alert("Hello Data=========="+JSON.stringify(data));
                alert("Hello Data=========="+jQuery.parseJSON(JSON.stringify(data)));
                var obj  =jQuery.parseJSON(JSON.stringify(data));
                var setRows = document.getElementById("cardList").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
                setRows++;
                
                jQuery('<tr/>',
                        {
                            id:'setRow'+setRows,
                            class:'row'
                        }).appendTo($("#cardList"));
                var lstOldCard3 = new Array();  
                var i = 0;
                
                $.each(obj, function(key,value) {
                    
                    if(value.isNew === 'N')
                    {
                         jQuery('<td/>',
                        {
                            id:'col'+value.cardId,
                            class:'row'
                        }).appendTo($("#setRow"+setRows));
                                                           
                        jQuery('<img/>',{
                            class:'img-responsive',
                            src: 'image/' + value.pImg
                        }).appendTo($("#col" +value.cardId));
                        lstOldCard3[i] = value.cardId;
                        i++;
//                       $("#thumb"+i).append(
//                       $({ id: value.cardId               
//                          }));                            
                    }
                
                  });   
                  var j=0;

                   $.each(obj, function(key,value) {
                    if(value.isNew === 'Y')
                    {
                        
                            var thumId = $("#divImg"+lstOldCard3[j]).parent().prop("id");
                            $("#divImg" + lstOldCard3[j]).remove();
                            $("#btnAdd"+lstOldCard3[j]).remove();
                                  
                            jQuery('<div/>',{
                                    id:'divImg'+ value.cardId
                                }).appendTo($("#"+thumId));


                            jQuery('<img/>',{
                                    class:'img-responsive',
                                    src: 'image/' + value.pImg
                                }).appendTo($("#divImg"+value.cardId));



                                   $("#" + thumId).append(
                                   $('<button/>',{
                                       id:'btnAdd'+value.cardId,
                                       class:'btn btn-primary',
                                       onclick:'addToSet("' + value.cardId+'")'
                                      }).text("Add To Set"));
                         j++;
                    }
                   
                });
                  count = -1;
            }              
       })
       .fail(function() 
        {
            alert("fail");
            count = -1;
        });

    } else {
            alert("You pressed Cancel!");
            count = -1;
    }
  
    }//End count == 2 // already select 3 cards.
}//End AddToSet Method
                                        
//function RemoveSelectedCard(){     
//     var promise = $.post("removeCard");
//     promise.done(function(){
//         count=-1;
//        $("[id^='tblRow']").remove();  
//         alert("already remove selected card on Table");
//     });
//}
//
//
//function ReplaceSelectedCard(){     
//     var promise = $.post("replaceCard");
//     promise.done(function(){
//         count=0;
//         lstcardId[count] = cardId;
//         alert("already replace new card in selected card");
//     });
//}































//
//
        //alert("You already chose 3 cards after!"+param);
            //param =  [{'cardId0':'2','cardId1':'2','cardId2':'2'}]          
            // Pass Value Json To Servlet
            
//            var promise = $.post("getCardList");     
//            promise.done(function(result){
//               
//                alert("You Result!"+result);
//                count = -1;
//                if (result == 1)
//                {
//                   for(var iImg=0; iImg < numOfElement; iImg++)
//                    {
//                        var i = lstcardId[iImg];
//                         JSON.stringify(result[i]);
//                        jQuery('<div/>',{
//                        id:'row'+i,
//                        class:'row'}).appendTo($("#cardList"));
//                    
//                        jQuery('<img/>',{
//                                 class:'img-responsive',
//                                 src: 'image/' + data[i].pImg
//                             }).appendTo($("#thumb"+i));
//                             
//                        
//                    }
//             
//              window.location.href="success.html";
//                }

    
    
//    for (i=0;i<lstcardId.length;i++)
//    {
//            
//            $("#tempSetList"+cardId).val(parseInt($("#tempSetList"+cardId).val())+1);
//            return;
//    }
//
//            lstcardId[count]=cardId;
//            count++;
//          
//            $('#cardList').append(
//            $('<tr/>',{ id:"tblRow"+cardId}).append(
//            $('<td/>').append(
//            $('<button/>',{
//                class:'btn btn-default btn-danger btn-xs',
//                onclick:'addToSet(' + cardId +','+pImg +')'
//            }))).append('<hr/>'));
          

