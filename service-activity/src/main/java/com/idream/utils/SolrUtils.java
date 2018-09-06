package com.idream.utils;

import com.alibaba.fastjson.JSON;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

/**
 * @author hejiang
 */
public class SolrUtils {

    //指定solr服务器的地址
    private final static String SOLR_URL = "http://192.168.37.134:8080/solr/";

    /**
     * 创建SolrServer对象
     */
    public HttpSolrClient createSolrServer(String url) {
        HttpSolrClient solr = new HttpSolrClient.Builder(url).withConnectionTimeout(10000).withSocketTimeout(60000).build();
        return solr;
    }


    /**
     * 往索引库添加文档
     */
    public void addDoc() throws SolrServerException, IOException {
        //构造一篇文档
        SolrInputDocument document = new SolrInputDocument();
        //往doc中添加字段,在客户端这边添加的字段必须在服务端中有过定义
        //获得一个solr服务端的请求，去提交  ,选择具体的某一个solr core
        HttpSolrClient solr = createSolrServer(SOLR_URL + "community_info");
        solr.add(document);
        solr.commit();
        solr.close();
    }


    /**
     * 根据id从索引库删除文档
     */
    public void deleteDocumentById() throws Exception {
        //选择具体的某一个solr core
        HttpSolrClient server = createSolrServer(SOLR_URL + "community_info");
        //删除文档
        server.deleteById("8");
        //删除所有的索引
        //solr.deleteByQuery("*:*");
        //提交修改
        server.commit();
        server.close();
    }

    /**
     * 查询
     */
    public void querySolr() throws Exception {
        HttpSolrClient solrServer = createSolrServer(SOLR_URL + "community_info");
        SolrQuery query = new SolrQuery();
        //下面设置solr查询参数
        query.set("q", "address:余杭");// 参数q  查询所有
//        query.set("address","余杭");

        //参数sort,设置返回结果的排序规则
        query.setSort("id", SolrQuery.ORDER.desc);
        //设置分页参数
        query.setStart(0);
        query.setRows(10);//每一页多少值
        //参数hl,设置高亮
        query.setHighlight(true);
        //设置高亮的字段
        query.addHighlightField("communityName");
        //设置高亮的样式
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");

        //获取查询结果
        QueryResponse response = solrServer.query(query);
        //两种结果获取：得到文档集合或者实体对象

        //查询得到文档的集合
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println("查询结果的总数量：" + solrDocumentList.getNumFound());

        System.out.println(JSON.toJSONString(solrDocumentList));
    }
}
