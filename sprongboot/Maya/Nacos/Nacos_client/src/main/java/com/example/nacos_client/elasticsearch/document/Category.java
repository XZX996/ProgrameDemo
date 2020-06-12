package com.example.nacos_client.elasticsearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "how2java",type = "product")
public class Category implements Serializable {

    @Id
    private int code;
    //analyzer：分词器名称
    //ik_max_word：会将文本做最细粒度的拆分
    //ik_smart：会将文本做最粗粒度的拆分
    //@Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.Text)
    private String price;
    private String name;
    private String place;
    private String category;


}
