package com.lets.web;

import com.lets.domain.likePost.LikePost;
import com.lets.domain.likePost.LikePostRepository;
import com.lets.domain.post.Post;
import com.lets.domain.post.PostRepository;
import com.lets.domain.postTechStack.PostTechStack;
import com.lets.domain.postTechStack.PostTechStackRepository;
import com.lets.domain.tag.Tag;
import com.lets.domain.tag.TagRepository;
import com.lets.domain.user.User;
import com.lets.domain.user.UserRepository;
import com.lets.exception.ErrorResponse;
import com.lets.security.JwtAuthentication;
import com.lets.security.JwtTokenProvider;
import com.lets.security.UserPrincipal;
import com.lets.security.oauth2.AuthProvider;
import com.lets.service.likePost.LikePostService;
import com.lets.service.post.PostService;
import com.lets.service.postTechStack.PostTechStackService;
import com.lets.service.tag.TagService;
import com.lets.service.user.UserService;
import com.lets.util.CloudinaryUtil;
import com.lets.util.CookieUtil;
import com.lets.util.RedisUtil;
import com.lets.web.dto.*;
import com.lets.web.dto.post.PostResponseDto;
import com.lets.web.dto.user.SettingRequestDto;
import com.lets.web.dto.user.SettingResponseDto;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;

