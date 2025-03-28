package com.example.rest.service.Impl;

import com.example.rest.dto.post.PostRequestTo;
import com.example.rest.dto.post.PostResponseTo;
import com.example.rest.dto.post.PostUpdate;
import com.example.rest.entity.Post;
import com.example.rest.mapper.PostMapper;
import com.example.rest.repository.PostRepository;
import com.example.rest.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private PostMapper postMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }
    
    @Override
    public PostResponseTo create(PostRequestTo post) {
        return postMapper.toResponse(postRepository.create(postMapper.toEntity(post)));
    }

    @Override
    public PostResponseTo update(PostUpdate updatedPost) {
        Post post = postRepository.findById(updatedPost.getId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        if (updatedPost.getId() != null) {
            post.setId(updatedPost.getId());
        }
        if (updatedPost.getContent() != null) {
            post.setContent(updatedPost.getContent());
        }
        if (updatedPost.getTopicId() != null) {
            post.setTopicId(updatedPost.getTopicId());
        }

        return postMapper.toResponse(postRepository.update(post));
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);

    }

    @Override
    public List<PostResponseTo> findAll() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<PostResponseTo> findById(Long id) {
        return postRepository.findById(id)
                .map(postMapper::toResponse);
    }
}
