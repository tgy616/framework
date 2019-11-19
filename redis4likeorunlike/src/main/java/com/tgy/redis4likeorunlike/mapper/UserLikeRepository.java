package com.tgy.redis4likeorunlike.mapper;

import com.tgy.redis4likeorunlike.dto.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author DragonSwimDiving
 * @program redis4likeorunlike
 * @Date 2019-11-18 15:29
 **/
public interface UserLikeRepository {
    UserLike save(UserLike userLike);

    List<UserLike> saveAll(List<UserLike> list);

    Page<UserLike> findByLikedUserIdAndStatus(String likedUserId, Integer code, Pageable pageable);

    Page<UserLike> findByLikedPostIdAndStatus(String likedPostId, Integer code, Pageable pageable);

    UserLike findByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId);

}