import javax.servlet.http.Cookie;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    TagService tagService;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostTechStackService postTechStackService;

    @Autowired
    PostTechStackRepository postTechStackRepository;

    @Autowired
    LikePostService likePostService;

    @Autowired
    LikePostRepository likePostRepository;

    @Autowired
    CloudinaryUtil cloudinaryUtil;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Autowired
    CookieUtil cookieUtil;

    @Autowired
    RedisUtil redisUtil;

    @SpyBean
    private JwtTokenProvider jwtTokenProvider;

    private User user;
    private UserPrincipal principal;
    private Authentication authentication;
    private String accessToken = "Bearer ";
    private String refreshToken;
    private Cookie refreshTokenCookie;

    @BeforeEach
    void before(){
        user = User.createUser("user1", "1234", AuthProvider.google, "default");

        userRepository.save(user);


        principal = UserPrincipal.create(user);

        authentication = new JwtAuthentication(principal);
        accessToken += jwtTokenProvider.generateRefreshToken(authentication);
        refreshToken = jwtTokenProvider.generateRefreshToken(authentication);
        refreshTokenCookie = cookieUtil.createCookie("refreshToken", refreshToken);

    }

    @AfterEach
    void after(){
        likePostRepository.deleteAllInBatch();
        postTechStackRepository.deleteAllInBatch();
        tagRepository.deleteAllInBatch();
        postRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
        redisUtil.deleteData(refreshToken);
    }
    //security test
    @Test
    public void myPosts_access_token_??????(){
        //given
        HttpHeaders headers = new HttpHeaders();
        String url = "http://localhost:" + port + "/api/users/myPosts";

        //when
        ResponseEntity<Object> res = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Object.class);


        //then
        //ACCESS_TOKEN_NOT_FOUND(UNAUTHORIZED, "?????? ????????? ACCESS_TOKEN??? ???????????? ????????????."),
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

    }




    @Test
    public void myPosts_refresh_token_??????(){
        //given
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  accessToken);
        String url = "http://localhost:" + port + "/api/users/myPosts";
        given(jwtTokenProvider.validateToken(any()))
                .willReturn(false);

        //when
        ResponseEntity<Object> res = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Object.class);


        //then
        //REFRESH_TOKEN_NOT_FOUND(BAD_REQUEST, "????????? REFRESH_TOKEN??? ???????????? ????????????."),
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);



    }


    @Test
    public void myPosts_??????_??????_??????_refresh_token1(){
        //given
        principal = UserPrincipal.create(1L, user);
        authentication = new JwtAuthentication(principal);
        refreshToken = jwtTokenProvider.generateRefreshToken(authentication);
        refreshTokenCookie = cookieUtil.createCookie("refreshToken", refreshToken);
        redisUtil.setData(refreshToken, Long.toString(2L));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  accessToken);
        headers.add("Cookie", "refreshToken=" + refreshTokenCookie.getValue());

        given(jwtTokenProvider.validateToken(any()))
                .willReturn(false);

        String url = "http://localhost:" + port + "/api/users/myPosts";

        //when
        ResponseEntity<Object> res = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Object.class);


        //then
        //INVALID_REFRESH_TOKEN(UNAUTHORIZED, "???????????? REFRESH_TOKEN??? ???????????? ????????????."),
        assertThat(res.getStatusCode()).isEqualTo(UNAUTHORIZED);

    }

    @Test
    public void myPosts_??????_??????_??????_refresh_token2(){
        //given
        principal = UserPrincipal.create(1L, user);
        authentication = new JwtAuthentication(principal);
        refreshToken = jwtTokenProvider.generateRefreshToken(authentication);
        refreshTokenCookie = cookieUtil.createCookie("refreshToken", refreshToken);
        redisUtil.setData(refreshToken, Long.toString(1L));


        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  accessToken);
        headers.add("Cookie", "refreshToken=" + refreshTokenCookie.getValue());

        given(jwtTokenProvider.validateToken(any()))
                .willReturn(false);

        String url = "http://localhost:" + port + "/api/users/myPosts";

        //when
        ResponseEntity<Object> res = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Object.class);


        //then
        //INVALID_REFRESH_TOKEN(UNAUTHORIZED, "REFRESH_TOKEN??? ???????????? ????????????."),
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

    }
    @Test
    public void myPosts_??????_??????_??????(){
        //given
        userRepository.deleteById(user.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  accessToken);

        String url = "http://localhost:" + port + "/api/users/myPosts";

        //when
        ResponseEntity<ErrorResponse> res = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), ErrorResponse.class);

        //then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(res.getBody().getMessage()).isEqualTo("?????? ?????? ????????? ?????? ??? ????????????.");

    }

    @Test
    public void myPosts_??????(){
        //given
        Post post = Post.createPost(user, "title1", "content1");
        postRepository.save(post);

        Tag tag = Tag.createTag("spring");
        tagService.save(tag);

        PostTechStack postTechStack = PostTechStack.createPostTechStack(tag, post);
        postTechStackService.save(postTechStack);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  accessToken);
        String url = "http://localhost:" + port + "/api/users/myPosts";

        //when
        ResponseEntity<List<PostResponseDto>> res = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<PostResponseDto>>() {});

        //then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getBody().size()).isEqualTo(1);

    }

    @Test
    public void myLikes_????????????_??????(){
        //given
        userRepository.deleteById(user.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  accessToken);

        String url = "http://localhost:" + port + "/api/users/myLikes";

        //when
        ResponseEntity<ErrorResponse> res = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), ErrorResponse.class);


        //then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(res.getBody().getMessage()).isEqualTo("?????? ?????? ????????? ?????? ??? ????????????.");

    }

    @Test
    public void myLikes_??????_??????(){
        //given
        Post post = Post.createPost(user, "title1", "content1");
        postRepository.save(post);

        Tag tag = Tag.createTag("spring");
        tagService.save(tag);


        PostTechStack postTechStack = PostTechStack.createPostTechStack(tag, post);
        postTechStackService.save(postTechStack);

        LikePost likePost = LikePost.createLikePost(user, post);
        likePostRepository.save(likePost);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  accessToken);
        String url = "http://localhost:" + port + "/api/users/myLikes";

        //when
        ResponseEntity<List<PostResponseDto>> res = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<PostResponseDto>>() {});

        //then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getBody().size()).isEqualTo(1);

    }

    @Test
    public void setting_??????_??????(){
        //given
        String url = "http://localhost:" + port + "/api/users/setting";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  accessToken);

        //when
        ResponseEntity<SettingResponseDto> res = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), SettingResponseDto.class);


        //then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getBody().getNickname()).isEqualTo(user.getNickname());
        assertThat(res.getBody().getProfile().contains("default"));

    }

    @Test
    public void setting_??????_??????_??????_??????(){
        //given
        userRepository.deleteById(user.getId());
        String url = "http://localhost:" + port + "/api/users/setting";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  accessToken);

        //when
        ResponseEntity<ErrorResponse> res = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), ErrorResponse.class);

        //then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(res.getBody().getMessage()).isEqualTo("?????? ?????? ????????? ?????? ??? ????????????.");

    }

    @Test
    public void setting_??????_??????_??????_??????(){
        //given
        userRepository.deleteById(user.getId());

        String url = "http://localhost:" + port + "/api/users/setting";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  accessToken);

        SettingRequestDto settingRequestDto = new SettingRequestDto("default", user.getNickname(), new ArrayList<>());

        //when
        ResponseEntity<ErrorResponse> res = testRestTemplate.exchange(url, HttpMethod.PATCH, new HttpEntity<>(settingRequestDto, headers), ErrorResponse.class);


        //then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(res.getBody().getMessage()).isEqualTo("?????? ?????? ????????? ?????? ??? ????????????.");

    }


    @Test
    public void setting_??????_??????_?????????_??????(){

        //given
        String url = "http://localhost:" + port + "/api/users/setting";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  accessToken);


        //when
        ResponseEntity<ApiResponseDto> res = testRestTemplate.exchange(url, HttpMethod.PATCH,
                new HttpEntity<>(new SettingRequestDto("KEEP", "user2", Arrays.asList()), headers),
                ApiResponseDto.class);


        //then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getBody().getMessage()).isEqualTo("?????? ?????????????????????.");
        assertThat(userService.findOneById(user.getId()).getNickname()).isEqualTo("user2");
        assertThat(userService.findOneById(user.getId()).getPublicId()).isEqualTo("default");



    }
    @Test
    public void setting_??????_??????_??????_????????????_??????(){

        //given
        String url = "http://localhost:" + port + "/api/users/setting";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  accessToken);


        //when
        ResponseEntity<ApiResponseDto> res = testRestTemplate.exchange(url, HttpMethod.PATCH,
                new HttpEntity<>(new SettingRequestDto("PUBLIC", "user2", Arrays.asList()), headers),
                ApiResponseDto.class);


        //then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getBody().getMessage()).isEqualTo("?????? ?????????????????????.");
        assertThat(userService.findOneById(user.getId()).getNickname()).isEqualTo("user2");
        assertThat(userService.findOneById(user.getId()).getPublicId()).isEqualTo("default");


    }
    @Test
    public void setting_??????_??????_?????????_????????????_??????(){

        //given
        File file = new File("src/test/java/com/lets/tea.jpg");

        String encodedImage = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            encodedImage = Base64.encodeBase64String(fis.readAllBytes());
        }catch(Exception e){
            throw new RuntimeException();
        }

        String url = "http://localhost:" + port + "/api/users/setting";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  accessToken);


        //when
        ResponseEntity<ApiResponseDto> res = testRestTemplate.exchange(url, HttpMethod.PATCH,
                new HttpEntity<>(new SettingRequestDto(encodedImage, "user2", Arrays.asList()), headers),
                ApiResponseDto.class);


        //then
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getBody().getMessage()).isEqualTo("?????? ?????????????????????.");
        assertThat(userService.findOneById(user.getId()).getNickname()).isEqualTo("user2");
        assertThat(userService.findOneById(user.getId()).getPublicId()).isNotEqualTo("default");


    }
}
