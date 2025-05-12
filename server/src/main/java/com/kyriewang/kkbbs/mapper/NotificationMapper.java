package com.kyriewang.kkbbs.mapper;

import com.kyriewang.kkbbs.entity.Notification;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationMapper {
    @Select("SELECT * FROM notification WHERE notifier = #{userId} LIMIT #{offset}, #{limit}")
    List<Notification> findNotificationsByUserId(Long userId, Integer offset, Integer limit);

    @Select("SELECT COUNT(*) FROM notification WHERE notifier = #{userId}")
    int countNotificationsByUserId(Long userId);

    @Select("SELECT * FROM notification WHERE id = #{id}")
    Notification findById(Long id);

    @Update("UPDATE notification SET status = 1 WHERE id = #{id}")
    void updateStatus(Long id);
}