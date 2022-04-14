package com.lets.service;

import com.lets.domain.likePost.LikePostRepository;
import com.lets.domain.post.PostRepository;
import com.lets.domain.postTechStack.PostTechStackRepository;
import com.lets.domain.tag.TagRepository;
import com.lets.domain.user.UserRepository;
import com.lets.domain.userTechStack.UserTechStackRepositoryTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PostServiceTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostTechStackRepository postTechStackRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    UserTechStackRepositoryTest userTechStackRepositoryTest;

    @Autowired
    LikePostRepository likePostRepository;

    /*@BeforeEach
    void 데이터_집어넣기(){
        Tag springTag = Tag.createTag("Spring");
        Tag javaTag = Tag.createTag("Java");
        tagRepository.save(springTag);
        tagRepository.save(javaTag);
        UserTechStack springTechStack = UserTechStack.createUserTechStack(springTag);
        UserTechStack javaTechStack = UserTechStack.createUserTechStack(javaTag);
        userTechStackRepository.save(springTechStack);
        userTechStackRepository.save(javaTechStack);
        List<UserTechStack> list = new ArrayList<>();
        list.add(springTechStack);
        list.add(javaTechStack);

        User user = User.createUser("member1", list);
        userRepository.save(user);

        PostTechStack springPTechStack = PostTechStack.createPostTechStack(springTag);
        PostTechStack javaPTechStack = PostTechStack.createPostTechStack(javaTag);
        postTechStackRepository.save(springPTechStack);
        postTechStackRepository.save(javaPTechStack);

        List<PostTechStack> postList = new ArrayList<>();
        postList.add(springPTechStack);
        postList.add(javaPTechStack);

        Post post1 = Post.createPost(user, "title1", "content1", postList);
        Post post2 = Post.createPost(user, "title2", "content2", postList);
        Post post3 = Post.createPost(user, "title3", "content3", postList);
        post1.addView();
        post2.addView();
        post2.addView();
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
    }*/
/*    @Test
    void 좋아요_테스트(){
        //given
        List<User> users = userRepository.findAll();
        List<Post> posts = postRepository.findAll();
        LikePostService likePostService = new LikePostService(likePostRepository, userRepository, postRepository);

        //when
        LikePost likePost = likePostService.findOneByUserIdAndPostId(users.get(0).getId(), posts.get(0).getId());

        System.out.println("likePost = " + likePost);
        //then
        System.out.println("BEFORE : ");
        System.out.println(posts.get(0).getLikeCount());
        System.out.println("likePost.getStatus() = " + likePost.getStatus());
        likePost.activateLike();
        System.out.println("AFTER : ");
        System.out.println(posts.get(0).getLikeCount());
        System.out.println("likePost.getStatus() = " + likePost.getStatus());
    }*/

    /*@Test
    public void 메인페이지_날짜순조회_테스트() throws Exception{
        //given
        Pageable pageable = PageRequest.of(0, 10);

        //when
        Page<Post> posts = postRepository.findAllByStatusOrderByCreatedDateDesc(PostStatus.RECRUITING, pageable);

        //then
        System.out.println("POST TEST ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        posts.get().forEach(post -> {
            System.out.println("post.getTitle() = " + post.getTitle());
            System.out.println("post.getContent() = " + post.getContent());
            System.out.println("post.getCreatedDate() = " + post.getCreatedDate());
        });
    }*/

//    @Test
//    public void 메인페이지_인기순조회_테스트() throws Exception{
//        //given
//        Pageable pageable = PageRequest.of(0, 10);
//        //when
//        Page<Post> posts = postRepository.findAllByStatusOrderByViewCountDesc(PostStatus.RECRUITING, pageable);
//        //then
//        System.out.println("POST TEST ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
//        posts.get().forEach(post -> {
//            System.out.println("post.getTitle() = " + post.getTitle());
//            System.out.println("post.getContent() = " + post.getContent());
//            System.out.println("post.getVIewCount() = " + post.getViewCount());
//            System.out.println("post.getCreatedDate() = " + post.getCreatedDate());
//        });
//    }

