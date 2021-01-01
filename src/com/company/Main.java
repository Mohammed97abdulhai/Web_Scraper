package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<ArticleModel> articlesList;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        articlesList = new ArrayList<>();

        //connect to the site to get the information
        Document doc = Jsoup.connect("https://aliqtisadi.com/").get();

        //get the category names to fetch the first 8 articles from each category later
        Element mainBar = doc.getElementById("megamenu-news");
        Elements categories = mainBar.getElementsByAttributeValue("role", "menuitem");

        //id for database storage
        int id =2;

        //iterate over the categories of the articles
        for (Element category : categories)
        {
            Element a = category.select("a").first();
            String categoryName = a.attr("title"); // get category name
            String articleURL = a.attr("href");     // get the link for the website with the category specified

            int last_id = add_articles(articleURL,categoryName, id);

            id = last_id++;
        }

    }

    //this function gets the first 8 articles from each category url and add them to the golbal list that contains all the articles
    public static int add_articles(String categoryURL, String categoryName, int id) throws IOException, ClassNotFoundException {

        Document doc = Jsoup.connect(categoryURL).get();
        Elements div = doc.getElementsByClass("page-article");
        for ( Element article: div)
        {

            String title = article.getElementsByTag("h2").text();  // get the article title
            String details = article.getElementsByTag("p").text();      // get the whoel article details
            System.out.println("title:" + title + " category: "+ categoryName);

            ArticleModel articleModel = new ArticleModel(title,details,categoryName);
            DataBaseUtility.insert(id,title, details, categoryName); //each article we fetch we insert it into the database we created in the spring application
            articlesList.add(articleModel);
            id++;
        }
        return id;
    }
}
