<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="functions.*" %>
<%@page import="java.sql.*" %> 
<%@page import="java.io.*" %> 
<%@page import="twitter4j.TwitterException" %>

<!DOCTYPE html>
<html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tweet Analytics</title>
        
        <link href="css/MainPage.css" rel="stylesheet" type="text/css" />
        <link href="css/jquery.fullPage.css" rel="stylesheet" type="text/css"/>
        <script src='http://code.jquery.com/jquery-1.7.1.min.js'></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
        <script type="text/javascript" src="jquery/jquery.fullPage.js"></script>
        
        <script type="text/javascript">
		$(document).ready(function() {
			$('#fullpage').fullpage({
                                slidesColor: ['#44c8f5', '#4BBFC3'],
				anchors: ['main', 'about'],
                                scrollingSpeed: 800,
                                afterLoad: function(anchorLink){
                                    if(anchorLink == 'about'){
                                        $('#section2').find('.intro').delay(500).animate({
                                                left: '0%'
                                        }, 1500, 'easeOutExpo');
                                        $('#section2').find('.intro2').delay(600).animate({
                                                left: '0%'
                                        }, 1500, 'easeOutExpo');
                                        $('#section2').find('.intro3').delay(700).animate({
                                                left: '0%'
                                        }, 1500, 'easeOutExpo');
                                        $('#section2').find('.intro4').delay(800).animate({
                                                left: '0%'
                                        }, 1500, 'easeOutExpo');
                                    }
				}
			});
		});
	</script>
        
        <script>
            $(document).ready(function(){  

                  var FieldLength1 = $("input#tweetkeyword").val().length;
                  var FieldLength2 = $("input#keyword_1").val().length;
                  var FieldLength3 = $("input#keyword_2").val().length;
                  var FieldLength4 = $("input#keyword_3").val().length;

                  var ButtonEnablerDisabler = function(){         
                        if(FieldLength1 > 0 && FieldLength2 > 0 && FieldLength3 > 0 && FieldLength4 > 0){
                          $('#analyzebutton').removeAttr("disabled");
                        }else{
                          $('#analyzebutton').attr("disabled","disabled");
                        }
                  }        

                  ButtonEnablerDisabler();            

                  $('input#tweetkeyword').keyup(function(){ 
                        FieldLength1 = $("input#tweetkeyword").val().length;
                        ButtonEnablerDisabler();
                  });
                  $('input#keyword_1').keyup(function(){ 
                        FieldLength2 = $("input#keyword_1").val().length;
                        ButtonEnablerDisabler();
                  });
                  $('input#keyword_2').keyup(function(){ 
                        FieldLength3 = $("input#keyword_2").val().length;
                        ButtonEnablerDisabler();
                  });
                  $('input#keyword_3').keyup(function(){ 
                        FieldLength4 = $("input#keyword_3").val().length;
                        ButtonEnablerDisabler();
                  });
                });
        </script> 
        
        <script> 
            $(document).ready(function(){
                $("#slidebutton").click(function(){
                  $("#config").slideToggle("fast");
                });
            });
        </script>
                
    </head>
    <body>
        
        <%  
            try {
                String connectionURL = "jdbc:mysql://localhost:3306/tweets?zeroDateTimeBehavior=convertToNull";
                Connection connection = null; 
                Class.forName("com.mysql.jdbc.Driver").newInstance(); 
                connection = DriverManager.getConnection(connectionURL, "root", "");
                connection.close();
            }catch(Exception ex){
        %>
                <script type='text/javascript'>alert('unable to access database');</script>
        <%
            }
        %>
                   
        <div id="fullpage">
            
            <div class="section" id="section1">
                <div id="header1"></div>
                <div id="citybg"></div>
                <div id="title">
                    <h1>Tweet Analytics</h1>
                </div>
                <div class="slide active" data-anchor="main">
                    <div id="query">
                        <form action="#main/result" method="post">
                            <input type="text" id="tweetkeyword" name="tweetkeyword" placeholder="Tweet Keyword" />
                            <input type="text" id="keyword_1" name="keyword_1" placeholder="Traffic Keyword" />
                            <input type="text" id="keyword_2" name="keyword_2" placeholder="Closed Road Keyword" />
                            <input type="text" id="keyword_3" name="keyword_3" placeholder="Traffic Accident Keyword" />
                            <div id="configPanel">
                                <div id="config">
                                    <div id="algorithm">
                                        <h1>Algorithm</h1>
                                        <input type="radio" name="algorithm" value="BM" id="BM" checked>Boyer-Moore
                                        <input type="radio" name="algorithm" value="KMP">KMP
                                    </div>
                                    <div id="connection">
                                        <h1>Connection</h1>
                                        <input type="radio" name="connection" value="Online" id="OL" checked>Online
                                        <input type="radio" name="connection" value="Offline">Offline
                                    </div>
                                </div>
                                <div id="slidebutton"><h1>Config</h1></div>
                            </div>
                            <input type="submit" id="analyzebutton" value="Analyze"/>
                        </form>  
                    </div>
                                      
                </div>
                <div class="slide" data-anchor="result">
                    <h1 data-menuanchor="main">
                        <a href="#main"><div id="back"></div></a>
                    </h1>
                    <div class="custom_scrollbar" id="wrapper">
                        <h1>
                            <%!
                                String tweetKeyword;
                                String[] explodedKeyword1;
                                String[] explodedKeyword2;
                                String[] explodedKeyword3;
                                String[] combinedKeyword;
                            %>
                            <%
                                String link;
                                Category Macet = new Category();
                                Category Tutup = new Category();
                                Category Kecelakaan = new Category();
                                Category Unknown = new Category();
                                tweets t = new tweets();
                                String algorithm = request.getParameter("algorithm");
                                String connection = request.getParameter("connection");  
                                String tweetKeywordTemp = request.getParameter("tweetkeyword");
                                String keyword1 = request.getParameter("keyword_1");
                                String keyword2 = request.getParameter("keyword_2");
                                String keyword3 = request.getParameter("keyword_3");
                                int i;
                                if(tweetKeywordTemp != null){
                                     tweetKeyword = tweetKeywordTemp.replaceAll("(; |;| ; | ;|, |,| , | ,)", " ");
                                }
                                if(keyword1 != null){
                                    explodedKeyword1 = keyword1.split("(; |;| ; | ;|, |,| , | ,)");
                                    for (i=0; i<explodedKeyword1.length; i++){
                                        Macet.SetKeywords(explodedKeyword1[i]);
                                    }
                                }
                                if(keyword2 != null){
                                    explodedKeyword2 = keyword2.split("(; |;| ; | ;|, |,| , | ,)");
                                    for (i=0; i<explodedKeyword2.length; i++){
                                        Tutup.SetKeywords(explodedKeyword2[i]);
                                    }
                                }
                                if(keyword3 != null){
                                    explodedKeyword3 = keyword3.split("(; |;| ; | ;|, |,| , | ,)");
                                    for (i=0; i<explodedKeyword3.length; i++){
                                        Kecelakaan.SetKeywords(explodedKeyword3[i]);
                                    }
                                    combinedKeyword = functions.Function.concat(explodedKeyword1, explodedKeyword2, explodedKeyword3);
                                    if(connection.equals("Online")){
                                        try{
                                            t.SetTweet(tweetKeyword);       
                                            String tweet[] = t.GetTweetText();
                                            String user[] = t.GetTweetUser();
                                            for(i = 0; i < tweet.length; i++){
                                                if(!tweet[i].equals("null")){
                                                    if(algorithm.equals("BM")){
                                                       if(Macet.IsKeywords(functions.BM.filter(tweet[i], combinedKeyword))){
                                                                Macet.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                            else if(Kecelakaan.IsKeywords(functions.BM.filter(tweet[i], combinedKeyword))){
                                                                Kecelakaan.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                            else if(Tutup.IsKeywords(functions.BM.filter(tweet[i], combinedKeyword))){
                                                                Tutup.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                            else{
                                                                Unknown.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                    }else{
                                                        if(algorithm.equals("KMP")){
                                                           if(Macet.IsKeywords(functions.KMP.filter(tweet[i], combinedKeyword))){
                                                                Macet.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                            else if(Kecelakaan.IsKeywords(functions.KMP.filter(tweet[i], combinedKeyword))){
                                                                Kecelakaan.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                            else if(Tutup.IsKeywords(functions.KMP.filter(tweet[i], combinedKeyword))){
                                                                Tutup.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                            else{
                                                                Unknown.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            out.println("MACET");
                                            out.println("<BR>");
                                            out.println("Jumlah: " + Macet.tweets.size());
                                            out.println("<BR>");
                                            out.println("Lokasi : "); 
                                            out.println("<BR>");
                                            if(Macet.tempat.size() == 0){
                                                out.println("-");
                                                out.println("<BR>");
                                                out.println("<BR>");
                                            }
                                            for(i=0; i<Macet.tempat.size(); i++){
                                                out.print(Macet.tempat.get(i));
                                                if(i < Macet.tempat.size()-1){
                                                    out.print("; ");
                                                }
                                                else{
                                                    out.println();
                                                    out.println("<BR>");
                                                    out.println("<BR>");
                                                }
                                            }
                                            for(i=0; i<Macet.tweets.size(); i++){
                                                out.println("<a target=\"_blank\" href=\"https://www.twitter.com/"+Macet.user.get(i)+"\">@"+ Macet.user.get(i) + "</a> : " + Macet.tweets.get(i));
                                                out.println("<BR>");
                                                out.println("<BR>");
                                            }
                                        

                                            out.println("KECELAKAAN");
                                            out.println("<BR>");
                                            out.println("Jumlah: " + Kecelakaan.tweets.size());
                                            out.println("<BR>");
                                            out.println("Lokasi : "); 
                                            out.println("<BR>");
                                            if(Kecelakaan.tempat.size() == 0){
                                                out.println("-");
                                                out.println("<BR>");
                                                out.println("<BR>");
                                            }
                                            for(i=0; i<Kecelakaan.tempat.size(); i++){
                                                out.print(Kecelakaan.tempat.get(i));
                                                if(i < Kecelakaan.tempat.size()-1){
                                                    out.print("; ");
                                                }
                                                else{
                                                    out.println();
                                                    out.println("<BR>");
                                                    out.println("<BR>");
                                                }
                                            }
                                            for(i=0; i<Kecelakaan.tweets.size(); i++){
                                                out.println("<a target=\"_blank\" href=\"https://www.twitter.com/"+Kecelakaan.user.get(i)+"\">@"+ Kecelakaan.user.get(i) + "</a> : " + Kecelakaan.tweets.get(i));    
                                                out.println("<BR");
                                                out.println("<BR>");
                                            }

                                            out.println("TUTUP");
                                            out.println("<BR>");
                                            out.println("Jumlah: " + Tutup.tweets.size());
                                            out.println("<BR>");
                                            out.println("Lokasi : "); 
                                            out.println("<BR>");
                                            if(Tutup.tempat.size() == 0){
                                                out.println("-");
                                                out.println("<BR>");
                                                out.println("<BR>");
                                            }
                                            for(i=0; i<Tutup.tempat.size(); i++){
                                                out.print(Tutup.tempat.get(i));
                                                if(i < Tutup.tempat.size()-1){
                                                    out.print("; ");
                                                }
                                                else{
                                                    out.println();
                                                    out.println("<BR>");
                                                    out.println("<BR>");
                                                }
                                            }
                                            for(i=0; i<Tutup.tweets.size(); i++){
                                                out.println("<a target=\"_blank\" href=\"https://www.twitter.com/"+Tutup.user.get(i)+"\">@"+ Tutup.user.get(i) + "</a> : " + Tutup.tweets.get(i));
                                                out.println("<BR>");
                                                out.println("<BR>");
                                            }

                                            out.println("UNKNOWN");
                                            out.println("<BR>");
                                            out.println("Jumlah: " + Unknown.tweets.size());
                                            out.println("<BR>");
                                            out.println("Lokasi : "); 
                                            out.println("<BR>");
                                            if(Unknown.tempat.size() == 0){
                                                out.println("-");
                                                out.println("<BR>");
                                                out.println("<BR>");
                                            }
                                            for(i=0; i<Unknown.tempat.size(); i++){
                                                out.print(Unknown.tempat.get(i));
                                                if(i < Unknown.tempat.size()-1){
                                                    out.print("; ");
                                                }
                                                else{
                                                    out.println();
                                                    out.println("<BR>");
                                                    out.println("<BR>");
                                                }
                                            }
                                            for(i=0; i<Unknown.tweets.size(); i++){
                                                   out.println("<a target=\"_blank\" href=\"https://www.twitter.com/"+Unknown.user.get(i)+"\">@"+ Unknown.user.get(i) + "</a> : " + Unknown.tweets.get(i));
                                                   out.println("<BR>");
                                                   out.println("<BR>");
                                            }
                                        }catch(TwitterException e){
                            %>
                                            <script type='text/javascript'>alert('unable to connect to twitter');</script>
                            <%
                                        }
                                    }else{
                                        if(connection.equals("Offline")){
                                            t.SetTweetFromDatabase();       
                                            String tweet[] = t.GetTweetText();
                                            String user[] = t.GetTweetUser();
                                            for(i=0; i<tweet.length; i++){
                                                if(!tweet[i].equals("null")){
                                                    if(algorithm.equals("BM")){
                                                       if(Macet.IsKeywords(functions.BM.filter(tweet[i], combinedKeyword))){
                                                                Macet.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                            else if(Kecelakaan.IsKeywords(functions.BM.filter(tweet[i], combinedKeyword))){
                                                                Kecelakaan.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                            else if(Tutup.IsKeywords(functions.BM.filter(tweet[i], combinedKeyword))){
                                                                Tutup.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                            else{
                                                                Unknown.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                    }else{
                                                        if(algorithm.equals("KMP")){
                                                           if(Macet.IsKeywords(functions.KMP.filter(tweet[i], combinedKeyword))){
                                                                Macet.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                            else if(Kecelakaan.IsKeywords(functions.KMP.filter(tweet[i], combinedKeyword))){
                                                                Kecelakaan.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                            else if(Tutup.IsKeywords(functions.KMP.filter(tweet[i], combinedKeyword))){
                                                                Tutup.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                            else{
                                                                Unknown.AddTweetUser(user[i], tweet[i]);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            out.println("MACET");
                                            out.println("<BR>");
                                            out.println("Jumlah: " + Macet.tweets.size());
                                            out.println("<BR>");
                                            out.println("Lokasi : "); 
                                            out.println("<BR>");
                                            if(Macet.tempat.size() == 0){
                                                out.println("-");
                                                out.println("<BR>");
                                                out.println("<BR>");
                                            }
                                            for(i=0; i<Macet.tempat.size(); i++){
                                                out.print(Macet.tempat.get(i));
                                                if(i < Macet.tempat.size()-1){
                                                    out.print("; ");
                                                }
                                                else{
                                                    out.println();
                                                    out.println("<BR>");
                                                    out.println("<BR>");
                                                }
                                            }
                                            for(i=0; i<Macet.tweets.size(); i++){
                                                out.println("<a target=\"_blank\" href=\"https://www.twitter.com/"+Macet.user.get(i)+"\">@"+ Macet.user.get(i) + "</a> : " + Macet.tweets.get(i));
                                                out.println("<BR>");
                                                out.println("<BR>");
                                            }
                                        

                                            out.println("KECELAKAAN");
                                            out.println("<BR>");
                                            out.println("Jumlah: " + Kecelakaan.tweets.size());
                                            out.println("<BR>");
                                            out.println("Lokasi : "); 
                                            out.println("<BR>");
                                            if(Kecelakaan.tempat.size() == 0){
                                                out.println("-");
                                                out.println("<BR>");
                                                out.println("<BR>");
                                            }
                                            for(i=0; i<Kecelakaan.tempat.size(); i++){
                                                out.print(Kecelakaan.tempat.get(i));
                                                if(i < Kecelakaan.tempat.size()-1){
                                                    out.print("; ");
                                                }
                                                else{
                                                    out.println();
                                                    out.println("<BR>");
                                                    out.println("<BR>");
                                                }
                                            }
                                            for(i=0; i<Kecelakaan.tweets.size(); i++){
                                                out.println("<a target=\"_blank\" href=\"https://www.twitter.com/"+Kecelakaan.user.get(i)+"\">@"+ Kecelakaan.user.get(i) + "</a> : " + Kecelakaan.tweets.get(i));    
                                                out.println("<BR");
                                                out.println("<BR>");
                                            }

                                            out.println("TUTUP");
                                            out.println("<BR>");
                                            out.println("Jumlah: " + Tutup.tweets.size());
                                            out.println("<BR>");
                                            out.println("Lokasi : "); 
                                            out.println("<BR>");
                                            if(Tutup.tempat.size() == 0){
                                                out.println("-");
                                                out.println("<BR>");
                                                out.println("<BR>");
                                            }
                                            for(i=0; i<Tutup.tempat.size(); i++){
                                                out.print(Tutup.tempat.get(i));
                                                if(i < Tutup.tempat.size()-1){
                                                    out.print("; ");
                                                }
                                                else{
                                                    out.println();
                                                    out.println("<BR>");
                                                    out.println("<BR>");
                                                }
                                            }
                                            for(i=0; i<Tutup.tweets.size(); i++){
                                                out.println("<a target=\"_blank\" href=\"https://www.twitter.com/"+Tutup.user.get(i)+"\">@"+ Tutup.user.get(i) + "</a> : " + Tutup.tweets.get(i));
                                                out.println("<BR>");
                                                out.println("<BR>");
                                            }

                                            out.println("UNKNOWN");
                                            out.println("<BR>");
                                            out.println("Jumlah: " + Unknown.tweets.size());
                                            out.println("<BR>");
                                            out.println("Lokasi : "); 
                                            out.println("<BR>");
                                            if(Unknown.tempat.size() == 0){
                                                out.println("-");
                                                out.println("<BR>");
                                                out.println("<BR>");
                                            }
                                            for(i=0; i<Unknown.tempat.size(); i++){
                                                out.print(Unknown.tempat.get(i));
                                                if(i < Unknown.tempat.size()-1){
                                                    out.print("; ");
                                                }
                                                else{
                                                    out.println();
                                                    out.println("<BR>");
                                                    out.println("<BR>");
                                                }
                                            }
                                            for(i=0; i<Unknown.tweets.size(); i++){
                                                   out.println("<a target=\"_blank\" href=\"https://www.twitter.com/"+Unknown.user.get(i)+"\">@"+ Unknown.user.get(i) + "</a> : " + Unknown.tweets.get(i));
                                                   out.println("<BR>");
                                                   out.println("<BR>");
                                            }
                                        }                                       
                                    }
                                }                              
                            %>
                        </h1>
                    </div>
                </div>
                <div id="footer1">
                    <h1 data-menuanchor="about">
                        <a href="#about">About</a>
                    </h1>
                </div>

            </div>
                        
            <div class="section" id="section2">
                <div id="header2"></div>
                <div id="citybg"></div>
                <div id="title">
                    <div class="intro">
                        <h1>About</h1><br>
                    </div>
                    <div class="intro2">
                        <h2>Rita Sarah - 13512009</h2>
                    </div>
                    <div class="intro3">
                        <h2>Gifari Kautsar - 13512020</h2>
                    </div>
                    <div class="intro4">
                        <h2>Mario Filino - 13512055</h2>
                    </div>
                </div>
                <div id="footer2">
                    <h1>
                        2014 Tweet Analytics
                    </h1>
                </div>
            </div>
                        
    </div>
        
    </body>
</html>
