package com.yang.po;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;  //博客标题
    //@Column(name = "content",length = 50,nullable = false)
    private String content;  //博客内容
    private String firstPicture;  //首图
    private String flag;  //标记（原创，转载或者翻译）
    private Integer views; //浏览次数
    private Boolean appreciation; //赞赏功能是否开启
    private Boolean shareStatement; //转载是否开启
    private Boolean commentabled; //评论是否开启
    private Boolean published; //文章是发布还是保存草稿
    private Boolean recommend; //是否推荐
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime; //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime; //更新时间
    @Transient
    private String tagIds;

    private String description;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", tagIds='" + tagIds + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", tags=" + tags +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    private Type type; //Blog是多的一端，一个Blog从属于一个类别

    @ManyToMany(cascade = {CascadeType.PERSIST})//多对多需要有一个中间表来维护关系
    //中间表中最少由两个字段组成，这两个字段作为作为外键指向两张表的主键，同时这两个字段、
    //又组成了联合主键，
    private List<Tag> tags=new ArrayList<>();  //级联新增

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog") //Blog是一的一方，将一的一方称为主表，多的一方称为从表
    //需要在从表上新建一列作为外键，他的取值来源于主表的主键，所以从表可以随意删除，但是主表
    //不可以，删除主表数据之前必须解除要删除的那一行的外键关系。一的一方具有维护外键的作用。
    //mappedBy放弃了外键的维护权，就不会有多余的update语句了；
    //mappedBy的取值是参照的对方的属性名。
    //多表关系的删除时，要格外重视。在一对多的关系中，对于多的一方的数据可以随便删除，但是对于
    //一的一方，有可能他的主键被别人引用作为外键约束，这时一的一方的数据就不能随意删除了。
    //级联删除：操作一个对象的同时，操作他关联的对象（包括级联添加，级联删除，级联更新等）
    //级联删除就是在删除一个Blog的同时，也会级联把这个Blog的所有评论都删除，要慎用。
    //级联操作：1.需要区分操作主体；2.需要在操作主体的实体类上面添加级联属性（需要添加到多表
    // 映射关系的注解上；3.添加cascade配置级联：CascadeType.PERSIST表示级联添加；
    // CascadeType.REMOVE表示级联删除；CascadeType.MERGE表示级联更新；CascadeType.ALL
    // 表示级联所有操作，包括添加，更新和删除）
    private List<Comment> comments=new ArrayList<>();

    public Blog(){}

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
    }

    public Boolean getShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(Boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public Boolean getCommentabled() {
        return commentabled;
    }

    public void setCommentabled(Boolean commentabled) {
        this.commentabled = commentabled;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void init(){
        this.tagIds=tagsToIds(this.getTags());
    }
    private String tagsToIds(List<Tag> tags){
        if (!tags.isEmpty()){
            StringBuffer ids=new StringBuffer();
            boolean flag=false;
            for (Tag tag:tags){
                if (flag){
                    ids.append(",");
                }else {
                    flag=true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }
}
