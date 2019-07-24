package priv.cweiy.pro.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 *java 博客实体
 *
 * @date   2017年8月24日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@Entity
@Table(name="java_boke_model")
public class JavaBokeModel {
    @Id
    @Column(name="id")
    private Long id;
    //标题
    @Column(name="title")
    private String title;

    //链接地址
    @Column(name="linke")
    private String linke;

    //作者
    @Column(name="author")
    private String author;

    //作者主页地址
    @Column(name="author_url")
    private String authorUrl;

    //简介
    @Column(name="summary")
    private String summary;
}