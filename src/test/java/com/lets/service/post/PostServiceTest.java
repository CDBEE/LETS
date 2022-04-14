package com.lets.service.post;

import com.lets.domain.likePost.LikePost;
import com.lets.domain.likePost.LikePostRepository;
import com.lets.domain.post.Post;
import com.lets.domain.post.PostRepository;
import com.lets.domain.post.PostStatus;
import com.lets.domain.postTechStack.PostTechStack;
import com.lets.domain.postTechStack.PostTechStackRepository;
import com.lets.domain.tag.Tag;
import com.lets.domain.tag.TagRepository;
import com.lets.domain.user.User;
import com.lets.security.oauth2.AuthProvider;
import com.lets.web.dto.post.PostResponseDto;
import com.lets.web.dto.post.PostSaveRequestDto;
import com.lets.web.dto.post.PostSearchRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @InjectMocks
    PostService postService;
    @Mock
    TagRepository tagRepository;

    @Mock
    PostRepository postRepository;

    @Mock
    PostTechStackRepository postTechStackRepository;

    @Mock
    LikePostRepository likePostRepository;

    User user = User.createUser("user1", "123", AuthProvider.google, null);
    Post post = Post.createPost(user, "title1", "content1");
    List<Post> posts = Arrays.asList(post);
    Tag tag = Tag.createTag("spring");
    PostTechStack postTechStack = PostTechStack.createPostTechStack(tag, post);
    List<PostTechStack> postTechStacks = Arrays.asList(postTechStack);
    List<String> tags = Arrays.asList("spring");
    List<LikePost> likePosts = Arrays.asList(LikePost.createLikePost(user, post));
    HashSet<Post> postSet = new HashSet<>();

    @Test
    @Transactional
    void 단일게시글_조회(){

        PostResponseDto postResponseDto = postService.savePost(user, new PostSaveRequestDto("title1", "content1", tags));

        System.out.println(postResponseDto);;

        //PostCommentResponseDto post = postService.findPost(id);

        post.toString();

    }

    @Test
    void searchPosts(){

        //given
        postSet.add(post);

        given(postTechStackRepository.findPostTechStacks(any(), any()))
                .willReturn(postTechStacks);


        //when
        List<PostResponseDto> postResponseDtos = postService.searchPosts(new PostSearchRequestDto(PostStatus.RECRUITING.toString(), tags), PageRequest.of(0, 20));

        //then
        assertThat(postResponseDtos.size()).isEqualTo(1);
    }
    @Test
    void findPosts(){
        //given
        given(postTechStackRepository.findAllByUser(any()))
                .willReturn(postTechStacks);
        //when
        List<PostResponseDto> postResponseDtos = postService.findPosts(user);

        //then
        assertThat(postResponseDtos.size()).isEqualTo(1);
    }
    @Test
    void findPostsTags(){
        //given

        //when
        List<PostResponseDto> postResponseDtos = postService.findPostsTags(postTechStacks);

        //then
        assertThat(postResponseDtos.size()).isEqualTo(1);
    }


}
