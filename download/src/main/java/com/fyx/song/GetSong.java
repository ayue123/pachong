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
                                FileUtils.writeFileWithParent("download/" + id.incrementAndGet() + ".mp4", page.getContent());
                                System.out.println("download:" + page.getUrl());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

        private void getSongIdList() throws IOException {
                BufferedReader br = new BufferedReader(new FileReader("D:\\log\\song.txt"));
                String s;
                while ((s = br.readLine()) != null) {
                        songIdList.add(Integer.parseInt(s));
                }

        }

        public static void main(String[] args) throws Exception {
                GetSong crawler = new GetSong("crawl", "download3");
                //                crawler.getSongIdList();
                //                Links link = new Links();
                //                for (int songId : songIdList) {
                //                        String songUrl = "http://music.163.com/song/media/outer/url?id=" + songId + ".mp3";
                //                        link.add(songUrl);
                //                }
                //                crawler.addSeed(link);
                crawler.addSeed("https://live.bilibili.com/5636118?spm_id_from=333.334.bili_live.5");
                crawler.setThreads(9);
                crawler.setResumable(false);
                crawler.start(10);
        }

}
