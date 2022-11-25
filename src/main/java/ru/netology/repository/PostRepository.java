package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostRepository {
  static AtomicInteger counter = new AtomicInteger(1);
  final static int NEW_POST = 0;
  ConcurrentHashMap<Long, Post> repository;
  public PostRepository(){
    this.repository = new ConcurrentHashMap<>();
  }
  public List<Post> all() {
    return repository.values().stream().toList();
  }

  public Optional<Post> getById(long id) {
    return repository.values().stream()
            .filter(x -> x.getId() == id && !x.isRemoved())
            .findFirst();
  }

  public Post save(Post post) {
    if (post.getId() == NEW_POST){
      post.setId(counter.getAndIncrement());
      repository.put(post.getId(), post);
    } else if (!post.isRemoved()){
      removeById(post.getId());
      repository.put(post.getId(), post);
    } else {
      throw new NotFoundException();
    }
    return post;
  }

  public void removeById(long id) {
    Optional<Post> optional = getById(id);
    optional.orElseThrow(NotFoundException::new).setRemoved(true);
  }
}

