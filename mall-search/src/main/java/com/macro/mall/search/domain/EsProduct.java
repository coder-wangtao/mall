package com.macro.mall.search.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 搜索商品的信息
 * Created by macro on 2018/6/19.
 */
@Data
@EqualsAndHashCode
@Document(indexName = "pms") // Elasticsearch 索引名 pms
@Setting(shards = 1,replicas = 0) // shards = 1 → 分片数量 replicas = 0 → 副本数量
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    private Long id;
    @Field(type = FieldType.Keyword) // 不分词，做过滤/聚合更快
    private String productSn;
    private Long brandId;
    @Field(type = FieldType.Keyword) // 不分词，做过滤/聚合更快
    private String brandName;
    private Long productCategoryId;
    @Field(type = FieldType.Keyword) // 不分词，做过滤/聚合更快
    private String productCategoryName;
    private String pic;
    @Field(analyzer = "ik_max_word",type = FieldType.Text) // 使用 IK 分词器（中文分词）
    private String name;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String subTitle;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String keywords;
    private BigDecimal price;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;
    @Field(type = FieldType.Nested, fielddata = true) // 嵌套对象
    private List<EsProductAttributeValue> attrValueList;
}
