package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import functions.*;
import java.sql.*;
import java.io.*;
import twitter4j.TwitterException;

public final class MainPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


                                String tweetKeyword;
                                String[] explodedKeyword1;
                                String[] explodedKeyword2;
                                String[] explodedKeyword3;
                                String[] combinedKeyword;
                            
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write(" \n");
      out.write(" \n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>    \n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Tweet Analytics</title>\n");
      out.write("        \n");
      out.write("        <link href=\"css/MainPage.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("        <link href=\"css/jquery.fullPage.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src='http://code.jquery.com/jquery-1.7.1.min.js'></script>\n");
      out.write("        <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js\"></script>\n");
      out.write("\t<script src=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"jquery/jquery.fullPage.js\"></script>\n");
      out.write("        \n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("\t\t$(document).ready(function() {\n");
      out.write("\t\t\t$('#fullpage').fullpage({\n");
      out.write("                                slidesColor: ['#44c8f5', '#4BBFC3'],\n");
      out.write("\t\t\t\tanchors: ['main', 'about'],\n");
      out.write("                                scrollingSpeed: 800,\n");
      out.write("                                afterLoad: function(anchorLink){\n");
      out.write("                                    if(anchorLink == 'about'){\n");
      out.write("                                        $('#section2').find('.intro').delay(500).animate({\n");
      out.write("                                                left: '0%'\n");
      out.write("                                        }, 1500, 'easeOutExpo');\n");
      out.write("                                        $('#section2').find('.intro2').delay(600).animate({\n");
      out.write("                                                left: '0%'\n");
      out.write("                                        }, 1500, 'easeOutExpo');\n");
      out.write("                                        $('#section2').find('.intro3').delay(700).animate({\n");
      out.write("                                                left: '0%'\n");
      out.write("                                        }, 1500, 'easeOutExpo');\n");
      out.write("                                        $('#section2').find('.intro4').delay(800).animate({\n");
      out.write("                                                left: '0%'\n");
      out.write("                                        }, 1500, 'easeOutExpo');\n");
      out.write("                                    }\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\t</script>\n");
      out.write("        \n");
      out.write("        <script>\n");
      out.write("            $(document).ready(function(){  \n");
      out.write("\n");
      out.write("                  var FieldLength1 = $(\"input#tweetkeyword\").val().length;\n");
      out.write("                  var FieldLength2 = $(\"input#keyword_1\").val().length;\n");
      out.write("                  var FieldLength3 = $(\"input#keyword_2\").val().length;\n");
      out.write("                  var FieldLength4 = $(\"input#keyword_3\").val().length;\n");
      out.write("\n");
      out.write("                  var ButtonEnablerDisabler = function(){         \n");
      out.write("                        if(FieldLength1 > 0 && FieldLength2 > 0 && FieldLength3 > 0 && FieldLength4 > 0){\n");
      out.write("                          $('#analyzebutton').removeAttr(\"disabled\");\n");
      out.write("                        }else{\n");
      out.write("                          $('#analyzebutton').attr(\"disabled\",\"disabled\");\n");
      out.write("                        }\n");
      out.write("                  }        \n");
      out.write("\n");
      out.write("                  ButtonEnablerDisabler();            \n");
      out.write("\n");
      out.write("                  $('input#tweetkeyword').keyup(function(){ \n");
      out.write("                        FieldLength1 = $(\"input#tweetkeyword\").val().length;\n");
      out.write("                        ButtonEnablerDisabler();\n");
      out.write("                  });\n");
      out.write("                  $('input#keyword_1').keyup(function(){ \n");
      out.write("                        FieldLength2 = $(\"input#keyword_1\").val().length;\n");
      out.write("                        ButtonEnablerDisabler();\n");
      out.write("                  });\n");
      out.write("                  $('input#keyword_2').keyup(function(){ \n");
      out.write("                        FieldLength3 = $(\"input#keyword_2\").val().length;\n");
      out.write("                        ButtonEnablerDisabler();\n");
      out.write("                  });\n");
      out.write("                  $('input#keyword_3').keyup(function(){ \n");
      out.write("                        FieldLength4 = $(\"input#keyword_3\").val().length;\n");
      out.write("                        ButtonEnablerDisabler();\n");
      out.write("                  });\n");
      out.write("                });\n");
      out.write("        </script> \n");
      out.write("        \n");
      out.write("        <script> \n");
      out.write("            $(document).ready(function(){\n");
      out.write("                $(\"#slidebutton\").click(function(){\n");
      out.write("                  $(\"#config\").slideToggle(\"fast\");\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("                \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        ");
  
            try {
                String connectionURL = "jdbc:mysql://localhost:3306/tweets?zeroDateTimeBehavior=convertToNull";
                Connection connection = null; 
                Class.forName("com.mysql.jdbc.Driver").newInstance(); 
                connection = DriverManager.getConnection(connectionURL, "root", "");
                connection.close();
            }catch(Exception ex){
        
      out.write("\n");
      out.write("                <script type='text/javascript'>alert('unable to access database');</script>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("                   \n");
      out.write("        <div id=\"fullpage\">\n");
      out.write("            \n");
      out.write("            <div class=\"section\" id=\"section1\">\n");
      out.write("                <div id=\"header1\"></div>\n");
      out.write("                <div id=\"citybg\"></div>\n");
      out.write("                <div id=\"title\">\n");
      out.write("                    <h1>Tweet Analytics</h1>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"slide active\" data-anchor=\"main\">\n");
      out.write("                    <div id=\"query\">\n");
      out.write("                        <form action=\"#main/result\" method=\"post\">\n");
      out.write("                            <input type=\"text\" id=\"tweetkeyword\" name=\"tweetkeyword\" placeholder=\"Tweet Keyword\" />\n");
      out.write("                            <input type=\"text\" id=\"keyword_1\" name=\"keyword_1\" placeholder=\"Traffic Keyword\" />\n");
      out.write("                            <input type=\"text\" id=\"keyword_2\" name=\"keyword_2\" placeholder=\"Closed Road Keyword\" />\n");
      out.write("                            <input type=\"text\" id=\"keyword_3\" name=\"keyword_3\" placeholder=\"Traffic Accident Keyword\" />\n");
      out.write("                            <div id=\"configPanel\">\n");
      out.write("                                <div id=\"config\">\n");
      out.write("                                    <div id=\"algorithm\">\n");
      out.write("                                        <h1>Algorithm</h1>\n");
      out.write("                                        <input type=\"radio\" name=\"algorithm\" value=\"BM\" id=\"BM\" checked>Boyer-Moore\n");
      out.write("                                        <input type=\"radio\" name=\"algorithm\" value=\"KMP\">KMP\n");
      out.write("                                    </div>\n");
      out.write("                                    <div id=\"connection\">\n");
      out.write("                                        <h1>Connection</h1>\n");
      out.write("                                        <input type=\"radio\" name=\"connection\" value=\"Online\" id=\"OL\" checked>Online\n");
      out.write("                                        <input type=\"radio\" name=\"connection\" value=\"Offline\">Offline\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                <div id=\"slidebutton\"><h1>Config</h1></div>\n");
      out.write("                            </div>\n");
      out.write("                            <input type=\"submit\" id=\"analyzebutton\" value=\"Analyze\"/>\n");
      out.write("                        </form>  \n");
      out.write("                    </div>\n");
      out.write("                                      \n");
      out.write("                </div>\n");
      out.write("                <div class=\"slide\" data-anchor=\"result\">\n");
      out.write("                    <h1 data-menuanchor=\"main\">\n");
      out.write("                        <a href=\"#main\"><div id=\"back\"></div></a>\n");
      out.write("                    </h1>\n");
      out.write("                    <div class=\"custom_scrollbar\" id=\"wrapper\">\n");
      out.write("                        <h1>\n");
      out.write("                            ");
      out.write("\n");
      out.write("                            ");

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
                            
      out.write("\n");
      out.write("                                            <script type='text/javascript'>alert('unable to connect to twitter');</script>\n");
      out.write("                            ");

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
                            
      out.write("\n");
      out.write("                        </h1>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div id=\"footer1\">\n");
      out.write("                    <h1 data-menuanchor=\"about\">\n");
      out.write("                        <a href=\"#about\">About</a>\n");
      out.write("                    </h1>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("                        \n");
      out.write("            <div class=\"section\" id=\"section2\">\n");
      out.write("                <div id=\"header2\"></div>\n");
      out.write("                <div id=\"citybg\"></div>\n");
      out.write("                <div id=\"title\">\n");
      out.write("                    <div class=\"intro\">\n");
      out.write("                        <h1>About</h1><br>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"intro2\">\n");
      out.write("                        <h2>Rita Sarah - 13512009</h2>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"intro3\">\n");
      out.write("                        <h2>Gifari Kautsar - 13512020</h2>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"intro4\">\n");
      out.write("                        <h2>Mario Filino - 13512055</h2>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div id=\"footer2\">\n");
      out.write("                    <h1>\n");
      out.write("                        2014 Tweet Analytics\n");
      out.write("                    </h1>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("                        \n");
      out.write("    </div>\n");
      out.write("        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
