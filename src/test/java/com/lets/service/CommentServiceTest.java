package com.lets.service;

import com.lets.domain.comment.CommentRepository;
import com.lets.domain.user.UserRepository;
import com.lets.domain.userTechStack.UserTechStackRepository;
import com.lets.domain.post.PostRepository;
import com.lets.domain.postTechStack.PostTechStackRepository;
import com.lets.domain.tag.TagRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CommentServiceTest {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostTechStackRepository postTechStackRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    UserTechStackRepository userTechStackRepository;

/*    @BeforeEach
    void 데이터_집어넣기(){
        Tag springTag = Tag.createTag("Spring");
        Tag javaTag = Tag.createTag("Java");
        tagRepository.save(springTag);
        tagRepository.save(javaTag);
        UserTechStack springTechStack = UserTechStack.createMemberTechStack(springTag);
        UserTechStack javaTechStack = UserTechStack.createMemberTechStack(javaTag);
        userTechStackRepository.save(springTechStack);
        userTechStackRepository.save(javaTechStack);
        List<UserTechStack> list = new ArrayList<>();
        list.add(springTechStack);
        list.add(javaTechStack);

        User user = User.createMember("member1", list);
        User user2 = User.createMember("member2", list);
        userRepository.save(user);
        userRepository.save(user2);

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
    void 댓글_불러오기_테스트(){
        //given
        Post post = postRepository.findAll().get(0);
        Post post1 = postRepository.findAll().get(1);
        User user = userRepository.findAll().get(0);
        User user1 = userRepository.findAll().get(1);
        Comment a = Comment.createComment(user, post, "aaaaaaaaaaaaa");
        Comment b = Comment.createComment(user, post, "bbbbbbbbbbbbb");
        Comment c = Comment.createComment(user1, post, "ccccccccccccc");
        Comment d = Comment.createComment(user, post1, "dddddddddddd");
        commentRepository.save(a);
        commentRepository.save(b);
        commentRepository.save(c);
        commentRepository.save(d);

        //when
        List<Comment> comments = commentRepository.findByPostIdOrderByCreatedDateAsc(post.getId());
        
        //then
        for(Comment comment : comments){
            System.out.println("comment.getContent() = " + comment.getContent());
            System.out.println("comment.getUser().getName() = " + comment.getUser().getName());
            System.out.println("comment.getCreatedDate() = " + comment.getCreatedDate());
        }
    }*/

/*    @Test
    void 댓글_삭제하기_테스트(){

        Post post = postRepository.findAll().get(0);
        User user = userRepository.findAll().get(0);
        Comment comment = Comment.createComment(user, post, "COMMENT1");
        commentRepository.save(comment);
        List<Comment> all = commentRepository.findAll();

        System.out.println("all.size() = " + all.size());

        commentRepository.delete(comment);

        System.out.println("all.size() = " + commentRepository.findAll().size());
    }*/


}