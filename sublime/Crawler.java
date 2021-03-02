import java.util.List;
import java.util.ArrayList;
import java.net.URI;

interface HtmlParser {
  // Return a list of all urls from a webpage of given url.
  // This is a blocking call, that means it will do HTTP request and return when this request is finished.
  public List<String> getUrls(String url);
}

// 爬虫线程（相当于原始的dfs方法）
public class Crawler extends Thread {
    String startUrl; // 当前url
    String hostname; // 域名
    HtmlParser htmlParser; // 爬虫接口
    // 返回结果
    public static volatile List<String> res = new ArrayList<>();
    // 初始化线程
    public Crawler(String startUrl, String hostname, HtmlParser htmlParser){
        this.startUrl = startUrl;
        this.hostname = hostname;
        this.htmlParser = htmlParser;
    }
    @Override 
    public void run(){
        // 获得当前url的域名
        String host=URI.create(startUrl).getHost();
        // 如果当前域名不属于目标网站，或者当前域名已经爬过，略过
        if(!host.equals(hostname) || res.contains(startUrl)){
            return;
        }
        // 将当前url加入结果集
        res.add(startUrl);
        // 记录当前url页面包含的链接
        // 每个链接启动一个新的线程继续dfs
        List<Thread> threads = new ArrayList<>();
        for(String s: htmlParser.getUrls(startUrl)){
            Crawler crawler = new Crawler(s, hostname, htmlParser);
            crawler.start();
            threads.add(crawler);
        }
        // 等待每个子线程执行结束后，再结束当前线程
        for(Thread t: threads){
            joinThread(t);
        }
    }
    
    public static void joinThread(Thread thread){
        try{
            thread.join();
        } catch(InterruptedException e){
        }
    }
}

class Solution {
   public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        // 取得startUrl的域名
        String host = URI.create(startUrl).getHost();
        // 新建一个线程，爬取startUrl中的所有链接
        Crawler crawler = new Crawler(startUrl, host, htmlParser);
        // 初始化线程的返回结果
        crawler.res = new ArrayList<>();
        // 开启线程（相当于从起点开始dfs）
        crawler.start();
        // 等待线程执行结束
        Crawler.joinThread(crawler);
        // 返回线程的执行结果
        return crawler.res;
    }
}