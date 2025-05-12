//package com.kyriewang.kkbbs.service.impl;
//
//import com.kyriewang.kkbbs.dto.NotificationDto;
//import com.kyriewang.kkbbs.entity.Notification;
//import com.kyriewang.kkbbs.mapper.NotificationMapper;
//import com.kyriewang.kkbbs.service.NotificationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class NotificationServiceImpl implements NotificationService {
//
//    @Autowired
//    private NotificationMapper notificationMapper;
//
//    @Override
//    public NotificationDto findNotificationsByUserId(Long userId, Integer page, Integer size) {
//        Integer offset = (page - 1) * size;
//        List<Notification> notificationDtos = notificationMapper.findNotificationsByUserId(userId, offset, size);
//        int totalCount = notificationMapper.countNotificationsByUserId(userId);
//        int totalPages = (int) Math.ceil((double) totalCount / size);
//
//        NotificationDto dto = new NotificationDto();
//        dto.setNotificationDtos(notificationDtos);
//        dto.setCurrentPage(page);
//        dto.setTotalPages(totalPages);
//        return dto;
//    }
//
//    @Override
//    public Notification findById(Long id) {
//        return notificationMapper.findById(id);
//    }
//
//    @Override
//    public void updateStatus(Long id) {
//        notificationMapper.updateStatus(id);
//    }
//}
//