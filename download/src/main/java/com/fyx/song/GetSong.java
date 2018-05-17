/**
 * 
 */
package com.fyx.song;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.util.FileUtils;

/**
 * 2018年5月15日
 *
 * @author ayue
 */
public class GetSong extends BreadthCrawler {
        File downloadDir;
        static Set<Integer> songIdList = new HashSet<Integer>();
        static Set<String> songUrlList = new HashSet<String>();

        /*用一个整数，不断自增，来作为下载的图片的文件名*/
        AtomicInteger id = new AtomicInteger(0);

        public GetSong(String crawlPath, String downloadPath) {
                super(crawlPath, true);
                downloadDir = new File(downloadPath);
                if (!downloadDir.exists()) {
                        downloadDir.mkdirs();
                }
        }

        @Override
        public void visit(Page page, CrawlDatums next) {

                //                /*不处理非jpg的网页/文件*/
                //                if (!Pattern.matches(".mp3", page.getUrl())) {
                //                        return;
                //                }
                /*将歌曲内容保存到文件，page.getContent()获取的是文件的byte数组*/
                if (page.getContent().length > 1000000) {
                        try {
                                FileUtils.writeFileWithParent("qqdownload/" + "qwq" + id.incrementAndGet() + ".mp3", page.getContent());
                                System.out.println("download:" + page.getUrl());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

        //通过歌单获取歌曲id（网易云音乐）
        private void getSongIdList() throws IOException {
                //歌单复制的html文件路径
                BufferedReader br = new BufferedReader(new FileReader("D:\\log\\html.txt"));
                //                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\log\\wangyiSongUrl.txt")));
                String s;
                String ss;
                String sss = null;
                //将id附近的字符串替换为*
                String a = "href\\=\"\\/song\\?id\\=";
                String b = "\\*";
                String c = "\"\\>\\<b title\\=";
                while ((s = br.readLine()) != null) {
                        ss = s.replaceAll(a, b);
                        sss = ss.replaceAll(c, b);
                }
                //按*截取
                String[] outResult = sss.split("\\*");
                for (int i = 0; i < outResult.length; i++) {
                        try {
                                //将id添加到idList中
                                int id = Integer.parseInt(outResult[i]);
                                songIdList.add(id);
                        } catch (Exception e) {

                        }
                }
                //拼接url
                //                for (int songId : songIdList) {
                //                        String songUrl = "http://music.163.com/song/media/outer/url?id=" + songId + ".mp3";
                //                        out.write(songUrl + "\n");
                //                }
                br.close();
                //                out.close();
        }

        //获取歌曲Url（所有音乐）
        private void getSongUrl() throws IOException {
                //歌曲url的路径
                BufferedReader br = new BufferedReader(new FileReader("D:\\log\\qqSongUrl.txt"));
                String s;
                while ((s = br.readLine()) != null) {
                        songUrlList.add(s);
                }

        }

        public static void main(String[] args) throws Exception {
                GetSong crawler = new GetSong("crawl", "download3");
                //                crawler.getSongIdList();
                crawler.getSongUrl();
                Links link = new Links();
                //获取歌曲Url
                for (String songUrl : songUrlList) {
                        link.add(songUrl);
                }
                //通过歌单歌曲id拼接为url
                //                for (int songId : songIdList) {
                //                        //拼接下载路径
                //                        String songUrl = "http://music.163.com/song/media/outer/url?id=" + songId + ".mp3";
                //                        link.add(songUrl);
                //                }
                crawler.addSeed(link);
                crawler.setThreads(9);
                crawler.setResumable(false);
                crawler.start(2);
        }

}
