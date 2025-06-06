package com.kyriewang.kkbbs.mapper;

import com.kyriewang.kkbbs.dto.CommentDto;
import com.kyriewang.kkbbs.dto.queDto;
import com.kyriewang.kkbbs.entity.Comment;
import com.kyriewang.kkbbs.entity.Question;
import com.kyriewang.kkbbs.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("SELECT * FROM question WHERE creator = #{userId} ")
    List<Question> findQuestionsByUserId(Long userId);

    @Select("SELECT COUNT(*) FROM question WHERE creator = #{userId}")
    int countQuestionsByUserId(Long userId);

    @Select("SELECT * FROM question WHERE id = #{id}")
    List<Question> findById(String id);


//主页初始化获取信息
    @Select("select id, title, description, tag, creator, gmt_create as gmtCreate, gmt_modified as gmtModified, comment_count, view_count, like_count from question order by gmt_create desc limit #{page},#{size}")
    List<Question> getList(int page,int size);
    
    @Select("select id, title, description, tag, creator, gmt_create as gmtCreate, gmt_modified as gmtModified, comment_count, view_count, like_count from question order by gmtCreate desc")
    public List<Question> getq();
    @Select("select c.*, u.name as receiver_name from comment c left join user u on c.receiver_id = u.id where c.parent_id = #{id}")
    List<CommentDto> findPL(int id);
    @Select("select * from question where id = #{id}")
    List<queDto> getallque(int id);

    //    获取帖子作者
    @Select("select userid  from comandqu where queid = #{id}")
    public int getcomuser(int id);

    @Select("select name from user where id = #{id}")
    public String getcomuser2(int id);
//    发新帖子需要给中间表一个修改的数据
    @Select("select id from user where name = #{username} or phonenb = #{username}")
    public int getuserid(String username);
//    倒数第一条数据
    @Select("select id from question order by id desc limit 0,1")
    public int getdescid();

    @Insert("insert into comandqu(userid,queid)values(#{uid},#{qid})")
    public int result(int uid,int qid);
//  我的问题获取
    @Select("select question.id, title, description, tag, creator, gmt_create as gmtCreate, gmt_modified as gmtModified, comment_count, view_count, like_count from question join comandqu on question.id = comandqu.queid and comandqu.userid = #{uid}")
    public List<Question> getUserQue(int uid);

    @Insert("INSERT INTO question (id,title, description, tag, creator, gmt_create, gmt_modified) " +
            "VALUES (#{id},#{title}, #{description}, #{tag}, #{creator}, #{gmtCreate}, #{gmtModified})")
    void insert(Question question);

    @Update("UPDATE question " +
            "SET title = #{title}, description = #{description}, tag = #{tag}, gmt_modified = #{gmtModified} " +
            "WHERE id = #{id}")
    void update(Question question);
//删除提问
    @Delete("DELETE FROM question WHERE id = #{id}")
    void deleteById(Long id);
    @Delete("delete from comandqu where queid = #{id}")
    void cqDeById(Long id);

    @Insert("INSERT INTO comment (parent_id, type, comment_creator, receiver_id, receiver_name, content, gmt_create, gmt_modified, like_count, comment_count) " +
            "VALUES (#{parent_id}, #{type}, #{comment_creator}, #{receiver_id}, #{receiver_name}, #{content}, #{gmt_create}, #{gmt_modified}, 0, 0)")
    void insertComment(Comment comment);
    
    @Delete("DELETE FROM comment WHERE id = #{id}")
    int deleteComment(Long id);
//    修改id
    @Update("updata  from comment set comment_creator = #{id}")
    void changeId(int id);
//    搜索
    @Select("select * from question where title like #{st} ")
    List<Question> sousuo(String st);
    
//    获取热门标签（前5个）
    @Select("SELECT tag, COUNT(*) as tag_count FROM question WHERE tag IS NOT NULL AND tag != '' GROUP BY tag ORDER BY tag_count DESC LIMIT 5")
    List<String> getHotTags();
}