/*    @Test
    public void 게시글_하나_불러오기_테스트() throws Exception{
        //given
        List<Post> all = postRepository.findAll();
        Long id1 = all.get(0).getId();
        Long id2 = all.get(1).getId();


        //when
        Optional<Post> post1 = postRepository.findById(id1);
        Optional<Post> post2 = postRepository.findById(id2);
        Optional<Post> postNone = postRepository.findById(4L);

        //then
        assertThat(post1.get().getTitle()).isEqualTo("title1");
        assertThat(post2.get().getTitle()).isEqualTo("title2");
        assertThat(postNone).isEqualTo(Optional.empty());
    }*/

/*    @Test
    @Transactional
    public void 게시글_조회수_증가_테스트() throws Exception{
        //given
        List<Post> posts = postRepository.findAll();
        Post post = posts.get(0);

        Long id = post.getId();
        Long viewCount = post.getViewCount();
        post.addView();

        //when
        Post postUpdated = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        //then
        System.out.println("viewCount = " + viewCount);
        System.out.println("post.getViewCount() = " + postUpdated.getViewCount());
        assertThat(viewCount).isLessThan(post.getViewCount());

    }*/

/*    @Test
    @Transactional
    public void 게시글_등록_테스트() throws Exception{
        //given
        User user = userRepository.findAll().get(0);
        List<PostTechStack> postTechStacks = postTechStackRepository.findAll();

        PostSaveRequestDto postSaveRequestDto = new PostSaveRequestDto( user,"title7", "content7", postTechStacks);

        Post post = postSaveRequestDto.createPost();
        //when
        postRepository.save(post);
        List<Post> all = postRepository.findAll();
        //then
        for (Post p : all) {
            System.out.println("p.getId() = " + p.getId());
            System.out.println("p.getTitle() = " + p.getTitle());
            System.out.println("p.getContent() = " + p.getContent());
        }
    }*/

/*    @Test
    @Transactional
    public void 게시글_수정_테스트(){
        //given
        List<Post> all = postRepository.findAll();
        Long key = all.get(0).getId();
        Post post = postRepository.findById(key)
                .orElse(null);
        List<PostTechStack> postTechStacks = post.getPostTechStacks();
        for(PostTechStack postTechStack : postTechStacks){
            System.out.println("postTechStack = " + postTechStack.getTag().getName());
        }

        List<PostTechStack> list = new ArrayList<>();
        Tag a = Tag.createTag("a");
        Tag b = Tag.createTag("b");
        Tag c = Tag.createTag("c");
        Tag d = Tag.createTag("d");
        List<Tag> taglist = new ArrayList<>();
        taglist.add(a);
        taglist.add(b);
        taglist.add(c);
        taglist.add(d);
        tagRepository.save(a);
        tagRepository.save(b);
        tagRepository.save(c);
        tagRepository.save(d);

        list.add(PostTechStack.createPostTechStack(a));
        list.add(PostTechStack.createPostTechStack(b));
        list.add(PostTechStack.createPostTechStack(c));
        list.add(PostTechStack.createPostTechStack(d));

        //when
        String title = "title777";
        String content = "content 777";
        PostStatus done = PostStatus.DONE;
        PostUpdateRequestDto postUpdateRequestDto = new PostUpdateRequestDto(title, content,taglist, list, done);
        postUpdateRequestDto.changePost(post);

        //then
        Post post1 = postRepository.findById(key).orElse(null);

        List<PostTechStack> allPostTechStack = postTechStackRepository.findAllByPostId(key);
        System.out.println("allPostTechStack = " + allPostTechStack.size());

    }*/

//    @Test
    /*void 게시글_수정_테스트_2(){
        PostService ps = new PostService(postRepository, postTechStackRepository);

        List<Post> list = postRepository.findAll();
        Post post = list.get(0);

        Tag a = Tag.createTag("a");
        Tag b = Tag.createTag("b");
        Tag c = Tag.createTag("c");
        Tag d = Tag.createTag("d");
        List<Tag> taglist = new ArrayList<>();
        taglist.add(a);
        taglist.add(b);
        taglist.add(c);
        taglist.add(d);

        PostUpdateRequestDto postUpdateRequestDto = new PostUpdateRequestDto("title", "content",
                taglist,  PostStatus.RECRUITING);

        Long updateId = ps.update(post.getId(), postUpdateRequestDto);

        PostResponseDto pr = ps.findById(updateId);

        System.out.println("pr.getTitle() = " + pr.getTitle());
        System.out.println("pr.getContent() = " + pr.getContent());
        for(PostTechStack pt : pr.getPostTechStacks()){
            System.out.println("pt.getTag().getName() = " + pt.getTag().getName());
        }


    }*/



}