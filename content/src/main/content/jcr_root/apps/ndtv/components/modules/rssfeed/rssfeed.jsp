<%@include file="/apps/ndtv/global/global.jsp"%>

<cq:includeClientLib categories="ndtv.rssfeed"/>

<html>
    <head>


		 <meta charset="UTF-8">
				<title>Sling Servlet</title>

    </head>


<script>

$(document).ready(function() {

    alert("Hii");

    $('#submit').click(function() {
        alert("Submitting...");

        var failure = function(err) {
             alert("Unable to retrive data "+err);
   		};
        //Use JQuery AJAX request to post data to a Sling Servlet
        $.ajax({
            url:'/bin/mySearchServlet?count=1',
            dataType: "text",
             type: 'GET',    
            //data: { count: 200} ,        
             success: function(msg){
                 //var json = jQuery.parseJSON(msg); 
                 //$('#json').val(feedsObj);   
                 var json = JSON.parse(msg);

                var json2 = jQuery.parseJSON(json.json);
                 alert("Title :: " +json2.feeds);
             }
         });
    });

}); // end ready
</script>






<body>

	<h1>Sling Servlet</h1>
              
			<form method="#">
					
					 <table border="1" align="left">

							<tr>
								 <td>
								<label for="count" id="count" >Items</label>
								 </td>
								 <td>
								 <input id="count" name="count"  type="text" value="">
								 </td>
							 </tr>

							 <tr>
								 <td></td>

								  <td>
								<textarea id="json" rows="4" cols="50">
								</textarea>
								 </td>

							 </tr> 
							  
							 <tr>
								 <td></td>
								 <td>
								<input type="button" value="submit"  name="submit" id="submit" >

								 </td>

							 </tr> 

					 </table>

		</form>


</body>

</html>


